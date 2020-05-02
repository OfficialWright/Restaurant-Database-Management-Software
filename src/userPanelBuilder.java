import java.awt.GridLayout;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class userPanelBuilder extends JPanel {
	private JTextField firstNameTextField;
	private JTextField lastNameTextField;
	private JTextField phoneTextField;
	private JTextField emailTextField;
	private JComboBox<String> permLvlComboField;
	
	public userPanelBuilder(String thePrompt) {
		// Create labels and text fields
		JLabel firstNamePrompt = new JLabel("First Name");
		firstNameTextField = new JTextField(60);
		  
		JLabel lastNamePrompt = new JLabel("Last Name");
		lastNameTextField = new JTextField(60);
		  
		JLabel phonePrompt = new JLabel("Phone");
		phoneTextField = new JTextField(60);
		
		JLabel emailPrompt = new JLabel("E-mail");
		emailTextField = new JTextField(60);
		
		JLabel permLvlPrompt = new JLabel("Permissions");
		permLvlComboField = new JComboBox<String>();
		permLvlComboField.addItem("");
		
		try {
			dropdownManager getPerm = new dropdownManager();
		    
		    ResultSet permInfo = dropdownManager.selectPermission();
		    
		    while (permInfo.next()) {
		    	
		    	String perm = permInfo.getString("permissionName");
		    	permLvlComboField.addItem(perm);
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// Create a grid layout manager
		setLayout(new GridLayout(11, 1));   
		setBorder(BorderFactory.createTitledBorder(thePrompt));
		
		// Add the labels and text fields to panel
		add(firstNamePrompt);
		add(firstNameTextField);
		  
		add(lastNamePrompt);
		add(lastNameTextField);
		  
		add(phonePrompt);
		add(phoneTextField);
		  
		add(emailPrompt);
		add(emailTextField);
		
		add(permLvlPrompt);
		add(permLvlComboField);
	}
	// Setters
	public void setFirstName(String first) {
		firstNameTextField.setText(first);
	}
	
	public void setLastName(String last) {
		lastNameTextField.setText(last);
	}
	
	public void setPhone(String phone) {
		phoneTextField.setText(phone);
	}
	
	public void setEmail(String email) {
		emailTextField.setText(email);
	}
	
	public void setPermLvl(String perm) {
		permLvlComboField.setSelectedItem(perm);
	} 
	
	// Getters
	public String getFirstName() {
		return firstNameTextField.getText();
	}
	
	public String getLastName() {
		return lastNameTextField.getText();
	}
	
	public String getPhone() {
		return phoneTextField.getText();
	}
	
	public String getEmail() {
		return emailTextField.getText();
	}
	
	public String getPermLvl() {
		return permLvlComboField.getSelectedItem().toString();
	}
	
	// Clearing data
	public void clear() {
		firstNameTextField.setText("");
		lastNameTextField.setText("");
		phoneTextField.setText("");
		emailTextField.setText("");
		permLvlComboField.setSelectedIndex(0);
	}
}
