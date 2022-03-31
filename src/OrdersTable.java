import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import media.Album;
import media.Book;
import media.Movie;
import media.Track;

public class OrdersTable {
	public OrdersTable(JFrame frame) {
		JFrame ordersFrame = new JFrame("Orders Table");
		ordersFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ordersFrame.setLayout(new BoxLayout(ordersFrame.getContentPane(), BoxLayout.Y_AXIS));
		ordersFrame.setSize(1200,800);
	   
		JPanel mainPanel = new JPanel();
		
		String[] columnNames = {"Order ID", "Name of Order", "Name of Item", "Quantity", "Estimated Date of Arrival"};
		
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		for(String str: columnNames) {
			model.addColumn(str);
		}
		String[][] orders = Core.order.GetOrders();
		if (orders.length > 0) {
			model.setRowCount(0);
			for(String[] str: orders) {
				model.addRow(str);
			}
		}
		JScrollPane tablePanel = new JScrollPane(table);
		tablePanel.setBorder(BorderFactory.createTitledBorder(
		      	BorderFactory.createEtchedBorder(), "Orders", TitledBorder.LEFT,
		      	TitledBorder.TOP));
		mainPanel.add(tablePanel);
		JButton activateButton = new JButton("Activate Selected Order");
		JButton doneButton = new JButton("Done");
		
		activateButton.addActionListener(new ActionListener() {
	    	   @Override
	    	   public void actionPerformed(ActionEvent e) {
	    		   int target = table.getSelectedRow();
	    		   Core.order.Activate(Integer.valueOf(table.getValueAt(target, 0).toString()));
	    		   model.removeRow(target);
	    	   }
	    });
		
		doneButton.addActionListener(new ActionListener() {
	    	   @Override
	    	   public void actionPerformed(ActionEvent e) {
	    		   ordersFrame.dispose();
	    		   frame.setVisible(true);
	    	   }
	    });
		
		mainPanel.add(activateButton);
		mainPanel.add(doneButton);
		ordersFrame.getContentPane().add(mainPanel);
	    ordersFrame.setVisible(true);
	}
}
