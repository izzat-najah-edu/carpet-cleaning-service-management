package stu.najah.se.core.service.aya;

public class Logger {


    public static void print(String text) {
        System.out.println(text);
    }

    public static void print(String text, Object... args) {
        System.out.printf(text, args);
    }

}
