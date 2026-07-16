public class Q12_Booking {
    private String id;
    private String customer;
    private int seats;
    private double pricePerSeat;
    private boolean confirmed;

    public Q12_Booking(String id, String customer, int seats, double pricePerSeat, boolean confirmed) {
        this.id = id;
        this.customer = customer;
        this.seats = seats;
        this.pricePerSeat = pricePerSeat;
        this.confirmed = confirmed;
    }

    public String getId() {
        return this.id;
    }

    public String getCustomer() {
        return this.customer;
    }

    public int getSeats() {
        return this.seats;
    }

    public double getPricePerSeat() {
        return this.pricePerSeat;
    }

    public boolean isConfirmed() {
        return this.confirmed;
    }

    public double getTotalPrice() {
        return this.seats * this.pricePerSeat;
    }

    public void cancel() {
        this.confirmed = false;
    }

    @Override
    public String toString() {
        return this.id + " | " + this.customer + " | seats=" + this.seats + " | total=" + this.getTotalPrice() + " | confirmed=" + this.confirmed;
    }
}