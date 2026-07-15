import java.util.Scanner;

public class TextAnalyzer {
    public static String readValidInput(Scanner sc) {
        String input;
        do {
            System.out.print("請輸入一行文字：");
            input = sc.nextLine();
            if (input == null || input.trim().isEmpty()) {
                System.out.println("錯誤：輸入不能為空或全空白！請重新輸入。");
            }
        } while (input == null || input.trim().isEmpty());
        return input;
    }

    public static int getCleanedLength(String text) {
        return text.trim().length();
    }

    public static String[] getWords(String text) {
        String trimmed = text.trim();
        if (trimmed.isEmpty()) {
            return new String[0];
        }
        return trimmed.split("\\s+");
    }

    public static int countVowels(String text) {
        int count = 0;
        String lower = text.toLowerCase();
        for (int i = 0; i < lower.length(); i++) {
            char c = lower.charAt(i);
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                count++;
            }
        }
        return count;
    }

    public static int countKeyword(String text, String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return 0;
        }
        String lowerText = text.toLowerCase();
        String lowerKeyword = keyword.trim().toLowerCase();
        int count = 0;
        int index = 0;
        while ((index = lowerText.indexOf(lowerKeyword, index)) != -1) {
            count++;
            index += lowerKeyword.length();
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String original = readValidInput(sc);
        System.out.println("\n--- 分析結果 ---");
        System.out.println("原始字元數 (含空白)：" + original.length());

        int cleanedLen = getCleanedLength(original);
        System.out.println("有效字元數 (去首尾空白)：" + cleanedLen);

        String[] words = getWords(original);
        System.out.println("單字數量：" + words.length);

        int vowelsCount = countVowels(original);
        System.out.println("英文元音 (a, e, i, o, u) 總數：" + vowelsCount);

        if (words.length > 0) {
            System.out.println("最後一個單字 (終極單字)：" + words[words.length - 1]);
        }

        System.out.print("\n請輸入搜尋關鍵字：");
        String keyword = sc.nextLine();
        int keywordCount = countKeyword(original, keyword);
        System.out.println("關鍵字 \"" + keyword + "\" 出現次數 (不分大小寫)：" + keywordCount);

        sc.close();
    }
}