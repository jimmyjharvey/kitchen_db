package kitchen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import kitchen.model.Food;
import kitchen.model.Store;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class FoodWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_2;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodWindow window = new FoodWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FoodWindow() throws SQLException{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.setBounds(100, 100, 800, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBounds(37, 346, 106, 25);
		frame.getContentPane().add(btnNewButton);

		JComboBox<Food> comboBox = new JComboBox<Food>();
		comboBox.addItem(new Food(47, "Pizza", "Yummy yummy Pizza", 3, 1, "PizzaPie", 1));
		comboBox.setToolTipText("Select Item ");
		comboBox.setBounds(26, 144, 265, 24);
		comboBox.setSelectedItem(null);
		frame.getContentPane().add(comboBox);
		
		JLabel lblTestLabel = new JLabel("FOOD");
		lblTestLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTestLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblTestLabel.setBounds(26, 83, 200, 50);
		frame.getContentPane().add(lblTestLabel);
		
		textField = new JTextField();
		textField.setBounds(490, 145, 249, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(490, 178, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblOnHand = new JLabel("Qty");
		lblOnHand.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOnHand.setBounds(340, 184, 131, 16);
		frame.getContentPane().add(lblOnHand);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(490, 211, 116, 22);
		frame.getContentPane().add(textField_3);
		
		JLabel lblKaransKitchen = new JLabel("KARAN'S KITCHEN");
		lblKaransKitchen.setHorizontalAlignment(SwingConstants.LEFT);
		lblKaransKitchen.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblKaransKitchen.setBounds(357, 28, 311, 50);
		frame.getContentPane().add(lblKaransKitchen);
		
		JLabel lblMin = new JLabel("Min Qty");
		lblMin.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMin.setBounds(340, 213, 131, 16);
		frame.getContentPane().add(lblMin);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDescription.setBounds(340, 151, 131, 16);
		frame.getContentPane().add(lblDescription);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName.setBounds(340, 112, 131, 16);
		frame.getContentPane().add(lblName);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(490, 111, 116, 22);
		frame.getContentPane().add(textField_2);
		
		JLabel lblStdmeasure = new JLabel("Std Measure");
		lblStdmeasure.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblStdmeasure.setBounds(340, 241, 131, 16);
		frame.getContentPane().add(lblStdmeasure);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(490, 241, 116, 22);
		frame.getContentPane().add(textField_4);
		
		JLabel lblPreferredStore = new JLabel("Preferred Store");
		lblPreferredStore.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPreferredStore.setBounds(340, 270, 151, 16);
		frame.getContentPane().add(lblPreferredStore);

		JComboBox<Store> comboBox_1 = new JComboBox<Store>();
		ResultSet storeQuery = getStores();
		while(storeQuery.next()){
			comboBox_1.addItem(
				new Store(
					storeQuery.getInt("StoreID"), 
					storeQuery.getString("StoreName"), 
					storeQuery.getString("StoreHours"), 
					storeQuery.getString("ClubCardNumber"),
					storeQuery.getString("Address")
				)
			);
		}
		comboBox_1.setToolTipText("Select Item ");
		comboBox_1.setSelectedItem(null);
		comboBox_1.setBounds(490, 271, 131, 24);
		frame.getContentPane().add(comboBox_1);
		
		JButton btnNewItem = new JButton("New Item");
		btnNewItem.setBounds(166, 347, 106, 25);
		frame.getContentPane().add(btnNewItem);
		
		HashMap<Integer, Store> storeMap = new HashMap<>();
		for(int i=0; i < comboBox_1.getItemCount(); i++){
			Store x = (Store) comboBox_1.getItemAt(i);
			storeMap.put(x.getId(), x);
		}
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(comboBox.getSelectedItem() != null){
					textField_2.setText(((Food)comboBox.getSelectedItem()).getName());
					textField.setText(((Food)comboBox.getSelectedItem()).getDescription());
					textField_1.setText("" + ((Food)comboBox.getSelectedItem()).getQty());
					textField_3.setText("" + ((Food)comboBox.getSelectedItem()).getMinQty());
					textField_4.setText(((Food)comboBox.getSelectedItem()).getStandardMeasure());
					comboBox_1.setSelectedItem(storeMap.get(((Food)comboBox.getSelectedItem()).getPreferredStore()));
				}
			}
		});
	}
	
	public static ResultSet getStores() throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		Statement qs = conn.createStatement();
		return qs.executeQuery("SELECT * FROM STORE");
	}
}

