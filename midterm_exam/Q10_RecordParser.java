public class Q10_RecordParser {
    public static void main(String[] args) {
        String[] records = {
            "A101|Keyboard|3|850",
            "A102|Mouse|-1|500",
            "broken data",
            "A103|Monitor|2|4200",
            "A104||1|300"
        };

        for (String record : records) {
            System.out.println(record + " -> " + calculateRecordTotal(record));
        }

        System.out.println("合法筆數：" + countValidRecords(records));
        System.out.println("總金額：" + calculateGrandTotal(records));
    }

    public static boolean isValidRecord(String record) {
        if (record == null || record.trim().length() == 0) {
            return false;
        }

        String[] tokens = record.split("\\|", -1);

        if (tokens.length != 4) {
            return false;
        }

        String col1 = tokens[0].trim();
        String col2 = tokens[1].trim();

        if (col1.length() == 0 || col2.length() == 0) {
            return false;
        }

        try {
            int qty = Integer.parseInt(tokens[2].trim());
            int prc = Integer.parseInt(tokens[3].trim());

            if (qty > 0 && prc >= 0) {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return false;
    }

    public static int calculateRecordTotal(String record) {
        if (!isValidRecord(record)) {
            return -1;
        }

        String[] tokens = record.split("\\|", -1);
        int qty = Integer.parseInt(tokens[2].trim());
        int prc = Integer.parseInt(tokens[3].trim());
        
        return qty * prc;
    }

    public static int countValidRecords(String[] records) {
        int count = 0;
        for (int i = 0; i < records.length; i++) {
            if (isValidRecord(records[i])) {
                count++;
            }
        }
        return count;
    }

    public static int calculateGrandTotal(String[] records) {
        int finalSum = 0;
        for (int i = 0; i < records.length; i++) {
            int val = calculateRecordTotal(records[i]);
            if (val != -1) {
                finalSum += val;
            }
        }
        return finalSum;
    }
}