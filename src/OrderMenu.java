import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DatabaseBridge.Bridge;
import media.Album;
import media.Book;
import media.MediaItem;
import media.Movie;
import media.Track;
import ordering.Order;

public class OrderMenu {
	
	private String[] attributes = new String[8];
	private String[] orderAttributes = new String[8];
	private boolean EditMode = false;
	private boolean done = false;
	
	public OrderMenu(JFrame frame, Connection conn) {
		
		String[] orderDefaults = {"Entry ID", "Order ID", "Employee ID", "Media ID", "Price", "Number of Copies", "Order Date", "Estimated Date of Arrival"};
		String[] defaults = {"MediaID", "Name", "2022", "Type", "0.00", "Location", "Status", "Certificate"};
		OrderMenu(frame, defaults, orderDefaults, conn);
		
	}
	
	private void OrderMenu(JFrame frame, String[] defaults, String[] orderDefaults, Connection conn) {
		
			String[] optionsToChoose = {"BOOK", "TRACK", "MOVIE", "ALBUM"};
		
			String getType = (String) JOptionPane.showInputDialog(
	                null,
	                "What type are you ordering?",
	                "Choose Type",
	                JOptionPane.QUESTION_MESSAGE,
	                null,
	                optionsToChoose,
	                optionsToChoose[0]);
		
		   JFrame addFrame = new JFrame("Ordering");
	       addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       addFrame.setLayout(new BoxLayout(addFrame.getContentPane(), BoxLayout.Y_AXIS));
	       addFrame.setSize(600,400);
	       
	       JPanel mainPanel = new JPanel();
	       JPanel orderPanel = new JPanel();
	       JPanel itemPanel = new JPanel();
	       
	       JLabel orderLabel = new JLabel("Order Info");
	       JLabel itemLabel = new JLabel("Item Info");
	       
	       JPanel mediaIDPanel = new JPanel();
	       JPanel NamePanel = new JPanel();
	       JPanel yearPanel = new JPanel();
	       JPanel typePanel = new JPanel();
	       JPanel locationPanel = new JPanel();
	       JPanel crPanel = new JPanel();
	       JPanel pricePanel = new JPanel();
	       
	       JTextField mediaID = new JTextField(defaults[0], 8);
	       JTextField nameText = new JTextField(defaults[1], 8);
	       JTextField yearText = new JTextField(defaults[2], 4);
	       JTextField priceText = new JTextField(defaults[4], 5);
	       JTextField locationText = new JTextField(defaults[5], 7);
	       JTextField crText = new JTextField(defaults[7]);
	       JTextField typeText = new JTextField(defaults[3], 8);
	       
	       JPanel entryIDPanel = new JPanel();
	       JPanel orderIDPanel = new JPanel();
	       JPanel employeeIDPanel = new JPanel();
	       JPanel numCopiesPanel = new JPanel();
	       JPanel orderDatePanel = new JPanel();
	       JPanel estDeliveryPanel = new JPanel();
	       
	       JTextField entryIDText = new JTextField(orderDefaults[0], 8);
	       JTextField orderIDText = new JTextField(orderDefaults[1], 8);
	       JTextField employeeIDText = new JTextField(orderDefaults[2], 8);
	       JTextField numCopiesText = new JTextField(orderDefaults[5], 8);
	       JTextField orderDateText = new JTextField(orderDefaults[6], 12);
	       JTextField estDeliveryText = new JTextField(orderDefaults[7], 12);
	       
	     //TRACK entries
	       JPanel trackAttributes = new JPanel();
	       JTextField trackIDTxt = new JTextField("TrackID", 6);
	       JTextField trackNameTxt = new JTextField("TrackName", 8);
	       
	       //MOVIE entries
	       JPanel movieAttributes = new JPanel();
	       JTextField leadActor = new JTextField("Actor", 12);
	       JTextField directorName = new JTextField("Director", 12);
	       
	       //Album entries
	       JPanel albumAttributes = new JPanel();
	       JTextField artistTxt = new JTextField("Artist", 12);
	       
	       //Book Entries
	       JPanel bookAttributes = new JPanel();
	       JTextField authorName = new JTextField("Author Name", 12);
	       JTextField publisherName = new JTextField("Publisher Name", 12);
	       
	       //shared entries
	       JTextField albumIDTxt = new JTextField("AlbumID", 6);
	       JTextField genreTxt = new JTextField("Genre", 8);
	       JTextField lengthTxt = new JTextField("0", 3);
	       
	       if(!getType.contains("TRACK")) {
		       mediaIDPanel.add(mediaID);
		       NamePanel.add(nameText);
		       yearPanel.add(yearText);
		       typePanel.add(typeText);
		       locationPanel.add(locationText);
		       crPanel.add(crText);
		       pricePanel.add(priceText);
		       
		       itemPanel.add(mediaIDPanel);
		       itemPanel.add(NamePanel);
		       itemPanel.add(yearPanel);
		       itemPanel.add(typePanel);
		       itemPanel.add(locationPanel);
		       itemPanel.add(crPanel);
		       itemPanel.add(pricePanel);
		       if(getType.contains("MOVIE")) {
				   movieAttributes.add(leadActor);
				   movieAttributes.add(directorName);
				   movieAttributes.add(genreTxt);
				   movieAttributes.add(lengthTxt);
				   itemPanel.add(movieAttributes);
			   } else if(getType.contains("ALBUM")) {
				   albumAttributes.add(artistTxt);
				   itemPanel.add(albumAttributes);
			   } else if(getType.contains("BOOK")) {
				   bookAttributes.add(authorName);
				   bookAttributes.add(publisherName);
				   itemPanel.add(bookAttributes);
			   }
	       } else if(getType.contains("TRACK")) {
	    	   trackAttributes.add(trackIDTxt);
	    	   trackAttributes.add(albumIDTxt);
	    	   trackAttributes.add(trackNameTxt);
	    	   trackAttributes.add(lengthTxt);
	    	   
	    	   itemPanel.add(trackAttributes);
		   }
	       
	       entryIDPanel.add(entryIDText);
	       orderIDPanel.add(orderIDText);
	       employeeIDPanel.add(employeeIDText);
	       numCopiesPanel.add(numCopiesText);
	       orderDatePanel.add(orderDateText);
	       estDeliveryPanel.add(estDeliveryText);
	       
	       orderPanel.add(entryIDPanel);
	       orderPanel.add(orderIDPanel);
	       orderPanel.add(employeeIDPanel);
	       orderPanel.add(numCopiesPanel);
	       orderPanel.add(orderDatePanel);
	       orderPanel.add(estDeliveryPanel);
	       
	       JButton backButton = new JButton("Back");
	       JButton doneButton = new JButton("Done");
	       
	       mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	       mainPanel.add(orderLabel);
	       mainPanel.add(orderPanel);
	       mainPanel.add(itemLabel);
	       mainPanel.add(itemPanel);
	       mainPanel.add(backButton);
	       mainPanel.add(doneButton);
	       
	       
	       doneButton.addActionListener(new ActionListener() {
	    	   @Override
	    	   public void actionPerformed(ActionEvent e) {
	    		   String strType = getType;
	    		   if(!strType.contains("TRACK")) {
		    		   attributes[0] = mediaID.getText();
		    		   attributes[1] = nameText.getText();
		    		   attributes[2] = yearText.getText();
		    		   attributes[4] = priceText.getText();
		    		   attributes[6] = "Ordered";
		    		   attributes[5] = locationText.getText();
		    		   attributes[7] = crText.getText();
		    		   attributes[3] = typeText.getText();
		    		   Bridge.addNewMedia(conn, attributes);
		    		   if(getType.contains("MOVIE")) {
		    			   String[] attr = new String[6];
		    			   attr[0] = mediaID.getText();
		    			   attr[1] = leadActor.getText();
		    			   attr[2] = directorName.getText();
		    			   attr[3] = genreTxt.getText();
		    			   attr[4] = crText.getText();
		    			   attr[5] = lengthTxt.getText();
		    			   
		    			   Bridge.addMovie(conn, attr);
		    		   } else if(getType.contains("ALBUM")) {
		    			   String[] attr = new String[5];
		    			   attr[0] = albumIDTxt.getText();
		    			   attr[1] = mediaID.getText();
		    			   attr[2] = artistTxt.getText();
		    			   attr[3] = genreTxt.getText();
		    			   attr[4] = crText.getText();
		    			   Bridge.addAlbum(conn, attr);
		    		   } else if(getType.contains("BOOK")) {
		    			   String[] attr = new String[5];
		    			   attr[0] = mediaID.getText();
		    			   attr[1] = authorName.getText();
		    			   attr[2] = publisherName.getText();
		    			   attr[3] = genreTxt.getText();
		    			   attr[4] = lengthTxt.getText();
		    			   
		    			   Bridge.addBook(conn, attr);
		    		   }
	    		   } else {
	    			   String[] trackAttr = new String[5];
	    			   trackAttr[0] = trackIDTxt.getText();
	    			   trackAttr[1] = albumIDTxt.getText();
	    			   trackAttr[2] = trackNameTxt.getText();
	    			   trackAttr[3] = lengthTxt.getText();
	    			   
	    			   Bridge.addTrack(conn, trackAttr);
	    		   }
	    		   
	    		   orderAttributes[0] = entryIDText.getText();
	    		   orderAttributes[1] = orderIDText.getText();
	    		   orderAttributes[2] = employeeIDText.getText();
	    		   orderAttributes[3] = mediaID.getText();
	    		   orderAttributes[4] = priceText.getText();
	    		   orderAttributes[5] = numCopiesText.getText();
	    		   orderAttributes[6] = orderDateText.getText();
	    		   orderAttributes[7] = estDeliveryText.getText();
	    		   Bridge.addNewOrder(conn, orderAttributes);
	    		   
	    		   addFrame.dispose();
	    		   done = true;
	    		   frame.setVisible(true);
	    	   }
	       });
	       
	       backButton.addActionListener(new ActionListener() {
	    	   @Override
	    	   public void actionPerformed(ActionEvent e) {
	    		   addFrame.dispose();
	    		   frame.setVisible(true);
	    	   }
	       });
	       
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
	
	public boolean isDone() {
		return done;
	}
}