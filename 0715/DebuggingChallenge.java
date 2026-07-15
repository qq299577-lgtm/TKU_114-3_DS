import java.util.Scanner;

public class DebuggingChallenge {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] scores = {80, 75, 92};
        int total = 0;

        for (int i = 0; i < scores.length; i++) {
            total += scores[i];
        }

        double average = (double) total / scores.length;
        System.out.printf("平均：%.2f%n", average);

        System.out.print("請輸入年齡：");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("請輸入指令：");
        String command = sc.nextLine();

        if ("exit".equals(command)) {
            System.out.println("系統結束，年齡：" + age);
        }

        sc.close();
    }
}

/*
========================= 除錯報告清單 =========================

1. 錯誤編號一：
   - 錯誤位置：System.out.println("系統結束，年齡：" + age)
   - 錯誤類型：編譯錯誤 (Compile-time Error)
   - 原因：行尾缺少分號 (`;`)，違反 Java 語法規範。
   - 修改方式：在該行結尾補上分號。

2. 錯誤編號二：
   - 錯誤位置：for (int i = 0; i <= scores.length; i++)
   - 錯誤類型：執行期錯誤 (Runtime Error - ArrayIndexOutOfBoundsException)
   - 原因：迴圈條件寫成 `i <= scores.length`。長度為 3 的陣列最大索引是 2，當 i = 3 時企圖存取 scores[3] 導致越界崩潰。
   - 關鍵數值記錄：越界發生時，i = 3, scores.length = 3, total = 247。
   - 修改方式：將 `<= ` 修改為 `<`。

3. 錯誤編號三：
   - 錯誤位置：double average = total / scores.length;
   - 錯誤類型：邏輯錯誤 (Logic Error)
   - 原因：`total` 與 `scores.length` 皆為 int，進行整數除法時會捨去小數點後數值（得到 82.00 而非 82.33）。
   - 關鍵數值記錄：除法前 total = 247, scores.length = 3；未修復前 average 得到 82.0；修復後 average 得到 82.33333...。
   - 修改方式：將首個操作數強制轉型為 double：`(double) total / scores.length`。

4. 錯誤編號四：
   - 錯誤位置：sc.nextInt() 之後直接執行 sc.nextLine()
   - 錯誤類型：Scanner 緩衝區換行問題 / 執行期輸入邏輯錯誤
   - 原因：`nextInt()` 只讀取了數字，使用者按下的 Enter 換行符 (決定的 `\n`) 仍遺留於緩衝區。這導致下一行 `command = sc.nextLine()` 直接讀到了空行，完全跳過使用者輸入指令的步驟。
   - 關鍵數值記錄：age 變數成功讀取 20；接下來的 command 變數直接被指派為空字串 `""`，而不是使用者真正想輸入的內容。
   - 修改方式：在 `int age = sc.nextInt();` 的下一行，額外加入 `sc.nextLine();` 來讀走並清空緩衝區中的換行符。

5. 錯誤編號五：
   - 錯誤位置：if (command == "exit")
   - 錯誤類型：邏輯錯誤 (Logic Error)
   - 原因：`==` 比較的是物件記憶體位址而非內容。由 Scanner 動態輸入產生的字串不一定位於常量池中，使用 `==` 比較經常會返回 false。
   - 關鍵數值記錄：當輸入 command 為 "exit" 時，`command == "exit"` 判斷式返回 false。
   - 修改方式：改為使用字串的 `"exit".equals(command)` 進行內容比對。
==============================================================
*/