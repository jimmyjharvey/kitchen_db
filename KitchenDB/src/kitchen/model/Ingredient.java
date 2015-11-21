package kitchen.model;

public class Ingredient {
	
	private int foodID;
	private String foodName;
	private String standardMeasure;
	private int recipeID;
	private int qty;
	
	public Ingredient(int foodID, int recipeID, int qty, String foodName, String standardMeasure){
		this.foodID = foodID;
		this.foodName = foodName;
		this.standardMeasure = standardMeasure;
		this.recipeID = recipeID;
		this.qty = qty;
	}

	public int getFoodID() { return foodID; }
	public int getRecipeID() { return recipeID; }
	public int getQty() { return qty; }

	@Override
	public String toString(){
		return qty + " " + standardMeasure + " " + foodName;
	}
}
