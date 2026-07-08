import java.util.Scanner;

public class ScoreMenu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("請輸入姓名：");
        String name = scanner.next();

        System.out.print("請輸入 Java 成績：");
        int javaScore = scanner.nextInt();

        System.out.print("請輸入英文成績：");
        int englishScore = scanner.nextInt();

        System.out.print("請輸入數學成績：");
        int mathScore = scanner.nextInt();

        double average = (javaScore + englishScore + mathScore) / 3.0;

        String status = (average >= 60) ? "Pass" : "Fail";

        String grade = "";
        if (average >= 90) {
            grade = "A";
        } else if (average >= 80) {
            grade = "B";
        } else if (average >= 70) {
            grade = "C";
        } else if (average >= 60) {
            grade = "D";
        } else {
            grade = "F";
        }

        int choice = -1;
        while (choice != 0) {
            System.out.println("\n=== 選單 ===");
            System.out.println("1：顯示平均分數");
            System.out.println("2：顯示及格狀態");
            System.out.println("3：顯示等第");
            System.out.println("0：離開");
            System.out.print("請選擇功能：");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("平均分數: " + average);
                    break;
                case 2:
                    System.out.println("及格狀態: " + status);
                    break;
                case 3:
                    System.out.println("學生等第: " + grade);
                    break;
                case 0:
                    System.out.println("程式已結束。");
                    break;
                default:
                    System.out.println("無效的選項，請重新輸入。");
            }
        }

        scanner.close();
    }
}