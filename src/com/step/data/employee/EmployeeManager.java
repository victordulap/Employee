package com.step.data.employee;

import com.step.data.menu.Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EmployeeManager {
    protected static final Scanner sc = new Scanner(System.in);
    protected static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    protected static List<Employee> employees = new ArrayList<>();

    public static void insert(Employee e) {
        employees.add(e);
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

        System.out.println(cornerTopLeft + Utilities.generateMultipleChars(iN, lineX) + crossTDown // i
                + Utilities.generateMultipleChars(nameN, lineX) + crossTDown //name
                + Utilities.generateMultipleChars(surnameN, lineX) + crossTDown // surname
                + Utilities.generateMultipleChars(birthDateN, lineX) + crossTDown // birth date
                + Utilities.generateMultipleChars(idnpN, lineX) + crossTDown // idnp
                + Utilities.generateMultipleChars(salaryN, lineX) + crossTDown // salary
                + Utilities.generateMultipleChars(jobN, lineX) // job
                + cornerTopRight);
        System.out.println(lineY + Utilities.insertWordWithNSpaces(iN, "nr") + lineY
                + Utilities.insertWordWithNSpaces(nameN, "name") + lineY
                + Utilities.insertWordWithNSpaces(surnameN, "surname") + lineY
                + Utilities.insertWordWithNSpaces(birthDateN, "birth date") + lineY
                + Utilities.insertWordWithNSpaces(idnpN, "idnp") + lineY
                + Utilities.insertWordWithNSpaces(salaryN, "salary ($)") + lineY
                + Utilities.insertWordWithNSpaces(jobN, "job") + lineY);

        for (int i = 0; i < employeesSize; i++) {
            System.out.println(crossTRight + Utilities.generateMultipleChars(iN, lineX) + cross // i
                    + Utilities.generateMultipleChars(nameN, lineX) + cross //name
                    + Utilities.generateMultipleChars(surnameN, lineX) + cross // surname
                    + Utilities.generateMultipleChars(birthDateN, lineX) + cross // birth date
                    + Utilities.generateMultipleChars(idnpN, lineX) + cross // idnp
                    + Utilities.generateMultipleChars(salaryN, lineX) + cross // salary
                    + Utilities.generateMultipleChars(jobN, lineX) // job
                    + crossTLeft);

            System.out.println(lineY + Utilities.insertWordWithNSpaces(iN, String.valueOf(i + 1)) + lineY
                    + Utilities.insertWordWithNSpaces(nameN, employees.get(i).getName()) + lineY
                    + Utilities.insertWordWithNSpaces(surnameN, employees.get(i).getSurname()) + lineY
                    + Utilities.insertWordWithNSpaces(birthDateN, employees.get(i).getBirthDateFormatted()) + lineY
                    + Utilities.insertWordWithNSpaces(idnpN, employees.get(i).getIdnp()) + lineY
                    + Utilities.insertWordWithNSpaces(salaryN, String.valueOf(employees.get(i).getSalary())) + lineY
                    + Utilities.insertWordWithNSpaces(jobN, (Utilities.firstLetterUpperCase(employees.get(i).getJob().toString()))) + lineY);
        }

        System.out.println(cornerBottomLeft + Utilities.generateMultipleChars(iN, lineX) + crossTUp // i
                + Utilities.generateMultipleChars(nameN, lineX) + crossTUp //name
                + Utilities.generateMultipleChars(surnameN, lineX) + crossTUp // surname
                + Utilities.generateMultipleChars(birthDateN, lineX) + crossTUp // birth date
                + Utilities.generateMultipleChars(idnpN, lineX) + crossTUp // idnp
                + Utilities.generateMultipleChars(salaryN, lineX) + crossTUp // salary
                + Utilities.generateMultipleChars(jobN, lineX) // job
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

    protected static void deleteByIdnp() {
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

    protected static void deleteByName() {
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

    protected static void deleteAllEmployees() {
        Utilities.clearScreen();
        employees.clear();
        System.out.println("Deleted all employees");
        Utilities.enterAnyValueToContinue();
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
        LocalDate birthDate = EmployeeDataChecker.enterBirthDate();
        //end birthDate

        //idnp
        System.out.print("idnp: " + employees.get(employeeIndex).getIdnp() + " -> ");
        String idnp = EmployeeDataChecker.enterIdnp(employees.get(employeeIndex).getIdnp());
        //end idnp

        System.out.print("salary: " + employees.get(employeeIndex).getSalary() + " -> ");
        double salary = EmployeeDataChecker.enterSalary();

        System.out.print("job: " + Utilities.firstLetterUpperCase(employees.get(employeeIndex).getJob().toString()) + " -> ");
        Job job = EmployeeDataChecker.enterJob();

        return new Employee(name, surname, idnp, birthDate, salary, job);
    }

    protected static void updateByIdnp() {
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

    protected static void updateByName() {
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