import java.util.Scanner;

public class ProductManagementSystem {
    public static void showAllProducts(Product[] products, int count) {
        System.out.println("\n--- 目前商品清單 (" + count + "/10) ---");
        if (count == 0) {
            System.out.println("無商品資料。");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + products[i]);
        }
    }

    public static int findProductIndex(Product[] products, int count, String name) {
        if (name == null) {
            return -1;
        }
        String cleanName = name.trim().toLowerCase();
        for (int i = 0; i < count; i++) {
            if (products[i].getName().toLowerCase().equals(cleanName)) {
                return i;
            }
        }
        return -1;
    }

    public static void searchProduct(Scanner sc, Product[] products, int count) {
        System.out.print("請輸入欲搜尋的完整商品名稱：");
        String name = sc.nextLine();
        int idx = findProductIndex(products, count, name);
        if (idx != -1) {
            System.out.println("找到商品：" + products[idx]);
        } else {
            System.out.println("找不到此商品。");
        }
    }

    public static int addNewProduct(Scanner sc, Product[] products, int count) {
        if (count >= products.length) {
            System.out.println("新增失敗：系統儲存空間已滿 (10/10)！");
            return count;
        }

        System.out.print("請輸入商品名稱：");
        String name = sc.nextLine();
        if (name.trim().isEmpty()) {
            System.out.println("新增失敗：名稱不可為空白！");
            return count;
        }

        if (findProductIndex(products, count, name) != -1) {
            System.out.println("新增失敗：商品已存在，名稱不可重複！");
            return count;
        }

        System.out.print("請輸入價格 (大於 0)：");
        int price = sc.nextInt();
        System.out.print("請輸入庫存 (不小於 0)：");
        int stock = sc.nextInt();
        sc.nextLine();

        if (price <= 0 || stock < 0) {
            System.out.println("新增失敗：價格或庫存資料不合規範！");
            return count;
        }

        products[count] = new Product(name, price, stock);
        System.out.println("商品「" + name.trim() + "」新增成功！");
        return count + 1;
    }

    public static void sellProduct(Scanner sc, Product[] products, int count, int[] summary) {
        System.out.print("請輸入欲購買商品名稱：");
        String name = sc.nextLine();
        int idx = findProductIndex(products, count, name);
        if (idx == -1) {
            System.out.println("錯誤：無此商品！");
            return;
        }

        System.out.print("請輸入購買數量 (現有庫存 " + products[idx].getStock() + ")：");
        int qty = sc.nextInt();
        sc.nextLine();

        if (products[idx].sell(qty)) {
            summary[idx] += qty;
            System.out.println("交易成功！已扣除庫存。");
        } else {
            System.out.println("交易失敗：數量錯誤或庫存不足！");
        }
    }

    public static void restockProduct(Scanner sc, Product[] products, int count) {
        System.out.print("請輸入補貨商品名稱：");
        String name = sc.nextLine();
        int idx = findProductIndex(products, count, name);
        if (idx == -1) {
            System.out.println("錯誤：無此商品！");
            return;
        }

        System.out.print("請輸入補貨數量：");
        int qty = sc.nextInt();
        sc.nextLine();

        if (products[idx].restock(qty)) {
            System.out.println("補貨成功！目前庫存：" + products[idx].getStock());
        } else {
            System.out.println("補貨失敗：數量必須大於 0！");
        }
    }

    public static void modifyProductPrice(Scanner sc, Product[] products, int count) {
        System.out.print("請輸入欲修改價格之商品名稱：");
        String name = sc.nextLine();
        int idx = findProductIndex(products, count, name);
        if (idx == -1) {
            System.out.println("錯誤：無此商品！");
            return;
        }

        System.out.print("請輸入新價格：");
        int price = sc.nextInt();
        sc.nextLine();

        if (products[idx].setPrice(price)) {
            System.out.println("價格修改成功！目前價格：" + products[idx].getPrice());
        } else {
            System.out.println("修改失敗：價格必須大於 0！");
        }
    }

    public static void showLowStockProducts(Product[] products, int count) {
        System.out.println("\n--- 低庫存商品清單 (< 10) ---");
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (products[i].isLowStock()) {
                System.out.println(products[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前無低庫存商品。");
        }
    }

    public static long calculateTotalInventoryValue(Product[] products, int count) {
        long total = 0;
        for (int i = 0; i < count; i++) {
            total += products[i].getInventoryValue();
        }
        return total;
    }

    public static void printSummaryReport(Product[] products, int count, int[] summary) {
        System.out.println("\n================ 操作摘要 ================");
        int totalRevenue = 0;
        for (int i = 0; i < count; i++) {
            if (summary[i] > 0) {
                int salesAmt = summary[i] * products[i].getPrice();
                System.out.println(products[i].getName() + " 共售出：" + summary[i] + " 件 | 銷售額：" + salesAmt);
                totalRevenue += salesAmt;
            }
        }
        System.out.println("本次系統運行總營收：" + totalRevenue + " 元");
        System.out.println("=========================================");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Product[] products = new Product[10];

        products[0] = new Product("Keyboard", 890, 12);
        products[1] = new Product("Mouse", 490, 20);
        products[2] = new Product("Monitor", 5200, 5);
        products[3] = new Product("USB Cable", 250, 30);
        products[4] = new Product("Headset", 1290, 8);
        int count = 5;

        int[] summary = new int[products.length];

        int choice;
        do {
            System.out.println("\n===== 物品導向商品管理系統 =====");
            System.out.println("1. 顯示全部商品");
            System.out.println("2. 依商品名稱搜尋");
            System.out.println("3. 新增商品");
            System.out.println("4. 出售商品");
            System.out.println("5. 補充庫存");
            System.out.println("6. 修改商品價格");
            System.out.println("7. 顯示低庫存商品");
            System.out.println("8. 顯示全部庫存總價值");
            System.out.println("9. 結束程式");
            System.out.print("請輸入選擇 (1~9)：");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    showAllProducts(products, count);
                    break;
                case 2:
                    searchProduct(sc, products, count);
                    break;
                case 3:
                    count = addNewProduct(sc, products, count);
                    break;
                case 4:
                    sellProduct(sc, products, count, summary);
                    break;
                case 5:
                    restockProduct(sc, products, count);
                    break;
                case 6:
                    modifyProductPrice(sc, products, count);
                    break;
                case 7:
                    showLowStockProducts(products, count);
                    break;
                case 8:
                    System.out.println("\n全部庫存總價值：" + calculateTotalInventoryValue(products, count) + " 元");
                    break;
                case 9:
                    printSummaryReport(products, count, summary);
                    System.out.println("系統結束。");
                    break;
                default:
                    System.out.println("無效選擇！");
            }
        } while (choice != 9);

        sc.close();
    }
}

