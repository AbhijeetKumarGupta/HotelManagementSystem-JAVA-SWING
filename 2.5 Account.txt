import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class acc extends JFrame implements ActionListener {

	JPanel eight = new JPanel();
	JButton banAc = new JButton(new ImageIcon("E:\\PSD\\JAVA\\EE\\Hotel Management System\\Images\\BanAc.JPG"));
	JButton gra = new JButton(new ImageIcon("E:\\PSD\\JAVA\\EE\\Hotel Management System\\Images\\lc.JPG"));
	JButton back = new JButton("BACK");
	
	
	acc(){
		
		super("LAP INTERNATIONAL HOTEL");
		add(eight);
		eight.setLayout(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		elements();
		
	}
	
	void elements() {
		
		eight.setBackground(Color.BLACK);
		
		eight.add(banAc);
		banAc.setBounds(20, 10, 1320, 220);
		
		eight.add(gra);
		gra.setBounds(330, 240, 700, 410);
		
		eight.add(back);
		back.addActionListener(this);
		back.setBounds(620, 670, 100, 20);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == back) {
			
			new mm();
			dispose();
			
		}

	}
	
}
