package stu.najah.se;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.*;
public class Admin {
    final Logger logger = Logger.getLogger(Admin.class.getName());
    boolean logState;
    String password;

    public Admin() {
        password="admin1234";
        logState=false;
    }

    public void setLogState(boolean b) {
        logState=b;
    }

    public boolean login(String pass) {

        if(logState) {
            logger.info("You are already logged in");
            return false;
        }
        else {
            if(this.password.equals(pass)) {
                logger.info("You're successfully logged in");
                logState=true;
                return true;

            }
            else {
                logger.info("The password is wrong");
                return false;
            }
        }
    }

    public boolean getLogState() {

        return logState;
    }

    public void logout() {

        logState=false;

    }



}