/*
========================= 測試案例清單 (共 10 組) =========================

1. 正常商品搜尋（忽略大小寫、首尾空白）：
   - 輸入：搜尋 "  mOuSe  "
   - 預期結果：成功定位 "Mouse" 並印出資訊。

2. 搜尋不存在商品：
   - 輸入：搜尋 "RTX 4090"
   - 預期結果：顯示 "找不到此商品。"。

3. 正常新增商品：
   - 輸入：新增 "Speaker", 價格 1500, 庫存 15
   - 預期結果：新增成功，count 增至 6。

4. 欄位邊界驗證錯誤：
   - 輸入：新增 "Microphone", 價格 -500, 庫存 5
   - 預期結果：顯示新增失敗，價格或庫存資料不合規範。

5. 新增重複商品（不可重複原則）：
   - 輸入：新增 "  KEYBOARD "
   - 預期結果：顯示 "新增失敗：商品已存在，名稱不可重複！"。

6. 正常出售扣除庫存：
   - 輸入：購買 "Headset" 3 件 (庫存從 8 降至 5)
   - 預期結果：交易成功，且該商品變為低庫存警示狀態。

7. 惡意超額購買驗證：
   - 輸入：購買 "Monitor" 10 件 (現有庫存僅 5)
   - 預期結果：顯示 "交易失敗：數量錯誤或庫存不足！"。

8. 補充庫存負值驗證（防呆）：
   - 輸入：幫 "Mouse" 補貨 -5
   - 預期結果：顯示 "補貨失敗：數量必須大於 0！"。

9. 價格修改為負值防呆：
   - 輸入：修改 "USB Cable" 價格為 -50
   - 預期結果：顯示 "修改失敗：價格必須大於 0！"。

10. 溢出極限測試（陣列已滿限制）：
    - 步驟：手動新增商品至滿 10 個後，嘗試新增第 11 個。
    - 預期結果：顯示 "新增失敗：系統儲存空間已滿 (10/10)！"。
========================================================================
*/