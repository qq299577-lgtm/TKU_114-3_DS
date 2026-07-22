import java.util.ArrayList;

public class EquipmentManager {
    public static void main(String[] args) {
        ArrayList<Equipment> list = new ArrayList<>();

        System.out.println("=== 新增設備 ===");
        addEquipment(list, new Equipment("E01", "Laptop"));
        addEquipment(list, new Equipment("E02", "Projector"));
        addEquipment(list, new Equipment("E01", "Duplicate Laptop"));

        System.out.println("\n=== 借出與歸還測試 ===");
        Equipment e = findByCode(list, "E02");
        if (e != null && e.borrow()) {
            System.out.println("✅ 成功借出：" + e.getName());
        }

        System.out.println("\n=== 目前可借用的設備 ===");
        listAvailable(list);
    }

    public static boolean addEquipment(ArrayList<Equipment> list, Equipment eq) {
        if (findByCode(list, eq.getCode()) != null) {
            System.out.println("❌ 新增失敗！代碼重複：" + eq.getCode());
            return false;
        }
        list.add(eq);
        System.out.println("✅ 成功新增設備：" + eq.getName());
        return true;
    }

    public static Equipment findByCode(ArrayList<Equipment> list, String code) {
        for (Equipment eq : list) {
            if (eq.getCode().equalsIgnoreCase(code)) {
                return eq;
            }
        }
        return null;
    }

    public static void listAvailable(ArrayList<Equipment> list) {
        boolean found = false;
        for (Equipment eq : list) {
            if (eq.isAvailable()) {
                System.out.println(eq);
                found = true;
            }
        }
        if (!found) {
            System.out.println("目前沒有可借用的設備。");
        }
    }
}