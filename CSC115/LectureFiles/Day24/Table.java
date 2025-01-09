public class Table extends Furniture{
    private Shape tableShape;

    public Table (String name, String itemID, String material, double price, Shape tableShape){
        super(name, itemID, material, price);
        this.tableShape = tableShape;
    }

    public Table (String name, String itemID, String material, double price, double radius){
        super(name, itemID, material, price);
        this.tableShape = new Circle(radius);
    }

    public String toString() {
        String s = super.toString();
        s += tableShape.toString();
        s += "\n";
        return s;
    }
}
