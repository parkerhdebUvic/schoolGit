public class Furniture {
	private String name;
	private String itemID;
	private String material;
	private double price;
 
	public Furniture(String name, String itemID, String material, double price) {
		this.name = name;
		this.itemID = itemID;
		this.material = material;
		this.price = price;
	}
	
	public double getPrice() {
		return price;
	}
	
	public double salePrice() {
		return price*0.75;
	}
	
	public String toString() {
		return "Name: "+this.name+ ", price: $"+this.price+"\n";
	}

}

