package stu.najah.se.test.core.service.aya;

import org.junit.Test;
import stu.najah.se.core.service.aya.GenerateStatistics;
import stu.najah.se.core.service.aya.Invoice;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GenerateStatisticsTest {

    @Test
    public void testGetTotalDeliveredItems() {
        GenerateStatistics generate = new GenerateStatistics();
        generate.addInvoice(new Invoice(10, 200.0, 100.0, true));
        generate.addInvoice(new Invoice(6, 80.0, 50.0, true));
        generate.addInvoice(new Invoice(8, 120.0, 60.0, false));
        generate.addInvoice(new Invoice(4, 60.0, 30.0, false));
        assertEquals(28, generate.getTotalDeliveredItems());
    }

    @Test
    public void testGetTotalCash() {
        GenerateStatistics generate = new GenerateStatistics();
        generate.addInvoice(new Invoice(10, 200.0, 100.0, true));
        generate.addInvoice(new Invoice(6, 80.0, 50.0, true));
        generate.addInvoice(new Invoice(8, 120.0, 60.0, false));
        generate.addInvoice(new Invoice(4, 60.0, 30.0, false));
        assertEquals(150.0, generate.getTotalCash(), 0.001);
    }

    @Test
    public void testGetTotalPaid() {
        GenerateStatistics generate = new GenerateStatistics();
        generate.addInvoice(new Invoice(10, 200.0, 100.0, true));
        generate.addInvoice(new Invoice(6, 80.0, 50.0, true));
        generate.addInvoice(new Invoice(8, 120.0, 60.0, false));
        generate.addInvoice(new Invoice(4, 60.0, 30.0, false));
        assertEquals(330.0, generate.getTotalPaid(), 0.001);
    }

    @Test
    public void testGetTotalDebts() {
        GenerateStatistics generate = new GenerateStatistics();
        generate.addInvoice(new Invoice(10, 200.0, 100.0, true));
        generate.addInvoice(new Invoice(6, 80.0, 50.0, true));
        generate.addInvoice(new Invoice(8, 120.0, 60.0, false));
        generate.addInvoice(new Invoice(4, 60.0, 30.0, false));
        assertEquals(150.0, generate.getTotalDebts(), 0.001);
    }
}
