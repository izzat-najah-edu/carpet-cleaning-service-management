package stu.najah.se.core.service.aya;

import java.util.logging.Level;

public class Logger {

    private Logger() {
    }

    private static final java.util.logging.Logger logger =
            java.util.logging.Logger.getGlobal();

    public static void print(String text) {
        logger.log(Level.INFO, text);
    }

    public static void print(String text, Object... args) {
        logger.log(Level.INFO, String.format(text, args));
    }

}
