package com.step.data.menu;

import com.step.data.employee.Employee;
import com.step.data.employee.EmployeeDataManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu{
    private static Scanner sc = new Scanner(System.in);

//    private static Employee[] employees = new Employee[100];
//    private static int employeesCount = 0;
    private static List<Employee> employees = new ArrayList<>();


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
                ScreenUtilities.enterAnyValueToContinue();
            }
            finally {
                sc.nextLine();
            }

            switch (nav) {
                case 1:
                    EmployeeDataManager.view(employees);
                    break;
                case 2:
                    EmployeeDataManager.insert(employees);
                    break;
                case 3:
                    EmployeeDataManager.update(employees);
                    break;
                case 4:
                    EmployeeDataManager.delete(employees);
                    break;
                case 0:
                    System.exit(0);
                    break;

                default:
                    System.out.println("\nNo such submenu, try again (ex: 1)");
//                    ScreenUtilities.wait(2000);
                    ScreenUtilities.enterAnyValueToContinue();
                    break;
            }

            ScreenUtilities.clearScreen();
        } while (nav != 0);
    }
}
