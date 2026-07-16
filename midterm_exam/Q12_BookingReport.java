public class Q12_BookingReport {
    public static void main(String[] args) {
        Q12_Booking[] bookings = {
            new Q12_Booking("B001", "Amy", 2, 750, true),
            new Q12_Booking("B002", "Ben", 4, 600, false),
            new Q12_Booking("B003", "Cara", 3, 900, true),
            new Q12_Booking("B004", "Dan", 1, 1200, true)
        };

        System.out.println("已確認筆數：" + countConfirmed(bookings));
        System.out.println("已確認收入：" + calculateConfirmedRevenue(bookings));

        Q12_Booking found = findById(bookings, "b003");
        System.out.println("搜尋結果：" + found);

        Q12_Booking largest = findLargestConfirmed(bookings);
        System.out.println("最高確認預約：" + largest);
    }

    public static int countConfirmed(Q12_Booking[] bookings) {
        int c = 0;
        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i].isConfirmed()) {
                c++;
            }
        }
        return c;
    }

    public static double calculateConfirmedRevenue(Q12_Booking[] bookings) {
        double sum = 0.0;
        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i].isConfirmed()) {
                sum += bookings[i].getTotalPrice();
            }
        }
        return sum;
    }

    public static Q12_Booking findById(Q12_Booking[] bookings, String targetId) {
        if (targetId == null) {
            return null;
        }
        
        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i].getId().equalsIgnoreCase(targetId)) {
                return bookings[i];
            }
        }
        return null;
    }

    public static Q12_Booking findLargestConfirmed(Q12_Booking[] bookings) {
        Q12_Booking maxObj = null;
        double maxVal = -1.0;

        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i].isConfirmed()) {
                double currentTotal = bookings[i].getTotalPrice();
                if (maxObj == null || currentTotal > maxVal) {
                    maxVal = currentTotal;
                    maxObj = bookings[i];
                }
            }
        }
        return maxObj;
    }
}