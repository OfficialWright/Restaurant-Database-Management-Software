import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class tableManager {
	// Connecting to database
	public final String DB_URL = "jdbc:ucanaccess://Restaurant.accdb";
	private static Connection conn;
	
	public tableManager() throws SQLException {
		conn = DriverManager.getConnection(DB_URL);
	}
	// Inserting data
	public void insertMenu(String name, String price) throws SQLException {
		String ourSQLInsert = "INSERT INTO productTable (productName, productUnitPrice)"
		   		+  "VALUES (?, ?)";
		
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLInsert);
		
		// Inserting data into question mark from above statement
		prepStmt.setString(1, name);
		prepStmt.setString(2, price);
		
		// Executes the query
		prepStmt.executeUpdate();
		prepStmt.close();
	}
	// Inserting data
	public void insertUser(String first, String last, String phone, String email, String perm) throws SQLException {
		// Selecting permissionkey
		Integer permissionKey = 0;
  		String ourSQLProduct = "SELECT permissionKey from permissionTable where permissionName = ?"; 
  		
  		PreparedStatement prepPerm = conn.prepareStatement(ourSQLProduct);
  		prepPerm.setString(1, perm);
  		ResultSet permResults = prepPerm.executeQuery();
  		
  		while (permResults.next()) {
  			permissionKey = permResults.getInt("permissionKey");
  		}
		
		String ourSQLInsert = "INSERT INTO employeeTable (employeeFirstName, employeeLastName, employeePhone, employeeEmail, permissionKey)"
		   		+  "VALUES (?, ?, ?, ?, ?)";
		
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLInsert);
		
		// Inserting data into question mark from above statement
		prepStmt.setString(1, first);
		prepStmt.setString(2, last);
		prepStmt.setString(3, phone);
		prepStmt.setString(4, email);
		prepStmt.setInt(5, permissionKey);
		
		// Executes the query
		prepStmt.executeUpdate();
		prepStmt.close();
	}
	// Inserting data
	public void insertTable(String name, String location) throws SQLException {
		String ourSQLInsert = "INSERT INTO tableTable (tableName, tableLocation)"
		   		+  "VALUES (?, ?)";
		
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLInsert);
		
		// Inserting data into question mark from above statement
		prepStmt.setString(1, name);
		prepStmt.setString(2, location);
		
		// Executes the query
		prepStmt.executeUpdate();
		prepStmt.close();
	}
	// Inserting data
	public void insertInventory(String quantity, String product) throws SQLException {
		// Selecting productkey
		Integer productKey = 0;
  		String ourSQLProduct = "SELECT productKey from productTable where productName = ?"; 
  		
  		PreparedStatement prepProduct = conn.prepareStatement(ourSQLProduct);
  		prepProduct.setString(1, product);
  		ResultSet productResults = prepProduct.executeQuery();
  		
  		while (productResults.next()) {
  			productKey = productResults.getInt("productKey");
  		}
		
		String ourSQLInsert = "INSERT INTO inventoryTable (inventoryQuantantyLevel, productKey)"
		   		+  "VALUES (?, ?)";
		
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLInsert);
		
		// Inserting data into question mark from above statement
		prepStmt.setString(1, quantity);
		prepStmt.setInt(2, productKey);
		
		// Executes the query
		prepStmt.executeUpdate();
		prepStmt.close();
	}
	// Inserting data
	public void insertOrder(String quantity, String price, String table, String user, String product) throws SQLException {
		// Selecting productkey
		Integer productKey = 0;
  		String ourSQLProduct = "SELECT productKey from productTable where productName = ?"; 
  		
  		PreparedStatement prepProduct = conn.prepareStatement(ourSQLProduct);
  		prepProduct.setString(1, product);
  		ResultSet productResults = prepProduct.executeQuery();
  		
  		while (productResults.next()) {
  			productKey = productResults.getInt("productKey");
  		}
  		// Selecting tablekey
  		Integer tableKey = 0;
  		String ourSQLTable = "SELECT tableKey from tableTable where tableName = ?"; 
  		
  		PreparedStatement prepTable = conn.prepareStatement(ourSQLTable);
  		prepTable.setString(1, table);
  		ResultSet tableResults = prepTable.executeQuery();
  		
  		while (tableResults.next()) {
  			tableKey = tableResults.getInt("tableKey");
  		}
  		// Selecting employeekey
  		Integer employeeKey = 0;
  		String ourSQLEmployee = "SELECT employeeKey from employeeTable where employeeFirstName = ?"; 
  		
  		PreparedStatement prepEmployee = conn.prepareStatement(ourSQLEmployee);
  		prepEmployee.setString(1, user);
  		ResultSet employeeResults = prepEmployee.executeQuery();
  		
  		while (employeeResults.next()) {
  			employeeKey = employeeResults.getInt("employeeKey");
  		}
		
		String ourSQLInsert = "INSERT INTO orderTable (orderStatus, tableKey, employeeKey)"
		   		+  "VALUES (?, ?, ?)";
		
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLInsert);
		
		// Inserting data into question mark from above statement
		prepStmt.setInt(1, 1);
		prepStmt.setInt(2, tableKey);
		prepStmt.setInt(3, employeeKey);
		
		// Executes the query
		prepStmt.executeUpdate();
		prepStmt.close();
		
		// Selecting orderkey
  		Integer orderKey = 0;
  		String ourSQLOrder = "SELECT orderKey from orderTable where orderStatus = ? AND tableKey = ? AND employeeKey = ?"; 
  		
  		PreparedStatement prepOrder = conn.prepareStatement(ourSQLOrder);
  		prepOrder.setInt(1, 1);
  		prepOrder.setInt(2, tableKey);
  		prepOrder.setInt(3, employeeKey);
  		ResultSet orderResults = prepOrder.executeQuery();
  		
  		while (orderResults.next()) {
  			orderKey = orderResults.getInt("orderKey");
  		}
  		
		String ourSQLNewInsert = "INSERT INTO orderDetailTable (orderDetailQuantity, orderDetailUnitPrice, productKey, orderKey)"
		   		+  "VALUES (?, ?, ?, ?)";
		
		// Create a Statement object
		PreparedStatement prepNewStmt = conn.prepareStatement(ourSQLNewInsert);
		
		// Inserting data into question mark from above statement
		prepNewStmt.setString(1, quantity);
		prepNewStmt.setString(2, price);
		prepNewStmt.setInt(3, productKey);
		prepNewStmt.setInt(4, orderKey);
		
		// Executes the query
		prepNewStmt.executeUpdate();
		prepNewStmt.close();
	}
	// Updating data
	public static ResultSet selectUpdateMenu(String userID) 
	           throws SQLException {
		// Creates the SQL Statement
		String ourSQLSelect = "SELECT productKey as Key,  ProductName as Name, ProductUnitPrice as Price"
				+ " from productTable where productKey Like ?";
		          
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
		
		// Inserting data into question mark from above statement
		prepStmt.setString(1, userID);
		
		// Executes the query
		ResultSet userResults = prepStmt.executeQuery();
		return userResults;
	}
	// Update data
	public void updateMenu(String name, String price, double userID) throws SQLException {
		String ourSQLUpdate = "update productTable set ProductName = ?, "
				+ "ProductUnitPrice =  ? WHERE productKey = ?";
		
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLUpdate);
	
		// Inserting data into question mark from above statement
		prepStmt.setString(1, name);
		prepStmt.setString(2, price);
		prepStmt.setDouble(3, userID);
		
		// Executes the query
		prepStmt.executeUpdate();
		prepStmt.close();
	}
	// Selecting data
	public static ResultSet selectMenu(String name, String price) throws SQLException {
		String ourSQLSelect = "SELECT productKey as Key, ProductName as Name, ProductUnitPrice as Price"
				+ " from productTable where ProductName Like ? AND"
				+ " ProductUnitPrice Like ?";  
              
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
	
		// Inserting data into question mark from above statement
		prepStmt.setString(1, "%" + name + "%");
		prepStmt.setString(2, "%" + price + "%");
	   
		// Executes the query
		ResultSet userResults = prepStmt.executeQuery();
		return userResults;
	}
	// Delete data
	public void deleteMenu(double userID) throws SQLException {
		String ourSQLUpdate = "delete from productTable WHERE productKey = ?";
		
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLUpdate);
	
		// Inserting data into question mark from above statement
		prepStmt.setDouble(1, userID);
		
		// Executes the query
		prepStmt.executeUpdate();
		prepStmt.close();
	}
	// Select data
	public static ResultSet selectUpdateTable(String userID) 
	           throws SQLException {
		// Creates the SQL Statement
		String ourSQLSelect = "SELECT tableKey as Key,  tableName as Name, tableLocation as Location"
				+ " from tableTable where tableKey Like ?";
		          
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
		
		// Inserting data into question mark from above statement
		prepStmt.setString(1, userID);
		
		// Executes the query
		ResultSet userResults = prepStmt.executeQuery();
		return userResults;
	}
	// Updating data
	public void updateTable(String name, String location, double userID) throws SQLException {
		String ourSQLUpdate = "update tableTable set tableName = ?, "
				+ "tableLocation =  ? WHERE tableKey = ?";
		
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLUpdate);
	
		// Inserting data into question mark from above statement
		prepStmt.setString(1, name);
		prepStmt.setString(2, location);
		prepStmt.setDouble(3, userID);
		
		// Executes the query
		prepStmt.executeUpdate();
		prepStmt.close();
	}
	// Selecting data
	public static ResultSet selectTable(String name, String location) throws SQLException {
		String ourSQLSelect = "SELECT tableKey as Key, tableName as Name, tableLocation as Location"
				+ " from tableTable where tableName Like ? AND"
				+ " tableLocation Like ?";  
              
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
	
		// Inserting data into question mark from above statement
		prepStmt.setString(1, "%" + name + "%");
		prepStmt.setString(2, "%" + location + "%");
	   
		// Executes the query
		ResultSet userResults = prepStmt.executeQuery();
		return userResults;
	}
	// Delete data
	public void deleteTable(double userID) throws SQLException {
		String ourSQLUpdate = "delete from tableTable WHERE tableKey = ?";
		
		// Create a Statement object.
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLUpdate);
	
		// Inserting data into question mark from above statement
		prepStmt.setDouble(1, userID);
		
		// Executes the query
		prepStmt.executeUpdate();
		prepStmt.close();
	}
	// Select data
	public static ResultSet selectUpdateUser(String userID) 
	           throws SQLException {
		String ourSQLSelect = "SELECT employeeKey as Key, employeeFirstName as First, employeeLastName as Last,"
				+ " employeePhone as Phone, employeeEmail as Email, permissionName as Permission from employeeTable"
				+ " inner join permissionTable on employeeTable.permissionKey=permissionTable.permissionKey where employeeKey Like ?";
		          
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
		
		// Inserting data into question mark from above statement
		prepStmt.setString(1, userID);
		
		// Executes the query
		ResultSet userResults = prepStmt.executeQuery();
		return userResults;
	}
	// Update data
	public void updateUser(String firstName, String lastName, String phone, String email, String perm, double userID) throws SQLException {
  		// Select permkey
		Integer permKey = 0;
  		String ourSQLPerm = "SELECT permissionKey from permissionTable"
  				+ " where permissionName = ?"; 
  		
  		PreparedStatement prepPerm = conn.prepareStatement(ourSQLPerm);
  		prepPerm.setString(1, perm);
  		ResultSet permResults = prepPerm.executeQuery();
  		
  		while (permResults.next()) {
  			permKey = permResults.getInt("permissionKey");
  		}
		
		String ourSQLUpdate = "update employeeTable set employeeFirstName = ?, "
				+ "employeeLastName =  ?, employeePhone = ?, employeeEmail = ?, permissionKey = ? WHERE employeeKey = ?";
		
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLUpdate);
	
		// Inserting data into question mark from above statement
		prepStmt.setString(1, firstName);
		prepStmt.setString(2, lastName);
		prepStmt.setString(3, phone);
		prepStmt.setString(4, email);
		prepStmt.setInt(5, permKey);
		prepStmt.setDouble(6, userID);
		
		// Executes the query
		prepStmt.executeUpdate();
		prepStmt.close();
	}
	// Selecting data
	public static ResultSet selectUser(String first, String last, String phone, String email, String perm) throws SQLException {
		String ourSQLSelect = "SELECT employeeKey as Key, employeeFirstName as First, employeeLastName as Last,"
				+ " employeePhone as Phone, employeeEmail as Email, permissionName as Permission from employeeTable"
				+ " inner join permissionTable on employeeTable.permissionKey=permissionTable.permissionKey where employeeFirstName Like ? AND"
				+ " employeeLastName Like ? AND employeePhone Like ? AND employeeEmail Like ? AND permissionName Like ?";  
        
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
	
		// Inserting data into question mark from above statement
		prepStmt.setString(1, "%" + first + "%");
		prepStmt.setString(2, "%" + last + "%");
		prepStmt.setString(3, "%" + phone + "%");
		prepStmt.setString(4, "%" + email + "%");
		prepStmt.setString(5, "%" + perm + "%");
	   
		// Executes the query
		ResultSet userResults = prepStmt.executeQuery();
		return userResults;
	}
	// Delete data
	public void deleteUser(double userID) throws SQLException {
		String ourSQLUpdate = "delete from employeeTable WHERE employeeKey = ?";
		
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLUpdate);
	
		// Inserting data into question mark from above statement
		prepStmt.setDouble(1, userID);
		
		// Executes the query
		prepStmt.executeUpdate();
		prepStmt.close();
	}
	// Select data
	public static ResultSet selectUpdateInventory(String userID) 
	           throws SQLException {
		String ourSQLSelect = "SELECT inventoryKey as Key,  productName as Product, inventoryQuantantyLevel as Quantity"
				+ " from inventoryTable inner join productTable on inventoryTable.productKey=productTable.productKey where inventoryKey Like ?";
		          
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
		
		// Inserting data into question mark from above statement
		prepStmt.setString(1, userID);
		
		// Executes the query
		ResultSet userResults = prepStmt.executeQuery();
		return userResults;
	}
	// Update data
	public void updateInventory(String product, String quantity, double userID) throws SQLException {
		// Select productkey
		Integer productKey = 0;
  		String ourSQLProduct = "SELECT productKey from productTable"
  				+ " WHERE productName = ?"; 
  		
  		PreparedStatement prepProduct = conn.prepareStatement(ourSQLProduct);
  		prepProduct.setString(1, product);
  		ResultSet productResults = prepProduct.executeQuery();
  		
  		while (productResults.next()) {
  			productKey = productResults.getInt("productKey");
  		}
		
		String ourSQLUpdate = "update inventoryTable set productKey = ?,"
				+ " inventoryQuantantyLevel =  ? WHERE inventoryKey = ?";
		
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLUpdate);
	
		// Inserting data into question mark from above statement
		prepStmt.setInt(1, productKey);
		prepStmt.setString(2, quantity);
		prepStmt.setDouble(3, userID);
		
		// Executes the query
		prepStmt.executeUpdate();
		prepStmt.close();
	}
	// Selecting data
	public static ResultSet selectInventory(String product, String quantity) throws SQLException {
		String ourSQLSelect = "SELECT inventoryKey as Key, productName as Product, inventoryQuantantyLevel as Quantity"
				+ " from inventoryTable inner join productTable on inventoryTable.productKey=productTable.productKey where productName Like ? AND"
				+ " inventoryQuantantyLevel Like ?";  
     
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
	
		// Inserting data into question mark from above statement
		prepStmt.setString(1, "%" + product + "%");
		prepStmt.setString(2, "%" + quantity + "%");
	   
		// Executes the query
		ResultSet userResults = prepStmt.executeQuery();
		return userResults;
	}
	// Delete data
	public void deleteInventory(double userID) throws SQLException {
		String ourSQLUpdate = "delete from inventoryTable WHERE inventoryKey = ?";
		
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLUpdate);
	
		// Inserting data into question mark from above statement
		prepStmt.setDouble(1, userID);
		
		// Executes the query
		prepStmt.executeUpdate();
		prepStmt.close();
	}
	// Select data
	public static ResultSet selectUpdateOrder(String userID) 
	           throws SQLException {
		String ourSQLSelect = "SELECT orderDetailKey as Key,  productName as Product, orderDetailQuantity as Quantity,"
				+ " orderDetailUnitPrice as Price, tableName as Table, employeeFirstName as User"
				+ " from orderDetailTable inner join orderTable on orderDetailTable.orderKey=orderTable.orderKey"
				+ " inner join productTable on orderDetailTable.productKey=productTable.productKey"
				+ " inner join tableTable on orderTable.tableKey=tableTable.tableKey"
				+ " inner join employeeTable on orderTable.employeeKey=employeeTable.employeeKey where orderDetailKey Like ?";
		          
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
		
		// Inserting data into question mark from above statement
		prepStmt.setString(1, userID);
		
		// Executes the query
		ResultSet userResults = prepStmt.executeQuery();
		return userResults;
	}
	// Update data
	public void updateOrder(String product, String quantity, String price, String table, String user, double userID) throws SQLException {
		// Select productkey
		Integer productKey = 0;
  		String ourSQLProduct = "SELECT productKey from productTable"
  				+ " WHERE productName = ?"; 
  		
  		PreparedStatement prepProduct = conn.prepareStatement(ourSQLProduct);
  		prepProduct.setString(1, product);
  		ResultSet productResults = prepProduct.executeQuery();
  		
  		while (productResults.next()) {
  			productKey = productResults.getInt("productKey");
  		}
		
		String ourSQLUpdate = "update orderDetailTable set productKey = ?,"
				+ " orderDetailQuantity =  ?, orderDetailUnitPrice = ? WHERE orderDetailKey = ?";
		
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLUpdate);
	
		// Inserting data into question mark from above statement
		prepStmt.setInt(1, productKey);
		prepStmt.setString(2, quantity);
		prepStmt.setString(3, price);
		prepStmt.setDouble(4, userID);
		
		// Executes the query
		prepStmt.executeUpdate();
		prepStmt.close();
		
		// Select orderkey
  		Integer orderKey = 0;
  		String ourSQLOrder = "SELECT orderKey from orderDetailTable inner join orderTable on orderDetailTable.orderKey=orderTable.orderKey"
  				+ " WHERE orderDetailKey = ?"; 
  		
  		PreparedStatement prepOrder = conn.prepareStatement(ourSQLOrder);
  		prepOrder.setDouble(1, userID);
  		ResultSet orderResults = prepOrder.executeQuery();
  		
  		while (orderResults.next()) {
  			orderKey = orderResults.getInt("orderKey");
  		}
  		
  		// Select tablekey
		Integer tableKey = 0;
  		String ourSQLTable = "SELECT tableKey from tableTable"
  				+ " WHERE tableName = ?"; 
  		
  		PreparedStatement prepTable = conn.prepareStatement(ourSQLTable);
  		prepTable.setString(1, table);
  		ResultSet tableResults = prepTable.executeQuery();
  		
  		while (tableResults.next()) {
  			tableKey = tableResults.getInt("tableKey");
  		}
  		
  		// Select userkey
		Integer userKey = 0;
  		String ourSQLUser = "SELECT employeeKey from employeeTable"
  				+ " WHERE employeeFirstName = ?"; 
  		
  		PreparedStatement prepUser = conn.prepareStatement(ourSQLUser);
  		prepUser.setString(1, user);
  		ResultSet userResults = prepUser.executeQuery();
  		
  		while (userResults.next()) {
  			userKey = userResults.getInt("employeeKey");
  		}
		
		String ourSQLNewUpdate = "update orderTable set tableKey = ?,"
				+ " employeeKey =  ? WHERE orderKey = ?";
		
		// Create a Statement object
		PreparedStatement prepNewStmt = conn.prepareStatement(ourSQLNewUpdate);
	
		// Inserting data into question mark from above statement
		prepNewStmt.setInt(1, tableKey);
		prepNewStmt.setInt(2, userKey);
		prepNewStmt.setInt(3, orderKey);
		
		// Executes the query
		prepNewStmt.executeUpdate();
		prepNewStmt.close();
	}
	// Selecting data
	public static ResultSet selectOrder(String product, String quantity, String price, String table, String user) throws SQLException {
		String ourSQLSelect = "SELECT orderDetailKey as Key, productName as Product, orderDetailQuantity as Quantity,"
				+ " orderDetailUnitPrice as Price, tableName as Table, employeeFirstName as User"
				+ " from orderDetailTable inner join orderTable on orderDetailTable.orderKey=orderTable.orderKey"
				+ " inner join productTable on orderDetailTable.productKey=productTable.productKey"
				+ " inner join tableTable on orderTable.tableKey=tableTable.tableKey"
				+ " inner join employeeTable on orderTable.employeeKey=employeeTable.employeeKey"
				+ " where productName Like ? AND orderDetailQuantity Like ? AND orderDetailUnitPrice Like ?"
				+ " AND tableName Like ? AND employeeFirstName Like ?";  
     
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
	
		// Inserting data into question mark from above statement
		prepStmt.setString(1, "%" + product + "%");
		prepStmt.setString(2, "%" + quantity + "%");
		prepStmt.setString(3, "%" + price + "%");
		prepStmt.setString(4, "%" + table + "%");
		prepStmt.setString(5, "%" + user + "%");
	   
		// Executes the query
		ResultSet userResults = prepStmt.executeQuery();
		return userResults;
	}
	// Delete data
	public void deleteOrder(double userID) throws SQLException {
		// Select orderkey
		Integer orderKey = 0;
  		String ourSQLOrder = "SELECT orderKey from orderDetailTable inner join orderTable on orderDetailTable.orderKey=orderTable.orderKey"
  				+ " WHERE orderDetailKey = ?"; 
  		
  		PreparedStatement prepOrder = conn.prepareStatement(ourSQLOrder);
  		prepOrder.setDouble(1, userID);
  		ResultSet orderResults = prepOrder.executeQuery();
  		
  		while (orderResults.next()) {
  			orderKey = orderResults.getInt("orderKey");
  		}
		
		String ourSQLNewUpdate = "delete from orderTable WHERE orderKey = ?";
		
		// Create a Statement object
		PreparedStatement prepNewStmt = conn.prepareStatement(ourSQLNewUpdate);
	
		// Inserting data into question mark from above statement
		prepNewStmt.setInt(1, orderKey);
		
		// Executes the query
		prepNewStmt.executeUpdate();
		prepNewStmt.close();
		
		String ourSQLUpdate = "delete from orderDetailTable WHERE orderDetailKey = ?";
		
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLUpdate);
	
		// Inserting data into question mark from above statement
		prepStmt.setDouble(1, userID);
		
		// Executes the query
		prepStmt.executeUpdate();
		prepStmt.close();
	}
}
