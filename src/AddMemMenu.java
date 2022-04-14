import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DatabaseBridge.Bridge;

public class AddMemMenu {
	private boolean EditMode = false;
	private Connection conn;
	public AddMemMenu(JFrame frame, Connection conn) {
		String[] defaults = {"MemberID", "PersonID", "Email", "Address", "PhoneNumber"};
		String perData = "Name";
		this.conn = conn;
		MakeMenu(frame, defaults, perData);
	}
	public AddMemMenu(JFrame frame, String[] defaults, String perName, Connection conn) {
		EditMode = true;
		this.conn = conn;
		MakeMenu(frame, defaults, perName);
	}
	
	private void MakeMenu(JFrame frame, String[] defaults, String perName) {
		JFrame addFrame = new JFrame("Adding");
	    addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    addFrame.setLayout(new BoxLayout(addFrame.getContentPane(), BoxLayout.Y_AXIS));
	    addFrame.setSize(600,400);
	    
	    JPanel mainPanel = new JPanel();
	    JPanel memberPanel = new JPanel();
	    JPanel personPanel = new JPanel();
	    
	    JTextField memIdTxt = new JTextField(defaults[0], 8);
	    JTextField personIdTxt = new JTextField(defaults[1], 8);
	    JTextField emailTxt = new JTextField(defaults[2], 8);
	    JTextField addressTxt = new JTextField(defaults[3], 8);
	    JTextField phoneNumberTxt = new JTextField(defaults[4], 8);
	    
	    
	    JTextField nameTxt = new JTextField(perName, 15);
	    
	    mainPanel.add(personIdTxt);
	    memberPanel.add(memIdTxt);
	    memberPanel.add(emailTxt);
	    memberPanel.add(addressTxt);
	    memberPanel.add(phoneNumberTxt);
	    
	    personPanel.add(nameTxt);
	    
	    mainPanel.add(memberPanel);
	    mainPanel.add(personPanel);
	    
	    JButton doneButton = new JButton("Done");
	    
	    doneButton.addActionListener(new ActionListener() {
	    	   @Override
	    	   public void actionPerformed(ActionEvent e) {
	    		   String[] memberData = new String[5];
	    		   memberData[0] = memIdTxt.getText();
	    		   memberData[1] = personIdTxt.getText();
	    		   memberData[2] = emailTxt.getText();
	    		   memberData[3] = addressTxt.getText();
	    		   memberData[4] = phoneNumberTxt.getText(); 
	    		   
	    		   String[] personData = new String[2];
	    		   personData[0] = personIdTxt.getText();
	    		   personData[1] = nameTxt.getText();
	    		   
	    		   if(EditMode) {
		    		   Bridge.removeMember(conn, Integer.parseInt(defaults[0]));
		    		   Bridge.removePerson(conn, Integer.parseInt(defaults[1]));
	    		   }
	    		   Bridge.addMember(conn, memberData);
	    		   Bridge.addPerson(conn, personData);
	    		   
	    		   addFrame.dispose();
	    		   frame.setVisible(true);
	    	   }
	    });
	    mainPanel.add(doneButton);
	    addFrame.getContentPane().add(mainPanel);
	    addFrame.setVisible(true); 
	}
}
