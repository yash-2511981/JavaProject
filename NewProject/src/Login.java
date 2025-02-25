import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class Login extends JFrame {
	JLabel label1,label2;
	JTextField field1,field2;
	JButton button1,button2;
	
	public Login() {
		
		setLayout(new FlowLayout());
		
		label1 = new JLabel("Username:");
		label2 = new JLabel("Password:");
		
		field1 = new JTextField(30);
		field2 = new JTextField(30);
		
		button1 = new JButton("Submit");
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(authenticateUser(field1.getText(), field2.getText())) {
					System.out.println("you are logged in");
					new Home();
				}else {
					System.out.println("invalid creadentials");
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
		
		
		//adding components in layout
		add(label1);
		add(field1);
		add(label2);
		add(field2);
		add(button1);
		add(button2);
		
		setVisible(true);
		setSize(400,400);
	}
	
	//method for authenticating user
	boolean authenticateUser(String username,String password) {
		ResultSet rs;
		boolean authentication =false;
		try {
			rs = DbCon.getCon().prepareStatement("select * from User").executeQuery();
			while (rs.next()) {
				String name=rs.getString(2);
				String pass=rs.getString(3);
				
				if(field1.getText().equals(name) && field2.getText().equals(pass)) {
					authentication = true;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return authentication;
	}
	
	public static void main(String[] args) {
		new Login();
	}
}	
