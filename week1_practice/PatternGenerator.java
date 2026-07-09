import java.util.Scanner;

public class PatternGenerator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int option = -1;

        // while 迴圈：重複顯示選單直到使用者選擇 0 離開
        while (option != 0) {
            printMenu(); // 呼叫方法 1：印出選單
            System.out.print("請輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    // 呼叫方法 3：印出九九乘法表
                    printMultiplicationTable();
                    break;

                case 2:
                    // 呼叫方法 2：輸入並驗證正整數高度
                    int triHeight = readPositiveInt(sc, "請輸入正三角形高度：");
                    // 呼叫方法 4：印出正三角形
                    printTriangle(triHeight);
                    break;

                case 3:
                    // 呼叫方法 2：輸入並驗證正整數高度
                    int revTriHeight = readPositiveInt(sc, "請輸入倒三角形高度：");
                    // 呼叫方法 5：印出倒三角形
                    printReverseTriangle(revTriHeight);
                    break;

                case 4:
                    // 呼叫方法 2：分別輸入並驗證列數與欄數
                    int rows = readPositiveInt(sc, "請輸入數字方格列數 (Rows)：");
                    int cols = readPositiveInt(sc, "請輸入數字方格欄數 (Cols)：");
                    // 呼叫方法 6：印出數字方格
                    printNumberGrid(rows, cols);
                    break;

                case 0:
                    System.out.println("謝謝使用，系統已結束。");
                    break;

                default:
                    System.out.println("無效選項，請重新輸入。");
            }
        }
        sc.close();
    }

    // === 方法 1：顯示圖形產生器選單 ===
    public static void printMenu() {
        System.out.println("\n=== Pattern & Table Generator ===");
        System.out.println("1. 九九乘法表");
        System.out.println("2. 正三角形星號");
        System.out.println("3. 倒三角形星號");
        System.out.println("4. 數字方格");
        System.out.println("0. 離開");
    }

    // === 方法 2：讀取並驗證正整數（防錯機制：高度、列、欄皆重用此功能） ===
    public static int readPositiveInt(Scanner sc, String message) {
        System.out.print(message);
        int value = sc.nextInt();
        while (value <= 0) {
            System.out.print("錯誤：輸入數值必須大於 0！請重新輸入：");
            value = sc.nextInt();
        }
        return value;
    }

    // === 方法 3：巢狀迴圈輸出九九乘法表 ===
    public static void printMultiplicationTable() {
        System.out.println("\n--- 九九乘法表 ---");
        // 外層迴圈控制被乘數 (1~9)
        for (int i = 1; i <= 9; i++) {
            // 內層迴圈控制乘數 (1~9)
            for (int j = 1; j <= 9; j++) {
                // 使用 \t 進行文字對齊
                System.out.print(j + "x" + i + "=" + (i * j) + "\t");
            }
            System.out.println(); // 換列
        }
    }

    // === 方法 4：巢狀迴圈輸出正三角形 ===
    public static void printTriangle(int height) {
        System.out.println("\n--- 正三角形 ---");
        // 外層迴圈控制「目前在第幾列」
        for (int row = 1; row <= height; row++) {
            // 內層迴圈控制「該列要印出幾個星號」
            for (int col = 1; col <= row; col++) {
                System.out.print("*");
            }
            System.out.println(); // 換列
        }
    }

    // === 方法 5：巢狀迴圈輸出倒三角形 ===
    public static void printReverseTriangle(int height) {
        System.out.println("\n--- 倒三角形 ---");
        // 外層迴圈從高度遞減，控制「目前在第幾列」
        for (int row = height; row >= 1; row--) {
            // 內層迴圈跟著 row 的數量遞減印出星號
            for (int col = 1; col <= row; col++) {
                System.out.print("*");
            }
            System.out.println(); // 換列
        }
    }

    // === 方法 6：巢狀迴圈輸出數字方格 ===
    public static void printNumberGrid(int rows, int cols) {
        System.out.println("\n--- 數字方格 ---");
        // 外層迴圈控制總共有幾列
        for (int row = 1; row <= rows; row++) {
            // 內層迴圈從 1 印到指定的總欄數 (cols)
            for (int col = 1; col <= cols; col++) {
                System.out.print(col + " ");
            }
            System.out.println(); // 換列
        }
    }
}