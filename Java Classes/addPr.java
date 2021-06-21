import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class addPr extends JFrame implements ActionListener {
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/hms";
	
	static final String USER = "root";
	static final String PASS = "59631234";
	
	Connection conn = null;
	Statement stmt = null;
	
	String dN;
	int pPU;

	JPanel six = new JPanel();
	JButton banAd = new JButton(new ImageIcon("E:\\PSD\\JAVA\\EE\\Hotel Management System\\Images\\BanAd.JPG"));
	JLabel proNa = new JLabel("PRODUCT NAME   :");
	JLabel proPr = new JLabel("PRICE (PER UNIT)  :");
	JTextField prNa = new JTextField(20);
	JTextField prPr = new JTextField(5);
	JButton add = new JButton("ADD");
	JButton back = new JButton("BACK");
	
	addPr(){
		
		super("LAP INTERNATIONAL HOTEL");
		add(six);
		six.setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		elements();
		
	}
	
	void elements() {
		
		six.setBackground(Color.WHITE);
		
		six.add(banAd);
		banAd.setBounds(20, 10, 1320, 220);
		
		six.add(proNa);
		proNa.setBounds(500, 350, 150, 20);
		six.add(prNa);
		prNa.setBounds(620, 350, 250, 20);
		
		six.add(proPr);
		proPr.setBounds(500, 400, 150, 20);
		six.add(prPr);
		prPr.setBounds(620, 400, 250, 20);
		
		six.add(add);
		add.addActionListener(this);
		add.setBounds(570, 500, 80, 20);
		
		six.add(back);
		back.addActionListener(this);
		back.setBounds(720, 500, 80, 20);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == add) {
			dN = prNa.getText();
			pPU = 2*Integer.valueOf(prPr.getText());
		
			
			try
			{
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
				stmt = conn.createStatement();
				String sql;
				
				sql = "INSERT INTO menu VALUES(NULL"+","+"'"+dN+"'"+","+pPU+","+Integer.valueOf(prPr.getText())+")";
				stmt.executeUpdate(sql);
				
			}catch(SQLException se) {
				se.printStackTrace();
			}catch(Exception f) {
				f.printStackTrace();
			}finally {
				try {
					if(conn != null) {
						conn.close();
					}
				}catch(SQLException se) {
					se.printStackTrace();
				}
			}
		}else if(e.getSource() == back) {
			
			new prLi();
			dispose();
			
		}
		
	}

}