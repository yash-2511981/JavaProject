package Practice;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InsertEmp extends JFrame {
	JLabel label1,label2;
	JTextField field1,field2;
	JButton button1,button2;
	
	public InsertEmp() {
		setLayout(new FlowLayout());
		
		label1 = new JLabel("Enter Id:");
		label2 = new JLabel("Enter Name:");
		
		field1 = new JTextField(30);
		field2 = new JTextField(30);
		
		button1 = new JButton("Submit");
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = field1.getText();
				String name = field2.getText();
				try {
					PreparedStatement st = Conn.getCon().prepareStatement("insert into Emp values (?,?)");
					st.setString(1, id);
					st.setString(2, name);
					st.executeUpdate();
					st.close();
					System.out.println("inserted successfully");
					field1.setText("");
					field2.setText("");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		button2 = new JButton("Reset");
		button2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				field1.setText("");
				field2.setText("");	
			}
		});
		
		add(label1);
		add(field1);
		add(label2);
		add(field2);
		add(button1);
		add(button2);
		
		setVisible(true);
		setSize(400,400);
		
	}
}
