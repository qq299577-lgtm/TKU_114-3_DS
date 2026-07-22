import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class CounterServiceSystem {
    private Queue<String> queue = new LinkedList<>();
    private ArrayList<String> servedHistory = new ArrayList<>();

    public static void main(String[] args) {
        CounterServiceSystem system = new CounterServiceSystem();

        System.out.println("=== 叫號系統測試 ===");
        system.callNext();
        
        system.takeNumber("A01 - 張三");
        system.takeNumber("A02 - 李四");
        system.takeNumber("A03 - 王五");

        system.peekNext();
        system.getWaitingCount();

        system.callNext();
        system.callNext();

        system.getWaitingCount();
        system.callNext();
        system.callNext();

        system.printHistory();
    }

    public void takeNumber(String customer) {
        queue.offer(customer);
        System.out.println("🎟️ 成功取號：" + customer);
    }

    public void callNext() {
        if (queue.isEmpty()) {
            System.out.println("⚠️ 目前沒有等待中的顧客，無法叫號！");
            return;
        }
        String customer = queue.poll();
        servedHistory.add(customer);
        System.out.println("🔔 請顧客前往櫃檯：" + customer);
    }

    public void peekNext() {
        if (queue.isEmpty()) {
            System.out.println("👀 目前沒有下一位等待的顧客。");
            return;
        }
        System.out.println("👀 下一位準備服務的顧客：" + queue.peek());
    }

    public void getWaitingCount() {
        System.out.println("📊 當前等待人數：" + queue.size());
    }

    public void printHistory() {
        System.out.println("\n=== 歷史服務紀錄 ===");
        if (servedHistory.isEmpty()) {
            System.out.println("無任何服務紀錄。");
            return;
        }
        for (int i = 0; i < servedHistory.size(); i++) {
            System.out.println((i + 1) + ". " + servedHistory.get(i));
        }
    }
}