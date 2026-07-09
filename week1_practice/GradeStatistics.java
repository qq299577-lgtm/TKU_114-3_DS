import java.util.Scanner;

public class GradeStatistics {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int count = 0;
        int total = 0;
        int max = -1;       // 預設最低，只要有任何合法分數進來都會被蓋過去
        int min = 101;      // 預設最高，只要有任何合法分數進來都會被蓋過去
        int passCount = 0;
        int failCount = 0;

        while (true) {
            System.out.print("請輸入成績（輸入 -1 結束）：");
            int score = sc.nextInt();

            // 判斷是否為結束訊號
            if (score == -1) {
                break;
            }

            // 呼叫方法 1：輸入驗證（若不合法則強迫重新輸入）
            while (!isValidScore(score)) {
                System.out.print("錯誤：成績必須在 0-100 之間！請重新輸入：");
                score = sc.nextInt();
                if (score == -1) {
                    break;
                }
            }

            // 如果在防錯迴圈裡輸入了 -1，要能再次觸發結束
            if (score == -1) {
                break;
            }

            // 開始統計各項數據
            count++;
            total += score;

            // 重要計算：動態更新最高分與最低分
            if (score > max) {
                max = score;
            }
            if (score < min) {
                min = score;
            }

            // 呼叫方法 2：判斷及格或不及格，並更新計數器
            if (isPassing(score)) {
                passCount++;
            } else {
                failCount++;
            }
        }

        // 情況特殊：如果第一個輸入就是 -1，count 會是 0
        if (count == 0) {
            System.out.println("No scores entered.");
        } else {
            // 重要計算：計算平均值（轉 double 避免無條件捨去）
            double average = (double) total / count;
            
            // 呼叫方法 3：取得平均分數的等第
            String grade = getGrade(average);

            // 呼叫方法 4：印出最終統計結果
            printSummary(count, total, average, max, min, passCount, failCount, grade);
        }

        sc.close();
    }

    // === 方法 1：驗證成績是否在合法範圍 (0-100) ===
    public static boolean isValidScore(int score) {
        return score >= 0 && score <= 100;
    }

    // === 方法 2：判斷分數是否及格 (60分以上) ===
    public static boolean isPassing(int score) {
        return score >= 60;
    }

    // === 方法 3：根據平均分數回傳對應等第 ===
    public static String getGrade(double average) {
        // 重要判斷：依照等第規則區分
        if (average >= 90) {
            return "A";
        } else if (average >= 80) {
            return "B";
        } else if (average >= 70) {
            return "C";
        } else if (average >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    // === 方法 4：輸出最終格式化報表 ===
    public static void printSummary(int count, int total, double average, int max, int min, int passCount, int failCount, String grade) {
        System.out.println("\n=== Grade Summary ===");
        System.out.println("Count: " + count);
        System.out.println("Total: " + total);
        System.out.println("Average: " + average);
        System.out.println("Max: " + max);
        System.out.println("Min: " + min);
        System.out.println("Pass count: " + passCount);
        System.out.println("Fail count: " + failCount);
        System.out.println("Average grade: " + grade);
    }
}