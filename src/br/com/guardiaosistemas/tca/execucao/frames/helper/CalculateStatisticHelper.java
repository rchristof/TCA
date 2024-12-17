package br.com.guardiaosistemas.tca.execucao.frames.helper;

import java.math.BigDecimal;
import java.util.List;

import br.com.guardiaosistemas.tca.execucao.consts.C;
import br.com.guardiaosistemas.tca.execucao.model.entity.HitEntity;

public class CalculateStatisticHelper {

	private final ResultDataEntity data = new ResultDataEntity();
	private final List<HitEntity> hitList;

	public CalculateStatisticHelper(List<HitEntity> hitList) {
		super();
		this.hitList = hitList;
		this.calcScores();
	}

	private void calcScores() {
//		total = (long)hitList.size();
		
		data.setTotalTarget(hitList.stream().filter(i->i.getType().equals(C.T_STAR)).count());
		data.setTotalTargetRight(hitList.stream().filter(i->i.getType().equals(C.T_STAR) && i.isOk()).count());
		data.setTotalTargetWrong(data.getTotalTarget() - data.getTotalTargetRight());
//		totalSpeedTarget = hitList.stream().filter(i->i.getType().equals(C.T_STAR)).mapToLong(HitEntity::getSpeed).sum();
		long totalSpeedHit = hitList.stream().filter(i->i.getSpeed()>0).mapToLong(HitEntity::getSpeed).sum();
		long totalSpeedHitRc = hitList.stream().filter(i->i.getType().equals(C.T_STAR)&&i.getSpeed()>0).mapToLong(HitEntity::getSpeed).sum();
		long totalSpeedHitRi = hitList.stream().filter(i->i.getType().equals(C.T_BALLON)&&i.getSpeed()>0).mapToLong(HitEntity::getSpeed).sum();
		long totalSpeedHitCount = hitList.stream().filter(i->i.getSpeed()>0).count();
		long totalSpeedHitCountRc = hitList.stream().filter(i->i.getType().equals(C.T_STAR)&&i.getSpeed()>0).count();
		long totalSpeedHitCountRi = hitList.stream().filter(i->i.getType().equals(C.T_BALLON)&&i.getSpeed()>0).count();
		data.setMaxSpeedTarget(hitList.stream().filter(i->i.getType().equals(C.T_STAR)).mapToLong(HitEntity::getSpeed).reduce(0L, (a, v) -> v>a ? v:a));
		data.setMinSpeedTarget(hitList.stream().filter(i->i.getType().equals(C.T_STAR) && i.getSpeed()>0).mapToLong(HitEntity::getSpeed).reduce(99999L, (a, v) -> v<a ? v:a));
		
		data.setTotalNonTarget(hitList.stream().filter(i->i.getType().equals(C.T_BALLON)).count());
		data.setTotalNonTargetRignt(hitList.stream().filter(i->i.getType().equals(C.T_BALLON) && i.isOk()).count());
		data.setTotalNonTargetWrong(data.getTotalNonTarget() - data.getTotalNonTargetRignt());
		
		data.setRcScore(data.getTotalTargetRight().toString());
		data.setRcPercent(data.getTotalTarget()!=0 ? String.valueOf(BigDecimal.valueOf(data.getTotalTargetRight()*100.0 / data.getTotalTarget()).intValue())+"%" : "n/d");
		data.setRoScore(data.getTotalTargetWrong().toString());
		data.setRoPercent(data.getTotalTarget()!=0 ? String.valueOf(BigDecimal.valueOf(data.getTotalTargetWrong()*100.0 / data.getTotalTarget()).intValue())+"%" : "n/d");
		data.setRiScore(data.getTotalNonTargetWrong().toString());
		data.setRiPercent(data.getTotalNonTarget()!=0 ? String.valueOf(BigDecimal.valueOf(data.getTotalNonTargetWrong()*100.0 / data.getTotalNonTarget()).intValue())+"%" : "n/d");
		
		double media = totalSpeedHitCount!=0 ? totalSpeedHit*1.0 / totalSpeedHitCount : -1;
		if (media >= 0) {
			double var = hitList.stream().filter(i->i.getSpeed()>0).mapToDouble(i->i.getSpeed()-media).reduce(0L, (a, v) -> a + v*v);
			data.setMedScore(String.valueOf(BigDecimal.valueOf(Math.round(media)).intValue()));
			data.setVarScore(String.valueOf(BigDecimal.valueOf(Math.round(Math.sqrt(var / (totalSpeedHitCount-1)))).intValue()));
		} else {
			data.setMedScore("n/d");
			data.setVarScore("n/d");
		}
		
		double mediaRc = totalSpeedHitRc!=0 ? totalSpeedHitRc*1.0 / totalSpeedHitCountRc : -1;
		if (mediaRc >= 0) {
			double var = hitList.stream().filter(i->i.getType().equals(C.T_STAR)&&i.getSpeed()>0).mapToDouble(i->i.getSpeed()-mediaRc).reduce(0L, (a, v) -> a + v*v);
			data.setMedScoreRc(String.valueOf(BigDecimal.valueOf(Math.round(mediaRc)).intValue()));
			data.setVarScoreRc(String.valueOf(BigDecimal.valueOf(Math.round(Math.sqrt(var / (totalSpeedHitCountRc-1)))).intValue()));
		} else {
			data.setMedScoreRc("n/d");
			data.setVarScoreRc("n/d");
		}
		
		double mediaRi = totalSpeedHitCountRi!=0 ? totalSpeedHitRi*1.0 / totalSpeedHitCountRi : -1;
		if (mediaRi >= 0) {
			double var = hitList.stream().filter(i->i.getType().equals(C.T_BALLON)&&i.getSpeed()>0).mapToDouble(i->i.getSpeed()-mediaRi).reduce(0L, (a, v) -> a + v*v);
			data.setMedScoreRi(String.valueOf(BigDecimal.valueOf(Math.round(mediaRi)).intValue()));
			data.setVarScoreRi(String.valueOf(BigDecimal.valueOf(Math.round(Math.sqrt(var / (totalSpeedHitCountRi-1)))).intValue()));
		} else {
			data.setMedScoreRi("n/d");
			data.setVarScoreRi("n/d");
		}
		
	}

