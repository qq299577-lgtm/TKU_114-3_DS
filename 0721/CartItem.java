public class CartItem {
    private String code;
    private String name;
    private double price;
    private int quantity;

    public CartItem(String code, String name, double price, int quantity) {
        this.code = code;
        this.name = name;
        this.price = Math.max(0, price);
        this.quantity = Math.max(1, quantity);
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void addQuantity(int amount) {
        if (amount > 0) this.quantity += amount;
    }

    public boolean setQuantity(int quantity) {
        if (quantity <= 0) return false;
        this.quantity = quantity;
        return true;
    }

    public double getSubtotal() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-12s | 單價: %6.1f | 數量: %2d | 小計: %7.1f", 
                code, name, price, quantity, getSubtotal());
    }
}