import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.proteanit.sql.DbUtils;

public class inventorySelect extends JFrame {
	inventoryPanelBuilder updatePanel;
	inventoryPanelBuilder searchPanel;
	JPanel buttonPanel;
	JPanel searchButtonPanel;
	JPanel listPanel;
	String searchString = "";
	JScrollPane scrollPane;
	JList nameList;
	JTable ourTable;
	String selectedValue;

	public inventorySelect() {
		// Set the window title
		setTitle("Select/Update/Delete Inventory");
  
		// Specify an action for the close button
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  
		// Create a panelBuilder object
		updatePanel = new inventoryPanelBuilder("Update Inventory");
		searchPanel = new inventoryPanelBuilder("Search Inventory");
  
		// Build the buttonPanel object
		buildButtonPanel();
		buildSearchButtonPanel();
		
		// Build the listPanel object
		buildListPanel();
  
		// Create a BorderLayout manager
		setLayout(new GridBagLayout()); 
		GridBagConstraints gbc = new GridBagConstraints();
  
		gbc.gridx = 0;
		gbc.gridy = 0;
		// Allows fill for both horizontal and vertical
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 0;
		gbc.gridheight = 3;
		// Adds search panel with settings for GridBag above
		add(searchPanel, gbc);
  
		gbc.gridheight = 1;
		gbc.weighty = 0;
		gbc.gridx = 0;
		gbc.gridy = 3;
		// Adds search button panel with settings for GridBag above
		add(searchButtonPanel, gbc);
  
		// Creates padding between columns
		gbc.insets = new Insets(0,10,0,0);
		gbc.weighty = .05;
		gbc.gridx = 1;
		gbc.gridy = 0;
		// Adds list panel with settings for GridBag above
		add(listPanel, gbc);
  
		gbc.weighty = .05;
		gbc.gridx = 1;
		gbc.gridy = 2;
		// Adds update panel with settings for GridBag above
		add(updatePanel, gbc);
  
		gbc.weighty = 0;
		gbc.gridx = 1;
		gbc.gridy = 3;
		// Adds button panel with settings for GridBag above
		add(buttonPanel, gbc);
  
		// Pack and display the window
		pack();
		setSize(1200, 650);
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
			Dimension dim = new Dimension(600, 125);

			// Scrollpane in case we have more records than we want to show up
			scrollPane = new JScrollPane(ourTable);
 
			// Set the size of the scroll pane
			scrollPane.setPreferredSize(dim);
 
			// Adds scrollPane to our listPanel
			listPanel.add(scrollPane);
 
			// Listens in to what we click on in the table and gets the index value of that item
				ourTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
					public void valueChanged(ListSelectionEvent event) {
						// Check to make sure table is not being reloaded
							if (ourTable.getSelectedRow() >= 0) {
								// Gets ID value of row
								selectedValue = ourTable.getValueAt(ourTable.getSelectedRow(), 0).toString();
								try {
									ResultSet updateInfo = null;
									// Create an object to instantiate the Connection to the table
									tableManager getInfo = new tableManager();
									//This creates a blank search of the table
									updateInfo = tableManager.selectUpdateInventory(selectedValue);

									while (updateInfo.next()) {
										updatePanel.setProduct(updateInfo.getString("Product"));
										updatePanel.setQuantity(updateInfo.getString("Quantity"));
									}
								} catch (SQLException e1) {
									e1.printStackTrace();
								}
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
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new SubmitButtonListener());
  
		// Create a Clear button and add an action listener
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ClearButtonListener());
		
		// Create a Delete button and add an action listener
	    JButton deleteButton = new JButton("Delete");
	    deleteButton.addActionListener(new DeleteButtonListener());

		// Create a Exit button and add an action listener
		JButton exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitButtonListener());
  
		// Add the buttons to the panel
		buttonPanel.add(submitButton);
		buttonPanel.add(clearButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(exitButton);
	}
   
	private void buildSearchButtonPanel() {
		// Create a panel for the buttons
		searchButtonPanel = new JPanel();
  
		// Create a Search button and add an action listener
		JButton searchButton = new JButton("Search");
		searchButton.addActionListener(new SearchButtonListener());
  
		// Add the buttons to the panel
		searchButtonPanel.add(searchButton);
	}
   
	private class SubmitButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Get the information from the text fields
			String product = updatePanel.getProduct();
			String quantity = updatePanel.getQuantity();
			// Parse value to double so it can be used in sql query
			Double selValue = Double.parseDouble(selectedValue);

			tableManager updater;
			try {
				// Create object and run update code in database
				updater = new tableManager();
				updater.updateInventory(product, quantity, selValue);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

			// The code below is to requery the DB to get the updated info and output to the screen
			ResultSet searchInfo = null;

			try {
				// Create an object to instantiate the Connection to the table
				tableManager getInfo = new tableManager();
				// This creates a blank search of the table
				searchInfo = getInfo.selectInventory("", "");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			// Resets the table to the new values
			ourTable.setModel(DbUtils.resultSetToTableModel(searchInfo));
		}
	}
   
	private class SearchButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Clears all the input boxes for update area
			updatePanel.clear();
			// Get the information from the text fields.
			String product = searchPanel.getProduct();
			String quantity = searchPanel.getQuantity();
			// Create a result set variable to hold the info from the search
			ResultSet searchInfo = null;

			try {
				// Create an object to instantiate the Connection to the table
				tableManager getInfo = new tableManager();
				// This creates a blank search of the table
				searchInfo = tableManager.selectInventory(product, quantity);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			// Resets the table to the search criteria given above
			ourTable.setModel(DbUtils.resultSetToTableModel(searchInfo));
		}
	}
   
	private class ClearButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Clears all the input boxes
			updatePanel.clear();
			searchPanel.clear();
  
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
	
	private class DeleteButtonListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	        // Parse value to double so it can be used in sql query
	        Double selValue = Double.parseDouble(selectedValue);
	     
	        tableManager updater;
	        
			try {
				// Create object and run update code in database
				updater = new tableManager();
				updater.deleteInventory(selValue);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}

			// The code below is to requery the DB to get the updated info and output to the screen
			ResultSet searchInfo = null;
			
			try {
	            // Create an object to instantiate the Connection to the table
				tableManager getInfo = new tableManager();
				// This creates a blank search of the table
				searchInfo = getInfo.selectInventory("", "");
	        } catch (SQLException e1) {
	        	e1.printStackTrace();
			}
	        // Resets the table to the new values
	        ourTable.setModel(DbUtils.resultSetToTableModel(searchInfo));
	    }
	}
   
	private class ExitButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Exit the application.
			dispose();
		}
	}
}
