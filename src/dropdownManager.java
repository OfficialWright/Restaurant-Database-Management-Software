import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dropdownManager {
	// Connecting to database
	public final String DB_URL = "jdbc:ucanaccess://Restaurant.accdb";
	private static Connection conn;
	
	public dropdownManager() throws SQLException {
		conn = DriverManager.getConnection(DB_URL);
	}
	// Select dropdown data
	public static ResultSet selectProduct() throws SQLException {
		String ourSQLSelect = "SELECT * from productTable";
		
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
		
		// Executes the query
		ResultSet positionResults = prepStmt.executeQuery();
		return positionResults;
	}
	// Select dropdown data
	public static ResultSet selectTable() throws SQLException {
		String ourSQLSelect = "SELECT * from tableTable";
		
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
		
		// Executes the query
		ResultSet positionResults = prepStmt.executeQuery();
		return positionResults;
	}
	// Select dropdown data
	public static ResultSet selectUser() throws SQLException {
		String ourSQLSelect = "SELECT * from employeeTable";
		
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
		
		// Executes the query
		ResultSet positionResults = prepStmt.executeQuery();
		return positionResults;
	}
	// Select dropdown data
	public static ResultSet selectPermission() throws SQLException {
		String ourSQLSelect = "SELECT * from permissionTable";
		
		// Create a Statement object
		PreparedStatement prepStmt = conn.prepareStatement(ourSQLSelect);
		
		// Executes the query
		ResultSet permissionResults = prepStmt.executeQuery();
		return permissionResults;
	}
}
