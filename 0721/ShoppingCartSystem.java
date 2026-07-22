import java.util.ArrayList;

public class ShoppingCartSystem {
    public static void main(String[] args) {
        ArrayList<CartItem> cart = new ArrayList<>();

        System.out.println("=== 購物車操作測試 ===");
        
        addItem(cart, new CartItem("P01", "Apple", 25.0, 2));
        addItem(cart, new CartItem("P02", "Milk", 65.0, 1));
        
        addItem(cart, new CartItem("P01", "Apple", 25.0, 3));

        System.out.println("\n=== 修改數量 ===");
        updateQuantity(cart, "P02", 0);
        updateQuantity(cart, "P02", 5);

        printCart(cart);
    }

    public static void addItem(ArrayList<CartItem> cart, CartItem newItem) {
        CartItem existing = findByCode(cart, newItem.getCode());
        if (existing != null) {
            existing.addQuantity(newItem.getQuantity());
            System.out.println("🔄 代碼 " + newItem.getCode() + " 已存在，數量增加 " + newItem.getQuantity());
        } else {
            cart.add(newItem);
            System.out.println("✅ 新增商品至購物車：" + newItem.getName());
        }
    }

    public static void updateQuantity(ArrayList<CartItem> cart, String code, int newQuantity) {
        CartItem item = findByCode(cart, code);
        if (item == null) {
            System.out.println("❌ 找不到商品代碼：" + code);
            return;
        }

        if (!item.setQuantity(newQuantity)) {
            System.out.println("❌ 更新失敗！數量必須大於 0。");
        } else {
            System.out.println("✅ 已將 " + item.getName() + " 數量更新為：" + newQuantity);
        }
    }

    public static CartItem findByCode(ArrayList<CartItem> cart, String code) {
        for (CartItem item : cart) {
            if (item.getCode().equalsIgnoreCase(code)) return item;
        }
        return null;
    }

    public static void printCart(ArrayList<CartItem> cart) {
        System.out.println("\n=== 購物車明細 ===");
        double total = 0;
        for (CartItem item : cart) {
            System.out.println(item);
            total += item.getSubtotal();
        }
        System.out.println("----------------------------------------------");
        System.out.println("結算總金額: $" + String.format("%.1f", total));
    }
}