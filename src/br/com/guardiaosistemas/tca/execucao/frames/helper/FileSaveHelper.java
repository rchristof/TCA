package br.com.guardiaosistemas.tca.execucao.frames.helper;

import bundle.Msg;
import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileSaveHelper {

	JFileChooser dialog;
	private Component parent;
	private StringBuffer buffer;

	public FileSaveHelper(Component parent, StringBuffer buffer) {
		super();
		this.parent = parent;
		this.buffer = buffer;
		
		this.dialog = new JFileChooser();
		this.dialog.setMultiSelectionEnabled(false);
		this.dialog.setFileFilter(new FileNameExtensionFilter(Msg.get("tipo.arquivo"), "tcv"));
	}
	
	public void show() {
		int selection = this.dialog.showSaveDialog(parent);
		if (selection == JFileChooser.APPROVE_OPTION) {
			File f = dialog.getSelectedFile();
			if (!save(f)) {
				show();
			}
		}
	}
	
	private boolean save(File f) {
		if (!f.getName().endsWith(".tcv")) {
			f = new File(f.getAbsoluteFile() + ".tcv");
		}
		try (FileWriter fw = new FileWriter(f);) {
			
			fw.write(buffer.toString());
			
			return true;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(parent, Msg.get("msg.falha.salvar.arquivo"), "Erro", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
}
