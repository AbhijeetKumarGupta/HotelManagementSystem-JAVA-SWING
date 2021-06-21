import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class mm extends JFrame implements ActionListener {

	JPanel two = new JPanel();
	JButton banMM = new JButton(new ImageIcon("E:\\PSD\\JAVA\\EE\\Hotel Management System\\Images\\BanMM.JPG"));
	JLabel sale = new JLabel("[1] SALE MENU.");
	JLabel purch = new JLabel("[2] PURCHASE MENU.");
	JLabel proLi = new JLabel("[3] ADD-REMOVE ITEMS.");
	JLabel proPri = new JLabel("[4] PRICE CHANGING MENU.");
	JLabel acc = new JLabel("[5] ACCOUNTS MENU.");
	JButton sal = new JButton("ENTER");
	JButton purc = new JButton("ENTER");
	JButton proL = new JButton("ENTER");
	JButton proPr = new JButton("ENTER");
	JButton ac = new JButton("ENTER");
	JButton lo = new JButton("LOG OUT");
	JButton bot = new JButton(new ImageIcon("E:\\PSD\\JAVA\\EE\\Hotel Management System\\Images\\g.JPG"));
	JButton lef = new JButton(new ImageIcon("E:\\PSD\\JAVA\\EE\\Hotel Management System\\Images\\e.JPG"));
	JButton rit = new JButton(new ImageIcon("E:\\PSD\\JAVA\\EE\\Hotel Management System\\Images\\f.JPG"));
	
	mm(){
	
		super("LAP INTERNATIONAL HOTEL");
		add(two);
		two.setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		elements();
	}
	
	void elements() {
		
		two.setBackground(Color.DARK_GRAY);
		
		two.add(banMM);
		banMM.setBounds(2, 0, 1360, 220);
		
		two.add(sale);
		sale.setForeground(Color.WHITE);
		sale.setBounds(520, 280, 160, 20);
		
		two.add(purch);
		purch.setForeground(Color.WHITE);
		purch.setBounds(520, 310, 160, 20);
		
		two.add(proLi);
		proLi.setForeground(Color.WHITE);
		proLi.setBounds(520, 340, 160, 20);
		
		two.add(proPri);
		proPri.setForeground(Color.WHITE);
		proPri.setBounds(520, 370, 160, 20);
		
		two.add(acc);
		acc.setForeground(Color.WHITE);
		acc.setBounds(520, 400, 160, 20);
		
		two.add(sal);
		sal.addActionListener(this);
		sal.setBounds(680, 280, 100, 20);
		
		two.add(purc);
		purc.addActionListener(this);
		purc.setBounds(680, 310, 100, 20);
		
		two.add(proL);
		proL.addActionListener(this);
		proL.setBounds(680, 340, 100, 20);
		
		two.add(proPr);
		proPr.addActionListener(this);
		proPr.setBounds(680, 370, 100, 20);
		
		two.add(ac);
		ac.addActionListener(this);
		ac.setBounds(680, 400, 100, 20);
		
		two.add(lo);
		lo.addActionListener(this);
		lo.setBounds(600, 450, 100, 20);
		
		two.add(bot);
		bot.setBounds(0, 580, 1365, 140);
		
		two.add(lef);
		lef.setBounds(0,220,176,360);
		
		two.add(rit);
		rit.setBounds(1194,220,176,360);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource() == sal) {
			
			new sale();
			dispose();
			
		}else if(e.getSource() == purc) {
			
			new purchase();
			dispose();
			
		}else if(e.getSource() == proL) {
			
			new prLi();
			dispose();
			
		}else if(e.getSource() == proPr) {
		
			new prodPr();
			dispose();
			
		}else if(e.getSource() == ac) {
			
			new acc();
			dispose();
			
		}else if(e.getSource() == lo) {
			new login();
			dispose();
		}
		
	}

}