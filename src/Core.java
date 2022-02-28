import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
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
	       
	       JButton button = new JButton("Add New Media");
	       
	       addPanel.add(button);
	       searchPanel.add(search);
	       
	       main.add(searchPanel);
	       main.add(addPanel);
	       
	       frame.getContentPane().add(main);
	       frame.setVisible(true);
	}
}