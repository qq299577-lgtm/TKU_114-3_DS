/*
 * 0708 課堂實作題：ATM方法拆解
 * * 預計拆解的 4 個方法名稱與目的：
 * 1. printAtmMenu()                     - 負責固定印出 ATM 功能選單
 * 2. validateAmount(int amount, Scanner sc) - 負責驗證輸入金額大於 0
 * 3. deposit(int balance, int amount)    - 處理存款邏輯，計算並更新餘額
 * 4. withdraw(int balance, int amount)   - 處理提款邏輯，包含餘額不足的驗證
 */

import java.util.Scanner;

public class AtmMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int balance = 1000; // 初始餘額
        int option = -1;

        while (option != 0) {
            printAtmMenu(); // 呼叫方法 1：印出選單
            System.out.print("請選擇功能：");
            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("目前帳戶餘額為：" + balance + " 元");
                    break;

                case 2:
                    System.out.print("請輸入存款金額：");
                    int depositAmount = sc.nextInt();
                    // 呼叫方法 2：驗證存款金額必須大於 0
                    depositAmount = validateAmount(depositAmount, sc);
                    
                    // 呼叫方法 3：執行存款並更新餘額
                    balance = deposit(balance, depositAmount);
                    break;

                case 3:
                    System.out.print("請輸入提款金額：");
                    int withdrawAmount = sc.nextInt();
                    // 呼叫方法 4：驗證提款金額（包含小於0與超額檢查）
                    withdrawAmount = validateWithdraw(withdrawAmount, balance, sc);
                    
                    // 呼叫方法 5：執行提款並更新餘額
                    balance = withdraw(balance, withdrawAmount);
                    break;

                case 0:
                    System.out.println("謝謝使用，系統已安全登出。");
                    break;

                default:
                    System.out.println("無效選項，請重新輸入。");
            }
        }
        sc.close();
    }

    // === 方法 1：固定印出 ATM 功能選單 ===
    public static void printAtmMenu() {
        System.out.println("\n=== ATM Menu ===");
        System.out.println("1：查詢餘額");
        System.out.println("2：存款");
        System.out.println("3：提款");
        System.out.println("0：離開");
    }

    // === 方法 2：驗證存款金額大於 0 ===
    public static int validateAmount(int amount, Scanner sc) {
        while (amount <= 0) {
            System.out.print("金額必須大於0，請重新輸入：");
            amount = sc.nextInt();
        }
        return amount;
    }

    // === 方法 4：驗證提款金額（包含餘額檢查） ===
    public static int validateWithdraw(int amount, int balance, Scanner sc) {
        while (amount <= 0 || amount > balance) {
            if (amount <= 0) {
                System.out.print("提款金額必須大於0，請重新輸入：");
            } else {
                System.out.print("餘額不足（目前餘額 " + balance + " 元），請重新輸入：");
            }
            amount = sc.nextInt();
        }
        return amount;
    }

    // === 方法 3：執行存款邏輯 ===
    public static int deposit(int balance, int amount) {
        balance += amount;
        System.out.println("存款成功！目前餘額為：" + balance + " 元");
        return balance;
    }

    // === 方法 5：執行提款邏輯 ===
    public static int withdraw(int balance, int amount) {
        balance -= amount;
        System.out.println("提款成功！請取走您的現金。目前餘額為：" + balance + " 元");
        return balance;
    }
}