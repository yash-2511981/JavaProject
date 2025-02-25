package Practice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Show extends JFrame{
		JTable table;
		JScrollPane pane;
		ArrayList<ArrayList<Object>> data =	new ArrayList();
		String[] col = {"ID","Name"};
		
		public Show() {
			try {
				ResultSet rs = Conn.getCon().prepareStatement("select * from emp").executeQuery();
				while(rs.next()) {
					ArrayList row =	new ArrayList();
					row.add(rs.getInt(1));
					row.add(rs.getString(2));
					data.add(row);
				}
				
				Object[][] rows = new Object[data.size()][data.get(0).size()];
				
				for (int i = 0; i < rows.length; i++) {
					for (int j = 0; j < rows[0].length; j++) {
						rows[i][j] = data.get(i).get(j);
					}
				}
				
				table = new JTable(rows,col);
				pane = new JScrollPane(table);
				add(pane);
				setVisible(true);
				setSize(500, 500);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public static void main(String[] args) {
			new Show();
		}
}
