import java.util.Scanner;

public class AtmSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int balance = 1000; // 初始餘額
        int depositCount = 0;
        int withdrawCount = 0;
        int totalDeposit = 0;
        int totalWithdraw = 0;
        int option = -1;

        // while 迴圈：重複顯示選單直到輸入 0 離開
        while (option != 0) {
            printMenu(); // 呼叫方法 1
            System.out.print("請輸入選項：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    // 呼叫方法 6：顯示餘額
                    printBalance(balance);
                    break;

                case 2:
                    // 呼叫方法 2：讀取並驗證存款金額大於 0
                    int depositAmount = readPositiveAmount(sc, "請輸入存款金額：");
                    
                    // 呼叫方法 3：執行存款並更新餘額
                    balance = deposit(balance, depositAmount);
                    
                    // 累加成功存款數據
                    depositCount++;
                    totalDeposit += depositAmount;
                    
                    System.out.println("Balance: " + balance);
                    break;

                case 3:
                    // 呼叫方法 2：讀取並驗證提款金額大於 0
                    int withdrawAmount = readPositiveAmount(sc, "請輸入提款金額：");
                    
                    // 呼叫方法 5：重要判斷，檢查餘額是否足夠提款
                    if (canWithdraw(balance, withdrawAmount)) {
                        // 呼叫方法 4：執行提款並更新餘額
                        balance = withdraw(balance, withdrawAmount);
                        
                        // 累加成功提款數據
                        withdrawCount++;
                        totalWithdraw += withdrawAmount;
                        
                        System.out.println("Balance: " + balance);
                    } else {
                        System.out.println("錯誤：餘額不足，提款失敗。");
                    }
                    break;

                case 4:
                    // 呼叫方法 7：提早觀看目前統計資料（對應選單功能 4）
                    printSummary(balance, depositCount, withdrawCount, totalDeposit, totalWithdraw);
                    break;

                case 0:
                    // 離開時輸出完整摘要
                    break;

                default:
                    System.out.println("無效選項，請重新輸入。");
            }
        }

        // 呼叫方法 7：離開系統前的最終操作摘要
        printSummary(balance, depositCount, withdrawCount, totalDeposit, totalWithdraw);

        sc.close();
    }

    // === 方法 1：顯示 ATM 功能選單 ===
    public static void printMenu() {
        System.out.println("\n=== ATM Menu ===");
        System.out.println("1. Check balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Show summary");
        System.out.println("0. Exit");
    }

    // === 方法 2：讀取並驗證正整數金額（防錯機制） ===
    public static int readPositiveAmount(Scanner sc, String message) {
        System.out.print(message);
        int amount = sc.nextInt();
        while (amount <= 0) {
            System.out.print("金額必須大於0，請重新輸入：");
            amount = sc.nextInt();
        }
        return amount;
    }

    // === 方法 3：執行存款邏輯 ===
    public static int deposit(int balance, int amount) {
        return balance + amount;
    }

    // === 方法 4：執行提款邏輯 ===
    public static int withdraw(int balance, int amount) {
        return balance - amount;
    }

    // === 方法 5：重要判斷：檢查提款金額是否小於等於當前餘額 ===
    public static boolean canWithdraw(int balance, int amount) {
        return amount <= balance;
    }

    // === 方法 6：列印當前餘額 ===
    public static void printBalance(int balance) {
        System.out.println("Balance: " + balance);
    }

    // === 方法 7：輸出最終操作摘要明細 ===
    public static void printSummary(int balance, int depositCount, int withdrawCount, int totalDeposit, int totalWithdraw) {
        System.out.println("\n=== ATM Summary ===");
        System.out.println("Final balance: " + balance);
        System.out.println("Deposit count: " + depositCount);
        System.out.println("Withdraw count: " + withdrawCount);
        System.out.println("Total deposit: " + totalDeposit);
        System.out.println("Total withdraw: " + totalWithdraw);
    }
}