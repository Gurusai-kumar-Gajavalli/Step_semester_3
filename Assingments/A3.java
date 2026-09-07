// Problem 3: Product Inventory CSV Parser[cite: 13]
public class A3 {
    public static void parseInventoryRecord(String csvLine) {
        String[] fields = csvLine.split(",");
        if (fields.length == 3) {
            System.out.println("Product: " + fields[0].trim() + " | SKU: " + fields[1].trim() + " | Qty: " + fields[2].trim());
        } else {
            System.out.println("Invalid Record");
        }
    }

    public static void main(String[] args) {
        parseInventoryRecord("Wireless Mouse, WM-2201,150");
        parseInventoryRecord("Wireless Mouse, 150");
    }
}