	public ResultDataEntity getData() {
		return data;
	}
	
	public List<HitEntity> getHitList() {
		return hitList;
	}

	@Override
	public String toString() {
		return "CalculateStatisticHelper ["
				+ "totalTarget=" + data.getTotalTarget() + ", totalTargetRight=" + data.getTotalTargetRight() + ", totalTargetWrong=" + data.getTotalTargetWrong() 
				+ ", totalNonTarget=" + data.getTotalNonTarget() + ", totalNonTargetRignt=" + data.getTotalNonTargetRignt() + ", totalNonTargetWrong=" + data.getTotalNonTargetWrong()
				+ ", totalSpeedTarget=" + data.getTotalSpeedTarget() + ", maxSpeedTarget=" + data.getMaxSpeedTarget() + ", minSpeedTarget=" + data.getMinSpeedTarget() 
				+ ", rcScore=" + data.getRcScore() + ", rcPercent=" + data.getRcPercent() 
				+ ", roScore=" + data.getRoScore() + ", roPercent=" + data.getRoPercent()
//				+ ", roPercentile=" + roPercentile + ", roDiagnosis=" + roDiagnosis 
				+ ", riScore=" + data.getRiScore() + ", riPercent=" + data.getRiPercent() 
//				+ ", riPercentile=" + riPercentile + ", riDiagnosis=" + riDiagnosis
				+ ", medScore=" + data.getMedScore() 
//				+ ", medPercentile=" + medPercentile + ", medDiagnosis=" + medDiagnosis 
				+ ", varScore=" + data.getVarScore() 
//				+ ", varPercentile=" + varPercentile + ", varDiagnosis=" + varDiagnosis 
				+ "]";
	}
	
	public static void main(String[] args) {
		
		CalculateStatisticHelper calc = new CalculateStatisticHelper(DataTestHelper.getExample());
		calc.calcScores();
//		System.out.println(calc);
	}
	
}
