public class PricePrinter {
    public static void main(String[] args) {
        printItem("Black tea", 30);
        printItem("Coffee", 50);
    }

    public static void printItem(String itemName, int price) {
        System.out.println("Item: " + itemName + ", Price: $" + price);
    }
}