package br.com.guardiaosistemas.tca.execucao.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import br.com.guardiaosistemas.tca.execucao.delegate.ExecuteDelegate;
import br.com.guardiaosistemas.tca.execucao.model.entity.PatientEntity;
import bundle.Msg;

public class SelecaoFrame extends JDialog {

	private static final long serialVersionUID = 4665415708861871163L;
	
	private PatientEntity patient;
	private ExecuteDelegate avancar;
//	private ExecuteDelegate voltar;
	
//	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public SelecaoFrame(ExecuteDelegate avancar, ExecuteDelegate voltar) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				System.out.println("I am back");
//				SelecaoFrame.this.voltar.executar(patient);
			}
		});
		this.avancar = avancar;
//		this.voltar = voltar;
		
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel pnlGeral = new JPanel();
		getContentPane().add(pnlGeral, BorderLayout.CENTER);
		pnlGeral.setLayout(new BorderLayout(0, 0));
		
		JPanel pnlInstrucoes = new JPanel();
		pnlGeral.add(pnlInstrucoes, BorderLayout.CENTER);
		pnlInstrucoes.setLayout(null);
		
		JTextPane txpInstrucoes = new JTextPane();
		txpInstrucoes.setBackground(SystemColor.menu);
		txpInstrucoes.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txpInstrucoes.setBounds(12, 12, 442, 220);
		pnlInstrucoes.add(txpInstrucoes);
		txpInstrucoes.setFont(new Font("Arial", Font.PLAIN, 20));
		txpInstrucoes.setEditable(false);
		txpInstrucoes.setText(Msg.get("selecao.info"));
		
		JPanel pnlFiguras = new JPanel();
		pnlFiguras.setBounds(459, 12, 143, 220);
		pnlFiguras.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
//		pnlFiguras.setBackground(SystemColor.menu);
		pnlInstrucoes.add(pnlFiguras);
		pnlFiguras.setLayout(new GridLayout(2, 1, 5, 0));
		
		JLabel lblEstrela = new JLabel("");
		lblEstrela.setHorizontalAlignment(SwingConstants.CENTER);
		pnlFiguras.add(lblEstrela);
		lblEstrela.setIcon(new ImageIcon(SelecaoFrame.class.getResource("/assets/ic_test_estrela-96.png")));
		lblEstrela.setBackground(new Color(0, 0, 0));
		lblEstrela.setOpaque(true);
		
		JLabel lblBalao = new JLabel("");
		lblBalao.setHorizontalAlignment(SwingConstants.CENTER);
		pnlFiguras.add(lblBalao);
		lblBalao.setIcon(new ImageIcon(SelecaoFrame.class.getResource("/assets/ic_test_balao-96.png")));
		lblBalao.setBackground(new Color(0, 0, 0));
		lblBalao.setOpaque(true);
		
		JTextArea txpInstrucoesTreinamento = new JTextArea();
		txpInstrucoesTreinamento.setOpaque(false);
		txpInstrucoesTreinamento.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		txpInstrucoesTreinamento.setAutoscrolls(false);
		txpInstrucoesTreinamento.setEditable(false);
		txpInstrucoesTreinamento.setWrapStyleWord(true);
		txpInstrucoesTreinamento.setRows(3);
		txpInstrucoesTreinamento.setLineWrap(true);
		txpInstrucoesTreinamento.setBounds(12, 244, 590, 45);
		pnlInstrucoes.add(txpInstrucoesTreinamento);
		txpInstrucoesTreinamento.setText(Msg.get("selecao.treinamento"));
//		txpInstrucoesTreinamento.setForeground(Color.WHITE);
		txpInstrucoesTreinamento.setFont(new Font("Arial", Font.ITALIC, 18));
//		txpInstrucoesTreinamento.setBackground(new Color(240, 240, 240));
		
		JPanel pnlBotoes = new JPanel();
		pnlGeral.add(pnlBotoes, BorderLayout.SOUTH);
		pnlBotoes.setBorder(new BevelBorder(BevelBorder.RAISED, SystemColor.textHighlight, null, null, null));
		FlowLayout flowLayout = (FlowLayout) pnlBotoes.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		flowLayout.setHgap(10);
		pnlBotoes.setBackground(SystemColor.control);
		
		JButton btn1Minutos = new JButton(Msg.get("selecao.minutos"));
		btn1Minutos.setIcon(new ImageIcon(SelecaoFrame.class.getResource("/assets/ic_time_1m30s-32.png")));
		btn1Minutos.addActionListener((e) -> iniciarTeste(1));
		pnlBotoes.add(btn1Minutos);
		
		JButton btn5Minutos = new JButton(Msg.get("selecao.minutos"));
		btn5Minutos.addActionListener((e) -> iniciarTeste(5));
		btn5Minutos.setIcon(new ImageIcon(SelecaoFrame.class.getResource("/assets/ic_time_5-32.png")));
		pnlBotoes.add(btn5Minutos);
		
		JButton btn10Minutos = new JButton(Msg.get("selecao.minutos"));
		btn10Minutos.addActionListener((e) -> iniciarTeste(10));
		btn10Minutos.setIcon(new ImageIcon(SelecaoFrame.class.getResource("/assets/ic_time_10-32.png")));
		pnlBotoes.add(btn10Minutos);
		
		JButton btn15Minutos = new JButton(Msg.get("selecao.minutos"));
		btn15Minutos.addActionListener((e) -> iniciarTeste(15));
		btn15Minutos.setIcon(new ImageIcon(SelecaoFrame.class.getResource("/assets/ic_time_15-32.png")));
		pnlBotoes.add(btn15Minutos);

		JTextField customTimeField = new JTextField(5);
		pnlBotoes.add(customTimeField);

		JButton btnCustomTime = new JButton("Custom Time");
		btnCustomTime.addActionListener((e) -> {
    		try {
        		int customTime = Integer.parseInt(customTimeField.getText());
        		iniciarTeste(customTime);
    		} catch (NumberFormatException ex) {
        		System.out.println("Invalid time input. Please enter a valid number.");
    		}
		});
		pnlBotoes.add(btnCustomTime);

		JPanel pnlTitulo = new JPanel();
		pnlGeral.add(pnlTitulo, BorderLayout.NORTH);
		pnlTitulo.setBackground(new Color(240, 226, 240));
		
		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(SelecaoFrame.class.getResource("/assets/ic_instrucoes-48.png")));
		pnlTitulo.add(lblIcon);
		
		JLabel lblTitulo = new JLabel(Msg.get("selecao.inst"));
		lblTitulo.setHorizontalTextPosition(SwingConstants.LEFT);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblTitulo.setAlignmentY(0.0f);
		pnlTitulo.add(lblTitulo);
		
		JLabel lblImagem = new JLabel("");
		pnlGeral.add(lblImagem, BorderLayout.WEST);
		lblImagem.setIcon(new ImageIcon(SelecaoFrame.class.getResource("/assets/img_keyboard_computer_hand-240x400.jpg")));
		setIconImage(Toolkit.getDefaultToolkit().getImage(SelecaoFrame.class.getResource("/assets/ic_brain-24.png")));
		setTitle(Msg.get("tca"));
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 863, 449);
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dw = getSize();
		setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
	}
	
	private void iniciarTeste(int minutos) {
		patient.setExecutionTime(minutos);
		avancar.executar(patient);
		setVisible(false);
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}
	
}
