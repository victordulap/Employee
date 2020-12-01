package com.step.data.employee;

import com.step.data.menu.Utilities;

import java.time.LocalDate;

public class EmployeeShowInConsoleManager {
    public static void insert() {
        String moreEmployees;

        do {
            Utilities.clearScreen();

            System.out.println("INSERTING NEW EMPLOYEE...");

            System.out.print("Enter name: ");
            String name = EmployeeManager.sc.nextLine();
            name = name.trim();
            name = (name.length() > 0) ? Utilities.firstLetterUpperCase(name) : "";

            System.out.print("Enter surname: ");
            String surname = EmployeeManager.sc.nextLine();
            surname = surname.trim();
            surname = (surname.length() > 0) ? Utilities.firstLetterUpperCase(surname) : "";

            System.out.print("Enter idnp: ");
            String idnp = EmployeeDataChecker.enterIdnp();

            LocalDate birthDate = EmployeeDataChecker.enterBirthDate();

            System.out.print("Enter salary: ");
            double salary = EmployeeDataChecker.enterSalary();

            System.out.print("Enter job: ");
            Job job = EmployeeDataChecker.enterJob();

            EmployeeManager.insert(new Employee(name, surname, idnp, birthDate, salary, job));

            Utilities.clearScreen();

            System.out.println("INSERTED NEW EMPLOYEE:");
            int i = EmployeeManager.employees.size() - 1;
            System.out.println("\tname: " + EmployeeManager.employees.get(i).getName());
            System.out.println("\tsurname: " + EmployeeManager.employees.get(i).getSurname());
            System.out.println("\tbirthdate: " + EmployeeManager.employees.get(i).getBirthDateFormatted());
            System.out.println("\tidnp: " + EmployeeManager.employees.get(i).getIdnp());
            System.out.println("\tsalary: $" + EmployeeManager.employees.get(i).getSalary());
            System.out.println("\tjob: " + Utilities.firstLetterUpperCase(EmployeeManager.employees.get(i).getJob().toString()));

            System.out.println("Enter 1 to add more, or any value to go back...");
            moreEmployees = EmployeeManager.sc.nextLine();
            moreEmployees = moreEmployees.trim();
        } while (moreEmployees.equals("1"));

    }

    public static void delete() {
        //submenu
        int nav = -1;
        do {
            Utilities.clearScreen();

            System.out.println("Select delete method:");
            System.out.println();
            System.out.println("\t1. delete by idnp");
            System.out.println("\t2. delete by name and surname");
            System.out.println("\t3. delete all employees");
            System.out.println();
            System.out.println("\t0. back");

            System.out.print("\nenter submenu number: ");
            try {
                nav = EmployeeManager.sc.nextInt();
            } catch (Exception e) {
                System.out.println("\nInvalid format, try again (ex: 1)");
                Utilities.enterAnyValueToContinue();
            } finally {
                EmployeeManager.sc.nextLine();
            }

            switch (nav) {
                case 1:
                    EmployeeManager.deleteByIdnp();
                    break;
                case 2:
                    EmployeeManager.deleteByName();
                    break;
                case 3:
                    EmployeeManager.deleteAllEmployees();
                    break;
                case 0:
                    return;

                default:
                    System.out.println("\nNo such submenu, try again (ex: 1)");
                    Utilities.enterAnyValueToContinue();
                    break;
            }
        } while (nav != 0);
        //submenu
    }

    public static void update() {
        //submenu
        int nav = -1;
        do {
            Utilities.clearScreen();

            System.out.println("Select update method:");
            System.out.println();
            System.out.println("\t1. update by idnp");
            System.out.println("\t2. update by name and surname");
            System.out.println();
            System.out.println("\t0. back");

            System.out.print("\nenter submenu number: ");
            try {
                nav = EmployeeManager.sc.nextInt();
            } catch (Exception e) {
                System.out.println("\nInvalid format, try again (ex: 1)");
                Utilities.enterAnyValueToContinue();
            } finally {
                EmployeeManager.sc.nextLine();
            }

            switch (nav) {
                case 1:
                    EmployeeManager.updateByIdnp();
                    break;
                case 2:
                    EmployeeManager.updateByName();
                    break;
                case 0:
                    return;

                default:
                    System.out.println("\nNo such submenu, try again (ex: 1)");
                    Utilities.enterAnyValueToContinue();
                    break;
            }
        } while (nav != 0);
        //submenu
    }
}
