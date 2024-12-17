package br.com.guardiaosistemas.tca.execucao.frames;

import br.com.guardiaosistemas.tca.execucao.frames.helper.ExecucaoTesteFrameHelper;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ExecucaoTesteFrame extends JDialog {

	private static final long serialVersionUID = -7349676322826143572L;
	
	private final JPanel contentPanel = new JPanel();

	private JLabel lblMain;
	
	private ExecucaoTesteFrameHelper helper;
	
	/**
	 * Create the dialog.
	 */
	public ExecucaoTesteFrame(ExecucaoTesteFrameHelper helper) {
		super();
		this.helper = helper;
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					ExecucaoTesteFrame.this.helper.hitKey();
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					ExecucaoTesteFrame.this.helper.exitKey();
				}
			}
		});
		
		setUndecorated(true);
		setResizable(false);
		setModal(true);
		getContentPane().setBackground(Color.BLACK);
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, ds.width, ds.height);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setOpaque(false);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		
		lblMain = new JLabel("Iniciando Treinamento");
		lblMain.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblMain.setForeground(Color.YELLOW);
		lblMain.setHorizontalAlignment(SwingConstants.CENTER);
		lblMain.setIcon(null);
		contentPanel.add(lblMain);
		
		Toolkit tk= getToolkit();
		Cursor transparent = tk.createCustomCursor(tk.getImage(""), new Point(), "trans");
		setCursor(transparent);
		
		helper.start();
	}
	
	public void setTestIcon(ImageIcon icon) {
		lblMain.setIcon(icon);
	}
	
	public void setTestText(String text) {
		lblMain.setText(text);
	}

}
