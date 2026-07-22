import java.util.LinkedList;
import java.util.Queue;
import java.util.HashSet;

public class ClinicQueueSystem {
    private Queue<Patient> queue = new LinkedList<>();
    private HashSet<String> patientIds = new HashSet<>();
    private int totalServedCount = 0;

    public static void main(String[] args) {
        ClinicQueueSystem clinic = new ClinicQueueSystem();

        System.out.println("=== 診所掛號與叫號測試 ===");
        clinic.register("P01", "張三", "內科");
        clinic.register("P02", "李四", "外科");
        clinic.register("P01", "重複張三", "內科");
        clinic.register("P03", "王五", "內科");

        clinic.showNextPatient();
        clinic.printWaitingList();

        clinic.callNext();
        clinic.callNext();

        clinic.printReport();
    }

    public boolean register(String id, String name, String department) {
        if (patientIds.contains(id)) {
            System.out.println("❌ 掛號失敗！病歷號碼 [" + id + "] 重複。");
            return false;
        }
        Patient p = new Patient(id, name, department);
        queue.offer(p);
        patientIds.add(id);
        System.out.println("✅ 掛號成功：" + p);
        return true;
    }

    public void callNext() {
        if (queue.isEmpty()) {
            System.out.println("⚠️ 目前無等待看診之患者！");
            return;
        }
        Patient p = queue.poll();
        totalServedCount++;
        System.out.println("🔔 請患者就診：" + p);
    }

    public void showNextPatient() {
        if (queue.isEmpty()) {
            System.out.println("👀 目前無下一位等待患者。");
            return;
        }
        System.out.println("👀 下一位看診患者：" + queue.peek());
    }

    public void printWaitingList() {
        System.out.println("\n--- 當前等待看診清單 ---");
        if (queue.isEmpty()) {
            System.out.println("目前無人等待。");
            return;
        }
        for (Patient p : queue) {
            System.out.println(p);
        }
    }

    public void printReport() {
        System.out.println("\n=== 診所統計報告 ===");
        int internalCount = 0;
        int surgeryCount = 0;
        int otherCount = 0;

        for (Patient p : queue) {
            if ("內科".equals(p.getDepartment())) internalCount++;
            else if ("外科".equals(p.getDepartment())) surgeryCount++;
            else otherCount++;
        }

        System.out.println("內科等待人數：" + internalCount);
        System.out.println("外科等待人數：" + surgeryCount);
        System.out.println("其他科別等待人數：" + otherCount);
        System.out.println("當前總等待看診人數：" + queue.size());
        System.out.println("今日已服務總人數：" + totalServedCount);
    }
}