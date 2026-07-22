public class DeliveryTask {
    private String id;
    private String destination;
    private int failCount;

    public DeliveryTask(String id, String destination) {
        this.id = id;
        this.destination = destination;
        this.failCount = 0;
    }

    public String getId() { return id; }
    public String getDestination() { return destination; }
    public int getFailCount() { return failCount; }
    public void incrementFailCount() { this.failCount++; }

    @Override
    public String toString() {
        return "[" + id + "] 目的地: " + destination + " (失敗重試次數: " + failCount + ")";
    }
}