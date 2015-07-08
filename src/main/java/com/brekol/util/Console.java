package com.brekol.util;

import com.googlecode.lanterna.terminal.Terminal;

/**
 * Created by brekol on 08.07.15.
 */
public class Console {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private Console() {
    }

    public static void logRed(String text) {
        System.out.println(ANSI_RED + text + ANSI_RESET);
    }

    public static void log(String text) {
        System.out.println(text);
    }





    public static void logGreen(String text) {
        System.out.println(ANSI_GREEN + text + ANSI_RESET);
    }


}
