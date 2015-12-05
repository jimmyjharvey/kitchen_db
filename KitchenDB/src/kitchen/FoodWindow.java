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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FoodWindow {
	private JFrame frame;
	private JTextField textFieldDescription;
	private JTextField textFieldQty;
	private JTextField textFieldMinQty;
	private JTextField textFieldName;
	private JTextField textFieldStandardMeasure;
	
	public JFrame getForm(){
		return frame;
	}

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

	public FoodWindow() throws SQLException{
		initialize();
	}

	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.setBounds(100, 100, 325, 350);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBounds(26, 255, 106, 25);
		frame.getContentPane().add(btnNewButton);

		List<Food> foodList = new ArrayList<Food>();
		JComboBox<Food> comboBox = new JComboBox<Food>();
		ResultSet foodQuery = DBConn.getResults("SELECT * FROM FOOD;");
		while(foodQuery.next()){
			foodList.add(
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
		foodList.sort((x,y) -> x.toString().compareTo(y.toString()));
		foodList.forEach(x -> comboBox.addItem(x));
		comboBox.setToolTipText("Select Item ");
		comboBox.setBounds(127, 13, 151, 24);
		comboBox.setSelectedItem(null);
		frame.getContentPane().add(comboBox);
		
		JLabel lblTestLabel = new JLabel("FOOD");
		lblTestLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTestLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblTestLabel.setBounds(26, 11, 85, 25);
		frame.getContentPane().add(lblTestLabel);
		
		textFieldDescription = new JTextField();
		textFieldDescription.setBounds(127, 88, 151, 22);
		frame.getContentPane().add(textFieldDescription);
		textFieldDescription.setColumns(10);
		
		textFieldQty = new JTextField();
		textFieldQty.setBounds(127, 115, 75, 22);
		frame.getContentPane().add(textFieldQty);
		textFieldQty.setColumns(10);
		
		JLabel lblOnHand = new JLabel("Qty");
		lblOnHand.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblOnHand.setBounds(26, 117, 131, 16);
		frame.getContentPane().add(lblOnHand);
		
		textFieldMinQty = new JTextField();
		textFieldMinQty.setColumns(10);
		textFieldMinQty.setBounds(127, 148, 75, 22);
		frame.getContentPane().add(textFieldMinQty);
		
		JLabel lblMin = new JLabel("Min Qty");
		lblMin.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMin.setBounds(26, 150, 85, 16);
		frame.getContentPane().add(lblMin);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDescription.setBounds(26, 90, 131, 16);
		frame.getContentPane().add(lblDescription);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblName.setBounds(26, 61, 131, 16);
		frame.getContentPane().add(lblName);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		textFieldName.setBounds(127, 59, 151, 22);
		frame.getContentPane().add(textFieldName);
		
		JLabel lblStdmeasure = new JLabel("Std Measure");
		lblStdmeasure.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblStdmeasure.setBounds(26, 183, 94, 16);
		frame.getContentPane().add(lblStdmeasure);
		
		textFieldStandardMeasure = new JTextField();
		textFieldStandardMeasure.setColumns(10);
		textFieldStandardMeasure.setBounds(127, 181, 151, 22);
		frame.getContentPane().add(textFieldStandardMeasure);
		
		JLabel lblPreferredStore = new JLabel("Preferred Store");
		lblPreferredStore.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPreferredStore.setBounds(26, 213, 106, 16);
		frame.getContentPane().add(lblPreferredStore);

		JComboBox<Store> comboBoxPreferredStore = new JComboBox<Store>();
		ResultSet storeQuery = DBConn.getResults("SELECT * FROM STORE;");
		while(storeQuery.next()){
			comboBoxPreferredStore.addItem(
				new Store(
					storeQuery.getInt("StoreID"), 
					storeQuery.getString("StoreName"), 
					storeQuery.getString("StoreHours"), 
					storeQuery.getString("ClubCardNumber"),
					storeQuery.getString("Address")
				)
			);
		}
		comboBoxPreferredStore.setToolTipText("Select Item ");
		comboBoxPreferredStore.setSelectedItem(null);
		comboBoxPreferredStore.setBounds(127, 210, 151, 24);
		frame.getContentPane().add(comboBoxPreferredStore);
		
		JButton btnNewItem = new JButton("New Item");
		btnNewItem.setBounds(137, 255, 106, 25);
		frame.getContentPane().add(btnNewItem);
		
		HashMap<Integer, Store> storeMap = new HashMap<>();
		for(int i=0; i < comboBoxPreferredStore.getItemCount(); i++){
			Store x = (Store) comboBoxPreferredStore.getItemAt(i);
			storeMap.put(x.getId(), x);
		}
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if(comboBox.getSelectedItem() != null){
					Food x = (Food)comboBox.getSelectedItem();
					textFieldName.setText(x.getName());
					textFieldDescription.setText(x.getDescription());
					textFieldQty.setText("" + x.getQty());
					textFieldMinQty.setText("" + x.getMinQty());
					textFieldStandardMeasure.setText(x.getStandardMeasure());
					comboBoxPreferredStore.setSelectedItem(storeMap.get(x.getPreferredStore()));
				}
			}
		});
		
		btnNewItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboBox.setSelectedItem(null);
				textFieldName.setText("");
				textFieldDescription.setText("");
				textFieldQty.setText("");
				textFieldMinQty.setText("");
				textFieldStandardMeasure.setText("");
				comboBoxPreferredStore.setSelectedItem(null);
			}
		});

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String qty = textFieldQty.getText();
				if(qty.equals("")) qty = "0";
				String minQty = textFieldMinQty.getText();
				if(minQty.equals("")) minQty = "0";
				
				if(comboBox.getSelectedItem() != null){
					Food x = (Food)comboBox.getSelectedItem();
					try{
						DBConn.updateDB(
								"UPDATE FOOD SET FoodName=\"" + textFieldName.getText() 
								+ "\",FoodDescription=\"" + textFieldDescription.getText() 
								+ "\",InventoryQty=\"" + qty 
								+ "\",MinQty=\"" + minQty
								+ "\",StandardMeasure=\"" + textFieldStandardMeasure.getText() 
								+ "\",PreferredStore=\"" + ((Store)comboBoxPreferredStore.getSelectedItem()).getId()
								+ "\" WHERE FoodID=" + ((Food)comboBox.getSelectedItem()).getId() + ";");
					} catch (Exception e){
						e.printStackTrace();
					}
				} else {
					try{
						DBConn.updateDB(
								"INSERT INTO FOOD VALUES (NULL, \"" + textFieldName.getText() 
								+ "\",\"" + textFieldDescription.getText() 
								+ "\"," + qty 
								+ "," + minQty 
								+ ",\"" + textFieldStandardMeasure.getText() 
								+ "\",\"" + ((Store)comboBoxPreferredStore.getSelectedItem()).getId()
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