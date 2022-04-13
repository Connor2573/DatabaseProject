import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import DatabaseBridge.Bridge;
import media.MediaItem;

public class Core {
	
	public static OrderCollection order;
	
	public static void main(String[] args) {
		Connection conn = Bridge.initializeDB();
		order = new OrderCollection();
		JFrame frame = new JFrame("Database Interface");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setSize(1200,1600);
		   
		JPanel main = new JPanel();
		JPanel searchPanel = new JPanel();
		JPanel addPanel = new JPanel();
		   
		JTextField search = new JTextField("Entry", 8);
		JButton finalS = new JButton("Search");
		JButton update = new JButton("Show all media");
		JButton addButton = new JButton("Add New Media");
		JButton orderButton = new JButton("Order Media");
		JButton viewOrdersButton = new JButton("View Orders");
		JButton employeeUpdate = new JButton("Show employees");
		JButton memberUpdate = new JButton("Show members");
		JButton edit = new JButton("Edit selected Media");
    
		addPanel.add(addButton);
		addPanel.add(orderButton);
		addPanel.add(viewOrdersButton);
		addPanel.add(update);
		addPanel.add(edit);
		addPanel.add(employeeUpdate);
		addPanel.add(memberUpdate);
		
		searchPanel.add(search);
		searchPanel.add(finalS);
		   
		main.add(searchPanel);
		main.add(addPanel);
		   
		   addButton.addActionListener(new ActionListener() {
			   @Override
			   public void actionPerformed(ActionEvent e) {
				   frame.setVisible(false);
				   
				   AddMenu am = new AddMenu(frame, conn);
			   }
		   });
		   orderButton.addActionListener(new ActionListener() {
			   @Override
			   public void actionPerformed(ActionEvent e) {
				   frame.setVisible(false);
				   
				   OrderMenu om = new OrderMenu(frame, conn);
			   }
		   });
		   viewOrdersButton.addActionListener(new ActionListener() {
			   @Override
			   public void actionPerformed(ActionEvent e) {
				   frame.setVisible(false);
				   
				   OrdersTable ot = new OrdersTable(frame);
			   }
		   });
		   
		String[] columnNames = {"MediaID", "Name", "Year", "Type", "Price", "Location", "Status", "Certificate"};
		
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		for(String str: columnNames) {
			model.addColumn(str);
		}
		JScrollPane tablePanel = new JScrollPane(table);
		tablePanel.setBorder(BorderFactory.createTitledBorder(
			      BorderFactory.createEtchedBorder(), "Media Items", TitledBorder.LEFT,
			      TitledBorder.TOP));
		main.add(tablePanel);
		
		String[] employeeCols = {"EmployeeID", "PersonID", "Email"};
		
		DefaultTableModel empModel = new DefaultTableModel();
		JTable empTable = new JTable(empModel);
		for(String str: employeeCols) {
			empModel.addColumn(str);
		}
		JScrollPane empTablePanel = new JScrollPane(empTable);
		empTablePanel.setBorder(BorderFactory.createTitledBorder(
			      BorderFactory.createEtchedBorder(), "Employees", TitledBorder.LEFT,
			      TitledBorder.TOP));
		main.add(empTablePanel);
		
		String[] memberCols = {"MemberID", "PersonID", "Email", "Address", "PhoneNumber"};
		
		DefaultTableModel memberModel = new DefaultTableModel();
		JTable memberTable = new JTable(memberModel);
		for(String str: memberCols) {
			memberModel.addColumn(str);
		}
		JScrollPane memberTablePanel = new JScrollPane(memberTable);
		memberTablePanel.setBorder(BorderFactory.createTitledBorder(
			      BorderFactory.createEtchedBorder(), "Members", TitledBorder.LEFT,
			      TitledBorder.TOP));
		main.add(memberTablePanel);
		
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String[]> items = Bridge.GetAllMediaItems(conn);
				model.setRowCount(0);
				for(String[] str: items) {
					model.addRow(str);
				}
			}
		});
		
		employeeUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String[]> items = Bridge.GetEmployeeItems(conn);
				empModel.setRowCount(0);
				for(String[] str: items) {
					empModel.addRow(str);
				}
			}
		});
		
		memberUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String[]> items = Bridge.GetMembersItems(conn);
				memberModel.setRowCount(0);
				for(String[] str: items) {
					memberModel.addRow(str);
				}
			}
		});
		
		finalS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String current = search.getText();
				ArrayList<String[]> items = Bridge.GetAllMediaItems(conn);
				model.setRowCount(0);
				for(String[] str: items) {
					String searchable = "";
					for(String x: str) {
						searchable += x;
					}
					if(searchable.contains(current)) {
						model.addRow(str);
					}
				}
			}
		});
		
		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int target = table.getSelectedRow();
				int cols = columnNames.length;
				String[] data = new String[cols];
				for(int i = 0; i < cols; i++) {
					data[i] = (String) table.getValueAt(target, i);
				}
				int id = Integer.parseInt(data[0]);
				String typeStr = data[3];
				MediaType type = MediaType.getMediaType(typeStr);
				switch(type) {
					case MOVIE:
						Bridge.RemoveMovieWithID(conn, id);
						break;
					case ALBUM:
						Bridge.RemoveAlbumWithID(conn, id);
						break;
					case BOOK:
						Bridge.RemoveBookWithID(conn, id);
						break;
					case TRACK:
						break;
				}
				model.removeRow(target);
				Bridge.RemoveMediaWithID(conn, id);
				AddMenu em = new AddMenu(frame, data, conn);
			}
		});
		   
		frame.getContentPane().add(main);
		frame.setVisible(true);
		
	}
}