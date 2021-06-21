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
public class prodPr extends JFrame{
	
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/hms";
	
	static final String USER = "root";
	static final String PASS = "59631234";
	
	Connection conn = null;
	Statement stmt = null;
	
	int pF, max;
	
	JButton banPp = new JButton(new ImageIcon("E:\\PSD\\JAVA\\EE\\Hotel Management System\\Images\\BanPp.JPG"));
	JLabel prodId = new JLabel("Product ID :");
	JLabel newPr = new JLabel("New Price :");
	JTextField prodI = new JTextField(10);
	JTextField newP = new JTextField(10);
	JButton save = new JButton("SAVE");
	JButton back = new JButton("BACK");
	
	JList<String> pl;
	
	Weest weest;
	ceen eaast;
	
	prodPr(){
		
		super("LAP INTERNATIONAL HOTEL");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		weest = new Weest();
		eaast = new ceen();
		
		elements();
		
	}
	
	void elements() {
		
		add(banPp, BorderLayout.NORTH);
		add(weest, BorderLayout.WEST);
		add(eaast, BorderLayout.CENTER);
		
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
	
	public class Weest extends JPanel implements ActionListener{
		
		Weest(){
			
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
			
			add(prodId, gc);
			
			gc.gridx = 1;
			gc.gridy = 0;
			
			gc.anchor = GridBagConstraints.LINE_START;
			add(prodI, gc);
			
			gc.gridx = 0;
			gc.gridy = 1;
			
			gc.anchor = GridBagConstraints.LINE_END;
			add(newPr, gc);
			
			gc.gridx = 1;
			gc.gridy = 1;
			
			gc.anchor = GridBagConstraints.LINE_START;
			add(newP, gc);
		
			gc.gridx = 1;
			gc.gridy = 2;
			
			gc.anchor = GridBagConstraints.LINE_START;
			add(save, gc);
			save.addActionListener(this);
			
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
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource() == save) {
				
				if(prodI.getText().equals("") || newP.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "EMPTY FIELD......!!!", "Warning!!!", JOptionPane.ERROR_MESSAGE);
				}
				
				int in = Integer.valueOf(prodI.getText());
				if(in>max) {
					JOptionPane.showMessageDialog(null, "NOT A VALID PRODUCT ID..!", "Warning!!!", JOptionPane.ERROR_MESSAGE);
				}else {
					
					try
					{
						Class.forName(JDBC_DRIVER);
						conn = DriverManager.getConnection(DB_URL,USER,PASS);
						stmt = conn.createStatement();
						String sql;
						
						pF = 2*Integer.valueOf(newP.getText());
						
						sql = "UPDATE menu SET PriceFull = '"+pF+"', PriceHalf = '"+Integer.valueOf(newP.getText())+"' WHERE ID="+Integer.valueOf(prodI.getText())+";";
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
					eaast.test.append("////Extracting details of the given ID..................................................."+"\n"+"///New price of product ID "+prodI.getText()+" is now set to "+newP.getText()+" Rs................."+"\n"+"//Completing process......................................"+"\n"+"/Process sucessfully executed.........\n\n");
				}
				
			}else if(e.getSource() == back) {
				
				new mm();
				dispose();
				
			}

		}
		
	}
	
	public class ceen extends JPanel {

		JTextArea test;
		
		ceen(){
			
			setBorder(BorderFactory.createEtchedBorder());
			setLayout(new BorderLayout()); 
			test = new JTextArea();
			add(new JScrollPane(test), BorderLayout.CENTER);		
		}
		
	}
}