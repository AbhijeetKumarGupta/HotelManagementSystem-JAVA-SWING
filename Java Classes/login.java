import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class login extends JFrame implements ActionListener {
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/hms";
	
	static final String USER = "root";
	static final String PASS = "59631234";
	
	static String ID;
	static String Pas;
	
	JPanel one = new JPanel(); 
	JButton banr = new JButton(new ImageIcon("E:\\PSD\\JAVA\\EE\\Hotel Management System\\Images\\Ban.JPG"));
	JTextField lId = new JTextField(20);
	JPasswordField pas = new JPasswordField(10);
	JLabel logId = new JLabel("Login ID      : ");
	JLabel pass = new JLabel("Password  : ");
	JButton login = new JButton("LOGIN");
	JButton rePas = new JButton("FORGOT PASSWORD"); 
	JButton bot = new JButton(new ImageIcon("E:\\PSD\\JAVA\\EE\\Hotel Management System\\Images\\a.JPG"));
	JButton lef = new JButton(new ImageIcon("E:\\PSD\\JAVA\\EE\\Hotel Management System\\Images\\c.JPG"));
	JButton rit = new JButton(new ImageIcon("E:\\PSD\\JAVA\\EE\\Hotel Management System\\Images\\d.JPG"));

	
	login(){
	super("LAP INTERNATIONAL HOTEL");
	one.setLayout(null);
	add(one);
	setExtendedState(JFrame.MAXIMIZED_BOTH);
	setVisible(true);
	setResizable(true);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	
	elements();
	}
	
	void elements() {
		
		one.setBackground(Color.BLACK);
		
		one.add(banr);
		banr.setBounds(2, 0, 1360, 220);
		
		one.add(logId);
		logId.setForeground(Color.WHITE);
		logId.setBounds(500, 350, 80, 20);
		one.add(lId);
		lId.setBounds(600, 350, 250, 20);
		
		one.add(pass);
		pass.setForeground(Color.WHITE);
		pass.setBounds(500, 400, 80, 20);
		one.add(pas);
		pas.setBounds(600, 400, 250, 20);
		
		one.add(login);
		login.addActionListener(this);
		login.setBounds(530, 450, 70, 20);
		
		one.add(rePas);
		rePas.addActionListener(this);
		rePas.setBounds(650, 450, 170, 20);
		
		one.add(bot);
		bot.setBounds(0, 550, 1370, 170);
		
		one.add(lef);
		lef.setBounds(0,220,176,330);
		
		one.add(rit);
		rit.setBounds(1194,220,176,330);
		

		String lID;
		String lPA;
		
		Connection conn = null;
		Statement stmt = null;
		
		try
		{
			
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql;
			
			sql = "SELECT LoginID,LoginPas FROM login";
			ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
	
				lID = rs.getString("LoginID");
				lPA = rs.getString("LoginPas");
				
				ID = String.valueOf(lID);
				Pas = lPA;
				}
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				if(conn != null) {
					conn.close();
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		
		new login();

	}
	

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == login) {
	         
            if (lId.getText().equals("") || pas.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Please enter valid ID and PASSWORD", "Warning!!!", JOptionPane.ERROR_MESSAGE);

            }else if(!lId.getText().equals(ID)) {
            	
            	 JOptionPane.showMessageDialog(null, "Wrong ID, Try different ID.", "Warning!!!", JOptionPane.ERROR_MESSAGE);
            	 
            }else if(!pas.getText().equals(Pas)) {
            	
            	JOptionPane.showMessageDialog(null, "Wrong Password, Try different password.", "Warning!!!", JOptionPane.ERROR_MESSAGE);
            	
            }
            
            if(lId.getText().equals(ID) && pas.getText().equals(Pas))
            {
                new mm();
                dispose();
            }

        }else if (e.getSource() == rePas){
    	 
        	dispose();
        	new reset();
    	   
       }
       
	}

}