package br.com.guardiaosistemas.tca.execucao.frames.task;

import java.util.List;

import br.com.guardiaosistemas.tca.execucao.delegate.ReturnListDelegate;
import br.com.guardiaosistemas.tca.execucao.frames.helper.ExecucaoTesteFrameHelper;
import br.com.guardiaosistemas.tca.execucao.model.entity.HitEntity;

public class ExecTestTask implements Runnable {

	private final ExecucaoTesteFrameHelper helper;
	private final List<HitEntity> testList;
	private final String msgStart;
	private String msgFinish;
	private final ReturnListDelegate finish;
	
	public ExecTestTask(ExecucaoTesteFrameHelper helper, String msgStart, String msgFinish, 
			List<HitEntity> testList, ReturnListDelegate finish) {
		super();
		this.helper = helper;
		this.msgStart = msgStart;
		this.msgFinish = msgFinish;
		this.testList = testList;
		this.finish = finish;
	}
	

	@Override
	public void run() {
		helper.setLabel(msgStart);
		waitAMoment(2000);
		helper.setLabel("");
		waitAMoment(500);
		executarTest();
		
		if (msgFinish != null && !"".equals(msgFinish)) {
			helper.setLabel(msgFinish);
			waitAMoment(2000);
			helper.setLabel("");
		}
//		System.out.println();
		finish.exec(testList);
	}
	

	private void executarTest() {
		for(HitEntity hit : testList) {
			helper.setInitHit(hit);
			waitAMoment(400);
			helper.setIconNone();
			waitAMoment(hit.getTime()*1000 - 400);
		}
	}
	
	private void waitAMoment(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
}
