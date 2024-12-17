package br.com.guardiaosistemas.tca.execucao.frames.helper;

import java.time.Duration;
import java.time.LocalTime;

import br.com.guardiaosistemas.tca.execucao.consts.C;
import br.com.guardiaosistemas.tca.execucao.frames.ExecucaoTesteFrame;
import br.com.guardiaosistemas.tca.execucao.frames.task.ExecTestTask;
import br.com.guardiaosistemas.tca.execucao.model.entity.HitEntity;
import br.com.guardiaosistemas.tca.execucao.model.entity.PatientEntity;
import bundle.Msg;

public class ExecucaoTesteFrameHelper {

	private ExecucaoTesteFrame frame;
	private LocalTime ini;
	private HitEntity lastHit = null;
	private HitEntity atualHit;
	private Thread wind;
	private PatientEntity patient;
	
	public ExecucaoTesteFrameHelper(PatientEntity patient) {
		super();
		this.patient = patient;
		frame = new ExecucaoTesteFrame(this);
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	public void hide() {
		frame.setVisible(false);
	}
	
	public void start() {
		startTraining1();
	}
	
	public void hitKey() {
		LocalTime hitTime = LocalTime.now();
		if (lastHit != atualHit) {
			Duration total = Duration.between(ini, hitTime);
			atualHit.tookShot(total.getNano() / 1000000);
			lastHit = atualHit;
//			System.out.printf(" Hit at: %s / Duration: %s (%s)", hitTime, total, String.valueOf(total.getNano()));
		}
	}
	
	public void star() {
//		System.out.println(" Hit at: " + LocalTime.now());
	}
	
	@SuppressWarnings("deprecation")
	public void exitKey() {
		hide();
//		System.out.println("SAINDA DA EXECUCAO");
		if (wind != null && wind.isAlive()) {
			wind.stop();
//			System.out.println("INTERROMPENDO A THREAD");
		}
	}
	
	public void setInitHit(HitEntity hit) {
		atualHit = hit;
//		frame.setTestIcon(hit.getType().equals(C.T_STAR) ? C.ICON_STAR : C.ICON_BALLON);
		frame.setTestIcon(hit.getType().equals(C.T_STAR) ? C.ICON_STAR_M : C.ICON_BALLON_M);
//		frame.setTestIcon(hit.getType().equals(C.T_STAR) ? C.ICON_STAR_G : C.ICON_BALLON_G);
		ini = LocalTime.now();
//		System.out.printf("%nInitial at: %s / ", ini);
	}
	
	public void setIconNone() {
		frame.setTestIcon(null);
	}
	
	public void setLabel(String text) {
		frame.setTestText(text);
	}
	
	private void startTraining1() {
		// Solicitação 14/10/19 - Treinamento exclusivo para o teste de 1,5 minutos
		if (patient.getExecutionTime() == 1) {
//			System.out.println("INICIANDO TREINAMENTO 1");
			wind = new Thread(new ExecTestTask(this, Msg.get("treinamento.iniciando"), null, DataTestHelper.getTrainingList1_30(), 
					(l) -> {
						long okHits = l.stream().filter(HitEntity::isOk).count();
//						System.out.println(hits);
						if (okHits >= 8) {
							try {
								Thread.sleep(10000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							startTest();
						} else {
							startTraining1();
						}
					}));
		} else {
//			System.out.println("INICIANDO TREINAMENTO 1");
			wind = new Thread(new ExecTestTask(this, Msg.get("treinamento.iniciando"), null, DataTestHelper.getTrainingList1(), 
					(l) -> {
						long okHits = l.stream().filter(HitEntity::isOk).count();
//						System.out.println(hits);
						if (okHits >= 5) {
							startTest();
						} else {
							startTraining2();
						}
					}));
		}
		
		wind.start();
	}
	
	private void startTraining2() {
//		System.out.println("INICIANDO TREINAMENTO 2");

		wind = new Thread(new ExecTestTask(this, Msg.get("treinamento.recomecando"), Msg.get("treinamento.finalizando"), DataTestHelper.getTrainingList2(), 
				(l) -> {
					long okHits = l.stream().filter(HitEntity::isOk).count();
					if (okHits >= 3) {
						startTest();
					} else {
						startTraining2();
						// Mensagem de confirmação: Treinar, Sair ou Iniciar
					}
				}));
		wind.start();
	}
	
	private void startTest() {
//		System.out.println("INICIANDO TESTE");
		wind = new Thread(new ExecTestTask(this, Msg.get("teste.inicializando"), Msg.get("teste.finalizando"), DataTestHelper.getTestList(patient.getExecutionTime()), // DataTestHelper.getTestList1M(),  
				(l) -> {
//					System.out.println("SALVAR O TREINAMENTO COMPLETO");
					new ResultadoFrameHelper(patient, l).show();
					hide();
				}));
		wind.start();
	}
	
}
