package stu.najah.se.core.service.aya;

public class Invoice {

    private int deliveredItems;
    private double total;
    private double cash;
    private boolean paid;

    public Invoice(int deliveredItems, double total, double cash, boolean paid) {
        this.deliveredItems = deliveredItems;
        this.total = total;
        this.cash = cash;
        this.paid = paid;
    }

    public int getDeliveredItems() {
        return deliveredItems;
    }

    public double getTotal() {
        return total;
    }

    public double getCash() {
        return cash;
    }

    public boolean isPaid() {
        return paid;
    }
}
