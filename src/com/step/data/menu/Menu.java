package com.step.data.menu;

import com.step.data.employee.EmployeeDataManager;

import java.util.Scanner;

public class Menu{
    private static Scanner sc = new Scanner(System.in);

    public static void showMenu() {
        int nav = -1;

        do {
            System.out.println("EMPLOYEE MANAGEMENT");
            System.out.println();
            System.out.println("\t1. view");
            System.out.println("\t2. insert");
            System.out.println("\t3. update");
            System.out.println("\t4. delete");
            System.out.println();
            System.out.println("\t0. exit");

            System.out.print("\nenter submenu number: ");
            try {
                nav = sc.nextInt();
            } catch (Exception e) {
                System.out.println("\nInvalid format, try again (ex: 1)");
                Utilities.enterAnyValueToContinue();
            }
            finally {
                sc.nextLine();
            }

            switch (nav) {
                case 1:
                    EmployeeDataManager.view();
                    break;
                case 2:
                    EmployeeDataManager.insert();
                    break;
                case 3:
                    EmployeeDataManager.update();
                    break;
                case 4:
                    EmployeeDataManager.delete();
                    break;
                case 0:
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nNo such submenu, try again (ex: 1)");
//                    ScreenUtilities.wait(2000);
                    Utilities.enterAnyValueToContinue();
                    break;
            }

            Utilities.clearScreen();
        } while (nav != 0);
    }
}
