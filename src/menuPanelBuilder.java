import java.awt.GridLayout;

import javax.swing.*;

public class menuPanelBuilder extends JPanel {
	private JTextField nameTextField;
	private JTextField priceTextField;
	
	public menuPanelBuilder(String thePrompt) {
		// Create labels and text fields
		JLabel namePrompt = new JLabel("Product Name");
		nameTextField = new JTextField(60);
		  
		JLabel pricePrompt = new JLabel("Price");
		priceTextField = new JTextField(60);
		
		// Create a grid layout manager 
		setLayout(new GridLayout(5, 1));   
		setBorder(BorderFactory.createTitledBorder(thePrompt));
		
		// Add the labels and text fields to panel
		add(namePrompt);
		add(nameTextField);
		  
		add(pricePrompt);
		add(priceTextField);
	}
	// Setters
	public void setName(String name) {
		nameTextField.setText(name);
	}
	
	public void setPrice(String price) {
		priceTextField.setText(price);
	}
	
	// Getters
	public String getName() {
		return nameTextField.getText();
	}
	
	public String getPrice() {
		return priceTextField.getText();
	}
	
	// Clearing data
	public void clear() {
		nameTextField.setText("");
		priceTextField.setText("");
	}
}
