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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.UIManager;

import kitchen.model.Food;
import kitchen.model.Ingredient;
import kitchen.model.Recipe;
import kitchen.model.Store;

import javax.swing.JList;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class RecipeBuilder {

	private JFrame frmRecipeBuilder;
	private JTextField textFieldFoodQty;
	private JTextField textFieldRecipeName;
	
	public JFrame getForm(){
		return frmRecipeBuilder;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RecipeBuilder window = new RecipeBuilder();
					window.frmRecipeBuilder.setVisible(true);
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
		frmRecipeBuilder = new JFrame();
		frmRecipeBuilder.setTitle("Recipe Builder");
		frmRecipeBuilder.setBounds(100, 100, 550, 500);
		frmRecipeBuilder.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRecipeBuilder.getContentPane().setLayout(null);
		
		textFieldFoodQty = new JTextField();
		textFieldFoodQty.setBounds(347, 153, 84, 20);
		frmRecipeBuilder.getContentPane().add(textFieldFoodQty);
		textFieldFoodQty.setColumns(10);
		
		List<Food> foodList = new ArrayList<Food>();
		JComboBox<Food> comboBoxFood = new JComboBox<>();
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
		foodList.forEach(x -> comboBoxFood.addItem(x));
		comboBoxFood.setBounds(347, 122, 151, 20);
		comboBoxFood.setSelectedItem(null);
		frmRecipeBuilder.getContentPane().add(comboBoxFood);
		
		JTextArea textAreaInstructions = new JTextArea();
		textAreaInstructions.setBounds(31, 302, 467, 112);
		frmRecipeBuilder.getContentPane().add(textAreaInstructions);
		
		JButton btnNewButtonAddIngredient = new JButton("Add Ingredient");
		btnNewButtonAddIngredient.setBounds(348, 184, 150, 23);
		frmRecipeBuilder.getContentPane().add(btnNewButtonAddIngredient);
		
		JLabel lblInstructions = new JLabel("Instructions:");
		lblInstructions.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblInstructions.setBounds(31, 277, 80, 26);
		frmRecipeBuilder.getContentPane().add(lblInstructions);
		
		JLabel lblIngredients = new JLabel("Ingredients:");
		lblIngredients.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblIngredients.setBounds(31, 98, 80, 26);
		frmRecipeBuilder.getContentPane().add(lblIngredients);
		
		JLabel lblRecipes = new JLabel("Recipe");
		lblRecipes.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblRecipes.setBounds(31, 11, 80, 26);
		frmRecipeBuilder.getContentPane().add(lblRecipes);
		
		List<Recipe> recipeList = new ArrayList<Recipe>();
		JComboBox<Recipe> comboBoxRecipes = new JComboBox<>();
		ResultSet recipeQuery = DBConn.getResults("SELECT * FROM RECIPE;");
		while(recipeQuery.next()){
			recipeList.add(
				new Recipe(
					recipeQuery.getInt("RecipeID"), 
					recipeQuery.getString("RecipeName"), 
					recipeQuery.getString("RecipeInstructions")
				)
			);
		}
		recipeList.sort((x,y) -> x.toString().compareTo(y.toString()));
		recipeList.forEach(x -> comboBoxRecipes.addItem(x));
		comboBoxRecipes.setBounds(132, 15, 196, 20);
		comboBoxRecipes.setSelectedItem(null);
		frmRecipeBuilder.getContentPane().add(comboBoxRecipes);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(31, 425, 127, 23);
		frmRecipeBuilder.getContentPane().add(btnSave);
		
		JButton btnNewRecipe = new JButton("New Recipe");
		btnNewRecipe.setBounds(179, 425, 127, 23);
		frmRecipeBuilder.getContentPane().add(btnNewRecipe);
		
		JList<Ingredient> listIngredients = new JList<>();
		listIngredients.setBounds(32, 124, 296, 142);
		frmRecipeBuilder.getContentPane().add(listIngredients);
		
		JButton btnNewButton = new JButton("Delete Ingredient");
		btnNewButton.setBounds(348, 242, 150, 23);
		frmRecipeBuilder.getContentPane().add(btnNewButton);
		
		JLabel lblStandardMeasure = new JLabel("");
		lblStandardMeasure.setBounds(452, 159, 46, 14);
		frmRecipeBuilder.getContentPane().add(lblStandardMeasure);
		
		JLabel lblRecipeName = new JLabel("Recipe Name");
		lblRecipeName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblRecipeName.setBounds(31, 61, 80, 26);
		frmRecipeBuilder.getContentPane().add(lblRecipeName);
		
		textFieldRecipeName = new JTextField();
		textFieldRecipeName.setBounds(132, 62, 196, 26);
		frmRecipeBuilder.getContentPane().add(textFieldRecipeName);
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
				Recipe recipe = (Recipe) comboBoxRecipes.getSelectedItem();
				Food f = (Food) comboBoxFood.getSelectedItem();
				if(recipe != null && f != null && !textFieldFoodQty.getText().equals("")){
					try{
						DBConn.updateDB("INSERT INTO INGREDIENTS VALUES (" + recipe.getId() + "," + f.getId() + "," + textFieldFoodQty.getText() + ");");
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
					frmRecipeBuilder.dispose();
				    frmRecipeBuilder.setVisible(false);
				    try{
				    	frmRecipeBuilder = (new RecipeBuilder()).frmRecipeBuilder;
				    } catch (Exception e){
				    	e.printStackTrace();
				    }
				    frmRecipeBuilder.setVisible(true);
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
