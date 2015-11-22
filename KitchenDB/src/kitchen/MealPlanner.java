package kitchen;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import kitchen.model.Cook;
import kitchen.model.Food;
import kitchen.model.Ingredient;
import kitchen.model.MealPlan;
import kitchen.model.Recipe;

public class MealPlanner {

	private JFrame frame;
	private JTextField textFieldMealCategory;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MealPlanner window = new MealPlanner();
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
	public MealPlanner() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox<Recipe> comboBoxRecipe = new JComboBox<>();
		ResultSet foodQuery = DBConn.getResults("SELECT * FROM RECIPE;");
		while(foodQuery.next()){
			comboBoxRecipe.addItem(
				new Recipe(
					foodQuery.getInt("RecipeID"), 
					foodQuery.getString("RecipeName"), 
					foodQuery.getString("RecipeInstructions")
				)
			);
		}
		comboBoxRecipe.setBounds(319, 186, 151, 20);
		comboBoxRecipe.setSelectedItem(null);
		frame.getContentPane().add(comboBoxRecipe);
		
		JButton btnNewButtonAddIngredient = new JButton("Add Recipe");
		btnNewButtonAddIngredient.setBounds(319, 217, 150, 23);
		frame.getContentPane().add(btnNewButtonAddIngredient);
		
		JLabel lblRecipes = new JLabel("Recipes:");
		lblRecipes.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblRecipes.setBounds(23, 162, 80, 26);
		frame.getContentPane().add(lblRecipes);
		
		JLabel lblMealPlans = new JLabel("Meal Plan");
		lblMealPlans.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMealPlans.setBounds(23, 25, 80, 26);
		frame.getContentPane().add(lblMealPlans);
		
