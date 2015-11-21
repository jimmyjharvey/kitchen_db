package kitchen;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ListModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.UIManager;

import kitchen.model.Food;
import kitchen.model.Ingredient;
import kitchen.model.Recipe;
import kitchen.model.Store;

import javax.swing.JList;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class RecipeBuilder {

	private JFrame frame;
	private JTextField textFieldFoodQty;
	private JTextField textFieldRecipeName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecipeBuilder window = new RecipeBuilder();
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
	public RecipeBuilder() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textFieldFoodQty = new JTextField();
		textFieldFoodQty.setBounds(394, 153, 84, 20);
		frame.getContentPane().add(textFieldFoodQty);
		textFieldFoodQty.setColumns(10);
		
		JComboBox<Food> comboBoxFood = new JComboBox<>();
		ResultSet foodQuery = DBConn.getResults("SELECT * FROM FOOD;");
		while(foodQuery.next()){
			comboBoxFood.addItem(
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
		comboBoxFood.setBounds(394, 122, 151, 20);
		comboBoxFood.setSelectedItem(null);
		frame.getContentPane().add(comboBoxFood);
		
		JTextArea textAreaInstructions = new JTextArea();
		textAreaInstructions.setBounds(78, 304, 467, 112);
		frame.getContentPane().add(textAreaInstructions);
		
		JButton btnNewButtonAddIngredient = new JButton("Add Ingredient");
		btnNewButtonAddIngredient.setBounds(394, 184, 150, 23);
		frame.getContentPane().add(btnNewButtonAddIngredient);
		
		JLabel lblInstructions = new JLabel("Instructions:");
		lblInstructions.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblInstructions.setBounds(78, 277, 80, 26);
		frame.getContentPane().add(lblInstructions);
		
		JLabel lblIngredients = new JLabel("Ingredients:");
		lblIngredients.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblIngredients.setBounds(78, 98, 80, 26);
		frame.getContentPane().add(lblIngredients);
		
		JLabel lblRecipes = new JLabel("Recipe");
		lblRecipes.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblRecipes.setBounds(78, 25, 80, 26);
		frame.getContentPane().add(lblRecipes);
		
		JComboBox<Recipe> comboBoxRecipes = new JComboBox<>();
		ResultSet recipeQuery = DBConn.getResults("SELECT * FROM RECIPE;");
		while(recipeQuery.next()){
			comboBoxRecipes.addItem(
				new Recipe(
					recipeQuery.getInt("RecipeID"), 
					recipeQuery.getString("RecipeName"), 
					recipeQuery.getString("RecipeInstructions")
				)
			);
		}
		comboBoxRecipes.setBounds(178, 29, 196, 20);
		comboBoxRecipes.setSelectedItem(null);
		frame.getContentPane().add(comboBoxRecipes);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(78, 441, 127, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnNewRecipe = new JButton("New Recipe");
		btnNewRecipe.setBounds(215, 441, 127, 23);
		frame.getContentPane().add(btnNewRecipe);
		
		JList<Ingredient> listIngredients = new JList<>();
		listIngredients.setBounds(78, 124, 296, 142);
		frame.getContentPane().add(listIngredients);
		
		JButton btnNewButton = new JButton("Delete Ingredient");
		btnNewButton.setBounds(394, 243, 150, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblStandardMeasure = new JLabel("");
		lblStandardMeasure.setBounds(488, 156, 46, 14);
		frame.getContentPane().add(lblStandardMeasure);
		
		JLabel lblRecipeName = new JLabel("Recipe Name");
		lblRecipeName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblRecipeName.setBounds(78, 62, 80, 26);
		frame.getContentPane().add(lblRecipeName);
		
		textFieldRecipeName = new JTextField();
		textFieldRecipeName.setBounds(178, 60, 196, 26);
		frame.getContentPane().add(textFieldRecipeName);
		textFieldRecipeName.setColumns(10);
		
		//Maps all the food in the comboBox
		HashMap<Integer, Food> foodMap = new HashMap<>();
		for(int i=0; i < comboBoxFood.getItemCount(); i++){
			Food x = (Food) comboBoxFood.getItemAt(i);
			foodMap.put(x.getId(), x);
		}

		//Update on new recipe selection
		comboBoxRecipes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Recipe x = (Recipe) comboBoxRecipes.getSelectedItem();
				if (x != null){
					textFieldRecipeName.setText(x.getName());
					textAreaInstructions.setText(x.getInstructions());
					listIngredients.removeAll();
					try{
						DefaultListModel<Ingredient> model = new DefaultListModel<>();
						ResultSet ingredientQuery = DBConn.getResults("SELECT * FROM INGREDIENTS WHERE RecipeID=" + x.getId() + ";");
						while(ingredientQuery.next()){
							int foodID = ingredientQuery.getInt("FoodID");
							Food food = foodMap.get(foodID);
							model.addElement(
								new Ingredient(
									foodID,
									ingredientQuery.getInt("RecipeID"),
									ingredientQuery.getInt("Qty"), 
									food.getName(),
									food.getStandardMeasure()
								)
							);
						}
						listIngredients.setModel(model);
					} catch (SQLException se) {
						se.printStackTrace();
					}
					comboBoxFood.setSelectedItem(null);
				}
			}
		});
		
		//Update on new food selection
		comboBoxFood.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				Food x = (Food) comboBoxFood.getSelectedItem();
				if(x != null){
					textFieldFoodQty.setText("");
					lblStandardMeasure.setText(x.getStandardMeasure());
				} else {
					textFieldFoodQty.setText("");
					lblStandardMeasure.setText("");
				}
			}
		});
		
		//Add new ingredients
		btnNewButtonAddIngredient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					Recipe recipe = (Recipe) comboBoxRecipes.getSelectedItem();
					Food food = (Food) comboBoxFood.getSelectedItem();
					if(recipe != null && food != null){
							DBConn.updateDB("INSERT INTO INGREDIENTS VALUES (" + recipe.getId() + "," + food.getId() + "," + textFieldFoodQty.getText() + ");");
					}
				} catch (Exception e){
					e.printStackTrace();
				}
				Recipe x = (Recipe) comboBoxRecipes.getSelectedItem();
				textFieldRecipeName.setText(x.getName());
				textAreaInstructions.setText(x.getInstructions());
				listIngredients.removeAll();
				try{
					DefaultListModel<Ingredient> model = new DefaultListModel<>();
					ResultSet ingredientQuery = DBConn.getResults("SELECT * FROM INGREDIENTS WHERE RecipeID=" + x.getId() + ";");
					while(ingredientQuery.next()){
						int foodID = ingredientQuery.getInt("FoodID");
						Food food = foodMap.get(foodID);
						model.addElement(
							new Ingredient(
								foodID,
								ingredientQuery.getInt("RecipeID"),
								ingredientQuery.getInt("Qty"), 
								food.getName(),
								food.getStandardMeasure()
							)
						);
					}
					listIngredients.setModel(model);
				} catch (SQLException se) {
					se.printStackTrace();
				}
				comboBoxFood.setSelectedItem(null);
			}
		});

		//Save recipe
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboBoxRecipes.getSelectedItem() != null){
					Recipe x = (Recipe)comboBoxRecipes.getSelectedItem();
					try{
						DBConn.updateDB(
								"UPDATE RECIPE SET RecipeName=\"" + textFieldRecipeName.getText() 
								+ "\",RecipeInstructions=\"" + textAreaInstructions.getText()
								+ "\" WHERE RecipeID=" + ((Recipe)comboBoxRecipes.getSelectedItem()).getId() + ";");
					} catch (Exception e){
						e.printStackTrace();
					}
				} else {
					try{
						DBConn.updateDB(
								"INSERT INTO RECIPE VALUES (NULL, \"" + textFieldRecipeName.getText() 
								+ "\",\"" + textAreaInstructions.getText() + "\");");
					} catch (Exception e){
						e.printStackTrace();
					}
					frame.dispose();
				    frame.setVisible(false);
				    try{
				    	frame = (new RecipeBuilder()).frame;
				    } catch (Exception e){
				    	e.printStackTrace();
				    }
				    frame.setVisible(true);
				}
			}
		});
		
		//Add new Recipe
		btnNewRecipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxRecipes.setSelectedItem(null);
				textFieldRecipeName.setText("");
				textAreaInstructions.setText("");

				DefaultListModel<Ingredient> model = new DefaultListModel<>();
				listIngredients.setModel(model);
			}
		});
		
		//delete selected ingredient
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ingredient i = listIngredients.getSelectedValue();
				if(i != null){
					try{
						DBConn.updateDB("DELETE FROM INGREDIENTS WHERE RecipeID=\"" + i.getRecipeID() + "\" AND FoodID=\"" + i.getFoodID() + "\";");
					} catch (SQLException sqle){
						sqle.printStackTrace();
					}	
					Recipe x = (Recipe) comboBoxRecipes.getSelectedItem();
					textFieldRecipeName.setText(x.getName());
					textAreaInstructions.setText(x.getInstructions());
					listIngredients.removeAll();
					try{
						DefaultListModel<Ingredient> model = new DefaultListModel<>();
						ResultSet ingredientQuery = DBConn.getResults("SELECT * FROM INGREDIENTS WHERE RecipeID=" + x.getId() + ";");
						while(ingredientQuery.next()){
							int foodID = ingredientQuery.getInt("FoodID");
							Food food = foodMap.get(foodID);
							model.addElement(
								new Ingredient(
									foodID,
									ingredientQuery.getInt("RecipeID"),
									ingredientQuery.getInt("Qty"), 
									food.getName(),
									food.getStandardMeasure()
								)
							);
						}
						listIngredients.setModel(model);
					} catch (SQLException se) {
						se.printStackTrace();
					}
				}
			}
		});
	}
}
