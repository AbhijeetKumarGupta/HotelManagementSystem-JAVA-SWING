import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class reset extends JFrame implements ActionListener {
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/hms";
	
	static final String USER = "root";
	static final String PASS = "59631234";
	
	Connection conn = null;
	Statement stmt = null;
	
	JPanel oOne = new JPanel();
	JLabel top = new JLabel("Enter ID and Passwords :-");
	JLabel nId = new JLabel("NEW ID                           :");
	JLabel nPa = new JLabel("NEW PASSWORD        :");
	JLabel mPa = new JLabel("MASTER PASSWORD :");
	JTextField nID = new JTextField(20);
	JTextField nPA = new JTextField(10);
	JPasswordField mPA = new JPasswordField(10);
	JButton ok = new JButton("OK");
	
	String ID;
	String Pas;
	
	reset(){
		
		super("Password Reset");
		add(oOne);
		oOne.setLayout(null);
		setLocation(523,300);
		setSize(350,220);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		element();
		
		
	}
	
	void element() {
		
		oOne.setBackground(Color.WHITE);
		
		oOne.add(top);
		top.setBounds(10, 10, 150, 20);
		
		oOne.add(nId);
		nId.setBounds(30, 60, 140, 20);
		
		oOne.add(nPa);
		nPa.setBounds(30, 80, 140, 20);
		
		oOne.add(mPa);
		mPa.setBounds(30, 100, 140, 20);
		
		oOne.add(nID);
		nID.setBounds(180, 60, 130, 20);
		
		oOne.add(nPA);
		nPA.setBounds(180, 80, 130, 20);
		
		oOne.add(mPA);
		mPA.setBounds(180, 100, 130, 20);
		
		oOne.add(ok);
		ok.addActionListener(this);
		ok.setBounds(130, 150, 80, 20);
		
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ok) {
			if(nID.getText().equals("")||nPA.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Please enter valid ID and PASSWORD", "Warning!!!", JOptionPane.ERROR_MESSAGE);
			}
			
			if(!nID.getText().equals("") && !nPA.getText().equals("")) {
				if(mPA.getText().equals(PASS)) {

					ID = nID.getText();
					Pas = nPA.getText();
					
					try
					{
						Class.forName(JDBC_DRIVER);
						conn = DriverManager.getConnection(DB_URL,USER,PASS);
						stmt = conn.createStatement();
						
						String sql;
						
						sql = "UPDATE login SET LoginID = "+"'"+ID+"'"+", LoginPas = "+"'"+Pas+"'"+" WHERE loginIndex=1;";
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
					
					dispose();
					new login();
					
				}else {
					
					JOptionPane.showMessageDialog(null, "Please enter correct MASTER PASSWORD", "Warning!!!", JOptionPane.ERROR_MESSAGE);
				
				}
			}
		}

		
	}

}
