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
	

	private String[] attributes = new String[7];
	private boolean EditMode = false;
	private boolean done = false;
	private MediaType type;
	private Connection conn;
	
	public AddMenu(JFrame frame, Connection conn) {
		String[] defaults = {"MediaID", "Name", "2022", "Status", "Location", "Certificate", "Type"};
		this.conn = conn;
		MakeMenu(frame, defaults);
	}
	public AddMenu(JFrame frame, String[] defaults, Connection conn) {
		EditMode = true;
		this.conn = conn;
		MakeMenu(frame, defaults);
	}
	
	private void MakeMenu(JFrame frame, String[] defaults) {
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
		   type = mt;
		   
		JFrame addFrame = new JFrame("Adding");
	       addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       addFrame.setLayout(new BoxLayout(addFrame.getContentPane(), BoxLayout.Y_AXIS));
	       addFrame.setSize(600,400);
	       
	       JPanel mainPanel = new JPanel();
	       JPanel mediaIDPanel = new JPanel();
	       JPanel NamePanel = new JPanel();
	       JPanel yearPanel = new JPanel();
	       JPanel statusPanel = new JPanel();
	       JPanel typePanel = new JPanel();
	       JPanel locationPanel = new JPanel();
	       JPanel crPanel = new JPanel();
	       JPanel donePanel = new JPanel();
	       
	       JTextField mediaID = new JTextField(defaults[0], 8);
	       JTextField nameText = new JTextField(defaults[1], 8);
	       JTextField yearText = new JTextField(defaults[2], 4);
	       JTextField statusText = new JTextField(defaults[3], 4);
	       JTextField locationText = new JTextField(defaults[4], 7);
	       JTextField crText = new JTextField(defaults[5]);
	       JTextField typeText = new JTextField(defaults[6], 8);
	       JButton doneButton = new JButton("Done");
	       
	       mediaIDPanel.add(mediaID);
	       NamePanel.add(nameText);
	       yearPanel.add(yearText);
	       statusPanel.add(statusText);
	       typePanel.add(typeText);
	       locationPanel.add(locationText);
	       crPanel.add(crText);
	       
	       mainPanel.add(mediaIDPanel);
	       mainPanel.add(NamePanel);
	       mainPanel.add(yearPanel);
	       mainPanel.add(statusPanel);
	       mainPanel.add(typePanel);
	       mainPanel.add(locationPanel);
	       mainPanel.add(crPanel);
	       
	       doneButton.addActionListener(new ActionListener() {
	    	   @Override
	    	   public void actionPerformed(ActionEvent e) {
	    		   attributes[0] = mediaID.getText();
	    		   attributes[1] = nameText.getText();
	    		   attributes[2] = yearText.getText();
	    		   attributes[3] = statusText.getText();
	    		   attributes[4] = locationText.getText();
	    		   attributes[5] = crText.getText();
	    		   attributes[6] = typeText.getText();
	    		   
	    		   Bridge.addNewMedia(conn, attributes);
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

