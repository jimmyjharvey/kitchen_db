package kitchen.model;

import java.util.HashMap;

public class Food {
	int id;
	String name;
	String description;
	int qty;
	int minQty;
	String standardMeasure;
	int preferredStore;
	HashMap<Store, Inventory> foodStore;
	
	public Food(int id, String name, String description, int qty, int minQty, String standardMeasure, int preferredStore) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.qty = qty;
		this.minQty = minQty;
		this.standardMeasure = standardMeasure;
		this.preferredStore = preferredStore;
		this.foodStore = new HashMap<Store, Inventory>();
	}
	
	public int getId() { return id; }
	public String getName() { return name; }
	public String getDescription() { return description; }
	public int getQty(){return qty;}
	public int getMinQty(){return minQty;}
	public String getStandardMeasure(){return standardMeasure;}
	public int preferredStore(){return preferredStore;}
	
	public void addStoreInventory(Store store, Inventory inv){
		foodStore.put(store, inv);
	}
	
	@Override
	public String toString() { return name; }
	
}
