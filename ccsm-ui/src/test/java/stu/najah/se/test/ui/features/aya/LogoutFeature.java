package stu.najah.se.test.ui.features.aya;

public class LogoutFeature {
    private boolean isLoggedIn = false; // assume user is initially not logged in


    public void login(String username, String password) {

        isLoggedIn = true;
    }

    // method to log out the user
    public void logout() {
        if (isLoggedIn) {
            // perform logout cleanup here
            isLoggedIn = false;
            System.out.println("You have successfully logged out.");
        } else {
            System.out.println("You are not currently logged in.");
        }
    }

    // main method to test the logout feature
    public static void main(String[] args) {
        LogoutFeature logoutFeature = new LogoutFeature();


        logoutFeature.isLoggedIn = true;


        logoutFeature.logout();


        logoutFeature.logout();
    }
}
