import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Home extends JFrame{
	JMenuBar bar;
	JMenu menu1,menu2,menu3;
	JMenuItem item1,item2,item3,item4,item5,item6;

	public Home() {
		setLayout(new FlowLayout());
		
		bar=new JMenuBar();
		setBounds(0,0,300,30);
		
		
		menu1=new JMenu("Employees");
		menu2=new JMenu("Manger");
		menu3=new JMenu("Staff");
		
		item1=new JMenuItem("EmpInsert");
		item2=new JMenuItem("EmpUpdate");
		item3=new JMenuItem("EmpDelete");
		item4=new JMenuItem("EmpShow");
		item5=new JMenuItem("ManagerInsert");
		item6=new JMenuItem("StaffInsert");
		
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
	
	public static void main(String[] args) {
		new Home();
	}
}
