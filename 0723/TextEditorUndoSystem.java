import java.util.Stack;

public class TextEditorUndoSystem {
    private StringBuilder currentText = new StringBuilder();
    private Stack<String> history = new Stack<>();

    public static void main(String[] args) {
        TextEditorUndoSystem editor = new TextEditorUndoSystem();

        System.out.println("=== 文字編輯器 Undo 測試 ===");
        
        editor.type("Hello");
        editor.type(" World");
        editor.type("!");
        editor.showContent();

        editor.deleteLast(1);
        editor.showContent();

        System.out.println("\n--- 開始連續 Undo 測試 ---");
        editor.undo();
        editor.showContent();

        editor.undo();
        editor.showContent();

        editor.undo();
        editor.showContent();

        editor.undo();
        editor.undo();
    }

    public void type(String str) {
        saveState();
        currentText.append(str);
        System.out.println("✍️ 輸入文字: \"" + str + "\"");
    }

    public void deleteLast(int count) {
        if (count <= 0) return;
        saveState();
        int length = currentText.length();
        int actualDelete = Math.min(count, length);
        currentText.delete(length - actualDelete, length);
        System.out.println("✂️ 刪除最後 " + actualDelete + " 個字元");
    }

    private void saveState() {
        history.push(currentText.toString());
    }

    public void undo() {
        if (history.isEmpty()) {
            System.out.println("⚠️ 沒有更早的操作歷史，無法再進行復原 (Undo)！");
            return;
        }
        currentText = new StringBuilder(history.pop());
        System.out.println("↩️ 執行撤銷 (Undo) 成功！");
    }

    public void showContent() {
        System.out.println("📄 當前文字內容: \"" + currentText.toString() + "\"");
    }
}