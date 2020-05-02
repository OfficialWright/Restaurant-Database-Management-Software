import java.awt.GridLayout;

import javax.swing.*;

public class tablePanelBuilder extends JPanel {
	private JTextField nameTextField;
	private JTextField locationTextField;
	
	public tablePanelBuilder(String thePrompt) {
		// Create labels and text fields
		JLabel namePrompt = new JLabel("Table Name");
		nameTextField = new JTextField(60);
		  
		JLabel locationPrompt = new JLabel("Location");
		locationTextField = new JTextField(60);
		
		// Create a grid layout manager 
		setLayout(new GridLayout(5, 1));   
		setBorder(BorderFactory.createTitledBorder(thePrompt));
		
		// Add the labels and text fields to panel
		add(namePrompt);
		add(nameTextField);
		  
		add(locationPrompt);
		add(locationTextField);
	}
	// Setters
	public void setName(String name) {
		nameTextField.setText(name);
	}
	
	public void setLoc(String loc) {
		locationTextField.setText(loc);
	}
	
	// Getters
	public String getName() {
		return nameTextField.getText();
	}
	
	public String getLoc() {
		return locationTextField.getText();
	}
	
	// Clearing data
	public void clear() {
		nameTextField.setText("");
		locationTextField.setText("");
	}
}
