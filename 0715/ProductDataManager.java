import java.util.Scanner;

public class ProductDataManager {
    public static void parseAndPrintTable(String[] records) {
        System.out.println("\n--- 解析商品資料表格 ---");
        System.out.printf("%-15s %-10s %-8s%n", "商品名稱", "價格", "庫存");
        System.out.println("-------------------------------------");
        for (String record : records) {
            String[] parts = record.split(",");
            if (parts.length == 3) {
                String name = parts[0].trim();
                System.out.printf("%-17s %-12s %-8s%n", name, parts[1].trim(), parts[2].trim());
            }
        }
    }

    public static void searchProduct(String[] records, String keyword) {
        String query = keyword.trim().toLowerCase();
        boolean found = false;
        System.out.println("\n--- 搜尋結果 ---");
        for (String record : records) {
            String[] parts = record.split(",");
            if (parts.length == 3) {
                String name = parts[0].trim();
                if (name.toLowerCase().contains(query)) {
                    System.out.printf("找到相符資料 -> [%s] 價格：%s, 庫存：%s%n", name, parts[1].trim(), parts[2].trim());
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("找不到符合此條件的商品名稱。");
        }
    }

    public static void showLowStocks(String[] records) {
        System.out.println("\n--- 低庫存商品警示 (< 10) ---");
        boolean found = false;
        for (String record : records) {
            String[] parts = record.split(",");
            if (parts.length == 3) {
                try {
                    String name = parts[0].trim();
                    int stock = Integer.parseInt(parts[2].trim());
                    if (stock < 10) {
                        System.out.printf("警告：[%s] 庫存偏低，目前僅剩 %d 個%n", name, stock);
                        found = true;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
        if (!found) {
            System.out.println("所有商品庫存水位正常。");
        }
    }

    public static int calculateTotalValue(String[] records) {
        int totalValue = 0;
        for (String record : records) {
            String[] parts = record.split(",");
            if (parts.length == 3) {
                try {
                    int price = Integer.parseInt(parts[1].trim());
                    int stock = Integer.parseInt(parts[2].trim());
                    totalValue += price * stock;
                } catch (NumberFormatException e) {
                }
            }
        }
        return totalValue;
    }

    public static boolean validateAndAddNewRecord(String input) {
        String[] parts = input.split(",");
        if (parts.length != 3) {
            System.out.println("格式驗證失敗：欄位數量不正確，必須剛好由兩個逗號分隔為三個欄位！");
            return false;
        }
        String name = parts[0].trim();
        if (name.isEmpty()) {
            System.out.println("格式驗證失敗：商品名稱不可為空白！");
            return false;
        }
        try {
            int price = Integer.parseInt(parts[1].trim());
            if (price < 0) {
                System.out.println("格式驗證失敗：價格不可為負數！");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("格式驗證失敗：價格必須是合法的整數！錯誤原因：" + e.getMessage());
            return false;
        }
        try {
            int stock = Integer.parseInt(parts[2].trim());
            if (stock < 0) {
                System.out.println("格式驗證失敗：庫存不可為負數！");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("格式驗證失敗：庫存必須是合法的整數！錯誤原因：" + e.getMessage());
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] records = {
            "Keyboard,890,12",
            "Mouse,490,20",
            "Monitor,5200,5",
            "USB Cable,250,30",
            "Headset,1290,8"
        };

        int choice;
        do {
            System.out.println("\n===== 商品文字資料管理選單 =====");
            System.out.println("1. 顯示商品表格");
            System.out.println("2. 商品名稱搜尋 (完整或部分搜尋)");
            System.out.println("3. 顯示低庫存商品列表 (< 10)");
            System.out.println("4. 顯示目前庫存總價值");
            System.out.println("5. 輸入並驗證新商品字串資料");
            System.out.println("6. 結束");
            System.out.print("請選擇 (1~6)：");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    parseAndPrintTable(records);
                    break;
                case 2:
                    System.out.print("請輸入搜尋關鍵字：");
                    String keyword = sc.nextLine();
                    searchProduct(records, keyword);
                    break;
                case 3:
                    showLowStocks(records);
                    break;
                case 4:
                    int val = calculateTotalValue(records);
                    System.out.println("\n庫存總價值：" + val + " 元");
                    break;
                case 5:
                    System.out.print("請輸入 CSV 格式資料 (例如: Speaker,1500,15)：");
                    String input = sc.nextLine();
                    if (validateAndAddNewRecord(input)) {
                        System.out.println("驗證成功！該筆資料格式完全正確。");
                    }
                    break;
                case 6:
                    System.out.println("結束程式。");
                    break;
                default:
                    System.out.println("無效選擇！");
            }
        } while (choice != 6);

        sc.close();
    }
}

/*
========================= 測試案例清單 =========================

1. 正常值測試（格式完全正確）：
   - 輸入："Speaker,1500,15"
   - 預期結果：驗證成功。

2. 格式錯誤測試一（欄位數量不足）：
   - 輸入："Speaker,1500"
   - 預期結果：格式驗證失敗：欄位數量不正確，必須剛好由兩個逗號分隔為三個欄位！

3. 格式錯誤測試二（多出額外欄位）：
   - 輸入："Speaker,1500,15,Taiwan"
   - 預期結果：格式驗證失敗：欄位數量不正確...

4. 格式錯誤測試三（名稱為空值）：
   - 輸入：" ,1500,15"
   - 預期結果：格式驗證失敗：商品名稱不可為空白！

5. 數值轉型錯誤測試一（價格為非整數）：
   - 輸入："Speaker,abc,15"
   - 預期結果：格式驗證失敗：價格必須是合法的整數！錯誤原因：For input string: "abc"

6. 數值轉型錯誤測試二（庫存為非整數）：
   - 輸入："Speaker,1500,3.5"
   - 預期結果：格式驗證失敗：庫存必須是合法的整數！錯誤原因：For input string: "3.5"

7. 邏輯邊界錯誤測試一（價格為負數）：
   - 輸入："Speaker,-10,15"
   - 預期結果：格式驗證失敗：價格不可為負數！

8. 邏輯邊界錯誤測試二（庫存為負數）：
   - 輸入："Speaker,1500,-1"
   - 預期結果：格式驗證失敗：庫存不可為負數！
==============================================================
*/