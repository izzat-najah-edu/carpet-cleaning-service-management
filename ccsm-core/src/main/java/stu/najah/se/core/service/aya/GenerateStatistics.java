package stu.najah.se.core.service.aya;

import java.util.ArrayList;
import java.util.List;

public class GenerateStatistics {

    private List<Invoice> invoices;

    public GenerateStatistics() {
        this.invoices = new ArrayList<>();
    }

    public void addInvoice(Invoice invoice) {
        this.invoices.add(invoice);
    }

    public int getTotalDeliveredItems() {
        int total = 0;
        for (Invoice invoice : invoices) {
            total += invoice.getDeliveredItems();
        }
        return total;
    }

    public double getTotalCash() {
        double total = 0;
        for (Invoice invoice : invoices) {
            if (invoice.isPaid()) {
                total += invoice.getCash();
            }
        }
        return total;
    }

    public double getTotalPaid() {
        double total = 0;
        for (Invoice invoice : invoices) {
            if (invoice.isPaid()) {
                total += invoice.getTotal();
            }
        }
        return total;
    }

    public double getTotalDebts() {
        double total = 0;
        for (Invoice invoice : invoices) {
            if (!invoice.isPaid()) {
                total += invoice.getTotal();
            }
        }
        return total;
    }


    public static void main(String[] args) {
        // Create a new cleaning service management instance
        GenerateStatistics generate = new GenerateStatistics();

        // Add some invoices
        generate.addInvoice(new Invoice(10, 200.0, 100.0, true));
        generate.addInvoice(new Invoice(6, 80.0, 50.0, true));
        generate.addInvoice(new Invoice(8, 120.0, 60.0, false));
        generate.addInvoice(new Invoice(4, 60.0, 30.0, false));

        // Get the statistics
        int totalDeliveredItems = generate.getTotalDeliveredItems();
        double totalCash = generate.getTotalCash();
        double totalPaid = generate.getTotalPaid();
        double totalDebts = generate.getTotalDebts();

        // Print the statistics
        Logger.print("Total delivered items: " + totalDeliveredItems);
        Logger.print("Total cash: " + totalCash);
        Logger.print("Total paid: " + totalPaid);
        Logger.print("Total debts: " + totalDebts);
    }
}
