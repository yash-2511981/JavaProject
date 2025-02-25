package Practice;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.invoke.StringConcatFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class InsertManager extends JFrame {
	JLabel label1, label2, label3;
	JTextField field1, field2;
	JButton btn1, btn2;
	JTable table;
	JScrollPane pane;
	ArrayList<ArrayList<String>> data = new ArrayList<>();

	public InsertManager() {
		setLayout(new FlowLayout());

		label1 = new JLabel("Enter a Name:-");
		label2 = new JLabel("Enter a salary:-");
		label3 = new JLabel("");

		field1 = new JTextField(35);
		field2 = new JTextField(35);

		btn1 = new JButton("Inserted");
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!field1.getText().isEmpty() && !field2.getText().isEmpty()) {
					insertManager(field1.getText(), Float.parseFloat(field2.getText()));
					field1.setText("");
					field2.setText("");
				} else {
					label3.setText("<html><br>Enter valid name and salary</html>");
				}
			}
		});
		btn2 = new JButton("Reset");
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				field1.setText("");
				field2.setText("");
				data.clear();
			}
		});

		table = new JTable();
		pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(400, 100));

		add(label1);
		add(field1);
		add(label2);
		add(field2);
		add(btn1);
		add(btn2);
		add(pane);
		add(label3);

		setVisible(true);
		setSize(500, 500);
	}

	void insertManager(String name, float salary) {
		PreparedStatement st;
		try {
			st = Conn.getCon().prepareStatement("insert into managers_info(name,salary) values(?,?)");
			st.setString(1, name);
			st.setFloat(2, salary);
			st.executeUpdate();
			st.close();
			showInsertedInfo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	void showInsertedInfo() {
		try {
			ResultSet rs = Conn.getCon().prepareStatement("select * from managers_info order by id desc limit 1")
					.executeQuery();
			while (rs.next()) {
				ArrayList<String> row = new ArrayList();
				row.add(String.valueOf(rs.getInt(1)));
				row.add(rs.getString(2));
				row.add(String.valueOf(rs.getFloat(3)));
				data.add(row);
			}

			if (data.isEmpty()) {
				return;
			} else {
				table();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// showing inserted managers
	void table() {
		String[] column = { "Id", "Name", "Salary" };

		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(column);

		for (ArrayList<String> row : data) {
			model.addRow(row.toArray());
		}

		table.setModel(model);
	}
}
