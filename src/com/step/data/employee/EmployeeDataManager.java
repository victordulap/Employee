package com.step.data.employee;

import com.step.data.menu.ScreenUtilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EmployeeDataManager {
    private static final Scanner sc = new Scanner(System.in);
    protected static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static List<Employee> employees = new ArrayList<>();

    private static String firstLetterUpperCase(String str) {
        str = str.toLowerCase();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);

        return str;
    }

    private static LocalDate enterBirthDate() {
        LocalDate birthDate;
        do {
            System.out.println("Enter birth date in format dd.MM.yyyy (ex: 31.01.1999)");
            System.out.print("birth date: ");
            String date = sc.nextLine();
            date = date.trim();

            try {
                birthDate = LocalDate.parse(date, dateTimeFormatter);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date. please try again (ex: 31.01.1999 (dd.MM.yyyy))");
                ScreenUtilities.enterAnyValueToContinue();
            }
        } while (true);

        return birthDate;
    }

    private static double enterSalary() {
        double salary;

        do {
//            System.out.print("Enter salary: ");
            try {
                salary = sc.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Invalid salary format, try again (ex: 9999.9)");
                System.out.print("Enter idnp: ");
            } finally {
                // reset scanner's line so it work properly,
                // it doesn't reset on any scanner nextXxx() methods other than nextLine()
                sc.nextLine();
            }
        } while (true);

        return salary;
    }

    private static String enterIdnp() {
        boolean repetitiveIdnp;
        String enteredIdnp;
        do {
            repetitiveIdnp = false;
            enteredIdnp = sc.nextLine();
            enteredIdnp = enteredIdnp.trim();

            List<String> idnps = new ArrayList<>();
            for (Employee employee : employees) {
                idnps.add(employee.getIdnp());
            }

            for (String idnp : idnps) {
                if (idnp.equals(enteredIdnp)) {
                    repetitiveIdnp = true;
                    break;
                }
            }

            if (repetitiveIdnp) {
                System.out.println("Entered idnp (" + enteredIdnp + ") is repetitive, try again...");
                ScreenUtilities.enterAnyValueToContinue();
                enteredIdnp = null;
                System.out.print("Enter idnp: ");
            }
        } while (repetitiveIdnp);

        return enteredIdnp;
    }

    private static String enterIdnp(String prevIdnp) {
        boolean repetitiveIdnp = false;
        String enteredIdnp;
        do {
            enteredIdnp = sc.nextLine();
            enteredIdnp = enteredIdnp.trim();

            if (enteredIdnp.equals(prevIdnp)) return enteredIdnp;

            List<String> idnps = new ArrayList<>();
            for (Employee employee : employees) {
                idnps.add(employee.getIdnp());
            }

            for (String idnp : idnps) {
                if (idnp.equals(enteredIdnp)) {
                    repetitiveIdnp = true;
                    break;
                }
            }

            if (repetitiveIdnp) {
                System.out.println("Entered idnp (" + enteredIdnp + ") is repetitive, try again...");
                ScreenUtilities.enterAnyValueToContinue();
            }
        } while (repetitiveIdnp);

        return enteredIdnp.trim();
    }

    public static void insert() {
        String moreEmployees;

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
            String idnp = enterIdnp();

            LocalDate birthDate = enterBirthDate();

            System.out.print("Enter salary: ");
            double salary = enterSalary();

            employees.add(new Employee(name, surname, idnp, birthDate, salary));

            ScreenUtilities.clearScreen();

            System.out.println("INSERTED NEW EMPLOYEE:");
            int i = employees.size() - 1;
            System.out.println("\tname: " + employees.get(i).getName());
            System.out.println("\tsurname: " + employees.get(i).getSurname());
            System.out.println("\tbirthdate: " + employees.get(i).getBirthDateFormatted());
            System.out.println("\tidnp: " + employees.get(i).getIdnp());
            System.out.println("\tsalary: $" + employees.get(i).getSalary());

            System.out.println("Enter 1 to add more, or any value to go back...");
            moreEmployees = sc.nextLine();
            moreEmployees = moreEmployees.trim();
        } while (moreEmployees.equals("1"));

    }

    private static String generateMultipleChars(int n, char c) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < n; i++) {
            str.append(c);
        }

        return str.toString();
    }

    // inserts word with n spaces needed
    private static String insertWord(int maxLenght, String word) {
        StringBuilder str = new StringBuilder();
        str.append(word);

        int n = maxLenght - word.length();
        for (int i = 0; i < n; i++) {
            str.append(' ');
        }

        return String.valueOf(str);
    }

    public static void view() {
        ScreenUtilities.clearScreen();

        if (employees.size() > 0) {
            System.out.println("EMPLOYEE LIST: \n");
        } else {
            System.out.println("NO EMPLOYEES FOUND!");
            ScreenUtilities.enterAnyValueToContinue();
            return;
        }

        char cornerTopLeft = '\u2554';
        char cornerTopRight = '\u2557';
        char cornerBottomLeft = '\u255A';
        char cornerBottomRight = '\u255D';
        char lineX = '\u2550';
        char lineY = '\u2551';
        char cross = '\u256C';
        char crossTDown = '\u2566';
        char crossTUp = '\u2569';
        char crossTLeft = '\u2563';
        char crossTRight = '\u2560';

        int iN = 3, nameN = 15, surnameN = 15, birthDateN = 10, idnpN = 13, salaryN = 10;

        System.out.println(cornerTopLeft + generateMultipleChars(iN, lineX) + crossTDown // i
                + generateMultipleChars(nameN, lineX) + crossTDown //name
                + generateMultipleChars(surnameN, lineX) + crossTDown // surname
                + generateMultipleChars(birthDateN, lineX) + crossTDown // birth date
                + generateMultipleChars(idnpN, lineX) + crossTDown // idnp
                + generateMultipleChars(salaryN, lineX) // salary
                + cornerTopRight);
        System.out.println(lineY + insertWord(iN, "nr") + lineY
                + insertWord(nameN, "name") + lineY
                + insertWord(surnameN, "surname") + lineY
                + insertWord(birthDateN, "birth date") + lineY
                + insertWord(idnpN, "idnp") + lineY
                + insertWord(salaryN, "salary ($)") + lineY);

        int employeesSize = employees.size();
        for (int i = 0; i < employeesSize; i++) {
            System.out.println(crossTRight + generateMultipleChars(iN, lineX) + cross // i
                    + generateMultipleChars(nameN, lineX) + cross //name
                    + generateMultipleChars(surnameN, lineX) + cross // surname
                    + generateMultipleChars(birthDateN, lineX) + cross // birth date
                    + generateMultipleChars(idnpN, lineX) + cross // idnp
                    + generateMultipleChars(salaryN, lineX) // salary
                    + crossTLeft);

            System.out.println(lineY + insertWord(iN, String.valueOf(i + 1)) + lineY
                    + insertWord(nameN, employees.get(i).getName()) + lineY
                    + insertWord(surnameN, employees.get(i).getSurname()) + lineY
                    + insertWord(birthDateN, employees.get(i).getBirthDateFormatted()) + lineY
                    + insertWord(idnpN, employees.get(i).getIdnp()) + lineY
                    + insertWord(salaryN, String.valueOf(employees.get(i).getSalary())) + lineY);
        }

        System.out.println(cornerBottomLeft + generateMultipleChars(iN, lineX) + crossTUp // i
                + generateMultipleChars(nameN, lineX) + crossTUp //name
                + generateMultipleChars(surnameN, lineX) + crossTUp // surname
                + generateMultipleChars(birthDateN, lineX) + crossTUp // birth date
                + generateMultipleChars(idnpN, lineX) + crossTUp // idnp
                + generateMultipleChars(salaryN, lineX)  // salary
                + cornerBottomRight);

        ScreenUtilities.enterAnyValueToContinue();
    }

    //delete
    private static void deleteStatement(int employeeIndex) {
        System.out.println("Employee " + employees.get(employeeIndex).getName()
                + " " + employees.get(employeeIndex).getSurname() /*+ " (id: "
                + employees.get(employeeIndex).getId() + " / idnp: "*/
                + " (idnp : " + employees.get(employeeIndex).getIdnp() + ") was removed.");
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

//    private static void deleteById() {
//        ScreenUtilities.clearScreen();
//
//        int id;
//        do {
//            System.out.print("Enter employee's id: ");
//            try {
//                id = sc.nextInt();
//                break;
//            } catch (Exception e) {
//                System.out.println("Invalid id format, try again (ex: 123)");
//            } finally {
//                // reset scanner's line so it work properly,
//                // it doesn't reset on any scanner nextXxx() methods other than nextLine()
//                sc.nextLine();
//            }
//        } while (true);
//
//        boolean idFound = false;
//        int employeeIndex = -1;
//        for (int i = 0; i < employees.size(); i++) {
//            if (employees.get(i).getId() == id) {
//                idFound = true;
//                employeeIndex = i;
//            }
//        }
//
//        if (idFound) {
//            deleteStatement(employeeIndex);
//        } else {
//            System.out.println("No such employee with indicated id (" + id + ").");
//        }
//
//        ScreenUtilities.enterAnyValueToContinue();
//    }

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
            System.out.println("\t2. delete by name and surname");
//            System.out.println("\t3. delete by id");
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
                    EmployeeDataManager.deleteByName();
                    break;
//                case 3:
//                    EmployeeDataManager.deleteById();
//                    break;
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
                + " " + employees.get(employeeIndex).getSurname() /*+ " (id: "
                + employees.get(employeeIndex).getId() + " / idnp: "*/
                + " (idnp: " + employees.get(employeeIndex).getIdnp() + ")...");
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
        System.out.print("birth date: " + employees.get(employeeIndex).getBirthDateFormatted() + " -> ");
        LocalDate birthDate = enterBirthDate();
        //end birthDate

        //idnp
        System.out.print("idnp: " + employees.get(employeeIndex).getIdnp() + " -> ");
        String idnp = enterIdnp(employees.get(employeeIndex).getIdnp());
        //end idnp

        System.out.print("salary: " + employees.get(employeeIndex).getSalary() + " -> ");
        double salary = enterSalary();

