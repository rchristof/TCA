package br.com.guardiaosistemas.tca.execucao.frames;

import br.com.guardiaosistemas.tca.execucao.frames.helper.ResultDataEntity;
import br.com.guardiaosistemas.tca.execucao.model.entity.PatientEntity;
import bundle.Msg;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;

public class ResultadoFrame extends JDialog {

	private static final long serialVersionUID = -487072174111034000L;
	
	private JLabel txtNome;
	private JLabel txtData;
	private JLabel txtGenero;
	private JLabel txtIdade;
	private JLabel txtMao;
	private JLabel txtRcScore;
	private JLabel txtRcPerc;
	private JLabel txtRoScore;
	private JLabel txtRoPerc;
	private JLabel txtRiScore;
	private JLabel txtRiPerc;
	private JLabel txtMedia;
	private JLabel txtVariabilidade;
	private JLabel txtMediaRc;
	private JLabel txtVariabilidadeRc;
	private JLabel txtMediaRi;
	private JLabel txtVariabilidadeRi;
	
	public JButton btnSalvar;
	public JButton btnAbrir;

	public JPanel pnlButtons;

	/**
	 * Create the dialog.
	 */
	public ResultadoFrame() {
		setTitle(Msg.get("tca"));
		setIconImage(Toolkit.getDefaultToolkit().getImage(ResultadoFrame.class.getResource("/assets/ic_brain-24.png")));
		getContentPane().setBackground(new Color(240, 240, 240));
		setModal(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 629, 535);
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dw = getSize();
		setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pnlPrincipal = new JPanel();
		pnlPrincipal.setOpaque(false);
		getContentPane().add(pnlPrincipal);
		pnlPrincipal.setLayout(new BorderLayout(0, 0));
		
		JLabel lblImagemLateral = new JLabel("");
		lblImagemLateral.setIcon(new ImageIcon(ResultadoFrame.class.getResource("/assets/img_watch_dial_240x400.jpg")));
		pnlPrincipal.add(lblImagemLateral, BorderLayout.WEST);
		
		JPanel pnlCentral = new JPanel();
		pnlCentral.setBorder(new CompoundBorder());
		pnlPrincipal.add(pnlCentral, BorderLayout.CENTER);
		pnlCentral.setLayout(null);
		
		txtNome = new JLabel("Nome");
		txtNome.setFont(new Font("Arial", Font.BOLD, 15));
		txtNome.setBounds(12, 12, 353, 16);
		pnlCentral.add(txtNome);
		
		JLabel lblData = new JLabel(Msg.getP("data"));
		lblData.setBounds(201, 35, 55, 16);
		pnlCentral.add(lblData);
		
		txtData = new JLabel("");
		txtData.setBounds(268, 35, 97, 16);
		pnlCentral.add(txtData);
		
		JLabel lblGenero = new JLabel(Msg.getP("paciente.genero"));
		lblGenero.setBounds(12, 35, 55, 16);
		pnlCentral.add(lblGenero);
		
		txtGenero = new JLabel("");
		txtGenero.setBounds(79, 35, 97, 16);
		pnlCentral.add(txtGenero);
		
		JLabel lblIdade = new JLabel(Msg.getP("paciente.idade"));
		lblIdade.setBounds(12, 54, 55, 16);
		pnlCentral.add(lblIdade);
		
		txtIdade = new JLabel("");
		txtIdade.setBounds(79, 54, 97, 16);
		pnlCentral.add(txtIdade);
		
		JLabel lblMao = new JLabel(Msg.getP("paciente.mao"));
		lblMao.setBounds(201, 54, 55, 16);
		pnlCentral.add(lblMao);
		
		txtMao = new JLabel("");
		txtMao.setBounds(268, 54, 97, 16);
		pnlCentral.add(txtMao);
		
		JLabel lblResultadosGlobais = new JLabel(Msg.getP("resultados.globais"));
		lblResultadosGlobais.setFont(new Font("Dialog", Font.ITALIC, 15));
		lblResultadosGlobais.setBounds(12, 91, 353, 16);
		pnlCentral.add(lblResultadosGlobais);
		
		JPanel pnlGlobalResults = new JPanel();
		pnlGlobalResults.setBounds(22, 108, 343, 272);
		pnlCentral.add(pnlGlobalResults);
		GridBagLayout gbl_pnlGlobalResults = new GridBagLayout();
		gbl_pnlGlobalResults.columnWidths = new int[] {0, 0, 0};
		gbl_pnlGlobalResults.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_pnlGlobalResults.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_pnlGlobalResults.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnlGlobalResults.setLayout(gbl_pnlGlobalResults);
		
		JLabel lblVairaveis = new JLabel(Msg.get("variaveis"));
		lblVairaveis.setHorizontalTextPosition(SwingConstants.LEFT);
		lblVairaveis.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblVairaveis = new GridBagConstraints();
		gbc_lblVairaveis.anchor = GridBagConstraints.WEST;
		gbc_lblVairaveis.insets = new Insets(0, 0, 5, 5);
		gbc_lblVairaveis.gridx = 0;
		gbc_lblVairaveis.gridy = 0;
		pnlGlobalResults.add(lblVairaveis, gbc_lblVairaveis);
		lblVairaveis.setBackground(Color.GRAY);
		lblVairaveis.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblScores = new JLabel(Msg.get("escores"));
		GridBagConstraints gbc_lblScores = new GridBagConstraints();
		gbc_lblScores.anchor = GridBagConstraints.WEST;
		gbc_lblScores.insets = new Insets(0, 10, 5, 0);
		gbc_lblScores.gridx = 1;
		gbc_lblScores.gridy = 0;
		pnlGlobalResults.add(lblScores, gbc_lblScores);
		lblScores.setFont(new Font("Arial", Font.BOLD, 13));
		
		JLabel lblRcScore = new JLabel(Msg.get("rc.correta"));
		GridBagConstraints gbc_lblRcScore = new GridBagConstraints();
		gbc_lblRcScore.anchor = GridBagConstraints.WEST;
		gbc_lblRcScore.insets = new Insets(0, 0, 5, 5);
		gbc_lblRcScore.gridx = 0;
		gbc_lblRcScore.gridy = 1;
		pnlGlobalResults.add(lblRcScore, gbc_lblRcScore);
		
		txtRcScore = new JLabel("");
		GridBagConstraints gbc_txtRcScore = new GridBagConstraints();
		gbc_txtRcScore.anchor = GridBagConstraints.WEST;
		gbc_txtRcScore.insets = new Insets(0, 10, 5, 0);
		gbc_txtRcScore.gridx = 1;
		gbc_txtRcScore.gridy = 1;
		pnlGlobalResults.add(txtRcScore, gbc_txtRcScore);
		
		JLabel lblRcPerc = new JLabel(Msg.get("rc.percent"));
		GridBagConstraints gbc_lblRcPerc = new GridBagConstraints();
		gbc_lblRcPerc.anchor = GridBagConstraints.WEST;
		gbc_lblRcPerc.insets = new Insets(0, 0, 5, 5);
		gbc_lblRcPerc.gridx = 0;
		gbc_lblRcPerc.gridy = 2;
		pnlGlobalResults.add(lblRcPerc, gbc_lblRcPerc);
		
		txtRcPerc = new JLabel("");
		GridBagConstraints gbc_txtRcPerc = new GridBagConstraints();
		gbc_txtRcPerc.anchor = GridBagConstraints.WEST;
		gbc_txtRcPerc.insets = new Insets(0, 10, 5, 0);
		gbc_txtRcPerc.gridx = 1;
		gbc_txtRcPerc.gridy = 2;
		pnlGlobalResults.add(txtRcPerc, gbc_txtRcPerc);
		
		JLabel lblRoScore = new JLabel(Msg.get("ro.correta"));
		GridBagConstraints gbc_lblRoScore = new GridBagConstraints();
		gbc_lblRoScore.anchor = GridBagConstraints.WEST;
		gbc_lblRoScore.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoScore.gridx = 0;
		gbc_lblRoScore.gridy = 3;
		pnlGlobalResults.add(lblRoScore, gbc_lblRoScore);
		
		txtRoScore = new JLabel("");
		GridBagConstraints gbc_txtRoScore = new GridBagConstraints();
		gbc_txtRoScore.anchor = GridBagConstraints.WEST;
		gbc_txtRoScore.insets = new Insets(0, 10, 5, 0);
		gbc_txtRoScore.gridx = 1;
		gbc_txtRoScore.gridy = 3;
		pnlGlobalResults.add(txtRoScore, gbc_txtRoScore);
		
		JLabel lblRoPerc = new JLabel(Msg.get("ro.percent"));
		GridBagConstraints gbc_lblRoPerc = new GridBagConstraints();
		gbc_lblRoPerc.anchor = GridBagConstraints.WEST;
		gbc_lblRoPerc.insets = new Insets(0, 0, 5, 5);
		gbc_lblRoPerc.gridx = 0;
		gbc_lblRoPerc.gridy = 4;
		pnlGlobalResults.add(lblRoPerc, gbc_lblRoPerc);
		
		txtRoPerc = new JLabel("");
		GridBagConstraints gbc_txtRoPerc = new GridBagConstraints();
		gbc_txtRoPerc.anchor = GridBagConstraints.WEST;
		gbc_txtRoPerc.insets = new Insets(0, 10, 5, 0);
		gbc_txtRoPerc.gridx = 1;
		gbc_txtRoPerc.gridy = 4;
		pnlGlobalResults.add(txtRoPerc, gbc_txtRoPerc);
		
		JLabel lblRiScore = new JLabel(Msg.get("ri.correta"));
		GridBagConstraints gbc_lblRiScore = new GridBagConstraints();
		gbc_lblRiScore.anchor = GridBagConstraints.WEST;
		gbc_lblRiScore.insets = new Insets(0, 0, 5, 5);
		gbc_lblRiScore.gridx = 0;
		gbc_lblRiScore.gridy = 5;
		pnlGlobalResults.add(lblRiScore, gbc_lblRiScore);
		
		txtRiScore = new JLabel("");
		GridBagConstraints gbc_txtRiScore = new GridBagConstraints();
		gbc_txtRiScore.anchor = GridBagConstraints.WEST;
		gbc_txtRiScore.insets = new Insets(0, 10, 5, 0);
		gbc_txtRiScore.gridx = 1;
		gbc_txtRiScore.gridy = 5;
		pnlGlobalResults.add(txtRiScore, gbc_txtRiScore);
		
		JLabel lblRiPerc = new JLabel(Msg.get("ri.percent"));
		GridBagConstraints gbc_lblRiPerc = new GridBagConstraints();
		gbc_lblRiPerc.anchor = GridBagConstraints.WEST;
		gbc_lblRiPerc.insets = new Insets(0, 0, 5, 5);
		gbc_lblRiPerc.gridx = 0;
		gbc_lblRiPerc.gridy = 6;
		pnlGlobalResults.add(lblRiPerc, gbc_lblRiPerc);
		
		txtRiPerc = new JLabel("");
		GridBagConstraints gbc_txtRiPerc = new GridBagConstraints();
		gbc_txtRiPerc.anchor = GridBagConstraints.WEST;
		gbc_txtRiPerc.insets = new Insets(0, 10, 5, 0);
		gbc_txtRiPerc.gridx = 1;
		gbc_txtRiPerc.gridy = 6;
		pnlGlobalResults.add(txtRiPerc, gbc_txtRiPerc);
		
		JLabel lblMedia = new JLabel(Msg.get("med.tr"));
		GridBagConstraints gbc_lblMedia = new GridBagConstraints();
		gbc_lblMedia.anchor = GridBagConstraints.WEST;
		gbc_lblMedia.insets = new Insets(0, 0, 5, 5);
		gbc_lblMedia.gridx = 0;
		gbc_lblMedia.gridy = 7;
		pnlGlobalResults.add(lblMedia, gbc_lblMedia);
		
		txtMedia = new JLabel("");
		GridBagConstraints gbc_txtMedia = new GridBagConstraints();
		gbc_txtMedia.anchor = GridBagConstraints.WEST;
		gbc_txtMedia.insets = new Insets(0, 10, 5, 0);
		gbc_txtMedia.gridx = 1;
		gbc_txtMedia.gridy = 7;
		pnlGlobalResults.add(txtMedia, gbc_txtMedia);
		
		JLabel lblVariabilidade = new JLabel(Msg.get("var.tr"));
		GridBagConstraints gbc_lblVariabilidade = new GridBagConstraints();
		gbc_lblVariabilidade.anchor = GridBagConstraints.WEST;
		gbc_lblVariabilidade.insets = new Insets(0, 0, 5, 5);
		gbc_lblVariabilidade.gridx = 0;
		gbc_lblVariabilidade.gridy = 8;
		pnlGlobalResults.add(lblVariabilidade, gbc_lblVariabilidade);
		
		txtVariabilidade = new JLabel("");
		GridBagConstraints gbc_txtVariabilidade = new GridBagConstraints();
		gbc_txtVariabilidade.insets = new Insets(0, 10, 5, 0);
		gbc_txtVariabilidade.anchor = GridBagConstraints.WEST;
		gbc_txtVariabilidade.gridx = 1;
		gbc_txtVariabilidade.gridy = 8;
		pnlGlobalResults.add(txtVariabilidade, gbc_txtVariabilidade);
		
		JLabel lblMediaRc = new JLabel(Msg.get("med.rc.tr"));
		GridBagConstraints gbc_lblMediaRc = new GridBagConstraints();
		gbc_lblMediaRc.anchor = GridBagConstraints.WEST;
		gbc_lblMediaRc.insets = new Insets(0, 0, 5, 5);
		gbc_lblMediaRc.gridx = 0;
		gbc_lblMediaRc.gridy = 9;
		pnlGlobalResults.add(lblMediaRc, gbc_lblMediaRc);
		
		txtMediaRc = new JLabel("");
		GridBagConstraints gbc_txtMediaRc = new GridBagConstraints();
		gbc_txtMediaRc.anchor = GridBagConstraints.WEST;
		gbc_txtMediaRc.insets = new Insets(0, 10, 5, 0);
		gbc_txtMediaRc.gridx = 1;
		gbc_txtMediaRc.gridy = 9;
		pnlGlobalResults.add(txtMediaRc, gbc_txtMediaRc);
		
		JLabel lblVariabilidadeRc = new JLabel(Msg.get("var.rc.tr"));
		GridBagConstraints gbc_lblVariabilidadeRc = new GridBagConstraints();
		gbc_lblVariabilidadeRc.anchor = GridBagConstraints.WEST;
		gbc_lblVariabilidadeRc.insets = new Insets(0, 0, 5, 5);
		gbc_lblVariabilidadeRc.gridx = 0;
		gbc_lblVariabilidadeRc.gridy = 10;
		pnlGlobalResults.add(lblVariabilidadeRc, gbc_lblVariabilidadeRc);
		
		txtVariabilidadeRc = new JLabel("");
		GridBagConstraints gbc_txtVariabilidadeRc = new GridBagConstraints();
		gbc_txtVariabilidadeRc.anchor = GridBagConstraints.WEST;
		gbc_txtVariabilidadeRc.insets = new Insets(0, 10, 5, 0);
		gbc_txtVariabilidadeRc.gridx = 1;
		gbc_txtVariabilidadeRc.gridy = 10;
		pnlGlobalResults.add(txtVariabilidadeRc, gbc_txtVariabilidadeRc);
		
		JLabel lblMediaRi = new JLabel(Msg.get("med.ri.tr"));
		GridBagConstraints gbc_lblMediaRi = new GridBagConstraints();
		gbc_lblMediaRi.anchor = GridBagConstraints.WEST;
		gbc_lblMediaRi.insets = new Insets(0, 0, 5, 5);
		gbc_lblMediaRi.gridx = 0;
		gbc_lblMediaRi.gridy = 11;
		pnlGlobalResults.add(lblMediaRi, gbc_lblMediaRi);
		
		txtMediaRi = new JLabel("");
		GridBagConstraints gbc_txtMediaRi = new GridBagConstraints();
		gbc_txtMediaRi.anchor = GridBagConstraints.WEST;
		gbc_txtMediaRi.insets = new Insets(0, 10, 5, 0);
		gbc_txtMediaRi.gridx = 1;
		gbc_txtMediaRi.gridy = 11;
		pnlGlobalResults.add(txtMediaRi, gbc_txtMediaRi);
		
		JLabel lblVariabilidadeRi = new JLabel(Msg.get("var.ri.tr"));
		GridBagConstraints gbc_lblVariabilidadeRi = new GridBagConstraints();
		gbc_lblVariabilidadeRi.anchor = GridBagConstraints.WEST;
		gbc_lblVariabilidadeRi.insets = new Insets(0, 0, 0, 5);
		gbc_lblVariabilidadeRi.gridx = 0;
		gbc_lblVariabilidadeRi.gridy = 12;
		pnlGlobalResults.add(lblVariabilidadeRi, gbc_lblVariabilidadeRi);
		
		txtVariabilidadeRi = new JLabel("");
		GridBagConstraints gbc_txtVariabilidadeRi = new GridBagConstraints();
		gbc_txtVariabilidadeRi.insets = new Insets(0, 10, 0, 0);
		gbc_txtVariabilidadeRi.anchor = GridBagConstraints.WEST;
		gbc_txtVariabilidadeRi.gridx = 1;
		gbc_txtVariabilidadeRi.gridy = 12;
		pnlGlobalResults.add(txtVariabilidadeRi, gbc_txtVariabilidadeRi);
		
		JPanel pnlTitulo = new JPanel();
		pnlTitulo.setBackground(new Color(212, 170, 212));
		getContentPane().add(pnlTitulo, BorderLayout.NORTH);
		
		JLabel lblImagem = new JLabel("");
		lblImagem.setIcon(new ImageIcon(ResultadoFrame.class.getResource("/assets/ic_instrucoes-48.png")));
		pnlTitulo.add(lblImagem);
		
		JLabel lblTitulo = new JLabel(Msg.get("resultado.teste"));
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 24));
		pnlTitulo.add(lblTitulo);
		
		pnlButtons = new JPanel();
		getContentPane().add(pnlButtons, BorderLayout.SOUTH);
		pnlButtons.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		FlowLayout fl_pnlButtons = new FlowLayout(FlowLayout.RIGHT, 5, 5);
		pnlButtons.setLayout(fl_pnlButtons);
		
		btnAbrir = new JButton(Msg.get("abrir.teste"));
		btnAbrir.setVisible(false);
		btnAbrir.setIcon(new ImageIcon(ResultadoFrame.class.getResource("/assets/ic_open-32.png")));
		pnlButtons.add(btnAbrir);
		
		btnSalvar = new JButton(Msg.get("salvar"));
		pnlButtons.add(btnSalvar);
		btnSalvar.setIcon(new ImageIcon(ResultadoFrame.class.getResource("/assets/ic_save-32.png")));
	}
	
	public void preencherDadosPaciente(PatientEntity patient) {
		txtNome.setText(patient.getName());
		txtData.setText(new SimpleDateFormat(Msg.get("format.date")).format(patient.getTestDate()));
		txtGenero.setText(Msg.get(patient.getGender().equals("M") ? "paciente.genero.masculino" : "paciente.genero.feminino"));
		txtMao.setText(Msg.get(patient.getHand().equals("R") ? "paciente.mao.direita" : "paciente.mao.esquerda"));
		txtIdade.setText(patient.getAge().toString());
	}
	
	public void preencherDadosEstatisticos(ResultDataEntity res) {
		txtRcScore.setText(res.getRcScore());
		txtRcPerc.setText(res.getRcPercent());
		txtRoScore.setText(res.getRoScore());
		txtRoPerc.setText(res.getRoPercent());
		txtRiScore.setText(res.getRiScore());
		txtRiPerc.setText(res.getRiPercent());
		txtMedia.setText(res.getMedScore());
		txtVariabilidade.setText(res.getVarScore());
		txtMediaRc.setText(res.getMedScoreRc());
		txtVariabilidadeRc.setText(res.getVarScoreRc());
		txtMediaRi.setText(res.getMedScoreRi());
		txtVariabilidadeRi.setText(res.getVarScoreRi());
	}
	
	public boolean verifySave() {
		int dialogResult = JOptionPane.showConfirmDialog(this, Msg.get("msg.confirma.salva"), Msg.get("aviso"), JOptionPane.YES_NO_OPTION);
		return dialogResult == JOptionPane.YES_OPTION;
	}
}
