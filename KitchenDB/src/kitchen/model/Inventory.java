package kitchen.model;

public class Inventory {
	private int id;
	private float price;
	private int size;
	private float salePrice;
	
	public Inventory(int id, float price, int size, float salePrice) {
		this(id, price, size);
		this.salePrice = salePrice;
	}
	
	public Inventory(int id, float price, int size){
		this.id = id;
		this.price = price;
		this.size = size;
	}
	
	public int getId(){return id;}
	public float getPrice(){return price;}
	public int getSize(){return size;}
	public float salePrice(){return salePrice;}
}
