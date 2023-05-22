package stu.najah.se.core.service.aya;

import java.util.ArrayList;

public class GenerateReports {

    private static final ArrayList<Customer> customers = new ArrayList<>();
    private static final ArrayList<Job> jobs = new ArrayList<>();

    public static void main(String[] args) {
        customers.add(new Customer("Aya Khammash", "Nablus", "aya.khammash@gmail.com"));
        customers.add(new Customer("Izzat AlSharif", "Nablus", "Izzat.Alsharif@gmail.com"));
        jobs.add(new Job("Cleaning", "Daily", 100.00));
        jobs.add(new Job("Floor Cleaning", "Weekly", 200.00));
        jobs.add(new Job("Window Washing", "Monthly", 150.00));
        // Generate reports
        generateCustomerReport();
        generateJobReport();
    }

    // Generate customer report
    private static void generateCustomerReport() {
        System.out.println("CUSTOMER REPORT");
        System.out.println("=============================");
        for (Customer customer : customers) {
            System.out.println("Name: " + customer.getName());
            System.out.println("Address: " + customer.getAddress());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("-----------------------------");
        }
    }

    // Generate job report
    private static void generateJobReport() {
        System.out.println("JOB REPORT");
        System.out.println("=============================");
        for (Job job : jobs) {
            System.out.println("Type: " + job.getType());
            System.out.println("Frequency: " + job.getFrequency());
            System.out.println("Price: $" + job.getPrice());
            System.out.println("-----------------------------");
        }
    }

    // Customer class
    private static class Customer {
        private String name;
        private String address;
        private String email;

        public Customer(String name, String address, String email) {
            this.name = name;
            this.address = address;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public String getEmail() {
            return email;
        }
    }

    // Job class
    private static class Job {
        private String type;
        private String frequency;
        private double price;

        public Job(String type, String frequency, double price) {
            this.type = type;
            this.frequency = frequency;
            this.price = price;
        }

        public String getType() {
            return type;
        }

        public String getFrequency() {
            return frequency;
        }

        public double getPrice() {
            return price;
        }
    }
}
