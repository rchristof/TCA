package br.com.guardiaosistemas.tca.execucao.consts;

import java.awt.Font;

import javax.swing.ImageIcon;

public interface C {
	String version = "1.0";
	String T_BALLON = "B";
	String T_STAR = "S";
	String F_LOW = "L";
	String F_HIGH = "H";
	
	Font fontMenu = new Font("Arial", Font.PLAIN, 14);
	Font labelFont = new Font("Arial", Font.BOLD, 12);
//	Font titleFont = new Font("Arial", Font.BOLD, 12);
	Font titleFont = new Font("Times New Roman", Font.BOLD, 24);
//	ImageIcon ICON_FLAG_BR = new ImageIcon(C.class.getResource("/assets/ic_flag_br.png"));
//	ImageIcon ICON_FLAG_US = new ImageIcon(C.class.getResource("/assets/ic_flag_us.png"));
//	ImageIcon ICON_FLAG_ES = new ImageIcon(C.class.getResource("/assets/ic_flag_es.png"));
	ImageIcon ICON_STAR = new ImageIcon(C.class.getResource("/assets/ic_test_estrela-96.png"));
	ImageIcon ICON_BALLON = new ImageIcon(C.class.getResource("/assets/ic_test_balao-96.png"));
	ImageIcon ICON_STAR_M = new ImageIcon(C.class.getResource("/assets/ic_test_estrela-144.png"));
	ImageIcon ICON_BALLON_M = new ImageIcon(C.class.getResource("/assets/ic_test_balao-144.png"));
//	ImageIcon ICON_STAR_G = new ImageIcon(C.class.getResource("/assets/ic_test_estrela-192.png"));
//	ImageIcon ICON_BALLON_G = new ImageIcon(C.class.getResource("/assets/ic_test_balao-192.png"));
	}
