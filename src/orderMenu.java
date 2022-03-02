import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import media.Album;
import media.Book;
import media.Movie;
import media.Track;

public class orderMenu {
	private String[] attributes = new String[7];
	private boolean done = false;
	
	public orderMenu(MediaType mt, JFrame frame) {
		JFrame addFrame = new JFrame("Ordering");
	       addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       addFrame.setLayout(new BoxLayout(addFrame.getContentPane(), BoxLayout.Y_AXIS));
	       addFrame.setSize(600,400);
	       
	       JPanel mainPanel = new JPanel();
	       JPanel namePanel = new JPanel();
	       JPanel genrePanel = new JPanel();
	       JPanel yearPanel = new JPanel();
	       JPanel lengthPanel = new JPanel();
	       JPanel typePanel = new JPanel();
	       JPanel locationPanel = new JPanel();
	       JPanel crPanel = new JPanel();
	       JPanel donePanel = new JPanel();
	       
	       JTextField nameText = new JTextField("Name", 8);
	       JTextField genreText = new JTextField("Genre", 8);
	       JTextField yearText = new JTextField("2022", 4);
	       JTextField lengthText = new JTextField("0", 4);
	       JTextField typeText = new JTextField("Type", 8);
	       JTextField locationText = new JTextField("Location", 8);
	       JTextField crText = new JTextField("Content Rating");
	       JButton doneButton = new JButton("Done");
	       
	       namePanel.add(nameText);
	       genrePanel.add(genreText);
	       yearPanel.add(yearText);
	       lengthPanel.add(lengthText);
	       typePanel.add(typeText);
	       locationPanel.add(locationText);
	       
	       mainPanel.add(namePanel);
	       mainPanel.add(genrePanel);
	       mainPanel.add(yearPanel);
	       mainPanel.add(lengthPanel);
	       mainPanel.add(typePanel);
	       mainPanel.add(locationPanel);
	       
	       if(mt == MediaType.MOVIE || mt == MediaType.TRACK) {
	    	   crPanel.add(crText);
	    	   addFrame.add(crPanel);
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
	    		   
	    		   switch(mt) {
	    		   case BOOK:
	    			   Core.media.Add(new Book(getName(), getGenre(), getYear(), getLength(), getType(), getLocation()));
	    			   break;
	    		   case MOVIE:
	    			   Core.media.Add(new Movie(getName(), getGenre(), getYear(), getLength(), getType(), getLocation(), getCR()));
	    			   break;
	    		   case TRACK:
	    			   Core.media.Add(new Track(getName(), getGenre(), getYear(), getLength(), getType(), getLocation(), getCR()));
	    			   break;
	    		   case ALBUM:
	    			   Core.media.Add(new Album(getName(), getGenre(), getYear(), getLength(), getType(), getLocation()));
	    			   break; 
	    		   }
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
	
	public boolean isDone() {
		return done;
	}
}
