package kitchen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class RecipeWindow {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecipeWindow window = new RecipeWindow();
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
	public RecipeWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1121, 690);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblKaransRecipes = new JLabel("KARAN'S RECIPE'S");
		lblKaransRecipes.setHorizontalAlignment(SwingConstants.LEFT);
		lblKaransRecipes.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblKaransRecipes.setBounds(368, 38, 311, 50);
		frame.getContentPane().add(lblKaransRecipes);
		
		JButton btnAddNewReceipe = new JButton("Add New Recipe");
		btnAddNewReceipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAddNewReceipe.setBounds(82, 503, 210, 25);
		frame.getContentPane().add(btnAddNewReceipe);
		
		JButton btnEditRecipe = new JButton("Edit Recipe");
		btnEditRecipe.setBounds(435, 503, 210, 25);
		frame.getContentPane().add(btnEditRecipe);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setToolTipText("Select Item ");
		comboBox.setBounds(54, 171, 265, 24);
		frame.getContentPane().add(comboBox);
		
		JLabel lblRecipeName = new JLabel("RECIPE NAME");
		lblRecipeName.setHorizontalAlignment(SwingConstants.LEFT);
		lblRecipeName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRecipeName.setBounds(54, 90, 200, 50);
		frame.getContentPane().add(lblRecipeName);
		
		JLabel lblNewLabel = new JLabel("RECIPE Name");
		lblNewLabel.setBounds(64, 208, 200, 50);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblTheActualRecipes = new JLabel("The actual recipes are a PDF file. They are scanned in from");
		lblTheActualRecipes.setBounds(509, 158, 443, 50);
		frame.getContentPane().add(lblTheActualRecipes);
		
		JLabel lblWhateverSourceMagazine = new JLabel("whatever source.  magazine, grandma,whatever.  the use will not enter the receipe    ");
		lblWhateverSourceMagazine.setBounds(461, 193, 563, 36);
		frame.getContentPane().add(lblWhateverSourceMagazine);
		
		JLabel lblDispayPartOf = new JLabel("Dispay part of the PDF file    We need to talk about this. ");
		lblDispayPartOf.setBounds(471, 228, 401, 50);
		frame.getContentPane().add(lblDispayPartOf);
		
		JLabel lblIngredients = new JLabel("INGREDIENTS");
		lblIngredients.setHorizontalAlignment(SwingConstants.LEFT);
		lblIngredients.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblIngredients.setBounds(82, 297, 200, 50);
		frame.getContentPane().add(lblIngredients);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setToolTipText("Select Item ");
		comboBox_1.setBounds(73, 360, 265, 24);
		frame.getContentPane().add(comboBox_1);
		
		JLabel lblSelectByFood = new JLabel("Select by food name  add to ingredients for this recipe");
		lblSelectByFood.setBounds(82, 409, 388, 16);
		frame.getContentPane().add(lblSelectByFood);
		
		JLabel lblShowListOf = new JLabel("show list of ingredients over here");
		lblShowListOf.setBounds(661, 364, 363, 16);
		frame.getContentPane().add(lblShowListOf);
		
		JLabel lblQty = new JLabel("QTY");
		lblQty.setHorizontalAlignment(SwingConstants.LEFT);
		lblQty.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblQty.setBounds(368, 297, 53, 50);
		frame.getContentPane().add(lblQty);
		
		textField = new JTextField();
		textField.setBounds(371, 360, 87, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblStdMeasure = new JLabel("Std Measure");
		lblStdMeasure.setHorizontalAlignment(SwingConstants.LEFT);
		lblStdMeasure.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblStdMeasure.setBounds(481, 301, 200, 50);
		frame.getContentPane().add(lblStdMeasure);
		
		JLabel lblEnterQty = new JLabel("Enter Qty   display std measure");
		lblEnterQty.setBounds(82, 435, 388, 16);
		frame.getContentPane().add(lblEnterQty);
		
		JLabel lblIAmThinking = new JLabel("I am thinking that along the bottom we can have buttons that take you to other screens.  comments");
		lblIAmThinking.setBounds(98, 577, 877, 16);
		frame.getContentPane().add(lblIAmThinking);
	}
}
