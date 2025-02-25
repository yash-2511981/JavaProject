package Practice;

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

public class DeleteEmp extends JFrame {
	JLabel label1,label2,label3;
	JTextField field1,field2;
	JButton button1,button2;
	
	public DeleteEmp(){
setLayout(new FlowLayout());
		
		label1 = new JLabel("Enter Id:");
		label2 = new JLabel("Enter Name:");
		label3 = new JLabel("");
		
		field1 = new JTextField(30);
		field2 = new JTextField(30);
		
		button1 = new JButton("Delete");
		button1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = field1.getText();
				String name = field2.getText();
				try {
					
					if(validateUser(id, name)){
					PreparedStatement st = Conn.getCon().prepareStatement("delete from emp where id=?");
					st.setString(1, id);
					st.executeUpdate();
					st.close();
					System.out.println("deleted successfully");
					field1.setText("");
					field2.setText("");
					}else {
						label3.setText("id and username is not matching");
					}
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
		add(label3);
		
		setVisible(true);
		setSize(400,400);
		
	}
	
	boolean validateUser(String id,String username) {
		boolean validation = false;
		try {
			PreparedStatement st= Conn.getCon().prepareStatement("select name from Emp where id=?");
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				String name = rs.getString(1);
				if(username.equals(name)) validation = true ;
			}
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return validation;
	}
	
}
