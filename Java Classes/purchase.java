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
public class purchase extends JFrame implements ActionListener {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/hms";
	
	static final String USER = "root";
	static final String PASS = "59631234";
	
	Connection conn = null;
	Statement stmt = null;
	
	JPanel four = new JPanel();
	JButton banPu = new JButton(new ImageIcon("E:\\PSD\\JAVA\\EE\\Hotel Management System\\Images\\BanPu.JPG"));
	JLabel proNa = new JLabel("PRODUCT NAME    :");
	JLabel proPr = new JLabel("PRICE (PER UNIT)  :");
	JLabel noUn = new JLabel("NO OF UNITS          :");
	JTextField prNa = new JTextField(20);
	JTextField prPr = new JTextField(5);
	JTextField nUn = new JTextField(10);
	JButton con = new JButton("CONFIRM");
	JButton back = new JButton("BACK");
	JButton list = new JButton("LIST");
	
	String pNa;
	int ppU;
	int nU;
	int tot;
	
	purchase(){
		
		super("LAP INTERNATIONAL HOTEL");
		add(four);
		four.setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		elements();
	}
	
	void elements() {
	
		four.setBackground(Color.DARK_GRAY);
		
		four.add(banPu);
		banPu.setBounds(20, 10, 1320, 220);
		
		four.add(proNa);
		proNa.setForeground(Color.BLACK);
		proNa.setBounds(500, 350, 150, 20);
		four.add(prNa);
		prNa.setBounds(620, 350, 250, 20);
		
		four.add(proPr);
		proPr.setForeground(Color.BLACK);
		proPr.setBounds(500, 400, 150, 20);
		four.add(prPr);
		prPr.setBounds(620, 400, 250, 20);
		
		four.add(noUn);
		noUn.setForeground(Color.BLACK);
		noUn.setBounds(500, 450, 150, 20);
		four.add(nUn);
		nUn.setBounds(620, 450, 250, 20);
		
		four.add(con);
		con.addActionListener(this);
		con.setBounds(530, 550, 150, 20);
		
		four.add(back);
		back.addActionListener(this);
		back.setBounds(730, 550, 100, 20);
		
		four.add(list);
		list.addActionListener(this);
		list.setBounds(660, 600, 80, 20);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == con) {
			
			try
			{
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
				stmt = conn.createStatement();
				
				String sql;
				
				pNa = prNa.getText();
				ppU = Integer.valueOf(prPr.getText());
				nU = Integer.valueOf(nUn.getText());
				tot = ppU*nU;
				
				sql = "INSERT INTO purchase VALUES(NULL,'"+pNa+"',"+ppU+","+nU+","+tot+");";
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
			
			new mm();
			dispose();
			
		}else if(e.getSource() == list) {
			
			new purList();
			dispose();
		}

	}

}
