package kitchen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class KitchenDBDriver {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KitchenDBDriver window = new KitchenDBDriver();
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
	public KitchenDBDriver() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 244, 237);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnRecipeBuilder = new JButton("Manage Foods");
		btnRecipeBuilder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							FoodWindow window = new FoodWindow();
							window.getForm().setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnRecipeBuilder.setBounds(42, 49, 135, 23);
		frame.getContentPane().add(btnRecipeBuilder);
		
		JLabel lblKitchenDbManager = new JLabel("Kitchen DB Manager");
		lblKitchenDbManager.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblKitchenDbManager.setBounds(10, 11, 207, 27);
		frame.getContentPane().add(lblKitchenDbManager);
		
		JButton button = new JButton("Recipe Builder");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							RecipeBuilder window = new RecipeBuilder();
							window.getForm().setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		button.setBounds(42, 83, 135, 23);
		frame.getContentPane().add(button);
		
		JButton btnMealPlanner = new JButton("Meal Planner");
		btnMealPlanner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							MealPlanner window = new MealPlanner();
							window.getForm().setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnMealPlanner.setBounds(42, 117, 135, 23);
		frame.getContentPane().add(btnMealPlanner);
		
		JButton btnGenerateShoppingList = new JButton("Generate Shopping List");
		btnGenerateShoppingList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ShoppingList window = new ShoppingList();
							window.getForm().setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnGenerateShoppingList.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnGenerateShoppingList.setBounds(10, 151, 207, 27);
		frame.getContentPane().add(btnGenerateShoppingList);
	}
}
