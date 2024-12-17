package br.com.guardiaosistemas.tca.execucao;

import java.util.List;

import br.com.guardiaosistemas.tca.execucao.frames.MainFrame;
import br.com.guardiaosistemas.tca.execucao.frames.PacienteFrame;
import br.com.guardiaosistemas.tca.execucao.frames.SelecaoFrame;
import br.com.guardiaosistemas.tca.execucao.frames.SobreFrame;
import br.com.guardiaosistemas.tca.execucao.frames.helper.CarregaResultadoFrameHelper;
import br.com.guardiaosistemas.tca.execucao.frames.helper.ExecucaoTesteFrameHelper;
import br.com.guardiaosistemas.tca.execucao.frames.helper.ResultadoFrameHelper;
import br.com.guardiaosistemas.tca.execucao.model.entity.HitEntity;
import br.com.guardiaosistemas.tca.execucao.model.entity.PatientEntity;

public class FrameManager {
	
	private MainFrame mainFrame;
	private ExecucaoTesteFrameHelper execucaoTesteFrameHelper;
	private PacienteFrame pacienteFrame;
	private SelecaoFrame selecaoFrame;
	private SobreFrame sobreFrame;
	private ResultadoFrameHelper resultadoFrameHelper;
	private CarregaResultadoFrameHelper carregaResultado;
	
	public FrameManager() {
		super();
		mainFrame = new MainFrame(this);
	}
	
	public void showMainFrame() {
		mainFrame.setVisible(true);
//		System.out.println(mainFrame.getParent());
	}
	
	public synchronized void showPacienteFrame(PatientEntity patient) {
//		if (pacienteFrame == null) {
			pacienteFrame = new PacienteFrame((p) -> showSelecaoFrame(p));
//		}
//		System.out.println(patient);
		if (patient != null) {
			pacienteFrame.setPatient(patient);
		}
		
		pacienteFrame.setVisible(true);
//		System.out.println(pacienteFrame.getParent().getName());
	}
	
	public synchronized void showSelecaoFrame(PatientEntity patient) {
//		if (selecaoFrame == null) {
			selecaoFrame = new SelecaoFrame((p) -> showExecucaoTesteFrame(p), null);
//		}
		selecaoFrame.setPatient(patient);
		selecaoFrame.setVisible(true);
//		System.out.println(selecaoFrame.getParent().getName());
	}
	
	public synchronized void showExecucaoTesteFrame(PatientEntity patient) {
//		if (execucaoTesteFrame == null) {
			execucaoTesteFrameHelper = new ExecucaoTesteFrameHelper(patient);
//		}
		
		execucaoTesteFrameHelper.show();
	}
	
	public synchronized void showResultadoFrame(PatientEntity patient, List<HitEntity> hitList) {
		resultadoFrameHelper = new ResultadoFrameHelper(patient, hitList);
		
		resultadoFrameHelper.show();
	}
	
	public synchronized void showLoadReaultadoFrame() {
//		if (carregaResultado == null) {
			carregaResultado = new CarregaResultadoFrameHelper();
//		}
		carregaResultado.load();
	}
	
	public synchronized void showSobreFrame() {
//		if (sobreFrame == null) {
			sobreFrame = new SobreFrame();
//		}
		
		sobreFrame.setVisible(true);
	}
	
	
	
}
