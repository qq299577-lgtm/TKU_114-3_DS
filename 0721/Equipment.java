public class Equipment {
    private String code;
    private String name;
    private boolean isAvailable;

    public Equipment(String code, String name) {
        this.code = code;
        this.name = name;
        this.isAvailable = true;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public boolean borrow() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    public boolean returnEquipment() {
        if (!isAvailable) {
            isAvailable = true;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-15s | 狀態: %s", 
            code, name, isAvailable ? "可借用" : "已借出");
    }
}