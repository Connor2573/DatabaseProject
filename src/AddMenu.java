import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddMenu {
	

	private String[] attributes = new String[7];
	
	public AddMenu(String getMediaType) {
		JFrame addFrame = new JFrame("Adding");
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
	       JTextField lengthText = new JTextField("# in seconds", 4);
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
	       
	       if(getMediaType.equals(MediaType.MOVIE.toString()) || getMediaType.equals(MediaType.TRACK.toString())) {
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
	    	   }
	       });
	       donePanel.add(doneButton);
	       
	       addFrame.getContentPane().add(mainPanel);
	       addFrame.setVisible(true); 
	   }
	
	public String getName() {
		return attributes[0];
	}
	
	public String getGenre() {
		return attributes[1];
	}
	
	public int getYear() {
		return Integer.parseInt(attributes[2]);
	}
	
	public int getLength() {
		return Integer.parseInt(attributes[3]);
	}
	
	public String getType() {
		return attributes[4];
	}
	
	public String getLocation() {
		return attributes[5];
	}
	
	public String getCR() {
		return attributes[6];
	}
	//needs implementation of other return types
}

