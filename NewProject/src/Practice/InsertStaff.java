package Practice;

import java.awt.FlowLayout;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class InsertStaff extends JFrame {
	JLabel label1, label2, label3, label4;
	JTextField field1, field2, field3;
	JButton btn1, btn2;
	JScrollPane pane;

	public InsertStaff() {
		setTitle("Employee Form");
		setLayout(new GridBagLayout());

		// Create GridBagConstraints object for layout management
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10); // Adds padding between components

		// Row 1: Name
		label1 = new JLabel("Name:");
		gbc.gridx = 0; // Column
		gbc.gridy = 0; // Row
		gbc.anchor = GridBagConstraints.CENTER; // Align label to the right
		add(label1, gbc);

		field1 = new JTextField(20);
		gbc.gridx = 1; // Column
		gbc.gridy = 0; // Row
		gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
		add(field1, gbc);

		// Row 2: Designation
		label2 = new JLabel("Designation:");
		gbc.gridx = 0; // Column
		gbc.gridy = 1; // Row
		gbc.anchor = GridBagConstraints.CENTER; // Align label to the right
		add(label2, gbc);

		field2 = new JTextField(20);
		gbc.gridx = 1; // Column
		gbc.gridy = 1; // Row
		gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
		add(field2, gbc);

		// Row 3: Salary
		label3 = new JLabel("Salary:");
		gbc.gridx = 0; // Column
		gbc.gridy = 2; // Row
		gbc.anchor = GridBagConstraints.CENTER; // Align label to the right
		add(label3, gbc);

		field3 = new JTextField(20);
		gbc.gridx = 1; // Column
		gbc.gridy = 2; // Row
		gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
		add(field3, gbc);

		// Buttons Row
		btn1 = new JButton("Insert");
		gbc.gridx = 0; // Column
		gbc.gridy = 3; // Row
		gbc.anchor = GridBagConstraints.CENTER; // Center the button
		add(btn1, gbc);

		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (field1.getText().equals("") || field2.getText().equals("") || field3.getText().equals("")) {
					label4.setText("please fill all the fields");
				}else {
					insertStaff(field1.getText(),Double.parseDouble(field3.getText()),field2.getText());
				}
			}
		});

		btn2 = new JButton("Reset");
		gbc.gridx = 1; // Column
		gbc.gridy = 3; // Row
		gbc.anchor = GridBagConstraints.CENTER; // Center the button
		add(btn2, gbc);
		
		btn2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				field1.setText("");
				field2.setText("");
				field3.setText("");
				
			}
		});

		// Empty label to show some space below buttons
		label4 = new JLabel("");
		gbc.gridx = 0; // Column
		gbc.gridy = 4; // Row
		gbc.gridwidth = 2; // Span both columns
		add(label4, gbc);

		setVisible(true);
		setSize(400, 300);
	}

	void insertStaff(String name, double sal, String desg) {
		PreparedStatement st = null;
		try {
			st = Conn.getCon().prepareStatement("insert into Staff_details(name,salary,designation) values (?,?,?)");
			st.setString(1, name);
			st.setDouble(2, sal);
			st.setString(3, desg);
			st.executeUpdate();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new InsertStaff();
	}
}
