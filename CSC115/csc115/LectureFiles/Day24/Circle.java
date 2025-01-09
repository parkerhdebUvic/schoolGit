public class Circle implements Shape {
	double radius; // in inches
	 
	public Circle (double radius) {
		this.radius = radius;
	}
	
	public double surfaceArea() {
		return radius*radius*3.14;
	}
	
	public String toString() {
		return "surface area: "+surfaceArea() + " inches squared";
	}
}