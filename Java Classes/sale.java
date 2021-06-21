import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class sale extends JFrame {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/hms";
	
	static final String USER = "root";
	static final String PASS = "59631234";
	
	Connection conn = null;
	Statement stmt = null;
	
	JButton banSa = new JButton(new ImageIcon("E:\\PSD\\JAVA\\EE\\Hotel Management System\\Images\\BanSa.JPG"));
	JLabel proId = new JLabel("PRODUCT ID  : ");
	JLabel noUn = new JLabel("NO OF UNITS : ");
	JTextField proI = new JTextField(10);
	JTextField noU = new JTextField(10);
	JButton cal = new JButton("COMPUTE");
	JButton back = new JButton("BACK");
	
	int max;

	JTextArea test;
	
	JList<String> pl;
	
	West west;
	Cen east;
	
	sale(){
		
		super("LAP INTERNATIONAL HOTEL");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		west = new West();
		east = new Cen();
		
		elements();
		
	}
	
	void elements() {
		
		add(banSa, BorderLayout.NORTH);
		add(east, BorderLayout.CENTER);
		add(west, BorderLayout.WEST);
		
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
	
	public class West extends JPanel implements ActionListener{
	
		West(){
			
			Dimension a = getPreferredSize();
			a.width = 275;
			setPreferredSize(a);
			
			setBorder(BorderFactory.createTitledBorder("Product Details"));
			
			setLayout(new GridBagLayout());
			
			GridBagConstraints gc = new GridBagConstraints();
			
			gc.gridx = 0;
			gc.gridy = 0;
			gc.weightx = 1;
			gc.weighty = 0.1;
			gc.fill = GridBagConstraints.NONE;
			
			gc.anchor = GridBagConstraints.LINE_END;
			
			add(proId, gc);
			
			gc.gridx = 1;
			gc.gridy = 0;
			
			gc.anchor = GridBagConstraints.LINE_START;
			add(proI, gc);
			
			gc.gridx = 0;
			gc.gridy = 1;
			
			gc.anchor = GridBagConstraints.LINE_END;
			add(noUn, gc);
			
			gc.gridx = 1;
			gc.gridy = 1;
			
			gc.anchor = GridBagConstraints.LINE_START;
			add(noU, gc);
		
			gc.gridx = 1;
			gc.gridy = 2;
			
			gc.anchor = GridBagConstraints.LINE_START;
			add(cal, gc);
			cal.addActionListener(this);
			
			gc.gridx = 1;
			gc.gridy = 3;
			
			pl = new JList<String>();
			add(pl, gc);
			
			try
			{
				
				Class.forName(JDBC_DRIVER);
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
				stmt = conn.createStatement();
				
				String sql;
				
				sql = "SELECT ID,DishName FROM menu";
				ResultSet rs = stmt.executeQuery(sql);
				
				DefaultListModel<String> lm = new DefaultListModel<String>();
				lm.addElement("_______________");
				lm.addElement(" ID     ITEM  ");
				lm.addElement("--------------------------");
				
				while(rs.next()) {
					
					int id = rs.getInt("ID");
					String na = rs.getString("DishName");
					
					lm.addElement("["+id+"]   "+na+".");
					
					}
				pl.setModel(lm);
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
			
			gc.weightx = 1;
			gc.weighty = 1;
			gc.gridx = 1;
			gc.gridy = 4;
			
			gc.anchor = GridBagConstraints.LAST_LINE_START;
			add(back, gc);
			back.addActionListener(this);
	
		}

		@SuppressWarnings("unused")
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == cal) {
				
				try
				{
					Class.forName(JDBC_DRIVER);
					conn = DriverManager.getConnection(DB_URL,USER,PASS);
					stmt = conn.createStatement();
					
					String sql;
					
					sql = "SELECT DishName,PriceFull,PriceHalf FROM menu WHERE ID="+Integer.valueOf(proI.getText());
					ResultSet rs = stmt.executeQuery(sql);
					while(rs.next()) {
				
						String nm = rs.getString("DishName");
						int pF = rs.getInt("PriceFull");
						int pH = rs.getInt("PriceHalf");
							
						int tot = pF*Integer.valueOf(noU.getText());
				
						test.append("Connecting to database......\n");
						test.append("Creating statement.....\n\n");
						test.append("   Please wait.......\n");
						test.append("   Retrieving content at specified ID......\n");
						test.append("   Extraction complete....\n\n");
						test.append("   |  As the customer bought "+noU.getText()+" "+nm+", so his bill is "+tot+" Rs  |\n\n");
					}
					
					sql ="SELECT ID FROM menu";
					rs = stmt.executeQuery(sql);
					
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
				
				int in = Integer.valueOf(proI.getText());
				if(in>max) {
					JOptionPane.showMessageDialog(null, "NOT A VALID PRODUCT ID..!", "Warning!!!", JOptionPane.ERROR_MESSAGE);
				}
				
			}else if(e.getSource() == back) {
				
				new mm();
				dispose();
				
			}

		}
		
	}
	
	
	public class Cen extends JPanel {
		
		Cen(){
			
			setBorder(BorderFactory.createEtchedBorder());
			setLayout(new BorderLayout());
			test = new JTextArea();
			add(new JScrollPane(test), BorderLayout.CENTER);		
		}
		
	}
	
}