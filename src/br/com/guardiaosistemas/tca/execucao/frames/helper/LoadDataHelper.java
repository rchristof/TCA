package br.com.guardiaosistemas.tca.execucao.frames.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.guardiaosistemas.tca.execucao.model.entity.PatientEntity;

public class LoadDataHelper {
	
	private final File file;
	private final List<String> buffer = new ArrayList<>();
	private final PatientEntity patient = new PatientEntity();
	private final ResultDataEntity resultado = new ResultDataEntity();
	
	public LoadDataHelper(File file) throws Exception {
		super();
		this.file = file;
		this.readFile();
	}
	
	private void readFile() throws Exception {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for (int i=0; i<17; i++) {
				buffer.add(br.readLine());
			}
			loadPatient();
			loadResultado();
		}
	}
	
	private void loadPatient() {
		String[] linha = buffer.get(2).split("\t");
		String strDate = linha[1];
		int year = Integer.parseInt(strDate.substring(6,10));
		int day;
		int month;
		if ("Date:".equals(linha[0])) {
			month = Integer.parseInt(strDate.substring(0,2));
			day = Integer.parseInt(strDate.substring(3,5));
		} else {
			day = Integer.parseInt(strDate.substring(0,2));
			month = Integer.parseInt(strDate.substring(3,5));
		}
		Calendar c = Calendar.getInstance();
		c.set(year, month-1, day);
		Date date = c.getTime();
		String strName = buffer.get(3).split("\t")[1];
		String strGender = buffer.get(4).split("\t")[1];
		String strAge = buffer.get(5).split("\t")[1];
		String strHand = buffer.get(8).split("\t")[1];
		strHand = strHand.substring(0,1);
		
		patient.setTestDate(date);
		patient.setName(strName);
		patient.setGender(strGender.substring(0,1));
		patient.setAge(Integer.parseInt(strAge));
		patient.setHand("R".equals(strHand) || "D".equals(strHand) ? "R" : "L");
	}
	
	private void loadResultado() {
		resultado.setRcScore(buffer.get(5).split("\t")[4]);
		resultado.setRcPercent(buffer.get(6).split("\t")[4]);
		resultado.setRoScore(buffer.get(7).split("\t")[4]);
		resultado.setRoPercent(buffer.get(8).split("\t")[4]);
		resultado.setRiScore(buffer.get(9).split("\t")[4]);
		resultado.setRiPercent(buffer.get(10).split("\t")[4]);
		resultado.setMedScore(buffer.get(11).split("\t")[4]);
		resultado.setVarScore(buffer.get(12).split("\t")[4]);
		resultado.setMedScoreRc(buffer.get(13).split("\t")[4]);
		resultado.setVarScoreRc(buffer.get(14).split("\t")[4]);
		resultado.setMedScoreRi(buffer.get(15).split("\t")[4]);
		resultado.setVarScoreRi(buffer.get(16).split("\t")[4]);
	}
	
	public PatientEntity getPatient() {
		return patient;
	}

	public ResultDataEntity getResultado() {
		return resultado;
	}
	
//	public static void main(String[] args) {
//		LoadDataHelper l = new LoadDataHelper(new File("C:\\Users\\Pablo\\Desktop\\chama.tcv"));
//		l.loadPatient();
//		l.loadResultado();
//	}
	
}
