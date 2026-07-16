public class Q07_ArrayAudit {
    private static final int MIN_VALID = 10;
    private static final int MAX_VALID = 60;
    private static final int TARGET = 35;
    private static final int INVALID_MARK = -1;

    public static void main(String[] args) {
        int[] readings = {12, 71, 35, -4, 35, 22, 60, 9, 48, 61};

        System.out.println("有效筆數：" + countValid(readings));
        System.out.printf("有效平均：%.2f%n", averageValid(readings));
        System.out.println("最後符合門檻的索引：" + findLastAtLeast(readings, TARGET));

        int[] cleaned = createCleanCopy(readings);
        System.out.print("清理後資料：");
        printArray(cleaned);
        System.out.print("原始資料：");
        printArray(readings);
    }

    private static boolean isValid(int value) {
        return value >= MIN_VALID && value <= MAX_VALID;
    }

    public static int countValid(int[] data) {
        int c = 0;
        for (int i = 0; i < data.length; i++) {
            if (isValid(data[i])) {
                c++;
            }
        }
        return c;
    }

    public static double averageValid(int[] data) {
        int total = 0;
        int c = 0;
        for (int i = 0; i < data.length; i++) {
            if (isValid(data[i])) {
                total += data[i];
                c++;
            }
        }
        
        if (c == 0) {
            return -1.0;
        }
        
        return (double) total / c;
    }

    public static int findLastAtLeast(int[] data, int target) {
        int lastIdx = -1;
        for (int i = 0; i < data.length; i++) {
            if (isValid(data[i]) && data[i] >= target) {
                lastIdx = i;
            }
        }
        return lastIdx;
    }

    public static int[] createCleanCopy(int[] data) {
        int[] arr = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            if (isValid(data[i])) {
                arr[i] = data[i];
            } else {
                arr[i] = INVALID_MARK;
            }
        }
        return arr;
    }

    private static void printArray(int[] data) {
        System.out.print("[");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]);
            if (i < data.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}