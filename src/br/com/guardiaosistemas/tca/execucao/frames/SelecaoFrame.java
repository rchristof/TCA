package br.com.guardiaosistemas.tca.execucao.frames;

import br.com.guardiaosistemas.tca.execucao.delegate.ExecuteDelegate;
import br.com.guardiaosistemas.tca.execucao.frames.helper.DataTestHelper;
import br.com.guardiaosistemas.tca.execucao.model.entity.HitEntity;
import br.com.guardiaosistemas.tca.execucao.model.entity.PatientEntity;
import br.com.guardiaosistemas.tca.execucao.model.entity.TestEntity;
import bundle.Msg;
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
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class SelecaoFrame extends JDialog {

	private static final long serialVersionUID = 4665415708861871163L;
	
	private PatientEntity patient;
	private ExecuteDelegate avancar;
	private int totalAlvos = 24;
	private int frequency = 80;
	private int soa = 1;
	
	// Adicionar grupos de botões
    private ButtonGroup frequencyGroup = new ButtonGroup();
    private ButtonGroup soaGroup = new ButtonGroup();
    private ButtonGroup alvosGroup = new ButtonGroup();

	/**
	 * Create the frame.
	 */
	public SelecaoFrame(ExecuteDelegate avancar, ExecuteDelegate voltar) {
		this.avancar = avancar;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
			}
		});
		
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
		txpInstrucoesTreinamento.setFont(new Font("Arial", Font.ITALIC, 18));
		
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
		setBounds(100, 100, 863, 600);
		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dw = getSize();
		setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
		

        JPanel pnlBotoesContainer = new JPanel();
        pnlGeral.add(pnlBotoesContainer, BorderLayout.SOUTH);
        pnlBotoesContainer.setLayout(new BorderLayout(0, 0));

        // Painel para os botões principais
        JPanel pnlBotoes = new JPanel();
        pnlBotoesContainer.add(pnlBotoes, BorderLayout.NORTH);
        pnlBotoes.setBorder(new BevelBorder(BevelBorder.RAISED, SystemColor.textHighlight, null, null, null));
        pnlBotoes.setBackground(SystemColor.control);
        pnlBotoes.setLayout(new GridLayout(4, 3, 10, 5)); // 4 linhas, 3 colunas, gap horizontal 10, gap vertical 5

        // Primeira linha - Frequência
        JToggleButton btn80Frequencia = new JToggleButton("80% " + Msg.get("selecao.freq"));
        btn80Frequencia.addActionListener(e -> frequency = 80);
        btn80Frequencia.setSelected(true); // Seleciona por padrão
        frequencyGroup.add(btn80Frequencia);
        pnlBotoes.add(btn80Frequencia);
        
        JToggleButton btn20Frequencia = new JToggleButton("20% " + Msg.get("selecao.freq"));
        btn20Frequencia.addActionListener(e -> frequency = 20);
        frequencyGroup.add(btn20Frequencia);
        pnlBotoes.add(btn20Frequencia);
        
        pnlBotoes.add(new JLabel()); // Espaço vazio
        
        // Segunda linha - Tempo
        JToggleButton btn1Tempo = new JToggleButton("1s");
        btn1Tempo.addActionListener(e -> soa = 1);
        btn1Tempo.setSelected(true); // Seleciona por padrão
        soaGroup.add(btn1Tempo);
        pnlBotoes.add(btn1Tempo);
        
        JToggleButton btn2Tempo = new JToggleButton("2s");
        btn2Tempo.addActionListener(e -> soa = 2);
        soaGroup.add(btn2Tempo);
        pnlBotoes.add(btn2Tempo);
        
        JToggleButton btn4Tempo = new JToggleButton("4s");
        btn4Tempo.addActionListener(e -> soa = 4);
        soaGroup.add(btn4Tempo);
        pnlBotoes.add(btn4Tempo);
        
        // Terceira linha - Alvos
        JToggleButton btn24Alvos = new JToggleButton("24 " + Msg.get("selecao.alvos"));
        btn24Alvos.addActionListener(e -> totalAlvos = 24);
        btn24Alvos.setSelected(true); // Seleciona por padrão
        alvosGroup.add(btn24Alvos);
        pnlBotoes.add(btn24Alvos);
        
        JToggleButton btn48Alvos = new JToggleButton("48 " + Msg.get("selecao.alvos"));
        btn48Alvos.addActionListener(e -> totalAlvos = 48);
        alvosGroup.add(btn48Alvos);
        pnlBotoes.add(btn48Alvos);
        
        JToggleButton btn72Alvos = new JToggleButton("72 " + Msg.get("selecao.alvos"));
        btn72Alvos.addActionListener(e -> totalAlvos = 72);
        alvosGroup.add(btn72Alvos);
        pnlBotoes.add(btn72Alvos);
        
        // Quarta linha - Botão iniciar
        pnlBotoes.add(new JLabel());
        
        JButton btnIniciar = new JButton("Iniciar Teste");
        btnIniciar.addActionListener(e -> {
            try {
                List<HitEntity> stimuliList = DataTestHelper.generateStimuliList(totalAlvos, frequency, soa);
                TestEntity testEntity = new TestEntity(totalAlvos, frequency, soa, stimuliList);
                avancar.executar(patient, testEntity);
                setVisible(false);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao iniciar o teste.");
            }
        });
        pnlBotoes.add(btnIniciar);
        
        pnlBotoes.add(new JLabel());

        // Painel para os botões predefinidos
        JPanel pnlBotoesPreDef = new JPanel();
        pnlBotoesContainer.add(pnlBotoesPreDef, BorderLayout.SOUTH);
        pnlBotoesPreDef.setBorder(new BevelBorder(BevelBorder.RAISED, SystemColor.textHighlight, null, null, null));
        pnlBotoesPreDef.setBackground(SystemColor.control);
        pnlBotoesPreDef.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

        // Botões predefinidos
        JButton btn1m30 = new JButton("1m30");
        btn1m30.addActionListener(e -> {
            List<HitEntity> stimuliList = DataTestHelper.getTestList1M30S();
            TestEntity testEntity = new TestEntity(0, 0, 1, stimuliList);
            setVisible(false);
            avancar.executar(patient, testEntity);
        });
        pnlBotoesPreDef.add(btn1m30);

        JButton btn5m = new JButton("5m");
        btn5m.addActionListener(e -> {
            List<HitEntity> stimuliList = DataTestHelper.getTestList5M();
            TestEntity testEntity = new TestEntity(0, 0, 1, stimuliList);
            setVisible(false);
            avancar.executar(patient, testEntity);
        });
        pnlBotoesPreDef.add(btn5m);

        JButton btn10m = new JButton("10m");
        btn10m.addActionListener(e -> {
            List<HitEntity> stimuliList = DataTestHelper.getTestList10M();
            TestEntity testEntity = new TestEntity(0, 0, 1, stimuliList);
            setVisible(false);
            avancar.executar(patient, testEntity);
        });
        pnlBotoesPreDef.add(btn10m);

        JButton btn15m = new JButton("15m");
        btn15m.addActionListener(e -> {
            List<HitEntity> stimuliList = DataTestHelper.getTestList15M();
            TestEntity testEntity = new TestEntity(0, 0, 1, stimuliList);
            setVisible(false);
            avancar.executar(patient, testEntity);
        });
        pnlBotoesPreDef.add(btn15m);
	}

	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}
	
}
