package br.com.guardiaosistemas.tca.execucao.frames.helper;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JOptionPane;

import br.com.guardiaosistemas.tca.execucao.frames.ResultadoFrame;
import bundle.Msg;

public class CarregaResultadoFrameHelper {
	
	private ResultadoFrame frame;
	
	public CarregaResultadoFrameHelper() {
		super();
		this.frame = new ResultadoFrame();
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
				super.windowClosing(e);
			}
		});
		this.frame.btnSalvar.setVisible(false);
		this.frame.btnAbrir.setVisible(false);
		this.frame.pnlButtons.setVisible(false);
		this.frame.btnAbrir.addActionListener(e -> load());
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	public void hide() {
		frame.setVisible(false);
	}
	
	public void load() {
		try {
			File f = new FileLoadHelper(frame).show();
			if (f != null) {
				LoadDataHelper l = new LoadDataHelper(f);
				frame.preencherDadosPaciente(l.getPatient());
				frame.preencherDadosEstatisticos(l.getResultado());
				frame.invalidate();
				show();
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frame, Msg.get("msg.falha.carregar.arquivo"), 
					Msg.get("erro"), JOptionPane.ERROR_MESSAGE);
		}
	}
}
