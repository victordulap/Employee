package com.step.data.employee;

import com.step.data.menu.ScreenUtilities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeDataManager {
    private static final Scanner sc = new Scanner(System.in);

    //    private static Employee[] employees = new Employee[100];
    //    private static int employeesCount = 0;
    private static List<Employee> employees = new ArrayList<>();

    private static String firstLetterUpperCase(String str) {
        str = str.toLowerCase();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);

        return str;
    }

    private static LocalDate enterBirthDate() {
        LocalDate birthDate;
        do {
            int year = 0, month = 0, day = 0;
            System.out.println("Entering birth date...");
            try {
                System.out.print("\tyear: ");
                year = sc.nextInt();
                System.out.print("\tmonth: ");
                month = sc.nextInt();
                System.out.print("\tday: ");
                day = sc.nextInt();

                try {
                    birthDate = LocalDate.of(year, month, day);
                    break;
                } catch (Exception e) {
                    System.out.println("Invalid date. please try again (ex: 2001-1-1 (yyyy/M/d))");
                    ScreenUtilities.enterAnyValueToContinue();
                }
            } catch (Exception e) {
                System.out.println("Invalid format, please try again... (don't use any characters other than integers)");
            }
            finally {
                sc.nextLine();
            }
        } while (true);

        return birthDate;
    }

    private static double enterSalary() {
        double salary;

        do {
            System.out.print("Enter salary: ");
            try {
                salary = sc.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Invalid salary format, try again (ex: 9999.9)");
            } finally {
                // reset scanner's line so it work properly,
                // it doesn't reset on any scanner nextXxx() methods other than nextLine()
                sc.nextLine();
            }
        } while (true);

        return salary;
    }

    public static void insert() {
        String moreEmployees = "false";

        do {
            ScreenUtilities.clearScreen();

            System.out.println("INSERTING NEW EMPLOYEE...");

            System.out.print("Enter name: ");
            String name = sc.nextLine();
            name = name.trim();
            name = firstLetterUpperCase(name);

            System.out.print("Enter surname: ");
            String surname = sc.nextLine();
            surname = surname.trim();
            surname = firstLetterUpperCase(surname);

            System.out.print("Enter idnp: ");
            String idnp = sc.nextLine();
            idnp = idnp.trim();

            LocalDate birthDate = enterBirthDate();

            double salary = enterSalary();


            int id = employees.size();
            employees.add(new Employee(id, name, surname, idnp, birthDate, salary));

            ScreenUtilities.clearScreen();

            System.out.println("INSERTED NEW EMPLOYEE:");
            int i = employees.size() - 1;
            System.out.println("\tname: " + employees.get(i).getName());
            System.out.println("\tsurname: " + employees.get(i).getSurname());
            System.out.println("\tbirthdate: " + employees.get(i).getBirthDate());
            System.out.println("\tidnp: " + employees.get(i).getIdnp());
            System.out.println("\tsalary: $" + employees.get(i).getSalary());

            System.out.println("Enter 1 to add more, or any value to go back...");
            moreEmployees = sc.nextLine();
            moreEmployees = moreEmployees.trim();
        } while (moreEmployees.equals("1"));

    }

    public static void view() {
        ScreenUtilities.clearScreen();

//        int employeesPerPage = 2;
        if (employees.size() > 0) {
            System.out.println("EMPLOYEE LIST: \n");
        } else {
            System.out.println("NO EMPLOYEES FOUND!");
            ScreenUtilities.enterAnyValueToContinue();
            return;
        }

        for (int i = 0; i < employees.size(); i++) {
            System.out.println((i + 1) + ". --------------------");
            System.out.println("\tid: " + employees.get(i).getId());
            System.out.println("\tname: " + employees.get(i).getName());
            System.out.println("\tsurname: " + employees.get(i).getSurname());
            System.out.println("\tbirthdate: " + employees.get(i).getBirthDate());
            System.out.println("\tidnp: " + employees.get(i).getIdnp());
            System.out.println("\tsalary: $" + employees.get(i).getSalary());
        }

        ScreenUtilities.enterAnyValueToContinue();
    }

    //delete
    private static void deleteStatement(int employeeIndex) {
        System.out.println("Employee " + employees.get(employeeIndex).getName()
                + " " + employees.get(employeeIndex).getSurname() + " (id: "
                + employees.get(employeeIndex).getId() + " / idnp: "
                + employees.get(employeeIndex).getIdnp() + ") was removed.");
        employees.remove(employeeIndex);
    }

    private static void deleteByIdnp() {
        ScreenUtilities.clearScreen();

        System.out.print("Enter employee's idnp: ");
        String idnp = sc.nextLine();
        idnp = idnp.trim();

        boolean idnpFound = false;
        int employeeIndex = -1;
//        for (Employee e : employees) {
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getIdnp().equals(idnp)) {
                idnpFound = true;
                employeeIndex = i;
            }
        }

        if (idnpFound) {
            deleteStatement(employeeIndex);
        } else {
            System.out.println("No such employee with indicated idnp (" + idnp + ").");
        }

        ScreenUtilities.enterAnyValueToContinue();
    }

    private static void deleteById() {
        ScreenUtilities.clearScreen();

        int id;
        do {
            System.out.print("Enter employee's id: ");
            try {
                id = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid id format, try again (ex: 123)");
            } finally {
                // reset scanner's line so it work properly,
                // it doesn't reset on any scanner nextXxx() methods other than nextLine()
                sc.nextLine();
            }
        } while (true);

        boolean idFound = false;
        int employeeIndex = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                idFound = true;
                employeeIndex = i;
            }
        }

        if (idFound) {
            deleteStatement(employeeIndex);
        } else {
            System.out.println("No such employee with indicated id (" + id + ").");
        }

        ScreenUtilities.enterAnyValueToContinue();
    }

    private static void deleteByName() {
        ScreenUtilities.clearScreen();

        System.out.print("Enter employee's name: ");
        String name = sc.nextLine();
        name = name.trim();
        System.out.print("Enter employee's surname: ");
        String surname = sc.nextLine();
        surname = surname.trim();

        int countFoundEmployees = 0;
        int employeeIndex = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getName().equalsIgnoreCase(name) && employees.get(i).getSurname().equalsIgnoreCase(surname)) {
                countFoundEmployees++;
                //for not searching for all users
                if (countFoundEmployees > 1) {
                    System.out.println("Too many employees with indicated name and surname " +
                            "(" + name + " " + surname + ")" + ", try other method.");
                    ScreenUtilities.enterAnyValueToContinue();

                    return;
                }
                employeeIndex = i;
            }
        }

        if (countFoundEmployees == 1) {
            deleteStatement(employeeIndex);
        } else {
            System.out.println("No such employee with indicated name and surname (" + name + " " + surname + ")" +
                    ", verify name and surname before entering");
        }

        ScreenUtilities.enterAnyValueToContinue();
    }

    public static void delete() {
        //submenu
        int nav = -1;
        do {
            ScreenUtilities.clearScreen();

            System.out.println("Select delete method:");
            System.out.println();
            System.out.println("\t1. delete by idnp");
            System.out.println("\t2. delete by id");
            System.out.println("\t2. delete by name and surname");
            System.out.println();
            System.out.println("\t0. back");

            System.out.print("\nenter submenu number: ");
            try {
                nav = sc.nextInt();
            } catch (Exception e) {
                System.out.println("\nInvalid format, try again (ex: 1)");
                ScreenUtilities.enterAnyValueToContinue();
            } finally {
                sc.nextLine();
            }

            switch (nav) {
                case 1:
                    EmployeeDataManager.deleteByIdnp();
                    break;
                case 2:
                    EmployeeDataManager.deleteById();
                    break;
                case 3:
                    EmployeeDataManager.deleteByName();
                    break;
                case 0:
                    return;

                default:
                    System.out.println("\nNo such submenu, try again (ex: 1)");
                    ScreenUtilities.enterAnyValueToContinue();
                    break;
            }
        } while (nav != 0);
        //submenu
    }
    //end delete

    //update
    private static Employee updateStatement(int employeeIndex) {
        System.out.println("Editing employee " + employees.get(employeeIndex).getName()
                + " " + employees.get(employeeIndex).getSurname() + " (id: "
                + employees.get(employeeIndex).getId() + " / idnp: "
                + employees.get(employeeIndex).getIdnp() + ")...");
        //name
        System.out.print("name: " + employees.get(employeeIndex).getName() + " -> ");
        String name = sc.nextLine();
        name = name.trim();
        name = firstLetterUpperCase(name);
        //end name

        //surname
        System.out.print("surname: " + employees.get(employeeIndex).getSurname() + " -> ");
        String surname = sc.nextLine();
        surname = surname.trim();
        surname = firstLetterUpperCase(surname);
        //end surname

        //birthDate
        System.out.print("birth date: " + employees.get(employeeIndex).getBirthDate() + " -> ");
        LocalDate birthDate = enterBirthDate();
        //end birthDate

        //idnp
        System.out.print("idnp: " + employees.get(employeeIndex).getIdnp() + " -> ");
        String idnp_ = sc.nextLine();
        idnp_ = idnp_.trim();
        //end idnp

        double salary = enterSalary();

        int id = employees.size();
        return new Employee(id, name, surname, idnp_, birthDate, salary);
    }

    private static void updateByIdnp() {
        ScreenUtilities.clearScreen();

        System.out.print("Enter employee's idnp: ");
        String idnp = sc.nextLine();
        idnp = idnp.trim();

        boolean idnpFound = false;
        int employeeIndex = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getIdnp().equals(idnp)) {
                idnpFound = true;
                employeeIndex = i;
            }
        }

        if (idnpFound) {
            employees.set(employeeIndex, updateStatement(employeeIndex));
            System.out.println("\nUpdated successfully!\n");
        } else {
            System.out.println("No such employee with indicated idnp (" + idnp + ").");
        }

        ScreenUtilities.enterAnyValueToContinue();
    }

    private static void updateByName() {
        ScreenUtilities.clearScreen();

        System.out.print("Enter employee's name: ");
        String name = sc.nextLine();
        name = name.trim();
        System.out.print("Enter employee's surname: ");
        String surname = sc.nextLine();
        surname = surname.trim();

        int countFoundEmployees = 0;
        int employeeIndex = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getName().equalsIgnoreCase(name) && employees.get(i).getSurname().equalsIgnoreCase(surname)) {
                countFoundEmployees++;
                if (countFoundEmployees > 1) {
                    System.out.println("Too many employees with indicated name and surname " +
                            "(" + name + " " + surname + ")" + ", try other method.");
                    ScreenUtilities.enterAnyValueToContinue();

                    return;
                }
                employeeIndex = i;
            }
        }

        if (countFoundEmployees == 1) {
            employees.set(employeeIndex, updateStatement(employeeIndex));
            System.out.println("\nUpdated successfully!\n");
        } else {
            System.out.println("No such employee with indicated name and surname (" + name + " " + surname + ")" +
                    ", verify name and surname before entering");
        }

        ScreenUtilities.enterAnyValueToContinue();
    }

    private static void updateById() {
        ScreenUtilities.clearScreen();

        int id;
        do {
            System.out.print("Enter employee's id: ");
            try {
                id = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid id format, try again (ex: 123)");
            } finally {
                sc.nextLine();
            }
        } while (true);

        boolean idFound = false;
        int employeeIndex = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                idFound = true;
                employeeIndex = i;
            }
        }

        if (idFound) {
            employees.set(employeeIndex, updateStatement(employeeIndex));
            System.out.println("\nUpdated successfully!\n");
        } else {
            System.out.println("No such employee with indicated id (" + id + ").");
        }

        ScreenUtilities.enterAnyValueToContinue();
    }

    public static void update() {
        //submenu
        int nav = -1;
        do {
            ScreenUtilities.clearScreen();

            System.out.println("Select update method:");
            System.out.println();
            System.out.println("\t1. update by idnp");
            System.out.println("\t1. update by id");
            System.out.println("\t3. update by name and surname");
            System.out.println();
            System.out.println("\t0. back");

            System.out.print("\nenter submenu number: ");
            try {
                nav = sc.nextInt();
            } catch (Exception e) {
                System.out.println("\nInvalid format, try again (ex: 1)");
                ScreenUtilities.enterAnyValueToContinue();
            } finally {
                sc.nextLine();
            }

            switch (nav) {
                case 1:
                    EmployeeDataManager.updateByIdnp();
                    break;
                case 2:
                    EmployeeDataManager.updateById();
                    break;
                case 3:
                    EmployeeDataManager.updateByName();
                    break;
                case 0:
                    return;

                default:
                    System.out.println("\nNo such submenu, try again (ex: 1)");
                    ScreenUtilities.enterAnyValueToContinue();
                    break;
            }
        } while (nav != 0);
        //submenu
    }
    //end update
}
