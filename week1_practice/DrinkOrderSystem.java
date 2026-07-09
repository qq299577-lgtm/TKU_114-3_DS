import java.util.Scanner;

public class DrinkOrderSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 個別商品數量累加器
        int blackTeaCount = 0;
        int greenTeaCount = 0;
        int milkTeaCount = 0;
        int coffeeCount = 0;
        
        int totalItems = 0;
        int totalAmount = 0;
        int option = -1;

        // while 迴圈：重複操作選單直到輸入 0
        while (option != 0) {
            printMenu(); // 呼叫方法 1：顯示選單
            System.out.print("請輸入選項：");
            option = sc.nextInt();

            if (option >= 1 && option <= 4) {
                // 呼叫方法 4：讀取並驗證數量
                int quantity = readValidQuantity(sc);
                
                // 呼叫方法 2：取得單價
                int price = getPrice(option);
                
                // 呼叫方法 5：計算小計
                int subtotal = calculateSubtotal(price, quantity);
                
                // 累加總杯數與折扣前總金額
                totalItems += quantity;
                totalAmount += subtotal;

                // 重要判斷：依據選項，統計各商品銷售杯數
                if (option == 1) blackTeaCount += quantity;
                else if (option == 2) greenTeaCount += quantity;
                else if (option == 3) milkTeaCount += quantity;
                else if (option == 4) coffeeCount += quantity;

                // 呼叫方法 3：取得商品名稱
                String itemName = getItemName(option);
                System.out.println(itemName + " x " + quantity);
                System.out.println("Subtotal: " + subtotal + "\n");

            } else if (option != 0) {
                System.out.println("無效選項，請重新輸入。\n");
            }
        }

        // 呼叫方法 6：計算折扣後總金額
        int finalAmount = calculateDiscountedTotal(totalAmount);

        // 呼叫方法 7：印出完整收據
        printReceipt(blackTeaCount, greenTeaCount, milkTeaCount, coffeeCount, totalItems, totalAmount, finalAmount);

        sc.close();
    }

    // === 方法 1：顯示商品選單 ===
    public static void printMenu() {
        System.out.println("=== Drink Menu ===");
        System.out.println("1. Black tea  $30");
        System.out.println("2. Green tea  $35");
        System.out.println("3. Milk tea   $45");
        System.out.println("4. Coffee     $50");
        System.out.println("0. Checkout");
    }

    // === 方法 2：依選項回傳價格 ===
    public static int getPrice(int option) {
        switch (option) {
            case 1: return 30;
            case 2: return 35;
            case 3: return 45;
            case 4: return 50;
            default: return 0;
        }
    }

    // === 方法 3：根據選項回傳商品名稱 ===
    public static String getItemName(int option) {
        switch (option) {
            case 1: return "Black tea";
            case 2: return "Green tea";
            case 3: return "Milk tea";
            case 4: return "Coffee";
            default: return "";
        }
    }

    // === 方法 4：讀取並驗證合法數量（防錯） ===
    public static int readValidQuantity(Scanner sc) {
        System.out.print("請輸入數量：");
        int quantity = sc.nextInt();
        while (quantity <= 0) {
            System.out.print("數量必須大於0，請重新輸入：");
            quantity = sc.nextInt();
        }
        return quantity;
    }

    // === 方法 5：計算商品小計 ===
    public static int calculateSubtotal(int price, int quantity) {
        return price * quantity;
    }

    // === 方法 6：計算折扣後總金額 ===
    public static int calculateDiscountedTotal(int totalAmount) {
        // 重要判斷：滿 300 元打 9 折 (乘以 0.9)
        if (totalAmount >= 300) {
            return (int) (totalAmount * 0.9);
        }
        return totalAmount;
    }

    // === 方法 7：輸出最終完整收據報表 ===
    public static void printReceipt(int blackTeaCount, int greenTeaCount, int milkTeaCount, int coffeeCount, int totalItems, int totalAmount, int finalAmount) {
        System.out.println("\n=== Receipt ===");
        System.out.println("Black tea: " + blackTeaCount);
        System.out.println("Green tea: " + greenTeaCount);
        System.out.println("Milk tea: " + milkTeaCount);
        System.out.println("Coffee: " + coffeeCount);
        System.out.println("Total items: " + totalItems);
        System.out.println("Original amount: " + totalAmount);
        
        // 重要判斷：根據金額決定折扣標籤顯示 Yes 或 No
        if (totalAmount >= 300) {
            System.out.println("Discount: Yes");
        } else {
            System.out.println("Discount: No");
        }
        
        System.out.println("Final amount: " + finalAmount);
    }
}