import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DatabaseBridge.Bridge;
import media.Album;
import media.Book;
import media.Movie;
import media.Track;


public class AddMenu {
	

	private String[] attributes = new String[8];
	private boolean EditMode = false;
	private boolean done = false;
	private Connection conn;
	
	public AddMenu(JFrame frame, Connection conn) {
		this.conn = conn;
		String[] defaults = {String.valueOf(Bridge.GetNextMediaID(conn)), "Name", "2022", "Type", "0.00", "Location", "Status", "Certificate"};
		MakeMenu(frame, defaults);
	}
	public AddMenu(JFrame frame, String[] defaults, Connection conn) {
		EditMode = true;
		this.conn = conn;
		MakeMenu(frame, defaults);
	}
	
	
	
	private void MakeMenu(JFrame frame, String[] defaults) {
		   
		JFrame addFrame = new JFrame("Adding");
	       addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       addFrame.setLayout(new BoxLayout(addFrame.getContentPane(), BoxLayout.Y_AXIS));
	       addFrame.setSize(600,400);
	       String[] types = { "BOOK", "MOVIE", "ALBUM", "TRACK" };
	       
	       JComboBox<String> typeCombo = new JComboBox<String>(types);
	       
	       JPanel mainPanel = new JPanel();
	       JPanel mediaIDPanel = new JPanel();
	       JPanel NamePanel = new JPanel();
	       JPanel yearPanel = new JPanel();
	       JPanel statusPanel = new JPanel();
	       JPanel locationPanel = new JPanel();
	       JPanel crPanel = new JPanel();
	       JPanel donePanel = new JPanel();
	       JPanel pricePanel = new JPanel();
	       
	       JTextField mediaID = new JTextField(defaults[0], 8);
	       JTextField nameText = new JTextField(defaults[1], 8);
	       JTextField yearText = new JTextField(defaults[2], 4);
	       JTextField priceText = new JTextField(defaults[4], 5);
	       JTextField statusText = new JTextField(defaults[6], 8);
	       JTextField locationText = new JTextField(defaults[5], 7);
	       JTextField crText = new JTextField(defaults[7]);
	       JButton doneButton = new JButton("Done");
	       JButton deleteButton = new JButton("Delete");
	       
	       mediaIDPanel.add(mediaID);
	       NamePanel.add(nameText);
	       yearPanel.add(yearText);
	       statusPanel.add(statusText);
	       locationPanel.add(locationText);
	       crPanel.add(crText);
	       pricePanel.add(priceText);
	       
	       mainPanel.add(typeCombo);
	       mainPanel.add(mediaIDPanel);
	       mainPanel.add(NamePanel);
	       mainPanel.add(yearPanel);
	       mainPanel.add(statusPanel);
	       mainPanel.add(locationPanel);
	       mainPanel.add(crPanel);
	       mainPanel.add(pricePanel);
	       
	       typeCombo.addActionListener(new ActionListener() {
	    	   public void actionPerformed(ActionEvent e) {
	    		   JComboBox temp = (JComboBox)e.getSource();
	    		   String type = (String)temp.getSelectedItem();
	    		   JPanel itemPanel = new JPanel();
	    		   if (type.equals("BOOK")) {
	    			   JTextField AuthorName = new JTextField("AuthorName", 8);
	    			   itemPanel.add(AuthorName);
	    		   } else if (type.equals("MOVIE")) {
	    			   JTextField LeadActor = new JTextField("LeadActor", 8);
	    			   itemPanel.add(LeadActor);
	    		   } else if (type.equals("ALBUM")) {
	    			   JTextField Artist = new JTextField("Artist", 8);
	    			   itemPanel.add(Artist);
	    		   } else if (type.equals("TRACK")) {
	    			   JTextField TrackName = new JTextField("TrackName", 8);
	    			   itemPanel.add(TrackName);
	    		   }
	    		   mainPanel.add(itemPanel);
	    		   itemPanel.setVisible(true);
	    		   mainPanel.revalidate();
	    		   mainPanel.repaint();

	    	   }
	       });
	       
	       doneButton.addActionListener(new ActionListener() {
	    	   @Override
	    	   public void actionPerformed(ActionEvent e) {
	    		   attributes[0] = mediaID.getText();
	    		   attributes[1] = nameText.getText();
	    		   attributes[2] = yearText.getText();
	    		   attributes[4] = priceText.getText();
	    		   attributes[6] = statusText.getText();
	    		   attributes[5] = locationText.getText();
	    		   attributes[7] = crText.getText();
	    		   //attributes[3] = typeText.getText();
	    		   
	    		   Bridge.addNewMedia(conn, attributes);
	    		   
	    		   //type = attributes[3];
	    		   
	    		   addFrame.dispose();
	    		   done = true;
	    		   frame.setVisible(true);
	    	   }
	       });
	       
	       deleteButton.addActionListener(new ActionListener() {
	    	   @Override
	    	   public void actionPerformed(ActionEvent e) {
	    		   addFrame.dispose();
	    		   done = true;
	    		   frame.setVisible(true);
	    	   }
	       });
	       donePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	       donePanel.add(doneButton);
	       donePanel.add(deleteButton);
	       mainPanel.add(donePanel);
	       
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

