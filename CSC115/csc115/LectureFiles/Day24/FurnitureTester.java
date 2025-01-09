public class FurnitureTester {
	 
	public static void main(String[] args) {
		Furniture f1 = new Furniture("vase", "x1z4322", "ceramic", 40.00);
		Furniture f2 = new Furniture("rocking chair", "ab998c", "wood", 179.99);
		 
		System.out.println(f1);
		// System.out.println(f1.getName() + " sale price: $" + f1.salePrice() +"\n");
		System.out.println(f2);
		
		/* 1) Create a subclass of the Furniture class for a certain type of furniture */
		
		Couch c1 = new Couch("sofa", "sl4511", "leather", 699.99, 4);
		Couch c2 = new Couch("futon", "fb3223s", "cotton", 399.99, 2);
		// System.out.println(c1);
		// System.out.println(c2);
		
		
		/* 2) Create another subclass that includes a has-a relationship */
		Shape s1 = new Circle(25);
		Shape s2 = new Rectangle(90,60);
		Table t1 = new Table("dinner table", "d101", "wood", 450.00, s2);
		Table t2 = new Table("coffee table", "ct003", "glass", 125.00, s1);

		// System.out.println(t1);
		// System.out.println(t2);
		


		/* 3) Create a furniture array the contains different types of furniture */
		Furniture[] storeItems = {f1, c1, t1, t2, c2, t2};
		for (int i = 0; i < storeItems.length; i++) {
			System.out.println(storeItems[i]);
		}
	}
}