package stu.najah.se.test.core.service;

import org.junit.Test;
import stu.najah.se.core.service.GenerateReports;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class GenerateReportsTest {

    @Test
    public void testGenerateReports() {

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));


        GenerateReports.main(null);


        System.setOut(System.out);


        String expectedOutput = "CUSTOMER REPORT\n" +
                "=============================\n" +
                "Name: Aya Khammash\n" +
                "Address: Nablus\n" +
                "Email: aya.khammash@gmail.com\n" +
                "-----------------------------\n" +
                "Name: Izzat AlSharif\n" +
                "Address: Nablus\n" +
                "Email: Izzat.Alsharif@gmail.com\n" +
                "-----------------------------\n" +
                "JOB REPORT\n" +
                "=============================\n" +
                "Type: Cleaning\n" +
                "Frequency: Daily\n" +
                "Price: $100.0\n" +
                "-----------------------------\n" +
                "Type: Floor Cleaning\n" +
                "Frequency: Weekly\n" +
                "Price: $200.0\n" +
                "-----------------------------\n" +
                "Type: Window Washing\n" +
                "Frequency: Monthly\n" +
                "Price: $150.0\n" +
                "-----------------------------\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
