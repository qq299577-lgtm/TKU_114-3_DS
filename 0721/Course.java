public class Course {
    private String code;
    private String name;
    private int capacity;
    private int enrolled;

    public Course(String code, String name, int capacity) {
        this.code = code;
        this.name = name;
        this.capacity = Math.max(1, capacity);
        this.enrolled = 0;
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public int getCapacity() { return capacity; }
    public int getEnrolled() { return enrolled; }

    public boolean isFull() {
        return enrolled >= capacity;
    }

    public boolean register() {
        if (!isFull()) {
            enrolled++;
            return true;
        }
        return false;
    }

    public boolean withdraw() {
        if (enrolled > 0) {
            enrolled--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-15s | 人數: %2d/%2d | 狀態: %s", 
                code, name, enrolled, capacity, isFull() ? "⚠️ 已額滿" : "尚可選課");
    }
}