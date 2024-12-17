package br.com.guardiaosistemas.tca.execucao.frames.task;

import br.com.guardiaosistemas.tca.execucao.delegate.ReturnListDelegate;
import br.com.guardiaosistemas.tca.execucao.frames.helper.ExecucaoTesteFrameHelper;
import br.com.guardiaosistemas.tca.execucao.model.entity.HitEntity;
import java.util.List;

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
		for (HitEntity hit : testList) {
			helper.setInitHit(hit); // Exibe o estímulo atual (alvo ou não-alvo)
	
			waitAMoment(250); // Tempo de exibição do estímulo: 0.25s
			helper.setIconNone(); // Remove o estímulo da tela
	
			waitAMoment((hit.getTime() * 1000) - 250); // Aguarda o restante do SOA

			System.out.println("Código do estímulo: " + hit.getCode()); // logs do acerto de cada alvo
		}
	}
	
	private void waitAMoment(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}
}
