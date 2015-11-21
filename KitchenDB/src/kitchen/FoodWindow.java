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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		ResultSet foodQuery = DBConn.getResults("SELECT * FROM FOOD;");
		while(foodQuery.next()){
			comboBox.addItem(
				new Food(
					foodQuery.getInt("FoodID"), 
					foodQuery.getString("FoodName"), 
					foodQuery.getString("FoodDescription"), 
					foodQuery.getInt("InventoryQty"),
					foodQuery.getInt("MinQty"),
					foodQuery.getString("StandardMeasure"),
					foodQuery.getInt("PreferredStore")
				)
			);
		}
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
		ResultSet storeQuery = DBConn.getResults("SELECT * FROM STORE;");
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
					Food x = (Food)comboBox.getSelectedItem();
					textField_2.setText(x.getName());
					textField.setText(x.getDescription());
					textField_1.setText("" + x.getQty());
					textField_3.setText("" + x.getMinQty());
					textField_4.setText(x.getStandardMeasure());
					comboBox_1.setSelectedItem(storeMap.get(x.getPreferredStore()));
				}
			}
		});
		
		btnNewItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboBox.setSelectedItem(null);
				textField_2.setText("");
				textField.setText("");
				textField_1.setText("");
				textField_3.setText("");
				textField_4.setText("");
				comboBox_1.setSelectedItem(null);
			}
		});

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(comboBox.getSelectedItem() != null){
					Food x = (Food)comboBox.getSelectedItem();
					try{
						DBConn.updateDB(
								"UPDATE FOOD SET FoodName=\"" + textField_2.getText() 
								+ "\",FoodDescription=\"" + textField.getText() 
								+ "\",InventoryQty=\"" + textField_1.getText() 
								+ "\",MinQty=\"" + textField_3.getText() 
								+ "\",StandardMeasure=\"" + textField_4.getText() 
								+ "\",PreferredStore=\"" + ((Store)comboBox_1.getSelectedItem()).getId()
								+ "\" WHERE FoodID=" + ((Food)comboBox.getSelectedItem()).getId() + ";");
					} catch (Exception e){
						e.printStackTrace();
					}
				} else {
					try{
						DBConn.updateDB(
								"INSERT INTO FOOD VALUES (NULL, \"" + textField_2.getText() 
								+ "\",\"" + textField.getText() 
								+ "\"," + textField_1.getText() 
								+ "," + textField_3.getText() 
								+ ",\"" + textField_4.getText() 
								+ "\",\"" + ((Store)comboBox_1.getSelectedItem()).getId()
								+ "\");");
					} catch (Exception e){
						e.printStackTrace();
					}
					frame.dispose();
				    frame.setVisible(false);
				    try{
				    	frame= (new FoodWindow()).frame;
				    } catch (Exception e){
				    	e.printStackTrace();
				    }
				    frame.setVisible(true);
				}
			}
		});
	}
	
}