		JComboBox<MealPlan> comboBoxMealPlans = new JComboBox<>();
		ResultSet recipeQuery = DBConn.getResults("SELECT * FROM MEALPLAN;");
		while(recipeQuery.next()){
			comboBoxMealPlans.addItem(
				new MealPlan(
					recipeQuery.getInt("PlanID"), 
					recipeQuery.getDate("MealDate"), 
					recipeQuery.getString("MealCategory"),
					recipeQuery.getInt("Chef")
				)
			);
		}
		comboBoxMealPlans.setBounds(113, 29, 196, 20);
		comboBoxMealPlans.setSelectedItem(null);
		frame.getContentPane().add(comboBoxMealPlans);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(23, 322, 127, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnNewMealPlan = new JButton("New Meal Plan");
		btnNewMealPlan.setBounds(160, 322, 127, 23);
		frame.getContentPane().add(btnNewMealPlan);
		
		JList<Recipe> listRecipes = new JList<>();
		listRecipes.setBounds(23, 188, 286, 111);
		frame.getContentPane().add(listRecipes);
		
		JButton btnNewButton = new JButton("Delete Recipe");
		btnNewButton.setBounds(319, 276, 150, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblMealCategory = new JLabel("Chef");
		lblMealCategory.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblMealCategory.setBounds(23, 87, 100, 26);
		frame.getContentPane().add(lblMealCategory);
		
		textFieldMealCategory = new JTextField();
		textFieldMealCategory.setBounds(159, 117, 150, 26);
		frame.getContentPane().add(textFieldMealCategory);
		textFieldMealCategory.setColumns(10);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblDate.setBounds(23, 56, 100, 26);
		frame.getContentPane().add(lblDate);
		
		JComboBox<Date> comboBoxDate = new JComboBox<Date>();
		comboBoxDate.setBounds(113, 56, 196, 20);
		frame.getContentPane().add(comboBoxDate);
		LocalDate now = LocalDate.now();
		for(int i=0; i<14; i++) comboBoxDate.addItem(Date.valueOf(now.plusDays(i)));
		comboBoxDate.setSelectedItem(null);
		
		JLabel label = new JLabel("Meal Category");
		label.setFont(new Font("Times New Roman", Font.BOLD, 14));
		label.setBounds(23, 116, 100, 26);
		frame.getContentPane().add(label);
		
		JComboBox<Cook> comboBoxChef = new JComboBox<>();
		comboBoxChef.setBounds(113, 87, 196, 20);
		frame.getContentPane().add(comboBoxChef);
		ResultSet chefQuery = DBConn.getResults("SELECT * FROM COOK;");
		while(chefQuery.next()){
			comboBoxChef.addItem(
				new Cook(
					chefQuery.getInt("CookID"), 
					chefQuery.getString("CookName")
				)
			);
		}
		comboBoxChef.setSelectedItem(null);
		
		//Maps all the recipes in the comboBox
		HashMap<Integer, Recipe> recipes = new HashMap<>();
		for(int i=0; i < comboBoxRecipe.getItemCount(); i++){
			Recipe x = (Recipe) comboBoxRecipe.getItemAt(i);
			recipes.put(x.getId(), x);
		}
		
		//Maps all the cooks in the comboBox
		HashMap<Integer, Cook> cooks = new HashMap<>();
		for(int i=0; i < comboBoxChef.getItemCount(); i++){
			Cook x = (Cook) comboBoxChef.getItemAt(i);
			cooks.put(x.getId(), x);
		}

		//Update on new meal plan selection
		comboBoxMealPlans.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				MealPlan x = (MealPlan) comboBoxMealPlans.getSelectedItem();
				if (x != null){
					textFieldMealCategory.setText(x.getCategory());
					comboBoxDate.setSelectedItem(x.getDate());
					comboBoxChef.setSelectedItem(cooks.get(x.getChef()));
					listRecipes.removeAll();
					try{
						DefaultListModel<Recipe> model = new DefaultListModel<>();
						ResultSet recipeQuery = DBConn.getResults("SELECT * FROM PLANRECIPE WHERE PlanID=" + x.getId() + ";");
						while(recipeQuery.next()){
							int recipeID = recipeQuery.getInt("RecipeID");
							Recipe recipe = recipes.get(recipeID);
							model.addElement(recipe);
						}
						listRecipes.setModel(model);
					} catch (SQLException se) {
						se.printStackTrace();
					}
					comboBoxRecipe.setSelectedItem(null);
				}
			}
		});
		
		//Add new recipes
		btnNewButtonAddIngredient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					MealPlan mealPlan = (MealPlan) comboBoxMealPlans.getSelectedItem();
					Recipe recipe = (Recipe) comboBoxRecipe.getSelectedItem();
					if(mealPlan != null && recipe != null){
							DBConn.updateDB("INSERT INTO PLANRECIPE VALUES (" + recipe.getId() + "," + mealPlan.getId() + ");");
					}
				} catch (Exception e){
					e.printStackTrace();
				}
				MealPlan x = (MealPlan) comboBoxMealPlans.getSelectedItem();
				listRecipes.removeAll();
				try{
					DefaultListModel<Recipe> model = new DefaultListModel<>();
					ResultSet recipeQuery = DBConn.getResults("SELECT * FROM PLANRECIPE WHERE PlanID=" + x.getId() + ";");
					while(recipeQuery.next()){
						int recipeID = recipeQuery.getInt("RecipeID");
						Recipe recipe = recipes.get(recipeID);
						model.addElement(recipe);
					}
					listRecipes.setModel(model);
				} catch (SQLException se) {
					se.printStackTrace();
				}
				comboBoxRecipe.setSelectedItem(null);
			}
		});

		//Save meal plan
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBoxMealPlans.getSelectedItem() != null){
					Recipe x = (Recipe)comboBoxMealPlans.getSelectedItem();
					Cook cook = (Cook) comboBoxChef.getSelectedItem();
					try{
						DBConn.updateDB(
								"UPDATE MEALPLAN SET MealCategory=\"" + textFieldMealCategory.getText() 
								+ "\",MealDate=\"" + comboBoxDate.getSelectedIndex() 
								+ "\",Chef=\"" + cook.getId()
								+ "\" WHERE PlanID=" + ((MealPlan)comboBoxMealPlans.getSelectedItem()).getId() + ";");
					} catch (Exception e){
						e.printStackTrace();
					}
				} else {
					try{
						DBConn.updateDB(
								"INSERT INTO MEALPLAN VALUES (NULL, \"" 
								+ comboBoxDate.getSelectedItem() + "\",\""
								+ textFieldMealCategory.getText() + "\",\"" 
								+ ((Cook) comboBoxChef.getSelectedItem()).getId() + "\");");
					} catch (Exception e){
						e.printStackTrace();
					}
					frame.dispose();
				    frame.setVisible(false);
				    try{
				    	frame = (new MealPlanner()).frame;
				    } catch (Exception e){
				    	e.printStackTrace();
				    }
				    frame.setVisible(true);
				}
			}
		});
		
		//Add new meal plan
		btnNewMealPlan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxMealPlans.setSelectedItem(null);
				comboBoxDate.setSelectedItem(null);
				comboBoxChef.setSelectedItem(null);
				textFieldMealCategory.setText("");
				DefaultListModel<Recipe> model = new DefaultListModel<>();
				listRecipes.setModel(model);
			}
		});
		
		//delete selected recipe
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Recipe r = listRecipes.getSelectedValue();
				MealPlan mp = (MealPlan) comboBoxMealPlans.getSelectedItem();
				if(r != null){
					try{
						DBConn.updateDB("DELETE FROM PLANRECIPE WHERE RecipeID=\"" + r.getId() + "\" AND PlanID=\"" + mp.getId() + "\";");
					} catch (SQLException sqle){
						sqle.printStackTrace();
					}	
					MealPlan x = (MealPlan) comboBoxMealPlans.getSelectedItem();
					listRecipes.removeAll();
					try{
						DefaultListModel<Recipe> model = new DefaultListModel<>();
						ResultSet ingredientQuery = DBConn.getResults("SELECT * FROM PLANRECIPE WHERE PlanID=" + x.getId() + ";");
						while(ingredientQuery.next()){
							int recipeID = ingredientQuery.getInt("RecipeID");
							Recipe recipe = recipes.get(recipeID);
							model.addElement(recipe);
						}
						listRecipes.setModel(model);
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
			}
		});
	}

}
