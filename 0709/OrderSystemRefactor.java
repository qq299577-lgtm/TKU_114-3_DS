import java.util.Scanner;

public class OrderSystemRefactor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;
        int totalItems = 0;
        int totalAmount = 0;

        while (option != 0) {
            printMenu(); // 1. 顯示選單
            System.out.print("請輸入選項：");
            option = sc.nextInt();

            if (option == 1 || option == 2 || option == 3) {
                // 2. 讀取並驗證數量
                int quantity = readValidQuantity(sc);
                
                // 3. 取得商品單價
                int price = getPrice(option);
                
                // 4. 計算本次小計
                int subtotal = calculateSubtotal(price, quantity);
                
                // 累加總數量與總金額
                totalItems += quantity;
                totalAmount += subtotal;

                System.out.println("Subtotal: " + subtotal + "\n");

            } else if (option != 0) {
                System.out.println("Unknown option\n");
            }
        }

        // 5. 印出最終收據明細
        printReceipt(totalItems, totalAmount);

        sc.close();
    }

    // === 1. 顯示選單方法 ===
    public static void printMenu() {
        System.out.println("=== Order Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Coffee     $50");
        System.out.println("0. Checkout");
    }

    // === 2. 根據選項回傳價格方法 ===
    public static int getPrice(int option) {
        switch (option) {
            case 1: return 30;
            case 2: return 35;
            case 3: return 50;
            default: return 0;
        }
    }

    // === 3. 讀取並驗證數量大於 0 的方法 ===
    public static int readValidQuantity(Scanner sc) {
        System.out.print("請輸入數量：");
        int quantity = sc.nextInt();
        while (quantity <= 0) {
            System.out.print("數量必須大於0，請重新輸入：");
            quantity = sc.nextInt();
        }
        return quantity;
    }

    // === 4. 計算商品小計方法 ===
    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    // === 5. 印出收據明細方法 ===
    public static void printReceipt(int totalItems, int totalAmount) {
        System.out.println("\n=== Receipt ===");
        System.out.println("Total items: " + totalItems);
        System.out.println("Total amount: " + totalAmount);
    }
}