//        int id = employees.size();
        return new Employee(name, surname, idnp, birthDate, salary);
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

//    private static void updateById() {
//        ScreenUtilities.clearScreen();
//
//        int id;
//        do {
//            System.out.print("Enter employee's id: ");
//            try {
//                id = sc.nextInt();
//                break;
//            } catch (Exception e) {
//                System.out.println("Invalid id format, try again (ex: 123)");
//            } finally {
//                sc.nextLine();
//            }
//        } while (true);
//
//        boolean idFound = false;
//        int employeeIndex = -1;
//        for (int i = 0; i < employees.size(); i++) {
//            if (employees.get(i).getId() == id) {
//                idFound = true;
//                employeeIndex = i;
//            }
//        }
//
//        if (idFound) {
//            employees.set(employeeIndex, updateStatement(employeeIndex));
//            System.out.println("\nUpdated successfully!\n");
//        } else {
//            System.out.println("No such employee with indicated id (" + id + ").");
//        }
//
//        ScreenUtilities.enterAnyValueToContinue();
//    }

    public static void update() {
        //submenu
        int nav = -1;
        do {
            ScreenUtilities.clearScreen();

            System.out.println("Select update method:");
            System.out.println();
            System.out.println("\t1. update by idnp");
            System.out.println("\t2. update by name and surname");
//            System.out.println("\t3. update by id");
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
                    EmployeeDataManager.updateByName();
                    break;
//                case 3:
//                    EmployeeDataManager.updateById();
//                    break;
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
