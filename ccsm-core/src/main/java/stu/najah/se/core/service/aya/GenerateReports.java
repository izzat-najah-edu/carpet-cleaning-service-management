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
        Logger.print("CUSTOMER REPORT");
        Logger.print("=============================");
        for (Customer customer : customers) {
            Logger.print("Name: " + customer.getName());
            Logger.print("Address: " + customer.getAddress());
            Logger.print("Email: " + customer.getEmail());
            Logger.print("-----------------------------");
        }
    }

    // Generate job report
    private static void generateJobReport() {
        Logger.print("JOB REPORT");
        Logger.print("=============================");
        for (Job job : jobs) {
            Logger.print("Type: " + job.getType());
            Logger.print("Frequency: " + job.getFrequency());
            Logger.print("Price: $" + job.getPrice());
            Logger.print("-----------------------------");
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
