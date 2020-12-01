package com.step.data.employee;

import com.step.data.menu.Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeManager implements IEmployeeManager {
    protected static final Scanner sc = new Scanner(System.in);
    protected static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    protected static List<Employee> employees = new ArrayList<>();

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

    //end delete

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

    //end update

    public static void importSerialized() {
        EmployeeFileDataReader.importFromSerializedFile();
    }


    /**
     * @return -1 if, Too many employees with indicated name and surname; <br>
     *          0 if, No such employee with indicated name and surname; <br>
     *          1 if, updated successfully; <br>
     */
    public int updateByName(String name, String surname) {
        int countFoundEmployees = 0;
        int employeeIndex = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getName().equalsIgnoreCase(name) && employees.get(i).getSurname().equalsIgnoreCase(surname)) {
                countFoundEmployees++;
                if (countFoundEmployees > 1) {
                    return -1;
                }
                employeeIndex = i;
            }
        }

        if (countFoundEmployees == 1) {
            employees.set(employeeIndex, updateStatement(employeeIndex));
            return 1;
        } else {
            return 0;
        }
    }

    public boolean updateByIdnp(String idnp) {
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
            return true;
        } else {
            return false;
        }
    }

    public void insert(Employee e) {
        employees.add(e);
    }
    //end data
}