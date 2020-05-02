import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

public class userInsert extends JFrame {
	userPanelBuilder infoPanel;
	JPanel buttonPanel;
	
	public userInsert() {
		// Set the window title
	    setTitle("Insert User");
	      
	    // Specify an action for the close button
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      
	    // Create a panelBuilder object
	    infoPanel = new userPanelBuilder("Insert User");
	      
	    // Build the buttonPanel object
	    buildButtonPanel();
	      
	    // Create a BorderLayout manager
	    setLayout(new BorderLayout());
	      
	    // Add the panels to the content pane
	    add(infoPanel, BorderLayout.CENTER);
	    add(buttonPanel, BorderLayout.SOUTH);
	     
	    // Pack and display the window
	    pack();
	    setVisible(true);
	}
	
	private void buildButtonPanel() {
		// Create a panel for the buttons
		buttonPanel = new JPanel();
		  
		// Create a Submit button and add an action listener
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new SubmitButtonListener());
		  
		// Create a Clear button and add an action listener
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ClearButtonListener());
		
		// Create a Exit button and add an action listener
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitButtonListener());
		  
		// Add the buttons to the panel
		buttonPanel.add(submitButton);
		buttonPanel.add(clearButton);
		buttonPanel.add(exitButton);
	}
	
	private class SubmitButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				// Get the information from the text fields
				String firstName = infoPanel.getFirstName();
				String lastName = infoPanel.getLastName();
				String phone = infoPanel.getPhone();
				String email = infoPanel.getEmail();
				String perm = infoPanel.getPermLvl();
				
				// Create a tableManager object
				tableManager tManager = new tableManager();
				
				// Insert the record
				tManager.insertUser(firstName, lastName, phone, email, perm);
				
				// Clear the text fields
				infoPanel.clear();
	
				// Let the user know the record was added
				JOptionPane.showMessageDialog(null, "Record Added");
			} catch (SQLException ex) {
				ex.printStackTrace();
            	System.exit(0);
			}
		}
	}
	
	private class ClearButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Clear information
			infoPanel.clear();
		}
	}
	   
	private class ExitButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Exit the application
			dispose();
		}
	}
}