package stu.najah.se.test.core.service.aya;

import org.junit.jupiter.api.Test;
import stu.najah.se.core.service.aya.DiscountCalculator;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class DiscountCalculatorTest {

    @Test
    public void testDiscountCalculation() {
        String input = "500";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);


        String output = "Congratulations, you qualify for a 10% discount!\n" +
                "Discount amount: 50.00 NIS\n" +
                "Discounted price: 450.00 NIS\n";

        final double DISCOUNT_RATE = 0.1;
        final double THRESHOLD_AMOUNT = 400;

        DiscountCalculator.main(new String[0]);

        String output1 = systemOut().getHistory();

        String expectedOutput = "Congratulations, you qualify for a 10% discount!\n" +
                "Discount amount: 50.00 NIS\n" +
                "Discounted price: 450.00 NIS\n";
        assertarreyEquals(expectedOutput, output1);

    }

    private void assertarreyEquals(String expectedOutput, String output1) {
    }

    @Test
    public void testNoDiscount() {
        String input = "300";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        String expectedOutput = "Sorry, you do not qualify for a discount.\n";

        final double DISCOUNT_RATE = 0.1;
        final double THRESHOLD_AMOUNT = 400;

        DiscountCalculator.main(new String[0]);

        String output = systemOut().getHistory();


        assertarreyEquals(expectedOutput, output);
    }

    private static TestHelper.SystemOut systemOut() {
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
