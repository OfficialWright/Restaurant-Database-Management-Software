import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class inventoryPanelBuilder extends JPanel {
	private JTextField quantityTextField;
	private JComboBox<String> productComboField;
	
	public inventoryPanelBuilder(String thePrompt) {
		// Create labels and text fields
		JLabel productPrompt = new JLabel("Product");
		productComboField = new JComboBox<String>();
		productComboField.addItem("");
		
		JLabel quantityPrompt = new JLabel("Quantity");
		quantityTextField = new JTextField(60);
		
		try {
			dropdownManager getProduct = new dropdownManager();
		    
		    ResultSet productInfo = dropdownManager.selectProduct();
		    
		    while (productInfo.next()) {
		    	
		    	String product = productInfo.getString("productName");
		    	productComboField.addItem(product);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Create a grid layout manager 
		setLayout(new GridLayout(5, 1));   
		setBorder(BorderFactory.createTitledBorder(thePrompt));
		
		// Add the labels and text fields to panel
		add(productPrompt);
		add(productComboField);
		
		add(quantityPrompt);
		add(quantityTextField);
	}
	// Setters
	public void setProduct(String product) {
		productComboField.setSelectedItem(product);
	}
	public void setQuantity(String quantity) {
		quantityTextField.setText(quantity);
	}
	
	// Getters
	public String getProduct() {
		return productComboField.getSelectedItem().toString();
	}
	public String getQuantity() {
		return quantityTextField.getText();
	}
	
	// Clearing data
	public void clear() {
		quantityTextField.setText("");
		productComboField.setSelectedIndex(0);
	}
}
