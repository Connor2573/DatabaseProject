import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import media.Album;
import media.Book;
import media.MediaItem;
import media.Movie;
import media.Track;
import ordering.Order;

public class OrderMenu {
	
	private String[] attributes = new String[7];
	private boolean EditMode = false;
	private boolean done = false;
	private MediaType type;
	
	public OrderMenu(JFrame frame) {
		
		String[] orderDefaults = {"Name of Order", "quantity", "estDelivery"};
		String[] defaults = {"Name", "Genre", "2022", "#", "Type", "Location", "CR"};
		OrderMenu(frame, defaults, orderDefaults);
		
	}
	
	private void OrderMenu(JFrame frame, String[] defaults, String[] orderDefaults) {
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
		   type = mt;
		
		   JFrame addFrame = new JFrame("Ordering");
	       addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       addFrame.setLayout(new BoxLayout(addFrame.getContentPane(), BoxLayout.Y_AXIS));
	       addFrame.setSize(600,400);
	       
	       JPanel mainPanel = new JPanel();
	       JPanel orderPanel = new JPanel();
	       JPanel itemPanel = new JPanel();
	       
	       JLabel orderLabel = new JLabel("Order Info");
	       JLabel itemLabel = new JLabel("Item Info");
	       
	       JPanel orderNamePanel = new JPanel();
	       JPanel quantityPanel = new JPanel();
	       JPanel estDeliveryPanel = new JPanel();
	       
	       JPanel namePanel = new JPanel();
	       JPanel genrePanel = new JPanel();
	       JPanel yearPanel = new JPanel();
	       JPanel lengthPanel = new JPanel();
	       JPanel typePanel = new JPanel();
	       JPanel locationPanel = new JPanel();
	       JPanel crPanel = new JPanel();
	       JPanel donePanel = new JPanel();
	       
	       JTextField orderNameText = new JTextField(orderDefaults[0], 16);
	       JTextField quantityText = new JTextField(orderDefaults[1], 8);
	       JTextField estDeliveryText = new JTextField(orderDefaults[2], 12);
	       
	       JTextField nameText = new JTextField(defaults[0], 8);
	       JTextField genreText = new JTextField(defaults[1], 8);
	       JTextField yearText = new JTextField(defaults[2], 4);
	       JTextField lengthText = new JTextField(defaults[3], 4);
	       JTextField typeText = new JTextField(defaults[4], 8);
	       JTextField locationText = new JTextField(defaults[5], 8);
	       JTextField crText = new JTextField(defaults[6]);
	       JButton doneButton = new JButton("Done");
	       
	       orderNamePanel.add(orderNameText);
	       quantityPanel.add(quantityText);
	       estDeliveryPanel.add(estDeliveryText);
	       
	       namePanel.add(nameText);
	       genrePanel.add(genreText);
	       yearPanel.add(yearText);
	       lengthPanel.add(lengthText);
	       typePanel.add(typeText);
	       locationPanel.add(locationText);
	       
	       orderPanel.add(orderNamePanel);
	       orderPanel.add(quantityPanel);
	       orderPanel.add(estDeliveryPanel);
	       
	       itemPanel.add(namePanel);
	       itemPanel.add(genrePanel);
	       itemPanel.add(yearPanel);
	       itemPanel.add(lengthPanel);
	       itemPanel.add(typePanel);
	       itemPanel.add(locationPanel);
	       
	       mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	       mainPanel.add(orderLabel);
	       mainPanel.add(orderPanel);
	       mainPanel.add(itemLabel);
	       mainPanel.add(itemPanel);
	       
	       if(mt == MediaType.MOVIE || mt == MediaType.TRACK) {
	    	   crPanel.add(crText);
	    	   mainPanel.add(crPanel);
	       }
	       
	       doneButton.addActionListener(new ActionListener() {
	    	   @Override
	    	   public void actionPerformed(ActionEvent e) {
	    		   attributes[0] = nameText.getText();
	    		   attributes[1] = genreText.getText();
	    		   attributes[2] = yearText.getText();
	    		   attributes[3] = lengthText.getText();
	    		   attributes[4] = typeText.getText();
	    		   attributes[5] = locationText.getText();
	    		   attributes[6] = crText.getText();
	    		   
	    		   MediaItem item = null;
	    		   switch(mt) {
	    		   case BOOK:
	    			   item = new Book(getName(), getGenre(), getYear(), getLength(), getType(), getLocation());
	    			   break;
	    		   case MOVIE:
	    			   item = new Movie(getName(), getGenre(), getYear(), getLength(), getType(), getLocation(), getCR());
	    			   break;
	    		   case TRACK:
	    			   item = new Track(getName(), getGenre(), getYear(), getLength(), getType(), getLocation(), getCR());
	    			   break;
	    		   case ALBUM:
	    			   item = new Album(getName(), getGenre(), getYear(), getLength(), getType(), getLocation());
	    			   break; 
	    		   }
	    		   Core.order.Add(new Order(item, orderNameText.getText(), Integer.valueOf(quantityText.getText()), estDeliveryText.getText()));
	    		   addFrame.dispose();
	    		   done = true;
	    		   frame.setVisible(true);
	    	   }
	       });
	       donePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	       donePanel.add(doneButton);
	       mainPanel.add(doneButton);
	       
	       addFrame.getContentPane().add(mainPanel);
	       addFrame.setVisible(true); 

		
	}
	
	private String getName() {
		return attributes[0];
	}
	
	private String getGenre() {
		return attributes[1];
	}
	
	private int getYear() {
		return Integer.parseInt(attributes[2]);
	}
	
	private int getLength() {
		return Integer.parseInt(attributes[3]);
	}
	
	private String getType() {
		return attributes[4];
	}
	
	private String getLocation() {
		return attributes[5];
	}
	
	private String getCR() {
		return attributes[6];
	}
	
	private MediaType getMediaType() {
		return type;
	}
	
	public boolean isDone() {
		return done;
	}
}