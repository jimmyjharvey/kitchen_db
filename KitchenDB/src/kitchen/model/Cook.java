package kitchen.model;

import java.util.ArrayList;
import java.util.List;

public class Cook {
	private int id;
	private String name;
	private List<Recipe> favorites;
	
	public Cook(int id, String name){
		this.id = id;
		this.name = name;
		this.favorites = new ArrayList<Recipe>();
	}
	
	public int getId() { return id; }
	public String getName() {return name; }
	
	public void addFavorite(Recipe recipe) {
		this.favorites.add(recipe);
	}
	
	@Override
	public String toString() { return name; }
}
