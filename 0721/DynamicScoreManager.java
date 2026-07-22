import java.util.ArrayList;
import java.util.Scanner;

public class DynamicScoreManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> scores = new ArrayList<>();

        System.out.println("=== 動態成績管理系統 ===");
        System.out.println("請輸入成績（0-100），輸入 -1 結束：");

        while (true) {
            System.out.print("請輸入成績: ");
            if (!scanner.hasNextInt()) {
                System.out.println("❌ 輸入格式錯誤，請輸入整數！");
                scanner.next();
                continue;
            }

            int input = scanner.nextInt();
            if (input == -1) {
                break;
            }

            if (input < 0 || input > 100) {
                System.out.println("❌ 成績必須介於 0 到 100 之間，請重新輸入。");
                continue;
            }

            scores.add(input);
        }

        if (scores.isEmpty()) {
            System.out.println("\n未輸入任何有效成績。");
        } else {
            System.out.println("\n=== 統計結果 ===");
            System.out.println("總筆數：" + scores.size());
            System.out.println("平均分數：" + String.format("%.2f", calculateAverage(scores)));
            System.out.println("最高分：" + findMax(scores));
            System.out.println("最低分：" + findMin(scores));
            System.out.println("及格名單（>=60）：" + filterPassed(scores, 60));
        }

        scanner.close();
    }

    public static double calculateAverage(ArrayList<Integer> list) {
        int sum = 0;
        for (int score : list) {
            sum += score;
        }
        return (double) sum / list.size();
    }

    public static int findMax(ArrayList<Integer> list) {
        int max = list.get(0);
        for (int score : list) {
            if (score > max) max = score;
        }
        return max;
    }

    public static int findMin(ArrayList<Integer> list) {
        int min = list.get(0);
        for (int score : list) {
            if (score < min) min = score;
        }
        return min;
    }

    public static ArrayList<Integer> filterPassed(ArrayList<Integer> list, int passGrade) {
        ArrayList<Integer> passed = new ArrayList<>();
        for (int score : list) {
            if (score >= passGrade) {
                passed.add(score);
            }
        }
        return passed;
    }
}