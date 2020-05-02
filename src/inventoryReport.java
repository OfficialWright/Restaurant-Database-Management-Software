import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.proteanit.sql.DbUtils;

public class inventoryReport extends JFrame {
	inventoryPanelBuilder infoPanel;
	JPanel buttonPanel;
	JPanel listPanel;
	JScrollPane scrollPane;
	JList nameList;
	JTable ourTable;
	String selectedValue;
	
	public inventoryReport() {
		// Set the window title
	    setTitle("Report Inventory");
	      
	    // Specify an action for the close button
	    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	      
	    // Create a panelBuilder object
	    infoPanel = new inventoryPanelBuilder("Inventory Report");
	      
	    // Build the buttonPanel object
	    buildButtonPanel();
	    
	    // Build the listPanel object
	    buildListPanel();
	      
	    // Create a BorderLayout manager
	    setLayout(new BorderLayout());
	      
	    // Add the panels to the content pane
	    add(infoPanel, BorderLayout.NORTH);
	    add(listPanel, BorderLayout.CENTER);
	    add(buttonPanel, BorderLayout.SOUTH);
	     
	    // Pack and display the window
	    pack();
	    setVisible(true);
	}
	
	private void buildListPanel() {
		try {
	        // Create a panel
	        listPanel = new JPanel();
	         
	        // Add a titled border to the panel
	        listPanel.setBorder(BorderFactory.
	        createTitledBorder("Inventory Information"));
	        // Create object to access database
	        tableManager getInfo = new tableManager();
	         
	        // Create a resultset to hold the blank search for when the page starts
	        ResultSet info = tableManager.selectInventory("", "");

		    ourTable = new JTable(DbUtils.resultSetToTableModel(info));
		    // Scrollpane in case we have more records than we want to show up
		    scrollPane = new JScrollPane(ourTable);
		    // Adds scrollPane to our listPanel
		    listPanel.add(scrollPane);
		     
		    // Listens in to what we click on in the table and gets the index value of that item
		    ourTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	            public void valueChanged(ListSelectionEvent event) {
	                // Check to make sure table is not being reloaded
	                if (ourTable.getSelectedRow() > 0) {
	                	// Gets ID value of row
	                    selectedValue = ourTable.getValueAt(ourTable.getSelectedRow(), 0).toString();
	                	}
	                }
	            });
		    } catch(SQLException ex) {
	      		// If something goes wrong with the database, 
	      		// display a message to the user.
	      		JOptionPane.showMessageDialog(null, ex.toString());
	      }
	   }
	
	private void buildButtonPanel() {
		// Create a panel for the buttons
		buttonPanel = new JPanel();
		  
		// Create a Submit button and add an action listener
		JButton submitButton = new JButton("Search");
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
			// Get the information from the text fields
	    	String product = infoPanel.getProduct();
			String quantity = infoPanel.getQuantity();
			
			// The code below is to requery the DB to get the updated info and output to the screen
			ResultSet searchInfo = null;
			
			try {
	            // Create an object to instantiate the Connection to the table
				tableManager getInfo = new tableManager();
				//This creates a blank search of the table
				searchInfo = getInfo.selectInventory(product, quantity);
	        } catch (SQLException e1) {
	        	e1.printStackTrace();
			}
	        // Resets the table to the new values
	        ourTable.setModel(DbUtils.resultSetToTableModel(searchInfo));
		}
	}
	
	private class ClearButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Clear information
			infoPanel.clear();
			
			// Clear search results
	    	ResultSet searchInfo = null;
	            
	        try {
	           	// Create an object to instantiate the Connection to the table
				tableManager getInfo = new tableManager();
				// This creates a blank search of the table
				searchInfo = tableManager.selectInventory("", "");
	        } catch (SQLException e1) {
				e1.printStackTrace();
			}
		    // Resets the table to the search criteria given above
	        ourTable.setModel(DbUtils.resultSetToTableModel(searchInfo));
		}
	}
	   
	private class ExitButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Exit the application
			dispose();
		}
	}
}