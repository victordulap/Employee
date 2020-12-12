package com.step.data.menu;

import com.step.data.employee.manager.file.EmployeeManagerInFile;
import com.step.data.employee.manager.EmployeeShowInConsoleManager;

import java.util.Scanner;

public class Menu{
    private static Scanner sc = new Scanner(System.in);
    private static EmployeeShowInConsoleManager em;
    private static int option;

    public Menu() {
        Scanner scanner = new Scanner(System.in);

        int nav = -1;

        Utilities.clearScreen();

        do {
            System.out.println("CHOOSE APPLICATION MODE");
            System.out.println();
            System.out.println("\t1. DEMO (don't save data)");
            System.out.println("\t2. LOCAL (save data in file)");
            System.out.println("\t3. SECURE (save data in DB)");
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
                case 2:
                case 3:
                    break;
                case 0:
                    Utilities.clearScreen();
                    System.out.println("Application exited");
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nNo such submenu, try again (ex: 1)");
                    Utilities.enterAnyValueToContinue();
                    break;
            }

            Utilities.clearScreen();
        } while (nav < 0 || nav > 3);

        em = new EmployeeShowInConsoleManager(nav);
    }

    public void showMenu() {
        int nav = -1;

        em.onOpenApp();

        Utilities.clearScreen();

        do {
            System.out.println("EMPLOYEE MANAGEMENT");
            System.out.println();
            System.out.println("\t1. view");
            System.out.println("\t2. insert");
            System.out.println("\t3. update");
            System.out.println("\t4. delete");
//            System.out.println("\t5. file");
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
                    viewEmployeesMenu();
                    break;
                case 2:
                    em.insert();
                    break;
                case 3:
                    em.update();
                    break;
                case 4:
                    em.delete();
                    break;
                case 0:
                    Utilities.clearScreen();
                    em.onCloseApp();
                    System.out.println("Application closed!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nNo such submenu, try again (ex: 1)");
                    Utilities.enterAnyValueToContinue();
                    break;
            }

            Utilities.clearScreen();
        } while (nav != 0);
    }

    private void viewEmployeesMenu() {
        int nav = -1;

        Utilities.clearScreen();

        do {
            System.out.println("VIEW EMPLOYEES");
            System.out.println();
            System.out.println("\t1. view all");
            System.out.println("\t2. filter");
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
                    em.view(EmployeeManagerInFile.employees);
                    break;
                case 2:
                    filterViewEmployeesMenu();
                    break;
                case 0:
                    break;

                default:
                    System.out.println("\nNo such submenu, try again (ex: 1)");
                    Utilities.enterAnyValueToContinue();
                    break;
            }

            Utilities.clearScreen();
        } while (nav != 0);
    }

    private void filterViewEmployeesMenu() {
        int nav = -1;

        Utilities.clearScreen();

        do {
            System.out.println("FILTER EMPLOYEES");
            System.out.println();
            System.out.println("\t1. by id");
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
                    em.filterById();
                    break;
                case 0:
                    break;

                default:
                    System.out.println("\nNo such submenu, try again (ex: 1)");
                    Utilities.enterAnyValueToContinue();
                    break;
            }

            Utilities.clearScreen();
        } while (nav != 0);
    }

    /*private static void fileMenu() {
        int nav = -1;

        do {
            Utilities.clearScreen();
            System.out.println("FILE IMPORT/EXPORT");
            System.out.println();
            System.out.println("\t1. import employees");
            System.out.println("\t2. export employees");
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
                    EmployeeManager.importCSV();
                    break;
                case 2:
                    EmployeeManager.exportCSV();
                    break;
                case 0:
                    break;


                default:
                    System.out.println("\nNo such submenu, try again (ex: 1)");
                    Utilities.enterAnyValueToContinue();
                    break;
            }

            Utilities.clearScreen();
        } while (nav != 0);
    }*/
}
