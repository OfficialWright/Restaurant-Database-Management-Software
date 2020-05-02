import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class Restaurant_Software extends JFrame {
	// Initialization
	// Window sizing
	private final int WINDOW_WIDTH = 1020;
	private final int WINDOW_HEIGHT = 375;
	// Components for window
	private JPanel panel = new JPanel();
	private JPanel panelUser = new JPanel();
	private JPanel panelMenu = new JPanel();
	private JPanel panelInventory = new JPanel();
	private JPanel panelTable = new JPanel();
	private JPanel panelOrder = new JPanel();
	// Buttons
	private JButton selectMenu = new JButton("Select/Update/Delete Menu");
	private JButton insertMenu = new JButton("Insert Menu");
	private JButton selectUser = new JButton("Select/Update/Delete User");
	private JButton insertUser = new JButton("Insert User");
	private JButton selectOrder = new JButton("Select/Update/Delete Order");
	private JButton insertOrder = new JButton("Insert Order");
	private JButton selectTable = new JButton("Select/Update/Delete Table");
	private JButton insertTable = new JButton("Insert Table");
	private JButton selectInventory = new JButton("Select/Update/Delete Inventory");
	private JButton insertInventory = new JButton("Insert Inventory");
	private JButton reportInventory = new JButton("Report Inventory");
	
	public Restaurant_Software() {
		// Set the title.
		setTitle("Restaurant Software");

		// Specify what happens when the close button is clicked.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Build the panels that contains the buttons.
		buildUserPanel();
		buildMenuPanel();
		buildInventoryPanel();
		buildTablePanel();
		buildOrderPanel();
		
		// Main panel layout
		panel.setLayout(new GridLayout(5, 1));
		
		// Add the panels to main panel
		panel.add(panelUser);
		panel.add(panelMenu);
		panel.add(panelInventory);
		panel.add(panelTable);
		panel.add(panelOrder);
		
		// Adding main panel to content pane
		add(panel);
		
		// Size and display the window.
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setVisible(true);
	}
	
	private void buildUserPanel() {
		// Panel layout and title
		panelUser.setLayout(new GridLayout(1, 2));
		panelUser.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(10,20,10,20), "User", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		// Adding buttons to panel
		panelUser.add(insertUser);
		panelUser.add(selectUser);
		
		// Opens new window based on button clicked
		insertUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userInsert insertUser = new userInsert();
			}
		});
		
		selectUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userSelect selectUser = new userSelect();
			}
		});
	}
	
	private void buildMenuPanel() {
		// Panel layout and title
		panelMenu.setLayout(new GridLayout(1, 2));
		panelMenu.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(10,20,10,20), "Menu", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		// Adding buttons to panel
		panelMenu.add(insertMenu);
		panelMenu.add(selectMenu);
		
		// Opens new window based on button clicked
		insertMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuInsert insertMenu = new menuInsert();
			}
		});
		
		selectMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuSelect selectMenu = new menuSelect();
			}
		});
	}
	
	private void buildInventoryPanel() {
		// Panel layout and title
		panelInventory.setLayout(new GridLayout(1, 3));
		panelInventory.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(10,20,10,20), "Inventory", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		// Adding buttons to panel
		panelInventory.add(insertInventory);
		panelInventory.add(selectInventory);
		panelInventory.add(reportInventory);
		
		// Opens new window based on button clicked
		insertInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryInsert insertInventory = new inventoryInsert();
			}
		});
		
		selectInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventorySelect selectInventory = new inventorySelect();
			}
		});
		
		reportInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryReport reportInventory = new inventoryReport();
			}
		});
	}
	
	private void buildTablePanel() {
		// Panel layout and title
		panelTable.setLayout(new GridLayout(1, 2));
		panelTable.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(10,20,10,20), "Table", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		// Adding buttons to panel
		panelTable.add(insertTable);
		panelTable.add(selectTable);
		
		// Opens new window based on button clicked
		insertTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableInsert insertTable = new tableInsert();
			}
		});
		
		selectTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableSelect selectTable = new tableSelect();
			}
		});
	}
	
	private void buildOrderPanel() {
		// Panel layout and title
		panelOrder.setLayout(new GridLayout(1, 2));
		panelOrder.setBorder(BorderFactory.createTitledBorder(new EmptyBorder(10,20,10,20), "Order", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		// Adding buttons to panel
		panelOrder.add(insertOrder);
		panelOrder.add(selectOrder);

		// Opens new window based on button clicked
		insertOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderInsert insertOrder = new orderInsert();
			}
		});
		
		selectOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderSelect selectOrder = new orderSelect();
			}
		});
	}
	
	public static void main(String[] args) {
		Restaurant_Software start = new Restaurant_Software();
	}
}
