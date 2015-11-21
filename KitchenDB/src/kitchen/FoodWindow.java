package kitchen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class FoodWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

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
	public FoodWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 18));
		frame.setBounds(100, 100, 1092, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Add New Item ");
		btnNewButton.setBounds(96, 516, 210, 25);
		frame.getContentPane().add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Select Item ");
		comboBox.setBounds(26, 194, 265, 24);
		frame.getContentPane().add(comboBox);
		
		JLabel lblTestLabel = new JLabel("FOOD");
		lblTestLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTestLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblTestLabel.setBounds(53, 118, 200, 50);
		frame.getContentPane().add(lblTestLabel);
		
		JLabel lblNewLabel = new JLabel("Drop down  FOOD name in alphabetical order ");
		lblNewLabel.setBounds(53, 223, 200, 50);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(404, 195, 210, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblDisplayFoodDescription = new JLabel("display FOOD Description");
		lblDisplayFoodDescription.setBounds(410, 230, 204, 16);
		frame.getContentPane().add(lblDisplayFoodDescription);
		
		textField_1 = new JTextField();
		textField_1.setBounds(811, 207, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblFoodInventoryQty = new JLabel("food Inventory qty");
		lblFoodInventoryQty.setBounds(811, 257, 210, 16);
		frame.getContentPane().add(lblFoodInventoryQty);
		
		JLabel lblOnHand = new JLabel("On Hand");
		lblOnHand.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblOnHand.setBounds(811, 164, 131, 16);
		frame.getContentPane().add(lblOnHand);
		
		textField_2 = new JTextField();
		textField_2.setBounds(534, 301, 116, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(379, 301, 116, 22);
		frame.getContentPane().add(textField_3);
		
		JLabel lblFoodMinqty = new JLabel("FOOD MinQty");
		lblFoodMinqty.setBounds(379, 319, 200, 50);
		frame.getContentPane().add(lblFoodMinqty);
		
		JLabel lblBuyQty = new JLabel("Buy qty");
		lblBuyQty.setBounds(576, 336, 56, 16);
		frame.getContentPane().add(lblBuyQty);
		
		JLabel lblMinimumBuyQty = new JLabel("Minimum       Buy qty");
		lblMinimumBuyQty.setHorizontalAlignment(SwingConstants.LEFT);
		lblMinimumBuyQty.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblMinimumBuyQty.setBounds(379, 237, 289, 50);
		frame.getContentPane().add(lblMinimumBuyQty);
		
		JLabel lblKaransKitchen = new JLabel("KARAN'S KITCHEN");
		lblKaransKitchen.setHorizontalAlignment(SwingConstants.LEFT);
		lblKaransKitchen.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblKaransKitchen.setBounds(357, 28, 311, 50);
		frame.getContentPane().add(lblKaransKitchen);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(756, 301, 200, 50);
		frame.getContentPane().add(comboBox_1);
		
		JLabel lblDisplayPreferredStore = new JLabel("display preferred store for FOOD FOODID");
		lblDisplayPreferredStore.setBounds(766, 372, 255, 16);
		frame.getContentPane().add(lblDisplayPreferredStore);
		
		JButton btnEditItem = new JButton("Edit Item");
		btnEditItem.setBounds(379, 516, 210, 25);
		frame.getContentPane().add(btnEditItem);
		
		JLabel label = new JLabel("New label");
		label.setBounds(756, 413, 200, 50);
		frame.getContentPane().add(label);
		
		JLabel lblTheDropDown = new JLabel("the drop down displays store name  ");
		lblTheDropDown.setBounds(750, 401, 206, 16);
		frame.getContentPane().add(lblTheDropDown);
	}
}

