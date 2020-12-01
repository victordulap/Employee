package com.step.data.employee;

import com.step.data.menu.Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EmployeeManager {
    private static final Scanner sc = new Scanner(System.in);
    protected static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    protected static List<Employee> employees = new ArrayList<>();

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
                Utilities.enterAnyValueToContinue();
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

            boolean verifyRest = true;

            if (enteredIdnp.length() != 13) {
                verifyRest = false;
                System.out.println("IDNP must contain 13 chars!");
                Utilities.enterAnyValueToContinue();
                repetitiveIdnp = true;
                System.out.print("Enter idnp: ");
            }

            if (verifyRest) {
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
                    Utilities.enterAnyValueToContinue();
                    enteredIdnp = null;
                    System.out.print("Enter idnp: ");
                }
            }
        } while (repetitiveIdnp);

        return enteredIdnp;
    }

    private static String enterIdnp(String prevIdnp) {
        boolean repetitiveIdnp;
        String enteredIdnp;
        do {
            repetitiveIdnp = false;
            enteredIdnp = sc.nextLine();
            enteredIdnp = enteredIdnp.trim();

            boolean verifyRest = true;

            if (enteredIdnp.length() != 13) {
                verifyRest = false;
                System.out.println("IDNP must contain 13 chars!");
                Utilities.enterAnyValueToContinue();
                repetitiveIdnp = true;
            }

            if (verifyRest) {

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
                    Utilities.enterAnyValueToContinue();
                }
            }
        } while (repetitiveIdnp);

        return enteredIdnp.trim();
    }

    private static Job enterJob() {
        boolean validJob = false;
        String job = Job.MANAGER.toString();
        while (!validJob) {
            job = sc.nextLine();

            for (Job j : Job.values()) {
                if (j.toString().equals(job.toUpperCase())) {
                    validJob = true;
                }
            }
            if (!validJob) {
                System.out.println("Invalid job, available jobs: " + Arrays.toString(Job.values()));
                Utilities.enterAnyValueToContinue();
                System.out.print("Enter job: ");
            }
        }
        job = job.toUpperCase();

        return Job.valueOf(job);
    }

    public static void insert() {
        String moreEmployees;

        do {
            Utilities.clearScreen();

            System.out.println("INSERTING NEW EMPLOYEE...");

            System.out.print("Enter name: ");
            String name = sc.nextLine();
            name = name.trim();
            name = (name.length() > 0) ? Utilities.firstLetterUpperCase(name) : "";

            System.out.print("Enter surname: ");
            String surname = sc.nextLine();
            surname = surname.trim();
            surname = (surname.length() > 0) ? Utilities.firstLetterUpperCase(surname) : "";

            System.out.print("Enter idnp: ");
            String idnp = enterIdnp();

            LocalDate birthDate = enterBirthDate();

            System.out.print("Enter salary: ");
            double salary = enterSalary();

            System.out.print("Enter job: ");
            Job job = enterJob();

            employees.add(new Employee(name, surname, idnp, birthDate, salary, job));

            Utilities.clearScreen();

            System.out.println("INSERTED NEW EMPLOYEE:");
            int i = employees.size() - 1;
            System.out.println("\tname: " + employees.get(i).getName());
            System.out.println("\tsurname: " + employees.get(i).getSurname());
            System.out.println("\tbirthdate: " + employees.get(i).getBirthDateFormatted());
            System.out.println("\tidnp: " + employees.get(i).getIdnp());
            System.out.println("\tsalary: $" + employees.get(i).getSalary());
            System.out.println("\tjob: " + Utilities.firstLetterUpperCase(employees.get(i).getJob().toString()));

            System.out.println("Enter 1 to add more, or any value to go back...");
            moreEmployees = sc.nextLine();
            moreEmployees = moreEmployees.trim();
        } while (moreEmployees.equals("1"));

    }

    //used for tabulation
    private static String generateMultipleChars(int n, char c) {
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < n; i++) {
            str.append(c);
        }

        return str.toString();
    }

    // inserts word with n spaces needed
    private static String insertWord(int maxLength, String word) {
        StringBuilder str = new StringBuilder();
        str.append(word);

        int n = maxLength - word.length();
        for (int i = 0; i < n; i++) {
            str.append(' ');
        }

        return String.valueOf(str);
    }

    public static void view() {
        Utilities.clearScreen();

        if (employees.size() > 0) {
            System.out.println("EMPLOYEE LIST: \n");
        } else {
            System.out.println("NO EMPLOYEES FOUND!");
            Utilities.enterAnyValueToContinue();
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

        int employeesSize = employees.size();
//        int iN = 3, nameN = 15, surnameN = 15, birthDateN = 10, idnpN = 13, salaryN = 10;
        int iN = employeesSize > 1 ? employeesSize : 2;

        int nameN = 4;
        int surnameN = 7;
        int birthDateN = 10;
        int idnpN = 13;
        int salaryN = 10;
        int jobN = 3;
        for (int i = 0; i < employeesSize; i++) {
            if (employees.get(i).getName().length() > nameN) {
                nameN = employees.get(i).getName().length();
            }
            if (employees.get(i).getSurname().length() > surnameN) {
                surnameN = employees.get(i).getSurname().length();
            }
            if (String.valueOf(employees.get(i).getSalary()).length() > salaryN) {
                salaryN = String.valueOf(employees.get(i).getSalary()).length();
            }
            if (employees.get(i).getJob().toString().length() > jobN) {
                jobN = employees.get(i).getJob().toString().length();
            }
        }

        System.out.println(cornerTopLeft + generateMultipleChars(iN, lineX) + crossTDown // i
                + generateMultipleChars(nameN, lineX) + crossTDown //name
                + generateMultipleChars(surnameN, lineX) + crossTDown // surname
                + generateMultipleChars(birthDateN, lineX) + crossTDown // birth date
                + generateMultipleChars(idnpN, lineX) + crossTDown // idnp
                + generateMultipleChars(salaryN, lineX) + crossTDown // salary
                + generateMultipleChars(jobN, lineX) // job
                + cornerTopRight);
        System.out.println(lineY + insertWord(iN, "nr") + lineY
                + insertWord(nameN, "name") + lineY
                + insertWord(surnameN, "surname") + lineY
                + insertWord(birthDateN, "birth date") + lineY
                + insertWord(idnpN, "idnp") + lineY
                + insertWord(salaryN, "salary ($)") + lineY
                + insertWord(jobN, "job") + lineY);

        for (int i = 0; i < employeesSize; i++) {
            System.out.println(crossTRight + generateMultipleChars(iN, lineX) + cross // i
                    + generateMultipleChars(nameN, lineX) + cross //name
                    + generateMultipleChars(surnameN, lineX) + cross // surname
                    + generateMultipleChars(birthDateN, lineX) + cross // birth date
                    + generateMultipleChars(idnpN, lineX) + cross // idnp
                    + generateMultipleChars(salaryN, lineX) + cross // salary
                    + generateMultipleChars(jobN, lineX) // job
                    + crossTLeft);

            System.out.println(lineY + insertWord(iN, String.valueOf(i + 1)) + lineY
                    + insertWord(nameN, employees.get(i).getName()) + lineY
                    + insertWord(surnameN, employees.get(i).getSurname()) + lineY
                    + insertWord(birthDateN, employees.get(i).getBirthDateFormatted()) + lineY
                    + insertWord(idnpN, employees.get(i).getIdnp()) + lineY
                    + insertWord(salaryN, String.valueOf(employees.get(i).getSalary())) + lineY
                    + insertWord(jobN, (Utilities.firstLetterUpperCase(employees.get(i).getJob().toString()))) + lineY);
        }

        System.out.println(cornerBottomLeft + generateMultipleChars(iN, lineX) + crossTUp // i
                + generateMultipleChars(nameN, lineX) + crossTUp //name
                + generateMultipleChars(surnameN, lineX) + crossTUp // surname
                + generateMultipleChars(birthDateN, lineX) + crossTUp // birth date
                + generateMultipleChars(idnpN, lineX) + crossTUp // idnp
                + generateMultipleChars(salaryN, lineX) + crossTUp // salary
                + generateMultipleChars(jobN, lineX) // job
                + cornerBottomRight);

        Utilities.enterAnyValueToContinue();
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
        Utilities.clearScreen();

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

        Utilities.enterAnyValueToContinue();
    }

    private static void deleteByName() {
        Utilities.clearScreen();

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
                    Utilities.enterAnyValueToContinue();

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

        Utilities.enterAnyValueToContinue();
    }

    private static void deleteAllEmployees() {
        Utilities.clearScreen();
        employees.clear();
        System.out.println("Deleted all employees");
        Utilities.enterAnyValueToContinue();
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
                nav = sc.nextInt();
            } catch (Exception e) {
                System.out.println("\nInvalid format, try again (ex: 1)");
                Utilities.enterAnyValueToContinue();
            } finally {
                sc.nextLine();
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
        name = Utilities.firstLetterUpperCase(name);
        //end name

        //surname
        System.out.print("surname: " + employees.get(employeeIndex).getSurname() + " -> ");
        String surname = sc.nextLine();
        surname = surname.trim();
        surname = Utilities.firstLetterUpperCase(surname);
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

        System.out.print("job: " + Utilities.firstLetterUpperCase(employees.get(employeeIndex).getJob().toString()) + " -> ");
        Job job = enterJob();

        return new Employee(name, surname, idnp, birthDate, salary, job);
    }

    private static void updateByIdnp() {
        Utilities.clearScreen();

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

        Utilities.enterAnyValueToContinue();
    }

    private static void updateByName() {
        Utilities.clearScreen();

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
                    Utilities.enterAnyValueToContinue();

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

        Utilities.enterAnyValueToContinue();
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
                nav = sc.nextInt();
            } catch (Exception e) {
                System.out.println("\nInvalid format, try again (ex: 1)");
                Utilities.enterAnyValueToContinue();
            } finally {
                sc.nextLine();
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
    //end update

    //data
    public static void exportCSV() {
        EmployeeFileDataReader.exportToCSVFile();

        System.out.println("Exported successfully!");
        Utilities.enterAnyValueToContinue();
    }

    public static void importCSV() {
        EmployeeFileDataReader.importFromCSVFile();

        System.out.println("Imported successfully!");
        Utilities.enterAnyValueToContinue();
    }

    public static void exportSerialized() {
        EmployeeFileDataReader.exportToSerializedFile();
    }

    public static void importSerialized() {
        EmployeeFileDataReader.importFromSerializedFile();
    }
    //end data
}