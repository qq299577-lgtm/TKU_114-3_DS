import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.ArrayList;

public class DeliveryProcessingSystem {
    private Queue<DeliveryTask> pendingQueue = new LinkedList<>();
    private Stack<DeliveryTask> completedStack = new Stack<>();
    private ArrayList<String> allLogs = new ArrayList<>();

    public static void main(String[] args) {
        DeliveryProcessingSystem system = new DeliveryProcessingSystem();

        System.out.println("=== 物流配送系統測試 ===");
        system.addTask("T01", "台北市");
        system.addTask("T02", "新北市");
        system.addTask("T03", "桃園市");

        system.viewNextTask();

        system.processNextTask(true);
        system.processNextTask(false);
        system.processNextTask(true);
        system.processNextTask(true);

        system.viewRecentCompleted(2);
        system.printStatsAndLogs();
    }

    public void addTask(String id, String destination) {
        DeliveryTask task = new DeliveryTask(id, destination);
        pendingQueue.offer(task);
        String log = "📦 新增配送工作: " + task;
        allLogs.add(log);
        System.out.println(log);
    }

    public void processNextTask(boolean isSuccess) {
        if (pendingQueue.isEmpty()) {
            System.out.println("⚠️ 當前沒有待處理的配送工作！");
            return;
        }

        DeliveryTask task = pendingQueue.poll();
        if (isSuccess) {
            completedStack.push(task);
            String log = "✅ 配送完成: " + task;
            allLogs.add(log);
            System.out.println(log);
        } else {
            task.incrementFailCount();
            pendingQueue.offer(task);
            String log = "❌ 配送失敗 (已重新移至佇列尾端): " + task;
            allLogs.add(log);
            System.out.println(log);
        }
    }

    public void viewNextTask() {
        if (pendingQueue.isEmpty()) {
            System.out.println("👀 下一個待處理工作: 無");
        } else {
            System.out.println("👀 下一個待處理工作: " + pendingQueue.peek());
        }
    }

    public void viewRecentCompleted(int count) {
        System.out.println("\n=== 最近完成的 " + count + " 筆工作 ===");
        if (completedStack.isEmpty()) {
            System.out.println("目前無已完成工作。");
            return;
        }
        int showCount = Math.min(count, completedStack.size());
        for (int i = completedStack.size() - 1; i >= completedStack.size() - showCount; i--) {
            System.out.println(completedStack.get(i));
        }
    }

    public void printStatsAndLogs() {
        System.out.println("\n=== 物流系統總體報告 ===");
        System.out.println("當前等待中工作數量：" + pendingQueue.size());
        System.out.println("已完成工作總數量：" + completedStack.size());
        
        System.out.println("\n--- 完整處理紀錄 (Log) ---");
        for (int i = 0; i < allLogs.size(); i++) {
            System.out.println((i + 1) + ". " + allLogs.get(i));
        }
    }
}