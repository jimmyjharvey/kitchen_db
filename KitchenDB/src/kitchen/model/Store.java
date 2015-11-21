package kitchen.model;

public class Store {
	int id;
	String name;
	String hours;
	String clubCardNumber;
	String address;
	
	public Store(int id, String name, String hours, String clubCardNumber, String address) {
		this.id = id;
		this.name = name;
		this.hours = hours;
		this.clubCardNumber = clubCardNumber;
		this.address = address;
	}
	
	public int getId() { return id; }
	public String getName() { return name; }
	public String getHours() { return hours; }
	public String getClubCardNumber() { return clubCardNumber; }
	public String getAddress() { return address; }

	@Override
	public String toString(){return name;}
}
