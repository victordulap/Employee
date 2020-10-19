package com.step.data.menu;

import java.util.Scanner;

public class ScreenUtilities {
    private static Scanner sc = new Scanner(System.in);

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        //not working ^

        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public static void wait(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void enterAnyValueToContinue() {
        System.out.println("Enter any value to continue...");
        sc.nextLine();
    }

    public static void enterAnyValueToContinue(String messageInsteadOfContinue) {
        System.out.println("Enter any value to " + messageInsteadOfContinue + "...");
        sc.nextLine();
    }
}
