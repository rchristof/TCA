package br.com.guardiaosistemas.tca.execucao.frames.helper;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.guardiaosistemas.tca.execucao.consts.C;
import br.com.guardiaosistemas.tca.execucao.frames.ExecucaoTesteFrame;
import br.com.guardiaosistemas.tca.execucao.frames.task.ExecTestTask;
import br.com.guardiaosistemas.tca.execucao.model.entity.HitEntity;
import br.com.guardiaosistemas.tca.execucao.model.entity.PatientEntity;
import br.com.guardiaosistemas.tca.execucao.model.entity.TestEntity;
import bundle.Msg;

public class ExecucaoTesteFrameHelper {

    private ExecucaoTesteFrame frame;
    private TestEntity testEntity;
    private LocalTime ini;
    private HitEntity lastHit = null;
    private HitEntity atualHit;
    private Thread wind;
    private PatientEntity patient;

    public ExecucaoTesteFrameHelper(PatientEntity patient, TestEntity testEntity) {
        this.patient = patient;
        this.testEntity = testEntity;
        this.frame = new ExecucaoTesteFrame(this);
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
            atualHit.setHit(true);
            atualHit.setSpeed(total.toMillisPart());
            lastHit = atualHit;
        }
    }

    @SuppressWarnings("deprecation")
    public void exitKey() {
        hide();
        if (wind != null && wind.isAlive()) {
            wind.stop();
        }
    }

    public void setInitHit(HitEntity hit) {
        atualHit = hit;
        frame.setTestIcon(hit.getType().equals(C.T_STAR) ? C.ICON_STAR_M : C.ICON_BALLON_M);
        ini = LocalTime.now();
    }

    public void setIconNone() {
        frame.setTestIcon(null);
    }

    public void setLabel(String text) {
        frame.setTestText(text);
    }
    
    private List<HitEntity> generateTrainingStimuli() {
        return DataTestHelper.generateStimuliList(3, 80, 2); // 3 estímulos, frequência 80%, SOA de 2s
    }

    private void startTraining1() {
    	System.out.println("Iniciando Teste com os seguintes parâmetros:");
        System.out.println("Total de Alvos: " + testEntity.getTotalItems());
        System.out.println("Frequência: " + testEntity.getFrequency() + "%");
        System.out.println("SOA (Tempo entre estímulos): " + testEntity.getSoa() + " segundos");
        
        List<HitEntity> trainingList = generateTrainingStimuli();

        wind = new Thread(new ExecTestTask(
            this,
            Msg.get("treinamento.iniciando"),
            Msg.get("treinamento.finalizando"),
            trainingList,
            (list) -> {
                setLabel("Treinamento concluído. O teste real está iniciando...");
                waitAMoment(2000);
                startTest();
            }
        ));
        wind.start();
    }

    private void waitAMoment(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void startTest() {
        wind = new Thread(new ExecTestTask(
            this,
            Msg.get("teste.inicializando"),
            Msg.get("teste.finalizando"),
            testEntity.getHitEntities(),
            (list) -> {
                new ResultadoFrameHelper(patient, list).show();
                hide();
            }
        ));
        wind.start();
    }
}
