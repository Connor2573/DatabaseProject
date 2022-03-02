import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class OrderMenu {
	
	private boolean editMode = false;
	
	public OrderMenu(JFrame frame) {
		
		String[] defaults = {"orderID", "empID", "itemID", "Name", "quantity", "estDelivery"};
		OrderMenu(frame, defaults);
		
	}
	
	public OrderMenu(JFrame frame, String[] defaults) {
		editMode = true;
		OrderMenu(frame, defaults);
	}

	
	private void OrderMenu(JFrame frame, String[] defaults) {
		
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
	       
	       JTextField nameText = new JTextField(defaults[0], 8);
	       JTextField genreText = new JTextField(defaults[1], 8);
	       JTextField yearText = new JTextField(defaults[2], 4);
	       JTextField lengthText = new JTextField(defaults[3], 4);
	       JTextField typeText = new JTextField(defaults[4], 8);
	       JTextField locationText = new JTextField(defaults[5], 8);
	       JTextField crText = new JTextField(defaults[6]);
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
	       
	       addFrame.setVisible(true); 

		
	}
}
