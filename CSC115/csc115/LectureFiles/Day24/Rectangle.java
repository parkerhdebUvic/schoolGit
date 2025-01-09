public class Rectangle implements Shape {
	double length; // in inches
	double width; // in inches
	
	public Rectangle(double dimension1, double dimension2) {
		if (dimension1 >= dimension2) {
			this.length = dimension1;
			this.width = dimension2;
		} else {
			this.length = dimension2;
			this.width = dimension1;
		}
	}
	 
	public double surfaceArea() {
		return length*width;
	}
	
	public String toString() {
		return "surface area: "+surfaceArea() + " inches squared";
	}
}