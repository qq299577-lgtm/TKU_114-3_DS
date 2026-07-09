import java.util.Scanner;

public class PersonalProfileApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("請輸入姓名：");
        String name = sc.nextLine();

        // 呼叫方法 1：輸入並驗證年齡
        int age = readPositiveInt(sc, "請輸入年齡：");

        // 呼叫方法 2：輸入並驗證身高與體重
        double height = readPositiveDouble(sc, "請輸入身高（公尺）：");
        double weight = readPositiveDouble(sc, "請輸入體重（公斤）：");

        // 呼叫方法 3：計算 BMI
        double bmi = calculateBmi(height, weight);

        // 呼叫方法 4：取得 BMI 分級
        String level = getBmiLevel(bmi);

        // 呼叫方法 5：判斷是否成年
        boolean adult = isAdult(age);

        // 呼叫方法 6：印出完整健康報表
        printReport(name, age, adult, height, weight, bmi, level);

        // 關閉 Scanner 資源，解除 VS Code 的黃色警告
        sc.close();
    }

    // === 方法 1：讀取並驗證正整數（年齡） ===
    public static int readPositiveInt(Scanner sc, String message) {
        System.out.print(message);
        int value = sc.nextInt();
        while (value <= 0) {
            System.out.print("輸入不合法！數值必須大於0，請重新輸入：");
            value = sc.nextInt();
        }
        return value;
    }

    // === 方法 2：讀取並驗證正浮點數（身高、體重） ===
    public static double readPositiveDouble(Scanner sc, String message) {
        System.out.print(message);
        double value = sc.nextDouble();
        // 修正後的 while 迴圈，不會再強迫使用者重複輸入兩次了
        while (value <= 0) {
            System.out.print("輸入不合法！數值必須大於0，請重新輸入：");
            value = sc.nextDouble();
        }
        return value;
    }

    // === 方法 3：計算 BMI 公式 ===
    public static double calculateBmi(double height, double weight) {
        // 重要計算：BMI = 體重 / (身高 * 身高)
        return weight / (height * height);
    }

    // === 方法 4：根據 BMI 判斷健康分級 ===
    public static String getBmiLevel(double bmi) {
        // 重要判斷：對應講義的分級規則
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 24) {
            return "Normal";
        } else if (bmi < 27) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    // === 方法 5：判斷是否成年 ===
    public static boolean isAdult(int age) {
        // 年齡滿 18 歲（含）以上為成年
        return age >= 18;
    }

    // === 方法 6：輸出最終格式化報表 ===
    public static void printReport(String name, int age, boolean adult, double height, double weight, double bmi, String level) {
        System.out.println("\n=== Personal Health Report ===");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Adult: " + adult);
        System.out.println("Height: " + height);
        System.out.println("Weight: " + weight);
        System.out.println("BMI: " + bmi);
        System.out.println("Level: " + level);
    }
}