public class Q04_LoopRepair {
    public static void main(String[] args) {
        System.out.println(sumOddRange(3, 7));
        System.out.println(sumOddRange(7, 3));
        System.out.println(sumOddRange(2, 2));
        System.out.println(sumOddRange(-3, 3));
    }

    public static int sumOddRange(int start, int end) {
        // 先決定真正的起點和終點
        int minNum = start;
        int maxNum = end;
        
        // 如果 start 比 end 大，就交換它們
        if (start > end) {
            minNum = end;
            maxNum = start;
        }

        int sum = 0;
        
        // 從較小的跑到較大的，並且包含終點 (<=)
        for (int i = minNum; i <= maxNum; i++) {
            // 判斷是否為奇數 (不等於0就代表是奇數)
            if (i % 2 != 0) {
                sum += i;
            }
        }
        
        return sum;
    }
}