package Practice;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ShowEmp extends JFrame {
	JTable table;
	JScrollPane pane;
	JTextField field;
	JButton btn;
	JLabel label1;
	ArrayList<ArrayList<String>> data = new ArrayList();

	public ShowEmp() {
		setLayout(new FlowLayout());
		
		table = new JTable();
		pane = new JScrollPane(table);
		field = new JTextField(30);
		label1 = new JLabel("");

		btn = new JButton("Show");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//clearing all the previous data
				data.clear();
				
				String id = field.getText().trim();
				
				//condition for user id is present
				if(!id.isEmpty()) {
					fetchEmp(id);
				}else {
					fetchAllUsers();
				}
				
				//updating table
				updateTable();
			}
		});
		
		fetchAllUsers();
		updateTable();
		
		add(pane);
		add(field);
		add(btn);
		add(label1);
		
		setVisible(true);
		setSize(700,700);
	}

	// method for fetching the data from data base
	void fetchAllUsers() {
		try {
			ResultSet rs = Conn.getCon().prepareStatement("select * from Emp").executeQuery();
			while (rs.next()) {
				ArrayList<String> row = new ArrayList();
				row.add(String.valueOf(rs.getInt("id")));
				row.add(rs.getString("name"));
				data.add(row);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	//Method for fetching data form user id
	void fetchEmp(String id) {
		ResultSet rs = null;
		try {
			PreparedStatement st = Conn.getCon().prepareStatement("select * from Emp where id=?");
			st.setString(1, id);
			rs = st.executeQuery();
			while (rs.next()) {
				ArrayList<String> row = new ArrayList();
				row.add(String.valueOf(rs.getInt("id")));
				row.add(rs.getString("name"));
				data.add(row);
			}

			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//method for displaying data into table
	void updateTable() {
		if (data.isEmpty()) {
			label1.setText("There is no Employee data");
			return;
		}
		
		String[] column = {"ID","Name"};
		
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(column);
		
		for(ArrayList<String> row:data) {
			model.addRow(row.toArray());
		}
		
		table.setModel(model);
	}
}
