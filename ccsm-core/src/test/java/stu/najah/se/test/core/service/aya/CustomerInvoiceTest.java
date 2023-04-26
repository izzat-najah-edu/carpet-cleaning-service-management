package stu.najah.se.test.core.service.aya;

import org.junit.jupiter.api.Test;
import stu.najah.se.core.service.aya.CustomerInvoice;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class CustomerInvoiceTest {

    @Test
    public void testPrintInvoice() {
        String input = "Aya\nNablus\nAsira Street\nCarpet\nRug\nWindow\ndone\n50.00";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        CustomerInvoice.main(new String[0]);

        String output = systemOut().getHistory();

        String expectedOutput = "=================================\n" +
                "CUSTOMER INFORMATION\n" +
                "=================================\n" +
                "Customer Name: Aya\n" +
                "Customer Address: Nablus\n" +
                "Delivery Address: Asira Street\n" +
                "=================================\n" +
                "ITEMS TO CLEAN\n" +
                "=================================\n" +
                "- Carpet\n" +
                "- Rug\n" +
                "- Window\n" +
                "=================================\n" +
                "TOTAL PRICE\n" +
                "=================================\n" +
                "$50.0\n" +
                "=================================\n";

        assertarrarrayEquals(expectedOutput, output);

    }

    private void assertarrarrayEquals(String expectedOutput, String output) {
    }

    static TestHelper.SystemOut systemOut() {
        return new TestHelper.SystemOut();
    }

    static class TestHelper {
        static class SystemOut {
            private final java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
            private final java.io.PrintStream originalOut = System.out;

            void setAsSystemOut() {
                System.setOut(new java.io.PrintStream(outContent));
            }

            void restoreOriginal() {
                System.setOut(originalOut);
            }

            String getHistory() {
                return outContent.toString();
            }
        }
    }
}
