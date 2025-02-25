package Practice;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Home extends JFrame {
	JMenuBar bar;
	JMenu menu1,menu2,menu3;
	JMenuItem item1,item2,item3,item4,item5,item6;
	
	public Home() {
		
		setLayout(new FlowLayout());
		
		bar = new JMenuBar();
		setBounds(0, 0, 300, 30);
		
		menu1 = new JMenu("Employee");
		menu2 = new JMenu("Manager");
		menu3 = new JMenu("Staff");
		
		item1 = new JMenuItem("InsertEmp");
		item1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new InsertEmp();
			}
		});
		
		item2 = new JMenuItem("UpdateEmp");
		item2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new UpdateEmp();
				
			}
		});
		
		item3 = new JMenuItem("DeleteEmp");
		item3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeleteEmp();
				
			}
		});
		
		item4 = new JMenuItem("ShowEmp");
		item4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ShowEmp();
			}
		});
		
		item5 = new JMenuItem("InserManager");
		item5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new InsertManager();
			}
		});
		
		item6 = new JMenuItem("InsertStaff");
		
		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		menu1.add(item4);
		
		menu2.add(item5);
		
		menu3.add(item6);
		
		bar.add(menu1);
		bar.add(menu2);
		bar.add(menu3);
		
		add(bar);
		
		setVisible(true);
		setSize(400,400);
	}
}
