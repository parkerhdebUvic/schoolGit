public class Rectangle implements Shape {

	private int length; //length units along x axis
	private int height;  //height units along y axis
	private Point position; // position is the (x,y) coordinates of lower left corner of the rectangle
 
	/*
	 * Purpose: initializes this Rectangle with length and height of 0
	 *   and position to new Point at 0,0
	 * Parameters: none
	 */
	public Rectangle() { 
		this.length = 0;
		this.height = 0;
		this.position = new Point();
	}

	/*
	 * Purpose: initializes this Rectangle with given length and height
	 *   and position to new Point at 0,0
	 * Parameters: int length, int height
	 * Precondition: given length and height >=0
	 */
	public Rectangle(int length, int height) {
		this.length = length;
		this.height = height;
		this.position = new Point();
	}

	/*
	 * Purpose: initializes this Rectangle with given length, height and position
	 * Parameters: int length, int height, Point position
	 * Precondition: given length and height >=0, position is not null
	 */
	public Rectangle(int length, int height, Point position) {
		this.length = length;
		this.height = height;
		this.position = position;
	}

	/*
	 * Purpose: calculates the area of this Shape
	 * Parameters: none
	 * Returns: double - the area of the shape
	 */
	public double area(){
		double result = height*length;
		return result;
	}

	/*
	 * Purpose: calculates the perimeter of this Shape
	 * Parameters: none
	 * Returns: double - the perimeter of the shape 
	 */
	public double perimeter(){
		double result = (2*length) + (2*height);
		return result;
	}

	/*
	 * Purpose: determines whether p is within this Shape
	 * Parameters: Point p - the point to search for
	 * Precondition: p is not null
	 * Returns: boolean - true if p is with this Shape,
	 *          false otherwise
	 */
	public boolean contains(Point p){
		boolean result = false;

		if ((p.getX() >= position.getX()) && (p.getX()<=(position.getX()+length))){
			if ((p.getY() >= position.getY()) && (p.getY()<=(position.getY()+height))){
				result = true;
			}
		}
		return result;
	}

	/*
	 * Purpose: returns a String reprensentation of this Shape
	 * Parameters: none
	 * Returns: String - the representation
	 */



	/*
	 * Purpose: prints a message about the characteristics of this Rectangle
	 * Parameters: none
	 * Returns: void
	 */
	public void printCharacteristics() {
		if (length > height) {
			System.out.println("long rectangle");
		} else if (height > length) {
			System.out.println("tall rectangle");
		} else {
			System.out.println("square");
		}
	}

	public String toString() {
		return "Rectangle of dimensions: " + length + " by " + height + " at Point: " + position;
	}

}
