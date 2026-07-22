import java.util.Stack;

public class BracketValidationSystem {
    public static void main(String[] args) {
        String[] testCases = {
            "a * (b + c) - [d / {e}]",
            "((a + b) * c]",
            "a + b) * c",
            "((a + b) * c",
            ""
        };

        for (String test : testCases) {
            System.out.println("測試字串: \"" + test + "\"");
            System.out.println("驗證結果: " + (isValid(test) ? "✅ 格式正確" : "❌ 格式錯誤"));
            System.out.println("------------------------------------");
        }
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (isOpeningBracket(ch)) {
                stack.push(ch);
            } else if (isClosingBracket(ch)) {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (!isMatchingPair(top, ch)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static boolean isOpeningBracket(char ch) {
        return ch == '(' || ch == '[' || ch == '{';
    }

    public static boolean isClosingBracket(char ch) {
        return ch == ')' || ch == ']' || ch == '}';
    }

    public static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}');
    }
}