package br.com.guardiaosistemas.tca.execucao.frames;

import br.com.guardiaosistemas.tca.execucao.consts.C;
import bundle.Msg;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SobreFrame extends JDialog {

	private static final long serialVersionUID = -487072174111034000L;

	/**
	 * Create the dialog.
	 */
	public SobreFrame() {
		setTitle(Msg.get("tca"));
		setIconImage(Toolkit.getDefaultToolkit().getImage(SobreFrame.class.getResource("/assets/ic_brain-24.png")));
		getContentPane().setBackground(new Color(85, 170, 170));
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 535, 417);
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dw = getSize();
		setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
		getContentPane().setLayout(null);

		JLabel lblDesenvolvidoPor = new JLabel(Msg.get("criado.por") + ": Sérgio L Shimidt e Alex C. Manhães");
		lblDesenvolvidoPor.setBounds(10, 219, 456, 46);
		getContentPane().add(lblDesenvolvidoPor);
		lblDesenvolvidoPor.setAlignmentX(Component.RIGHT_ALIGNMENT);
		lblDesenvolvidoPor.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDesenvolvidoPor.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDesenvolvidoPor.setForeground(Color.WHITE);

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(10, 12, 499, 208);
		getContentPane().add(panel);

		JLabel lblmain = new JLabel("");
		lblmain.setIcon(new ImageIcon(SobreFrame.class.getResource("/assets/ic_brain-128.png")));
		panel.add(lblmain);
		{
			JLabel lblTca = new JLabel(Msg.get("sigla"));
			panel.add(lblTca);
			lblTca.setForeground(new Color(255, 255, 255));
			lblTca.setFont(new Font("Times New Roman", Font.BOLD, 60));
		}
		{
			JLabel lblTesteComputacionalDe = new JLabel(Msg.get("tca"));
			panel.add(lblTesteComputacionalDe);
			lblTesteComputacionalDe.setHorizontalAlignment(SwingConstants.CENTER);
			lblTesteComputacionalDe.setForeground(new Color(255, 255, 255));
			lblTesteComputacionalDe.setBackground(new Color(64, 224, 208));
			lblTesteComputacionalDe.setFont(new Font("Times New Roman", Font.BOLD, 26));
		}

		JLabel lblVersao = new JLabel(Msg.getS("version") + C.version);
		panel.add(lblVersao);
		lblVersao.setIcon(null);
		lblVersao.setForeground(Color.WHITE);
		lblVersao.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblVersao.setBackground(new Color(64, 224, 208));

		JLabel lblLbljava = new JLabel("");
		lblLbljava.setBounds(10, 270, 64, 66);
		getContentPane().add(lblLbljava);
		lblLbljava.setBorder(null);
		lblLbljava.setIcon(new ImageIcon(SobreFrame.class.getResource("/assets/ic_java_cup-64.png")));

		JLabel lblDesenvolvidoEmJava = new JLabel(Msg.get("dev"));
		lblDesenvolvidoEmJava.setBounds(84, 281, 382, 27);
		lblDesenvolvidoEmJava.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDesenvolvidoEmJava.setForeground(Color.WHITE);
		getContentPane().add(lblDesenvolvidoEmJava);

		JLabel lblPabloguardioDe = new JLabel("");
		lblPabloguardioDe.setBounds(84, 309, 382, 27);
		lblPabloguardioDe.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPabloguardioDe.setForeground(Color.WHITE);
		getContentPane().add(lblPabloguardioDe);
	}
}
