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
import javax.swing.JList;
import javax.swing.JPanel;

import com.mysql.cj.util.StringUtils;

@SuppressWarnings("serial")
public class purList  extends JFrame implements ActionListener {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/hms";
	
	static final String USER = "root";
	static final String PASS = "59631234";
	
	Connection conn = null;
	Statement stmt = null;
	
	JPanel eight = new JPanel();
	JPanel aaa = new JPanel();
	JPanel bbb = new JPanel();
	JList<String> bb = new JList<String>();
	JButton banAc = new JButton(new ImageIcon("E:\\PSD\\JAVA\\EE\\Hotel Management System\\Images\\purList.JPG"));
	JButton back = new JButton("BACK");
	
	
	purList(){
		
		super("LAP INTERNATIONAL HOTEL");
		add(eight, BorderLayout.NORTH);
		aaa.setLayout(null);
		add(aaa, BorderLayout.SOUTH);
		bbb.setLayout(new BorderLayout());
		add(bbb, BorderLayout.CENTER);
		Dimension a = getPreferredSize();
		a.height = 130;
		aaa.setPreferredSize(a);
		
		bbb.setBorder(BorderFactory.createTitledBorder("Purchase List"));
		
		try
		{
			
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
			stmt = conn.createStatement();
			
			String sql;
			
			sql = "SELECT id,Product,PricePerUnit,NumOfUnit,TotalSpent FROM purchase";
			ResultSet rs = stmt.executeQuery(sql);
		
		DefaultListModel<String> bm = new DefaultListModel<String>();
		
		while(rs.next()) {
			
			int id = rs.getInt("id");
			String na = rs.getString("Product");
			int ppU = Integer.valueOf(rs.getString("PricePerUnit"));
			int nU = Integer.valueOf(rs.getString("NumOfUnit"));
			int tot = Integer.valueOf(rs.getString("TotalSpent"));
			int NA = 30,PPU = 10,NU = 10;
			int NAA = NA - na.length();
			
			if(ppU<100) {
				PPU = 11;
			}else if(ppU<10) {
				PPU = 12;
			}
			
			if(nU<100) {
				NU = 11;
			}else if(nU<10) {
				NU = 12;
			}
			
			bm.addElement(StringUtils.padString(" ",110)+"["+id+"] Product Name - "+na+StringUtils.padString(" ",NAA)+
					","+StringUtils.padString(" ",10)+"     Price Per Unit - "+ppU+StringUtils.padString(" ",PPU)+
					","+StringUtils.padString(" ",10)+"      Number of Unit - "+nU+StringUtils.padString(" ",NU)+
					","+StringUtils.padString(" ",10)+"     Total Spent - "+tot+".");
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
		
		eight.add(banAc);
		banAc.setBounds(20, 10, 1320, 220);
		
		bbb.setBackground(Color.WHITE);
		
		bbb.add(bb, BorderLayout.NORTH);
		bb.setBounds(0, 0, 600, 200);
		
		aaa.add(back);
		back.addActionListener(this);
		back.setBounds(625, 60, 100, 20);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == back) {
			
			new purchase();
			dispose();
			
		}

	}

}