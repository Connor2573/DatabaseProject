import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import media.MediaItem;

public class Core {
	
	public static MediaCollection media;
	
	public static void main(String[] args) {
		media = new MediaCollection();
		JFrame frame = new JFrame("Database Interface");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setSize(1200,800);
		   
		JPanel main = new JPanel();
		JPanel searchPanel = new JPanel();
		JPanel addPanel = new JPanel();
		   
		JTextField search = new JTextField("Entry", 8);
		JButton finalS = new JButton("Search");
		JButton update = new JButton("Update");
		JButton addButton = new JButton("Add New Media");
		JButton orderButton = new JButton("Order Media");
		JButton edit = new JButton("Edit selected Media");
    
		addPanel.add(addButton);
		addPanel.add(orderButton);
		addPanel.add(update);
		addPanel.add(edit);
		
		searchPanel.add(search);
		searchPanel.add(finalS);
		   
		main.add(searchPanel);
		main.add(addPanel);
		   
		   addButton.addActionListener(new ActionListener() {
			   @Override
			   public void actionPerformed(ActionEvent e) {
				   frame.setVisible(false);
				   
				   AddMenu am = new AddMenu(frame);
			   }
		   });
		   orderButton.addActionListener(new ActionListener() {
			   @Override
			   public void actionPerformed(ActionEvent e) {
				   frame.setVisible(false);
				   
				   OrderMenu om = new OrderMenu(frame);
			   }
		   });
		   
		String[] columnNames = {"Name", "Genre", "Year", "Length", "Type", "Location", "Content Rating", "Quantity", "MediaID"};
		
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
		
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String[][] items = media.GetItems();
				model.setRowCount(0);
				for(String[] str: items) {
					model.addRow(str);
				}
			}
		});
		
		finalS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String current = search.getText();
				String[][] items = media.GetItems();
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
				int cols = 9;
				System.out.println(cols);
				String[] data = new String[cols];
				for(int i = 0; i < cols; i++) {
					data[i] = (String) table.getValueAt(target, i);
				}
				media.Remove(Integer.parseInt(data[8]));
				AddMenu em = new AddMenu(frame, data);
				model.removeRow(target);
				
			}
		});
		   
		frame.getContentPane().add(main);
		frame.setVisible(true);
	}
}