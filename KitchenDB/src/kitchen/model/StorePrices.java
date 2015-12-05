package kitchen.model;

public class StorePrices {
	int FoodID;
	int StoreID;
	double price;
	int qty;
	double salePrice;
	String standardMeasure;
	
	public int getFoodID() {
		return FoodID;
	}

	public int getStoreID() {
		return StoreID;
	}

	public double getPrice() {
		return price;
	}

	public int getQty() {
		return qty;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public String getStandardMeasure() {
		return standardMeasure;
	}

	public StorePrices(int foodID, int storeID, double price, int qty,
			double salePrice, String standardMeasure) {
		super();
		FoodID = foodID;
		StoreID = storeID;
		this.price = price;
		this.qty = qty;
		this.salePrice = salePrice;
		this.standardMeasure = standardMeasure;
	}
	
	@Override
	public String toString(){
		return qty + " " + standardMeasure + " for $" + price;
	}
	
}
