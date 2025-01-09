public class Couch extends Furniture {
	private int seats;
	  
	public Couch(String name, String itemID, String material, double price, int seats) {
		super(name, itemID, material, price);
		this.seats = seats;
	}
	
	public String toString() {
		String s = super.toString();
		s += "This couch seats "+this.seats+"\n";
		return s;
	}
}