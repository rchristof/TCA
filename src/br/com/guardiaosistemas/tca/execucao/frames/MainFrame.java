package br.com.guardiaosistemas.tca.execucao.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;

import br.com.guardiaosistemas.tca.execucao.FrameManager;
import br.com.guardiaosistemas.tca.execucao.consts.C;
import bundle.Msg;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1673883272543760791L;
	private static final ImageIcon ICON_BG_DEFAULT = new ImageIcon(MainFrame.class.getResource("/assets/img_backgound.jpg"));
	private static final ImageIcon ICON_BG_EN = new ImageIcon(MainFrame.class.getResource("/assets/img_backgound_en.jpg"));
	
	private FrameManager manager;
	
	//	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnArquivo;
	private JMenuItem mntmAbrir;
	private JMenuItem mntmCadastrarPaciente;
	private JMenuItem mntmSair;
	private JMenu mnIdioma;
	private JRadioButtonMenuItem rdbtnmntmPortugues;
	private JRadioButtonMenuItem rdbtnmntmEnglish;
	private JRadioButtonMenuItem rdbtnmntmEspanhol;
	private JMenu mnAjuda;
	private JMenuItem mntmSobre;

	private ButtonGroup bgIdioma;
	private JLabel lblBackground;
	
	/**
	 * Create the frame.
	 */
	public MainFrame(FrameManager manager) {
		super();
		setMinimumSize(new Dimension(400, 400));
		this.manager = manager;
		
		getContentPane().setBackground(new Color(0, 0, 0));
		setBackground(new Color(0, 0, 0));
//		System.out.println(getName());

		initMenuBar();
		initForm();
		initLables();
	}
	
	private void initMenuBar() {
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnArquivo = new JMenu();
		mntmAbrir = new JMenuItem();
		mntmAbrir.setIcon(new ImageIcon(MainFrame.class.getResource("/assets/ic_open-24.png")));
		mntmCadastrarPaciente = new JMenuItem();
		mntmSair = new JMenuItem();
		mntmSair.setIcon(new ImageIcon(MainFrame.class.getResource("/assets/ic_exit-24.png")));
		mnIdioma = new JMenu();
		mnIdioma.setBackground(new Color(0, 128, 128));
		rdbtnmntmPortugues = new JRadioButtonMenuItem();
		rdbtnmntmEnglish = new JRadioButtonMenuItem();
		rdbtnmntmEspanhol = new JRadioButtonMenuItem();
		mnAjuda = new JMenu();
		mntmSobre = new JMenuItem();
		mntmSobre.setIcon(new ImageIcon(MainFrame.class.getResource("/assets/ic_brain-24.png")));
		
		mnArquivo.setName("menu.arquivo");
		mntmAbrir.setName("menu.open");
		mntmCadastrarPaciente.setName("menu.cadastro");
		mntmSair.setName("menu.sair");
		mnIdioma.setName("menu.idioma");
		rdbtnmntmPortugues.setName("menu.portugues");
		rdbtnmntmEnglish.setName("menu.ingles");
		rdbtnmntmEspanhol.setName("menu.espanhol");
		mnAjuda.setName("menu.ajuda");
		mntmSobre.setName("menu.sobre");
		
		// Menu Arquivo
		menuBar.add(mnArquivo);
		mnArquivo.add(mntmCadastrarPaciente);
		mnArquivo.add(mntmAbrir);
		mnArquivo.addSeparator();
		mnArquivo.add(mntmSair);
		
		// Menu Idioma
		menuBar.add(mnIdioma);
		mnIdioma.add(rdbtnmntmPortugues);
		mnIdioma.add(rdbtnmntmEnglish);
		mnIdioma.add(rdbtnmntmEspanhol);
		
		bgIdioma = new ButtonGroup();
		bgIdioma.add(rdbtnmntmPortugues);
		bgIdioma.add(rdbtnmntmEnglish);
		bgIdioma.add(rdbtnmntmEspanhol);
		
		rdbtnmntmPortugues.setIcon(new ImageIcon(MainFrame.class.getResource("/assets/ic_flag_br.png")));
		rdbtnmntmPortugues.addActionListener(e -> { Msg.changeLocaleToPtBr(); initLables(); });
		rdbtnmntmPortugues.setSelected(Msg.getCurrentLocale() == Msg.localePtBr);
		
		rdbtnmntmEnglish.addActionListener(e -> { Msg.changeLocaleToEnUs(); initLables(); });
		rdbtnmntmEnglish.setSelected(Msg.getCurrentLocale() == Msg.localeUsEn);
		rdbtnmntmEnglish.setIcon(new ImageIcon(MainFrame.class.getResource("/assets/ic_flag_us.png")));
		
		rdbtnmntmEspanhol.addActionListener(e -> { Msg.changeLocaleToEsEs(); initLables(); });
		rdbtnmntmEspanhol.setSelected(Msg.getCurrentLocale() == Msg.localeEsEs);
		rdbtnmntmEspanhol.setIcon(new ImageIcon(MainFrame.class.getResource("/assets/ic_flag_es.png")));

		// Menu Ajuda 
		menuBar.add(mnAjuda);
		mnAjuda.add(mntmSobre);

		// Configurações de fontes
//		mntmCadastrarPaciente.setFont(C.fontMenu);
		
		// Configurações de imagens
		mntmCadastrarPaciente.setIcon(new ImageIcon(MainFrame.class.getResource("/assets/ic_avatar_male_adm-24.png")));
		
		// Configurações de eventos
		mntmCadastrarPaciente.addActionListener(e -> manager.showPacienteFrame(null));
		mntmSair.addActionListener(e -> System.exit(EXIT_ON_CLOSE));
		mntmSobre.addActionListener(e -> manager.showSobreFrame());
		mntmAbrir.addActionListener(e -> manager.showLoadReaultadoFrame());
	}
	
	private void initLables() {
		mnArquivo.setText(Msg.get("menu.arquivo"));
		mntmAbrir.setText(Msg.get("abrir.teste"));
		mntmCadastrarPaciente.setText(Msg.get("menu.cadastro"));
		mntmSair.setText(Msg.get("menu.sair"));
		mnIdioma.setText(Msg.get("menu.idioma"));
		rdbtnmntmPortugues.setText(Msg.get("menu.portugues"));
		rdbtnmntmEnglish.setText(Msg.get("menu.ingles"));
		rdbtnmntmEspanhol.setText(Msg.get("menu.espanhol"));
		mnAjuda.setText(Msg.get("menu.ajuda"));
		mntmSobre.setText(Msg.get("menu.sobre"));
		
		setTitle(Msg.get("tca") + " v" + C.version);
		lblBackground.setIcon(Msg.getCurrentLocale() == Msg.localeUsEn ? ICON_BG_EN : ICON_BG_DEFAULT);
	}

	
	public void initForm() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/assets/ic_brain-24.png")));
		setBounds(100, 100, 450, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
//		Dimension dw = getSize();
//		setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		lblBackground = new JLabel();
		lblBackground.setAlignmentY(Component.TOP_ALIGNMENT);
		lblBackground.setOpaque(true);
		lblBackground.setHorizontalAlignment(SwingConstants.CENTER);
		lblBackground.setIcon(ICON_BG_DEFAULT);
		getContentPane().add(lblBackground);
	}

}
