import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class prLi extends JFrame implements ActionListener {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/hms";
	
	static final String USER = "root";
	static final String PASS = "59631234";
	
	Connection conn = null;
	Statement stmt = null;
	
	JPanel five = new JPanel();
	JPanel aaa = new JPanel();
	JPanel bbb = new JPanel();
	JButton banAv = new JButton(new ImageIcon("E:\\PSD\\JAVA\\EE\\Hotel Management System\\Images\\BanPl.JPG"));
	JList<String> bb = new JList<String>();
	JLabel addPr = new JLabel("[^] ADD NEW PRODUCT [^]");
	JLabel rem = new JLabel("[>]REMOVE PRODUCT[<] ID : ");
	JTextField re = new JTextField(10);
	JButton remo = new JButton("REMOVE");
	JButton add = new JButton("ENTER");
	JButton back = new JButton("BACK");
	
	int max;
	
	prLi(){
		
		super("LAP INTERNATIONAL HOTEL");
		add(five, BorderLayout.NORTH);
		aaa.setLayout(null);
		add(aaa, BorderLayout.SOUTH);
		bbb.setLayout(new BorderLayout());
		add(bbb, BorderLayout.CENTER);
		Dimension a = getPreferredSize();
		a.height = 130;
		aaa.setPreferredSize(a);
		
		bbb.setBorder(BorderFactory.createTitledBorder("Product List"));
		
		try
		{
			
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql;
			
			sql = "SELECT ID,DishName,PriceFull,PriceHalf FROM menu";
			ResultSet rs = stmt.executeQuery(sql);
		
		DefaultListModel<String> bm = new DefaultListModel<String>();
		while(rs.next()) {
			
			int id = rs.getInt("ID");
			String na = rs.getString("DishName");
			int pF = Integer.valueOf(rs.getString("PriceFull"));
			int pH = Integer.valueOf(rs.getString("PriceHalf"));
			
			bm.addElement("                                                                                                                                               ["+id+"] Dish Name - "+na+" ,             Price Full Plate - "+pF+" ,             Price Half Plate - "+pH+".");
		
			}
		
		bb.setModel(bm);
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
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		elements();
		
	}
	
	void elements() {
		
		bbb.setBackground(Color.WHITE);
		
		five.add(banAv);
		banAv.setBounds(20, 10, 1320, 220);
		
		bbb.add(bb, BorderLayout.NORTH);
		bb.setBounds(0, 0, 600, 200);
		
		aaa.add(addPr);
		addPr.setBounds(600, 10, 150, 20);
		aaa.add(add);
		add.addActionListener(this);
		add.setBounds(625, 30, 100, 20);
		
		aaa.add(rem);
		rem.setBounds(530, 65, 200, 20);
		aaa.add(re);
		re.setBounds(690, 65, 50, 20);
		aaa.add(remo);
		remo.addActionListener(this);
		remo.setBounds(740, 65, 100, 20);
		
		aaa.add(back);
		back.addActionListener(this);
		back.setBounds(625, 100, 100, 20);
		
		try
		{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			String sql;
			
			sql ="SELECT ID FROM menu";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int m = rs.getInt("ID");
				max = m;
			}
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
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == add) {
			
			new addPr();
			dispose();
			
		}else if(e.getSource() == back) {
			
			new mm();
			dispose();
			
		}else if(e.getSource() == remo){
			if(re.getText().equals("") || Integer.valueOf(re.getText())> max) {
				JOptionPane.showMessageDialog(null, "NOT A VALID PRODUCT ID..!", "Warning!!!", JOptionPane.ERROR_MESSAGE);
			}else {
			try
			{
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
				stmt = conn.createStatement();
				String sql, sqll;
				
				sql = "DELETE FROM menu WHERE ID="+Integer.valueOf(re.getText())+";";
				stmt.executeUpdate(sql);
				
				sqll = "ALTER TABLE menu AUTO_INCREMENT ="+Integer.valueOf(re.getText())+";";
				stmt.executeUpdate(sqll);
				
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
			
			new prLi();
			dispose();
			
			}
			
			
		}

	}


}