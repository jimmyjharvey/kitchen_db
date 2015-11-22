package kitchen.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MealPlan {
	private int id;
	private Date date;
	private String category;
	int chef;
	
	public MealPlan(int id, Date date, String category, int chef){
		this.id = id;
		this.date = date;
		this.category= category;
		this.chef = chef;
	}
	
	public int getId(){ return id; }
	public Date getDate() {return date;}
	public String getCategory(){ return category; }
	public int getChef() { return chef; }
	
	@Override
	public String toString(){
		return date + " " + category;
	}
}
