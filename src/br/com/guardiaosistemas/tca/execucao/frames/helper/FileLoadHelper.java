package br.com.guardiaosistemas.tca.execucao.frames.helper;

import bundle.Msg;
import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileLoadHelper {
	
	private JFileChooser dialog;
	private Component parent;
	private static File lastOpen = null;

	public FileLoadHelper(Component parent) {
		super();
		this.parent = parent;
		
		this.dialog = new JFileChooser();
		this.dialog.setMultiSelectionEnabled(false);
		this.dialog.setLocale(Msg.getCurrentLocale());
		this.dialog.setFileFilter(new FileNameExtensionFilter(Msg.get("tipo.arquivo"), "tcv"));
	}
	
	public File show() {
		if (lastOpen != null) {
			this.dialog.setCurrentDirectory(lastOpen.getParentFile());
		}
		
		int selection = this.dialog.showOpenDialog(parent);
		if (selection == JFileChooser.APPROVE_OPTION) {
			lastOpen = dialog.getSelectedFile();
			return lastOpen;
		}
		
		return null;
	}
	
}
