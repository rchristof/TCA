package br.com.guardiaosistemas.tca.execucao;

import java.awt.EventQueue;

public class MainTcaExec {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameManager manager = new FrameManager();
					manager.showMainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


}
