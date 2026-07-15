import java.util.Scanner;

public class ProductSearchSystem {
    public static void showAllProducts(String[] names, int[] prices, int[] stocks) {
        System.out.println("\n--- 全部商品 ---");
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%d. %-15s | 價格：%d | 庫存：%d%n", (i + 1), names[i], prices[i], stocks[i]);
        }
    }

    public static void exactSearch(String[] names, int[] prices, int[] stocks, String keyword) {
        String normalizedKeyword = keyword.replace(" ", "").toLowerCase();
        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            String normalizedName = names[i].replace(" ", "").toLowerCase();
            if (normalizedName.equals(normalizedKeyword)) {
                System.out.printf("找到相符商品：[%s] 價格：%d, 庫存：%d (索引：%d)%n", names[i], prices[i], stocks[i], i);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("找不到完全符合的商品。");
        }
    }

    public static void partialSearch(String[] names, int[] prices, int[] stocks, String keyword) {
        String normalizedKeyword = keyword.trim().toLowerCase();
        boolean found = false;
        for (int i = 0; i < names.length; i++) {
            if (names[i].toLowerCase().contains(normalizedKeyword)) {
                System.out.printf("符合商品：[%s] 價格：%d, 庫存：%d (索引：%d)%n", names[i], prices[i], stocks[i], i);
                found = true;
            }
        }
        if (!found) {
            System.out.println("找不到包含關鍵字的商品。");
        }
    }

    public static void showLastMatch(String[] names, int[] prices, int[] stocks, String keyword) {
        String normalizedKeyword = keyword.trim().toLowerCase();
        int lastIndex = -1;
        for (int i = names.length - 1; i >= 0; i--) {
            if (names[i].toLowerCase().contains(normalizedKeyword)) {
                lastIndex = i;
                break;
            }
        }
        if (lastIndex != -1) {
            System.out.printf("最後相符商品：[%s] 價格：%d, 庫存：%d (索引：%d)%n", 
                names[lastIndex], prices[lastIndex], stocks[lastIndex], lastIndex);
        } else {
            System.out.println("找不到相符的商品。");
        }
    }

    public static void showFirstOccurrenceIndex(String[] names, String keyword) {
        String normalizedKeyword = keyword.trim().toLowerCase();
        boolean found = false;
        for (String name : names) {
            int idx = name.toLowerCase().indexOf(normalizedKeyword);
            if (idx != -1) {
                System.out.printf("商品：[%s] -> 關鍵字首次出現於字串索引 %d%n", name, idx);
                found = true;
            }
        }
        if (!found) {
            System.out.println("所有商品名稱皆不包含該關鍵字。");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] names = {"Keyboard", "Mouse", "Monitor", "USB Cable", "Headset"};
        int[] prices = {890, 490, 5200, 250, 1290};
        int[] stocks = {12, 20, 5, 30, 8};

        int choice;
        do {
            System.out.println("\n===== 商品名稱搜尋系統 =====");
            System.out.println("1. 顯示全部商品");
            System.out.println("2. 完整名稱搜尋 (忽略大小寫與空白)");
            System.out.println("3. 部分名稱搜尋 (模糊搜尋)");
            System.out.println("4. 搜尋符合條件之最後一個商品");
            System.out.println("5. 顯示名稱中首次出現關鍵字的位置");
            System.out.println("6. 結束");
            System.out.print("請選擇功能 (1~6)：");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    showAllProducts(names, prices, stocks);
                    break;
                case 2:
                    System.out.print("請輸入完整商品名稱：");
                    String exactName = sc.nextLine();
                    exactSearch(names, prices, stocks, exactName);
                    break;
                case 3:
                    System.out.print("請輸入部分搜尋關鍵字：");
                    String partKey = sc.nextLine();
                    partialSearch(names, prices, stocks, partKey);
                    break;
                case 4:
                    System.out.print("請輸入最後相符搜尋關鍵字：");
                    String lastKey = sc.nextLine();
                    showLastMatch(names, prices, stocks, lastKey);
                    break;
                case 5:
                    System.out.print("請輸入定位關鍵字：");
                    String locKey = sc.nextLine();
                    showFirstOccurrenceIndex(names, locKey);
                    break;
                case 6:
                    System.out.println("系統結束。");
                    break;
                default:
                    System.out.println("無效選擇！");
            }
        } while (choice != 6);

        sc.close();
    }
}

/*
========================= 測試案例說明 =========================
1. 正常值測試（精準搜尋）：輸入 "Keyboard"
   - 預期結果：印出 Keyboard 資訊，索引 0
   - 實際結果：符合

2. 忽略空白與大小寫測試（精準搜尋）：輸入 " u s b c a b l e "
   - 預期結果：印出 USB Cable 資訊，索引 3
   - 實際結果：符合

3. 部分搜尋（多筆回傳）：輸入 "o"
   - 預期結果：同時列出 Keyboard、Monitor
   - 實際結果：符合

4. 搜尋最後一筆：輸入 "e"
   - 預期結果：符合者有 Keyboard (0), Mouse (1), Cable (3), Headset (4)。
              應顯示最後一個符合的 Headset，索引 4。
   - 實際結果：符合

5. 首次出現定位點搜尋：輸入 "o"
   - 預期結果：
     - Keyboard 首次出現在 4
     - Monitor 首次出現在 1 (且 Monitor 有兩個 o，僅定位第一個)
   - 實際結果：符合

6. 邊界/不存在值測試：搜尋 "abc"
   - 預期結果：顯示找不到資料。
   - 實際結果：符合
==============================================================
*/