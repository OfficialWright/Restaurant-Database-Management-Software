import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

public class tableInsert extends JFrame {
	tablePanelBuilder infoPanel;
	JPanel buttonPanel;
	
	public tableInsert() {
		// Set the window title
	    setTitle("Insert Table");
	      
	    // Specify an action for the close button
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      
	    // Create a panelBuilder object
	    infoPanel = new tablePanelBuilder("Insert Table");
	      
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
				String name = infoPanel.getName();
				String location = infoPanel.getLoc();
				
				// Create a tableManager object
				tableManager tManager = new tableManager();
				
				// Insert the record
				tManager.insertTable(name, location);
				
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