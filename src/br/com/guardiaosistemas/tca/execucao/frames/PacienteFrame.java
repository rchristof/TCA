package br.com.guardiaosistemas.tca.execucao.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import br.com.guardiaosistemas.tca.execucao.consts.C;
import br.com.guardiaosistemas.tca.execucao.delegate.ExecuteDelegate;
import br.com.guardiaosistemas.tca.execucao.model.entity.PatientEntity;
import br.com.guardiaosistemas.tca.execucao.model.entity.TestEntity;
import bundle.Msg;

public class PacienteFrame extends JDialog {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtName;

	private JComboBox<String> cmbInstruction;

	private JSpinner spnAge;

	private ButtonGroup btgGender;

	private ButtonGroup btgHand;

	private PatientEntity patient;
	private ExecuteDelegate avancar;

	/**
	 * Create the frame.
	 */
	public PacienteFrame(ExecuteDelegate avancar) {
		this.avancar = avancar;
		patient = new PatientEntity();

		setIconImage(Toolkit.getDefaultToolkit().getImage(PacienteFrame.class.getResource("/assets/ic_brain-24.png")));
		setTitle(Msg.get("tca"));
		setModal(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 563, 509);

		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dw = getSize();
		setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 10));

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		JPanel pnlTop = new JPanel();
		panel_1.add(pnlTop, BorderLayout.NORTH);
		FlowLayout flowLayout = (FlowLayout) pnlTop.getLayout();
		flowLayout.setHgap(10);
		pnlTop.setBackground(new Color(212, 170, 212));

		JLabel lblIconTitle = new JLabel("");
		lblIconTitle.setIcon(new ImageIcon(PacienteFrame.class.getResource("/assets/ic_avatar_male_adm-48.png")));
		pnlTop.add(lblIconTitle);

		JLabel lblTitulo = new JLabel(Msg.get("paciente.titulo"));
		pnlTop.add(lblTitulo);
		lblTitulo.setHorizontalTextPosition(SwingConstants.LEFT);
		lblTitulo.setAlignmentY(Component.TOP_ALIGNMENT);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(C.titleFont);

		JPanel pnlMain = new JPanel();
		panel_1.add(pnlMain, BorderLayout.CENTER);
		pnlMain.setBorder(new CompoundBorder());
		pnlMain.setFont(C.labelFont);
		pnlMain.setLayout(null);

		JLabel lblName = new JLabel(Msg.get("paciente.nome"));
		lblName.setFont(C.labelFont);
		lblName.setBounds(12, 0, 275, 24);
		pnlMain.add(lblName);

		txtName = new JTextField();
		lblName.setLabelFor(txtName);
		txtName.setBounds(12, 23, 275, 24);
		pnlMain.add(txtName);
		txtName.setColumns(10);

		JLabel lblAge = new JLabel(Msg.get("paciente.idade"));
		lblAge.setFont(C.labelFont);
		lblAge.setBounds(12, 55, 91, 24);
		pnlMain.add(lblAge);

		spnAge = new JSpinner();
		lblAge.setLabelFor(spnAge);
		spnAge.setModel(new SpinnerNumberModel(0, 0, 110, 1));
		spnAge.setBounds(12, 78, 91, 24);
		pnlMain.add(spnAge);

		JLabel lblInstruction = new JLabel(Msg.get("paciente.instrucao"));
		lblInstruction.setFont(C.labelFont);
		lblInstruction.setBounds(119, 55, 168, 24);
		pnlMain.add(lblInstruction);

		cmbInstruction = new JComboBox<String>();
		lblInstruction.setLabelFor(cmbInstruction);
		cmbInstruction.setModel(new DefaultComboBoxModel<String>(makeInstrutionList()));
		cmbInstruction.setBounds(119, 78, 168, 24);
		cmbInstruction.setSelectedIndex(-1);
		pnlMain.add(cmbInstruction);

		JPanel pnlHand = new JPanel();
		pnlHand.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), Msg.get("paciente.genero"),
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlHand.setBounds(12, 114, 275, 107);
		pnlMain.add(pnlHand);
		pnlHand.setLayout(new GridLayout(0, 2, 0, 0));
		pnlHand.setFont(C.labelFont);

		JToggleButton tbtMale = new JToggleButton(Msg.get("paciente.genero.masculino"));
		tbtMale.setActionCommand("M");
		tbtMale.setMnemonic('M');
		tbtMale.setIcon(new ImageIcon(PacienteFrame.class.getResource("/assets/ic_gender_male-48.png")));
		// tbtMale.setFont(C.labelFont);
		tbtMale.setVerticalTextPosition(SwingConstants.BOTTOM);
		tbtMale.setHorizontalTextPosition(SwingConstants.CENTER);
		tbtMale.setFont(C.labelFont);
		pnlHand.add(tbtMale);

		JToggleButton tbtFemale = new JToggleButton(Msg.get("paciente.genero.feminino"));
		tbtFemale.setActionCommand("F");
		tbtFemale.setMnemonic('F');
		tbtFemale.setIcon(new ImageIcon(PacienteFrame.class.getResource("/assets/ic_gender_female-48.png")));
		tbtFemale.setVerticalTextPosition(SwingConstants.BOTTOM);
		tbtFemale.setHorizontalTextPosition(SwingConstants.CENTER);
		tbtFemale.setFont(C.labelFont);
		pnlHand.add(tbtFemale);

		JPanel pnlSelecaoMao = new JPanel();
		pnlSelecaoMao.setFont(C.labelFont);
		pnlSelecaoMao.setBorder(new TitledBorder(null, Msg.get("paciente.mao.utilizada"), TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnlSelecaoMao.setBounds(12, 233, 275, 107);
		pnlMain.add(pnlSelecaoMao);
		pnlSelecaoMao.setLayout(new GridLayout(0, 2, 0, 0));

		JToggleButton tbtLeftHand = new JToggleButton(Msg.get("paciente.mao.esquerda"));
		tbtLeftHand.setActionCommand("L");
		tbtLeftHand.setMnemonic('L');
		tbtLeftHand.setIcon(new ImageIcon(PacienteFrame.class.getResource("/assets/ic_hand_left-48.png")));
		pnlSelecaoMao.add(tbtLeftHand);
		tbtLeftHand.setFont(C.labelFont);
		tbtLeftHand.setVerticalTextPosition(SwingConstants.BOTTOM);
		tbtLeftHand.setHorizontalTextPosition(SwingConstants.CENTER);

		JToggleButton tbtRightHand = new JToggleButton(Msg.get("paciente.mao.direita"));
		tbtRightHand.setActionCommand("R");
		tbtRightHand.setMnemonic('R');
		pnlSelecaoMao.add(tbtRightHand);
		tbtRightHand.setFont(C.labelFont);
		tbtRightHand.setHorizontalTextPosition(SwingConstants.CENTER);
		tbtRightHand.setVerticalTextPosition(SwingConstants.BOTTOM);
		tbtRightHand.setIcon(new ImageIcon(PacienteFrame.class.getResource("/assets/ic_hand_right-48.png")));

		JPanel pnlBottom = new JPanel();
		panel_1.add(pnlBottom, BorderLayout.SOUTH);
		pnlBottom.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		pnlBottom.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

		JButton btnIniciar = new JButton(Msg.get("teste.iniciar"));
		btnIniciar.setIcon(new ImageIcon(PacienteFrame.class.getResource("/assets/ic_start-32.png")));
		btnIniciar.addActionListener(e -> save());
		btnIniciar.setFont(C.labelFont);
		pnlBottom.add(btnIniciar);

		JLabel lblImagem = new JLabel("");
		panel_1.add(lblImagem, BorderLayout.WEST);
		lblImagem.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagem.setIcon(
				new ImageIcon(PacienteFrame.class.getResource("/assets/img_laptop_smartphone-240x400.jpg")));

		// Configuração dos grupos
		btgGender = new ButtonGroup();
		btgGender.add(tbtMale);
		btgGender.add(tbtFemale);

		btgHand = new ButtonGroup();
		btgHand.add(tbtLeftHand);
		btgHand.add(tbtRightHand);
	}

	private String[] makeInstrutionList() {
		String[] instructions = new String[14];
		instructions[0] = Msg.get("instrucao.analfabeto");
		instructions[1] = "1 " + Msg.get("instrucao.ano");
		for (int i = 2; i < 12; i++) {
			instructions[i] = i + " " + Msg.get("instrucao.anos");
		}
		instructions[12] = Msg.get("instrucao.graduado");
		instructions[13] = Msg.get("instrucao.posgraduado");

		return instructions;
	}

	private PatientEntity validateForm() throws Exception {
		if ("".equals(txtName.getText())) {
			throw new Exception(Msg.get("paciente.validacao.nome"));
		}

		Integer age = Integer.parseInt(spnAge.getValue().toString());
		if (age == 0) {
			throw new Exception(Msg.get("paciente.validacao.idade"));
		}

		if (cmbInstruction.getSelectedIndex() < 0) {
			throw new Exception(Msg.get("paciente.validacao.instrucao"));
		}

		if (btgGender.getSelection() == null) {
			throw new Exception(Msg.get("paciente.validacao.genero"));
		}

		if (btgHand.getSelection() == null) {
			throw new Exception(Msg.get("paciente.validacao.mao"));
		}

		patient.setName(txtName.getText());
		patient.setAge(age);
		patient.setInstruction(cmbInstruction.getSelectedIndex());
		patient.setGender(btgGender.getSelection().getActionCommand());
		patient.setHand(btgHand.getSelection().getActionCommand());
		patient.setTestDate(new Date());
		return patient;
	}

	private void save() {
	    try {
	        validateForm();
	        setVisible(false);

	        TestEntity testEntity = new TestEntity(0, 0, 0, null);

	        avancar.executar(patient, testEntity);
	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, e.getMessage(), Msg.get("paciente.validacao"), JOptionPane.ERROR_MESSAGE);
	    }
	}


	public PatientEntity getPatient() {
		return patient;
	}

	public void setPatient(PatientEntity patient) {
		this.patient = patient;
	}
}