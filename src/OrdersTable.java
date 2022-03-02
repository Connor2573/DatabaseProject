import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class OrdersTable {
	public OrdersTable(JFrame frame) {
		JFrame ordersFrame = new JFrame("Orders Table");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setSize(1200,800);
	   
		JPanel mainPanel = new JPanel();
		
		String[] columnNames = {"Name of Order", "Name of Item", "Quantity", "Estimated Date of Arrival"};
		
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		for(String str: columnNames) {
			model.addColumn(str);
		}
		String[][] orders = Core.order.GetOrders();
		model.setRowCount(0);
		for(String[] str: orders) {
			model.addRow(str);
		}
		JScrollPane tablePanel = new JScrollPane(table);
		tablePanel.setBorder(BorderFactory.createTitledBorder(
			      BorderFactory.createEtchedBorder(), "Orders", TitledBorder.LEFT,
			      TitledBorder.TOP));
		mainPanel.add(tablePanel);
		ordersFrame.getContentPane().add(mainPanel);
	    ordersFrame.setVisible(true);
	}
}
