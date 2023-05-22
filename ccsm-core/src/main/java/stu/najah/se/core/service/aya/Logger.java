package stu.najah.se.core.service.aya;

import java.util.logging.Level;

public class Logger {

    private Logger() {
    }

    private static final java.util.logging.Logger globalLogger =
            java.util.logging.Logger.getGlobal();

    public static void print(String text) {
        globalLogger.log(Level.INFO, text);
    }

    public static void print(String text, Object... args) {
        print(String.format(text, args));
    }

}
