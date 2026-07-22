import java.util.Stack;

public class BrowserUndoSystem {
    private Stack<String> history = new Stack<>();
    private String currentPage = null;

    public static void main(String[] args) {
        BrowserUndoSystem browser = new BrowserUndoSystem();

        System.out.println("=== 開始瀏覽器操作測試 (共8次操作) ===");
        
        browser.getCurrentPage();
        browser.visit("https://google.com");
        browser.visit("https://youtube.com");
        browser.visit("https://github.com");
        browser.getCurrentPage();
        
        browser.back();
        browser.getCurrentPage();
        
        browser.back();
        browser.back();
        browser.back();
    }

    public void visit(String url) {
        if (currentPage != null) {
            history.push(currentPage);
        }
        currentPage = url;
        System.out.println("🌐 開啟新頁面：" + url);
    }

    public void back() {
        if (history.isEmpty()) {
            if (currentPage != null) {
                System.out.println("⚠️ 已回到最起始頁面 (" + currentPage + ")，沒有更早的上一頁了。");
                currentPage = null;
            } else {
                System.out.println("⚠️ 目前沒有任何瀏覽歷史記錄，無法返回！");
            }
            return;
        }
        String previousPage = history.pop();
        System.out.println("⬅️ 返回上一頁：由 " + currentPage + " 回到 " + previousPage);
        currentPage = previousPage;
    }

    public void getCurrentPage() {
        if (currentPage == null) {
            System.out.println("📄 目前頁面：空白頁 (Null)");
        } else {
            System.out.println("📄 目前頁面：" + currentPage);
        }
    }
}