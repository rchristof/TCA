package br.com.guardiaosistemas.tca.execucao.frames.helper;

import br.com.guardiaosistemas.tca.execucao.model.entity.HitEntity;
import br.com.guardiaosistemas.tca.execucao.model.entity.PatientEntity;
import bundle.Msg;
import java.text.SimpleDateFormat;

public class SaveDataHelper {

	private PatientEntity patient;
	private StringBuffer strFile;
	private static final String TAB = "\t";
	private SimpleDateFormat df = new SimpleDateFormat(Msg.get("format.date"));
	private final CalculateStatisticHelper calc;
	private final int[][] matrixNumbers = new int[60][12];
	
	public SaveDataHelper(PatientEntity patient, CalculateStatisticHelper calc) {
		super();
		this.patient = patient;
		this.strFile = new StringBuffer();
		this.calc = calc;
		createMatrix();
	}
	
	private void appendText(String ...texts) {
		for(String text: texts) {
			strFile.append(text).append(TAB);
		}
		appendTab(11 - texts.length);
	}
	
	private void appendNumberOnly(int ...numbers) {
		appendText("");
		appendNumber(numbers);
		appendLine();
	}
	
	private void appendNumber(int ...numbers) {
		for(int num: numbers) {
			strFile.append(num).append(TAB);
		}
		
		for(int i = 0; i < 12-numbers.length; i++) {
			strFile.append(0).append(TAB);
		}
	}

	private void appendTab(int count) {
		for(int i = 0; i < count; i++) {
			strFile.append(TAB);
		}
	}

	private void appendLine() {
		strFile.append("\n");
	}

	
	public StringBuffer save() {
		
		// Line 0
		appendLine();
		
		// Line 1
		appendText("", "Usuário: Pesquisa", "", "Teste Visual");
		appendNumber(matrixNumbers[0]);
		appendLine();
		
		// Line 2
		appendText(Msg.getP("data"), df.format(patient.getTestDate()));
		appendNumber(matrixNumbers[1]);
		appendLine();
		
		// Line 3
		appendText(Msg.getP("paciente.nome"), patient.getName(), "", Msg.get("resultados.globais"));
		appendNumber(matrixNumbers[2]);
		appendLine();
		
		// Line 4
		String genero = patient.getGender().equals("M") ? "paciente.genero.masculino" : "paciente.genero.feminino";
		appendText(Msg.getP("paciente.genero"), Msg.get(genero), "", Msg.get("variaveis"), Msg.get("escores"), Msg.get("percentil"), Msg.get("diagnostico"));
		appendNumber(matrixNumbers[3]);
		appendLine();
		
		// Line 5
		appendText(Msg.getP("paciente.idade"), patient.getAge().toString(), "", Msg.get("rc.correta"), calc.getData().getRcScore());
		appendNumber(matrixNumbers[4]);
		appendLine();
		
		// Line 6
		appendText("ID.:", "", "", Msg.get("rc.percent"), calc.getData().getRcPercent());
		appendNumber(matrixNumbers[5]);
		appendLine();
		
		// Line 7
		appendText(Msg.getP("tempo.atencao"), "1", "", Msg.get("ro.correta"), calc.getData().getRoScore(), calc.getData().getRoPercentile(), calc.getData().getRoDiagnosis());
		appendNumber(matrixNumbers[6]);
		appendLine();
		
		// Line 8
		String hand = patient.getHand().equals("R") ? "paciente.mao.direita" : "paciente.mao.esquerda";
		appendText(Msg.getP("paciente.mao"), Msg.get(hand), "", Msg.get("ro.percent"), calc.getData().getRoPercent());
		appendNumber(matrixNumbers[7]);
		appendLine();
		
		// Line 9
		appendText("", "", "", Msg.get("ri.correta"), calc.getData().getRiScore(), calc.getData().getRiPercentile(), calc.getData().getRiDiagnosis());
		appendNumber(matrixNumbers[8]);
		appendLine();
		
		// Line 10
		appendText("", "", "", Msg.get("ri.percent"), calc.getData().getRiPercent());
		appendNumber(matrixNumbers[9]);
		appendLine();
		
		// Line 11
		appendText("", "", "", Msg.get("med.tr"), calc.getData().getMedScore(), calc.getData().getMedPercentile(), calc.getData().getMedDiagnosis());
		appendNumber(matrixNumbers[10]);
		appendLine();
		
		// Line 12
		appendText("", "", "", Msg.get("var.tr"), calc.getData().getVarScore(), calc.getData().getVarPercentile(), calc.getData().getVarDiagnosis());
		appendNumber(matrixNumbers[11]);
		appendLine();
		
		// Line 11
		appendText("", "", "", Msg.get("med.rc.tr"), calc.getData().getMedScoreRc());
		appendNumber(matrixNumbers[12]);
		appendLine();
		
		// Line 13
		appendText("", "", "", Msg.get("var.rc.tr"), calc.getData().getVarScoreRc());
		appendNumber(matrixNumbers[13]);
		appendLine();
		
		// Line 14
		appendText("", "", "", Msg.get("med.ri.tr"), calc.getData().getMedScoreRi());
		appendNumber(matrixNumbers[14]);
		appendLine();
		
		// Line 15
		appendText("", "", "", Msg.get("var.ri.tr"), calc.getData().getVarScoreRi());
		appendNumber(matrixNumbers[15]);
		appendLine();
		
		// Line 16 em diante
		for (int i=16; i<60; i++) {
			appendNumberOnly(matrixNumbers[i]);
		}
		
//		System.out.println(strFile);
		
		return strFile;
	}
	
	private void createMatrix() {
		for (int i=0; i<60; i++) {
			for (int j=0; j<12; j++) {
				matrixNumbers[i][j] = 0;		
			}
		}
		
		int i = 0;
		int colCode=0;
		int colSpeed=1;
		for (HitEntity hit : calc.getHitList()) {
			matrixNumbers[i][colCode] = hit.getCode();
			matrixNumbers[i++][colSpeed] = hit.getSpeed();
			if (i==60) {
				i=0;
				colCode += 2;
				colSpeed += 2;
			}
		}
		
	}

	public CalculateStatisticHelper getCalc() {
		return calc;
	}
	
//	public static void main(String[] args) {
//		PatientEntity patient = new PatientEntity();
//		patient.setName("Pablo Lima");
//		patient.setAge(38);
//		patient.setGender("M");
//		patient.setHand("R");
//		patient.setTestDate(new Date());
//		SaveDataHelper s = new SaveDataHelper(patient, DataTestHelper.getExample());
//		s.save();
////		System.out.println("MATRIX");
////		for (int i=0; i<60; i++) {
////			System.out.println();		
////			for (int j=0; j<12; j++) {
////				System.out.print(s.matrixNumbers[i][j] + "\t");		
////			}
////		}
//
//	}
	
	
}
