package br.com.guardiaosistemas.tca.execucao.frames.helper;

import br.com.guardiaosistemas.tca.execucao.frames.ResultadoFrame;
import br.com.guardiaosistemas.tca.execucao.model.entity.HitEntity;
import br.com.guardiaosistemas.tca.execucao.model.entity.PatientEntity;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class ResultadoFrameHelper {

	private ResultadoFrame frame;
	PatientEntity patient;
	List<HitEntity> hitList;
	private SaveDataHelper saveData;
	private boolean saved = false;

	public ResultadoFrameHelper(PatientEntity patient, List<HitEntity> hitList) {
		super();
		this.frame = new ResultadoFrame();
		this.saveData = new SaveDataHelper(patient, new CalculateStatisticHelper(hitList));
		this.patient = patient;
		
		this.frame.btnSalvar.addActionListener((e) -> save());
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				if (!saved) {
					if (frame.verifySave()) {
						save();
					}
				} else {
					frame.dispose();
				}
			}
		});
	}

	public void show() {
		frame.preencherDadosPaciente(patient);
		frame.preencherDadosEstatisticos(saveData.getCalc().getData());
		frame.setVisible(true);
	}
	
	public void hide() {
		frame.setVisible(false);
	}
	
	private void save() {
		saved = true;
		StringBuffer buffer = saveData.save();
		FileSaveHelper fs = new FileSaveHelper(frame, buffer);
		fs.show();
	}
		
}
