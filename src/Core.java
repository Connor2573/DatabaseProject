import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Core {
	public static void main(String[] args) {
	       JFrame frame = new JFrame("Database Interface");
	       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
	       frame.setSize(1200,800);
	       
	       JPanel main = new JPanel();
	       JPanel searchPanel = new JPanel();
	       JPanel addPanel = new JPanel();
	       JPanel list = new JPanel();
	       
	       JTextField search = new JTextField("Search", 4);
	       
	       JButton addButton = new JButton("Add New Media");
	       
	       addPanel.add(addButton);
	       searchPanel.add(search);
	       
	       main.add(searchPanel);
	       main.add(addPanel);
	       
	       addButton.addActionListener(new ActionListener() {
	    	   @Override
	    	   public void actionPerformed(ActionEvent e) {
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
	    		   
	    		   AddMenu am = new AddMenu(getMediaType);
	    	   }
	       });
	       
	       frame.getContentPane().add(main);
	       frame.setVisible(true);
	}
}