package kitchen.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MealPlan {
	private int id;
	private LocalDate date;
	private String category;
	Cook chef;
	List<Recipe> dishes;
	List<Equipment> equipment;
	
	public MealPlan(int id, LocalDate date, String category, Cook chef){
		this.id = id;
		this.date = date;
		this.category= category;
		this.chef = chef;
		this.dishes = new ArrayList<Recipe>();
		this.equipment = new ArrayList<Equipment>();
	}
	
	public int getId(){ return id; }
	public LocalDate getDate() {return date;}
	public String getCategory(){ return category; }
	public Cook getChef() { return chef; }
	
	public void addDish(Recipe dish){
		dishes.add(dish);
	}
	
	public void addEquipment(Equipment equipment){
		this.equipment.add(equipment);
	}
	
	@Override
	public String toString(){
		return date + " " + category;
	}
}
