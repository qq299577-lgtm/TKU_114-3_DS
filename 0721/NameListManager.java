import java.util.ArrayList;
import java.util.Scanner;

public class NameListManager {
    private static ArrayList<String> names = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println("\n=== 名單管理系統 ===");
            System.out.println("1. 新增姓名");
            System.out.println("2. 搜尋姓名");
            System.out.println("3. 修改姓名");
            System.out.println("4. 刪除姓名");
            System.out.println("5. 列出全部");
            System.out.println("6. 離開");
            System.out.print("請選擇功能 (1-6): ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> addName();
                case "2" -> searchName();
                case "3" -> updateName();
                case "4" -> deleteName();
                case "5" -> listAll();
                case "6" -> {
                    running = false;
                    System.out.println("系統已結束使用。");
                }
                default -> System.out.println("❌ 無效選擇，請重新輸入！");
            }
        }
    }

    private static void addName() {
        System.out.print("請輸入要新增的姓名: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("❌ 姓名不能為空白！");
            return;
        }
        names.add(name);
        System.out.println("✅ 已成功新增：" + name);
    }

    private static void searchName() {
        System.out.print("請輸入要搜尋的姓名: ");
        String target = scanner.nextLine().trim();
        int index = findIndexIgnoreCase(target);
        if (index != -1) {
            System.out.println("✅ 找到姓名！位置在索引 " + index + "（" + names.get(index) + "）");
        } else {
            System.out.println("❌ 找不到姓名：" + target);
        }
    }

    private static void updateName() {
        System.out.print("請輸入要修改的舊姓名: ");
        String oldName = scanner.nextLine().trim();
        int index = findIndexIgnoreCase(oldName);
        if (index == -1) {
            System.out.println("❌ 找不到該姓名，無法修改。");
            return;
        }

        System.out.print("請輸入新姓名: ");
        String newName = scanner.nextLine().trim();
        if (newName.isEmpty()) {
            System.out.println("❌ 新姓名不能為空白！");
            return;
        }

        names.set(index, newName);
        System.out.println("✅ 已將 " + oldName + " 修改為 " + newName);
    }

    private static void deleteName() {
        System.out.print("請輸入要刪除的姓名: ");
        String target = scanner.nextLine().trim();
        int index = findIndexIgnoreCase(target);
        if (index != -1) {
            String removed = names.remove(index);
            System.out.println("✅ 成功刪除：" + removed);
        } else {
            System.out.println("❌ 刪除失敗，找不到姓名：" + target);
        }
    }

    private static void listAll() {
        if (names.isEmpty()) {
            System.out.println("目前名單為空。");
            return;
        }
        System.out.println("--- 目前名單 ---");
        for (int i = 0; i < names.size(); i++) {
            System.out.println((i + 1) + ". " + names.get(i));
        }
    }

    private static int findIndexIgnoreCase(String target) {
        for (int i = 0; i < names.size(); i++) {
            if (names.get(i).equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }
}