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
		   
		JTextField search = new JTextField("Entry", 4);
		JButton finalS = new JButton("Search");
		JButton update = new JButton("Update");
		JButton addButton = new JButton("Add New Media");
		JButton orderButton = new JButton("Order Media");
		  
		addPanel.add(addButton);
		addPanel.add(orderButton);
		addPanel.add(update);
		searchPanel.add(search);
		searchPanel.add(finalS);
		   
		main.add(searchPanel);
		main.add(addPanel);
		   
		   addButton.addActionListener(new ActionListener() {
			   @Override
			   public void actionPerformed(ActionEvent e) {
				   frame.setVisible(false);
				   MediaType[] mts = MediaType.values();
				   
				   String[] options = new String[mts.length+1];
				   int index = 0;
				   for(MediaType mt: mts) {
					   options[index] = mt.toString();
					   index++;
				   }
				   String getMediaType = (String) JOptionPane.showInputDialog(
			                null,
			                "Which type of item do you want to add?",
			                "Choose item type",
			                JOptionPane.QUESTION_MESSAGE,
			                null,
			                options,
			                options[3]);
				   
				   MediaType mt = MediaType.valueOf(getMediaType.toUpperCase());
				   
				   AddMenu am = new AddMenu(mt, frame);
			   }
		   });
		   
		   orderButton.addActionListener(new ActionListener() {
			   @Override
			   public void actionPerformed(ActionEvent e) {
				   frame.setVisible(false);
				   MediaType[] mts = MediaType.values();
				   
				   String[] options = new String[mts.length+1];
				   int index = 0;
				   for(MediaType mt: mts) {
					   options[index] = mt.toString();
					   index++;
				   }
				   String getMediaType = (String) JOptionPane.showInputDialog(
			                null,
			                "Which type of item do you want to order?",
			                "Choose item type",
			                JOptionPane.QUESTION_MESSAGE,
			                null,
			                options,
			                options[3]);
				   
				   MediaType mt = MediaType.valueOf(getMediaType.toUpperCase());
				   
				   orderMenu om = new orderMenu(mt, frame);
			   }
		   });
		   
		String[] columnNames = {"Name", "Genre", "Year", "Length", "Type", "Location", "Content Rating", "Quantity"};
		
		DefaultTableModel model = new DefaultTableModel();
		JTable table = new JTable(model);
		for(String str: columnNames) {
			model.addColumn(str);
		}
		JScrollPane tablePanel = new JScrollPane(table);
		tablePanel.setBorder(BorderFactory.createTitledBorder(
			      BorderFactory.createEtchedBorder(), "Movies", TitledBorder.LEFT,
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
		   
		frame.getContentPane().add(main);
		frame.setVisible(true);
	}
}