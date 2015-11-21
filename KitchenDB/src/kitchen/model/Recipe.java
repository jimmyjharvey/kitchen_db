package kitchen.model;

import java.util.HashMap;

public class Recipe {
	private int id;
	private String name;
	private String instructions;
	HashMap<Food, Integer> ingredients;
	
	public Recipe(int id, String name, String instructions) {
		this.id = id;
		this.name = name;
		this.instructions = instructions;
		this.ingredients = new HashMap<Food, Integer>();
	}
	
	public int getId() { return id; }
	public String getName() { return name; }
	public String getInstructions() { return instructions; }
	
	public void addIngredient(Food food, Integer amount) {
		ingredients.put(food, amount);
	}
	
	@Override
	public String toString(){return name;}
}
