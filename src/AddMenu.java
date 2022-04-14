import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
	private MediaType type;
	private Connection conn;
	
	public AddMenu(JFrame frame, Connection conn) {
		String[] defaults = {"MediaID", "Name", "2022", "Type", "0.00", "Location", "Status", "Certificate"};
		this.conn = conn;
		MakeMenu(frame, defaults);
	}
	public AddMenu(JFrame frame, String[] defaults, Connection conn) {
		EditMode = true;
		this.conn = conn;
		MakeMenu(frame, defaults);
	}
	
	private void MakeMenu(JFrame frame, String[] defaults) {
		String getType = null;
		if(!EditMode) {
			String[] optionsToChoose = {"BOOK", "TRACK", "MOVIE", "ALBUM"};
			
			getType = (String) JOptionPane.showInputDialog(
		                null,
		                "What type are you adding?",
		                "Choose Type",
		                JOptionPane.QUESTION_MESSAGE,
		                null,
		                optionsToChoose,
		                optionsToChoose[0]);
		} else {
			getType = defaults[3];
		}
		
		JFrame addFrame = new JFrame("Adding");
	       addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       addFrame.setLayout(new BoxLayout(addFrame.getContentPane(), BoxLayout.Y_AXIS));
	       addFrame.setSize(600,400);
	       
	       //MEDIA_ITEM attributes
	       JPanel mainPanel = new JPanel();
	       JPanel mediaIDPanel = new JPanel();
	       JPanel NamePanel = new JPanel();
	       JPanel yearPanel = new JPanel();
	       JPanel statusPanel = new JPanel();
	       JPanel typePanel = new JPanel();
	       JPanel locationPanel = new JPanel();
	       JPanel crPanel = new JPanel();
	       JPanel pricePanel = new JPanel();
	       JTextField mediaID = new JTextField(defaults[0], 8);
	       JTextField nameText = new JTextField(defaults[1], 8);
	       JTextField yearText = new JTextField(defaults[2], 4);
	       JTextField priceText = new JTextField(defaults[4], 5);
	       JTextField statusText = new JTextField(defaults[6], 8);
	       JTextField locationText = new JTextField(defaults[5], 7);
	       JTextField crText = new JTextField(defaults[7]);
	       JTextField typeText = new JTextField(defaults[3], 8);
	       
	       //UI buttons/panels
	       JPanel donePanel = new JPanel();
	       JButton doneButton = new JButton("Done");
	       JButton deleteButton = new JButton("Delete");
	       
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
		       statusPanel.add(statusText);
		       typePanel.add(typeText);
		       locationPanel.add(locationText);
		       crPanel.add(crText);
		       pricePanel.add(priceText);
		       
		       mainPanel.add(mediaIDPanel);
		       mainPanel.add(NamePanel);
		       mainPanel.add(yearPanel);
		       mainPanel.add(statusPanel);
		       mainPanel.add(typePanel);
		       mainPanel.add(locationPanel);
		       mainPanel.add(crPanel);
		       mainPanel.add(pricePanel);
		       if(getType.contains("MOVIE")) {
				   movieAttributes.add(leadActor);
				   movieAttributes.add(directorName);
				   movieAttributes.add(genreTxt);
				   movieAttributes.add(lengthTxt);
				   type = MediaType.MOVIE;
				   mainPanel.add(movieAttributes);
			   } else if(getType.contains("ALBUM")) {
				   albumAttributes.add(albumIDTxt);
				   albumAttributes.add(artistTxt);
				   type = MediaType.ALBUM;
				   mainPanel.add(albumAttributes);
			   } else if(getType.contains("BOOK")) {
				   bookAttributes.add(authorName);
				   bookAttributes.add(publisherName);
				   type = MediaType.BOOK;
				   mainPanel.add(bookAttributes);
			   }
	       } else if(getType.contains("TRACK")) {
	    	   trackAttributes.add(trackIDTxt);
	    	   trackAttributes.add(albumIDTxt);
	    	   trackAttributes.add(trackNameTxt);
	    	   trackAttributes.add(lengthTxt);
	    	   type = MediaType.TRACK;
	    	   mainPanel.add(trackAttributes);
		   }
	       
	       doneButton.addActionListener(new ActionListener() {
	    	   @Override
	    	   public void actionPerformed(ActionEvent e) {
	    		   if(type != MediaType.TRACK) {
		    		   attributes[0] = mediaID.getText();
		    		   attributes[1] = nameText.getText();
		    		   attributes[2] = yearText.getText();
		    		   attributes[4] = priceText.getText();
		    		   attributes[6] = statusText.getText();
		    		   attributes[5] = locationText.getText();
		    		   attributes[7] = crText.getText();
		    		   attributes[3] = typeText.getText().toUpperCase();
		    		   Bridge.addNewMedia(conn, attributes);
		    		   if(type == MediaType.MOVIE) {
		    			   String[] attr = new String[6];
		    			   attr[0] = mediaID.getText();
		    			   attr[1] = leadActor.getText();
		    			   attr[2] = directorName.getText();
		    			   attr[3] = genreTxt.getText();
		    			   attr[4] = crText.getText();
		    			   attr[5] = lengthTxt.getText();
		    			   
		    			   if (!Bridge.getContentCreator(conn, attr[1])) {
		    				   String[] personVals = new String[2];
		    				   personVals[0] = String.valueOf(Integer.valueOf(attributes[0]) * Integer.valueOf(attributes[0]));
		    				   personVals[1] = attr[1];
		    				   Bridge.addPerson(conn, personVals);
		    				   Bridge.addContentCreator(conn, Integer.valueOf(personVals[0]), Integer.valueOf(personVals[0]));
		    			   }
		    			   
		    			   if (!Bridge.getContentCreator(conn, attr[2])) {
		    				   String[] personVals = new String[2];
		    				   personVals[0] = String.valueOf(Integer.valueOf(attributes[0]));
		    				   personVals[1] = attr[2];
		    				   Bridge.addPerson(conn, personVals);
		    				   Bridge.addContentCreator(conn, Integer.valueOf(personVals[0]), Integer.valueOf(personVals[0]));
		    			   }
		    			   
		    			   Bridge.addMovie(conn, attr);
		    		   } else if(type == MediaType.ALBUM) {
		    			   String[] attr = new String[5];
		    			   attr[0] = albumIDTxt.getText();
		    			   attr[1] = mediaID.getText();
		    			   attr[2] = artistTxt.getText();
		    			   attr[3] = genreTxt.getText();
		    			   attr[4] = crText.getText();
		    			   
		    			   if (!Bridge.getContentCreator(conn, attr[2])) {
		    				   String[] personVals = new String[2];
		    				   personVals[0] = String.valueOf(Integer.valueOf(attributes[0]));
		    				   personVals[1] = attr[2];
		    				   Bridge.addPerson(conn, personVals);
		    				   Bridge.addContentCreator(conn, Integer.valueOf(personVals[0]), Integer.valueOf(personVals[0]));
		    			   }
		    			   
		    			   Bridge.addAlbum(conn, attr);
		    		   } else if(type == MediaType.BOOK) {
		    			   String[] attr = new String[5];
		    			   attr[0] = mediaID.getText();
		    			   attr[1] = authorName.getText();
		    			   attr[2] = publisherName.getText();
		    			   attr[3] = genreTxt.getText();
		    			   attr[4] = lengthTxt.getText();
		    			   
		    			   if (!Bridge.getContentCreator(conn, attr[1])) {
		    				   String[] personVals = new String[2];
		    				   personVals[0] = String.valueOf(Integer.valueOf(attributes[0]));
		    				   personVals[1] = attr[1];
		    				   Bridge.addPerson(conn, personVals);
		    				   Bridge.addContentCreator(conn, Integer.valueOf(personVals[0]), Integer.valueOf(personVals[0]));
		    			   }
		    			   
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
	
	private MediaType getMediaType() {
		return type;
	}
	
	public boolean isDone() {
		return done;
	}
}

