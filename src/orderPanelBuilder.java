import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class orderPanelBuilder extends JPanel {
	private JTextField quantityTextField;
	private JTextField priceTextField;
	private JComboBox<String> productComboField;
	private JComboBox<String> tableComboField;
	private JComboBox<String> userComboField;
	
	public orderPanelBuilder(String thePrompt) {
		// Create labels and text fields
		JLabel productPrompt = new JLabel("Product");
		productComboField = new JComboBox<String>();
		productComboField.addItem("");
		
		JLabel tablePrompt = new JLabel("Table");
		tableComboField = new JComboBox<String>();
		tableComboField.addItem("");
		
		JLabel userPrompt = new JLabel("Employee");
		userComboField = new JComboBox<String>();
		userComboField.addItem("");
		
		JLabel quantityPrompt = new JLabel("Quantity");
		quantityTextField = new JTextField(60);
		
		JLabel pricePrompt = new JLabel("Price");
		priceTextField = new JTextField(60);
		
		try {
			dropdownManager getInfo = new dropdownManager();
		    
			ResultSet productInfo = dropdownManager.selectProduct();
			ResultSet tableInfo = dropdownManager.selectTable();
			ResultSet userInfo = dropdownManager.selectUser();
		    
		    while (productInfo.next()) {
		    	
		    	String product = productInfo.getString("productName");
		    	productComboField.addItem(product);
		    }
		    while (tableInfo.next()) {
		    	
		    	String table = tableInfo.getString("tableName");
		    	tableComboField.addItem(table);
		    }
		    while (userInfo.next()) {
		    	
		    	String user = userInfo.getString("employeeFirstName");
		    	userComboField.addItem(user);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Create a grid layout manager 
		setLayout(new GridLayout(11, 1));   
		setBorder(BorderFactory.createTitledBorder(thePrompt));
		
		// Add the labels and text fields to panel
		add(productPrompt);
		add(productComboField);
		
		add(quantityPrompt);
		add(quantityTextField);
		
		add(pricePrompt);
		add(priceTextField);
		
		add(tablePrompt);
		add(tableComboField);
		
		add(userPrompt);
		add(userComboField);
	}
	// Setters
	public void setProduct(String product) {
		productComboField.setSelectedItem(product);
	}
	public void setQuantity(String quantity) {
		quantityTextField.setText(quantity);
	}
	public void setPrice(String price) {
		priceTextField.setText(price);
	}
	public void setTable(String table) {
		tableComboField.setSelectedItem(table);
	}
	public void setUser(String user) {
		userComboField.setSelectedItem(user);
	}
	
	// Getters
	public String getProduct() {
		return productComboField.getSelectedItem().toString();
	}
	public String getQuantity() {
		return quantityTextField.getText();
	}
	public String getPrice() {
		return priceTextField.getText();
	}
	public String getTable() {
		return tableComboField.getSelectedItem().toString();
	}
	public String getUser() {
		return userComboField.getSelectedItem().toString();
	}
	
	// Clearing data
	public void clear() {
		productComboField.setSelectedIndex(0);
		quantityTextField.setText("");
		priceTextField.setText("");
		tableComboField.setSelectedIndex(0);
		userComboField.setSelectedIndex(0);
	}
}
