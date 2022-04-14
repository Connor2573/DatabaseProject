import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DatabaseBridge.Bridge;

public class AddEmpMenu {
	private boolean EditMode = false;
	private Connection conn;
	public AddEmpMenu(JFrame frame, Connection conn) {
		String[] defaults = {"EmployeeID", "PersonID", "Email"};
		String perData = "Name";
		this.conn = conn;
		MakeMenu(frame, defaults, perData);
	}
	public AddEmpMenu(JFrame frame, String[] defaults, String perName, Connection conn) {
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
	    JPanel empPanel = new JPanel();
	    JPanel personPanel = new JPanel();
	    
	    JTextField empIdTxt = new JTextField(defaults[0], 8);
	    JTextField personIdTxt = new JTextField(defaults[1], 8);
	    JTextField emailTxt = new JTextField(defaults[2], 8);
	    
	    JTextField nameTxt = new JTextField(perName, 15);
	    
	    mainPanel.add(personIdTxt);
	    empPanel.add(empIdTxt);
	    empPanel.add(emailTxt);
	    
	    personPanel.add(nameTxt);
	    
	    mainPanel.add(empPanel);
	    mainPanel.add(personPanel);
	    
	    JButton doneButton = new JButton("Done");
	    
	    doneButton.addActionListener(new ActionListener() {
	    	   @Override
	    	   public void actionPerformed(ActionEvent e) {
	    		   String[] employeeData = new String[3];
	    		   employeeData[0] = empIdTxt.getText();
	    		   employeeData[1] = personIdTxt.getText();
	    		   employeeData[2] = emailTxt.getText();
	    		   
	    		   String[] personData = new String[2];
	    		   personData[0] = personIdTxt.getText();
	    		   personData[1] = nameTxt.getText();
	    		   
	    		   if(EditMode) {
		    		   Bridge.removeEmployee(conn, Integer.parseInt(defaults[0]));
		    		   Bridge.removePerson(conn, Integer.parseInt(defaults[1]));
	    		   }
	    		   Bridge.addEmployee(conn, employeeData);
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
