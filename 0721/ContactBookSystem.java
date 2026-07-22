import java.util.ArrayList;
import java.util.Scanner;

public class ContactBookSystem {
    private static ArrayList<Contact> contacts = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            System.out.print("請選擇操作 (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addContact();
                case "2" -> searchContact();
                case "3" -> updatePhone();
                case "4" -> deleteContact();
                case "5" -> listContacts();
                case "6" -> {
                    System.out.println("感謝使用，系統關閉。");
                    return;
                }
                default -> System.out.println("❌ 無效選擇，請重新輸入！");
            }
        }
    }

    public static void printMenu() {
        System.out.println("\n=== 聯絡人管理系統 ===");
        System.out.println("1. 新增聯絡人");
        System.out.println("2. 搜尋聯絡人");
        System.out.println("3. 修改電話");
        System.out.println("4. 刪除聯絡人");
        System.out.println("5. 列出所有聯絡人");
        System.out.println("6. 離開");
    }

    public static Contact findByCode(String code) {
        for (Contact c : contacts) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }

    public static void addContact() {
        System.out.print("輸入代碼: ");
        String code = scanner.nextLine().trim();
        if (code.isEmpty() || findByCode(code) != null) {
            System.out.println("❌ 代碼無效或已存在！");
            return;
        }

        System.out.print("輸入姓名: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("❌ 姓名不可為空白！");
            return;
        }

        System.out.print("輸入電話: ");
        String phone = scanner.nextLine().trim();
        System.out.print("輸入 Email: ");
        String email = scanner.nextLine().trim();

        contacts.add(new Contact(code, name, phone, email));
        System.out.println("✅ 新增成功！");
    }

    public static void searchContact() {
        System.out.print("輸入搜尋關鍵字 (代碼或姓名): ");
        String keyword = scanner.nextLine().trim();
        boolean found = false;

        for (Contact c : contacts) {
            if (c.getCode().equalsIgnoreCase(keyword) || c.getName().equalsIgnoreCase(keyword)) {
                System.out.println(c);
                found = true;
            }
        }
        if (!found) System.out.println("❌ 查無此聯絡人！");
    }

    public static void updatePhone() {
        System.out.print("輸入欲修改者的代碼: ");
        String code = scanner.nextLine().trim();
        Contact c = findByCode(code);

        if (c == null) {
            System.out.println("❌ 找不到該代碼的聯絡人！");
            return;
        }

        System.out.print("輸入新電話: ");
        String newPhone = scanner.nextLine().trim();
        c.setPhone(newPhone);
        System.out.println("✅ 電話更新成功！");
    }

    public static void deleteContact() {
        System.out.print("輸入欲刪除者的代碼: ");
        String code = scanner.nextLine().trim();
        Contact c = findByCode(code);

        if (c != null && contacts.remove(c)) {
            System.out.println("✅ 聯絡人已安全刪除！");
        } else {
            System.out.println("❌ 刪除失敗，找不到指定代碼！");
        }
    }

    public static void listContacts() {
        if (contacts.isEmpty()) {
            System.out.println("通訊錄目前沒有資料。");
            return;
        }
        System.out.println("--- 完整聯絡人清單 ---");
        for (Contact c : contacts) {
            System.out.println(c);
        }
    }
}