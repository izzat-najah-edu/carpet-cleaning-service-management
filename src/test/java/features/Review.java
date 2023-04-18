import java.util.Scanner;

public class ReviewFeature {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to rate the software program out of five stars
        System.out.print("Rate the software program out of five stars (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();

        // Prompt the user to write a review title
        System.out.print("Write a review title: ");
        String title = scanner.nextLine();

        // Prompt the user to write a review body
        System.out.print("Write a review body: ");
        String body = scanner.nextLine();

        // Prompt the user to list the pros and cons of the software program
        System.out.print("List the pros of the software program: ");
        String pros = scanner.nextLine();
        System.out.print("List the cons of the software program: ");
        String cons = scanner.nextLine();

        // Prompt the user to indicate whether they would recommend the software program
        System.out.print("Would you recommend this software program? (yes or no): ");
        String recommend = scanner.nextLine();

        // Output the review information to the console
        System.out.println("\nReview Summary:");
        System.out.println("Rating: " + rating + " stars");
        System.out.println("Title: " + title);
        System.out.println("Body: " + body);
        System.out.println("Pros: " + pros);
        System.out.println("Cons: " + cons);
        System.out.println("Recommend: " + recommend);
    }
}
