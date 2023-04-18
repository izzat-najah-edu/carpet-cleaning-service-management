public class LogoutFeature {
    private boolean isLoggedIn = false; // assume user is initially not logged in

    // method to log in the user
    public void login(String username, String password) {
        // perform login authentication here
        // if successful, set isLoggedIn to true
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

        // assume the user is already logged in
        logoutFeature.isLoggedIn = true;

        // log the user out
        logoutFeature.logout();

        // try to log the user out again (should give error message)
        logoutFeature.logout();
    }
}
