package com.step.data.employee.manager;

import com.step.data.employee.Employee;
import com.step.data.employee.Job;
import com.step.data.menu.Utilities;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class EmployeeShowInConsoleManager {
    private IEmployeeManager em;
    protected static final Scanner sc = new Scanner(System.in);

    public EmployeeShowInConsoleManager(int option) {
        switch (option) {
            case 1: {
                em = new EmployeeManagerInMemory();
                break;
            } case 2: {
                em = new EmployeeManagerInFile();
                break;
            }
        }
    }

    public void view(List<Employee> employees) {
        Utilities.clearScreen();

        if (EmployeeManagerInFile.employees.size() > 0) {
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
        int iN = employeesSize > 1 ? employeesSize : 2;

        int idN = 2;
        int nameN = 4;
        int surnameN = 7;
        int birthDateN = 10;
        int idnpN = 13;
        int salaryN = 10;
        int jobN = 3;
        for (int i = 0; i < employeesSize; i++) {
            Employee currentEmployee = employees.get(i);

            if (currentEmployee.getId() > idN) {
                idN = currentEmployee.getId();
            }
            if (currentEmployee.getName().length() > nameN) {
                nameN = currentEmployee.getName().length();
            }
            if (currentEmployee.getSurname().length() > surnameN) {
                surnameN = currentEmployee.getSurname().length();
            }
            if (String.valueOf(currentEmployee.getSalary()).length() > salaryN) {
                salaryN = String.valueOf(currentEmployee.getSalary()).length();
            }
            if (currentEmployee.getJob().toString().length() > jobN) {
                jobN = currentEmployee.getJob().toString().length();
            }
        }

        System.out.println(cornerTopLeft + Utilities.generateMultipleChars(iN, lineX) + crossTDown // i
                + Utilities.generateMultipleChars(idN, lineX) + crossTDown //id
                + Utilities.generateMultipleChars(nameN, lineX) + crossTDown //name
                + Utilities.generateMultipleChars(surnameN, lineX) + crossTDown // surname
                + Utilities.generateMultipleChars(birthDateN, lineX) + crossTDown // birth date
                + Utilities.generateMultipleChars(idnpN, lineX) + crossTDown // idnp
                + Utilities.generateMultipleChars(salaryN, lineX) + crossTDown // salary
                + Utilities.generateMultipleChars(jobN, lineX) // job
                + cornerTopRight);
        System.out.println(lineY + Utilities.insertWordWithNSpaces(iN, "nr") + lineY
                + Utilities.insertWordWithNSpaces(idN, "id") + lineY
                + Utilities.insertWordWithNSpaces(nameN, "name") + lineY
                + Utilities.insertWordWithNSpaces(surnameN, "surname") + lineY
                + Utilities.insertWordWithNSpaces(birthDateN, "birth date") + lineY
                + Utilities.insertWordWithNSpaces(idnpN, "idnp") + lineY
                + Utilities.insertWordWithNSpaces(salaryN, "salary ($)") + lineY
                + Utilities.insertWordWithNSpaces(jobN, "job") + lineY);

        for (int i = 0; i < employeesSize; i++) {
            System.out.println(crossTRight + Utilities.generateMultipleChars(iN, lineX) + cross // i
                    + Utilities.generateMultipleChars(idN, lineX) + cross //id
                    + Utilities.generateMultipleChars(nameN, lineX) + cross //name
                    + Utilities.generateMultipleChars(surnameN, lineX) + cross // surname
                    + Utilities.generateMultipleChars(birthDateN, lineX) + cross // birth date
                    + Utilities.generateMultipleChars(idnpN, lineX) + cross // idnp
                    + Utilities.generateMultipleChars(salaryN, lineX) + cross // salary
                    + Utilities.generateMultipleChars(jobN, lineX) // job
                    + crossTLeft);

            System.out.println(lineY + Utilities.insertWordWithNSpaces(iN, String.valueOf(i + 1)) + lineY
                    + Utilities.insertWordWithNSpaces(idN, String.valueOf(employees.get(i).getId())) + lineY
                    + Utilities.insertWordWithNSpaces(nameN, employees.get(i).getName()) + lineY
                    + Utilities.insertWordWithNSpaces(surnameN, employees.get(i).getSurname()) + lineY
                    + Utilities.insertWordWithNSpaces(birthDateN, employees.get(i).getBirthDateFormatted()) + lineY
                    + Utilities.insertWordWithNSpaces(idnpN, employees.get(i).getIdnp()) + lineY
                    + Utilities.insertWordWithNSpaces(salaryN, String.valueOf(employees.get(i).getSalary())) + lineY
                    + Utilities.insertWordWithNSpaces(jobN, (Utilities.firstLetterUpperCase(employees.get(i).getJob().toString()))) + lineY);
        }

        System.out.println(cornerBottomLeft + Utilities.generateMultipleChars(iN, lineX) + crossTUp // i
                + Utilities.generateMultipleChars(idN, lineX) + crossTUp //id
                + Utilities.generateMultipleChars(nameN, lineX) + crossTUp //name
                + Utilities.generateMultipleChars(surnameN, lineX) + crossTUp // surname
                + Utilities.generateMultipleChars(birthDateN, lineX) + crossTUp // birth date
                + Utilities.generateMultipleChars(idnpN, lineX) + crossTUp // idnp
                + Utilities.generateMultipleChars(salaryN, lineX) + crossTUp // salary
                + Utilities.generateMultipleChars(jobN, lineX) // job
                + cornerBottomRight);

        Utilities.enterAnyValueToContinue();
    }

    public void filterById() {
        Scanner scanner = new Scanner(System.in);

        Utilities.clearScreen();



        int minId;
        int maxId;
        do {
            try {
                System.out.print("Enter min id for filtering: ");
                minId = scanner.nextInt();
                System.out.print("Enter max id for filtering: ");
                maxId = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid id format, try again (ex: 123)");
                scanner.nextLine();
            }
        } while (true);

        List<Employee> employees = em.filterById(minId, maxId);

        view(employees);
    }

    public void insert() {
        String moreEmployees;

        do {
            Utilities.clearScreen();

            System.out.println("INSERTING NEW EMPLOYEE...");

            System.out.print("Enter name: ");
            String name = EmployeeManagerInFile.sc.nextLine();
            name = name.trim();
            name = (name.length() > 0) ? Utilities.firstLetterUpperCase(name) : "";

            System.out.print("Enter surname: ");
            String surname = EmployeeManagerInFile.sc.nextLine();
            surname = surname.trim();
            surname = (surname.length() > 0) ? Utilities.firstLetterUpperCase(surname) : "";

            System.out.print("Enter idnp: ");
            String idnp = EmployeeDataChecker.enterIdnp();

            LocalDate birthDate = EmployeeDataChecker.enterBirthDate();

            System.out.print("Enter salary: ");
            double salary = EmployeeDataChecker.enterSalary();

            System.out.print("Enter job: ");
            Job job = EmployeeDataChecker.enterJob();

            em.insert(new Employee(name, surname, idnp, birthDate, salary, job));

            Utilities.clearScreen();

            System.out.println("INSERTED NEW EMPLOYEE:");
            int i = EmployeeManagerInFile.employees.size() - 1;
            System.out.println("\tid: " + EmployeeManagerInFile.employees.get(i).getId());
            System.out.println("\tname: " + EmployeeManagerInFile.employees.get(i).getName());
            System.out.println("\tsurname: " + EmployeeManagerInFile.employees.get(i).getSurname());
            System.out.println("\tbirthdate: " + EmployeeManagerInFile.employees.get(i).getBirthDateFormatted());
            System.out.println("\tidnp: " + EmployeeManagerInFile.employees.get(i).getIdnp());
            System.out.println("\tsalary: $" + EmployeeManagerInFile.employees.get(i).getSalary());
            System.out.println("\tjob: " + Utilities.firstLetterUpperCase(EmployeeManagerInFile.employees.get(i).getJob().toString()));

            System.out.println("Enter 1 to add more, or any value to go back...");
            moreEmployees = EmployeeManagerInFile.sc.nextLine();
            moreEmployees = moreEmployees.trim();
        } while (moreEmployees.equals("1"));

    }

    public void delete() {
        //submenu
        int nav = -1;
        do {
            Utilities.clearScreen();

            System.out.println("Select delete method:");
            System.out.println();
            System.out.println("\t1. delete by id");
            System.out.println("\t2. delete by idnp");
            System.out.println("\t3. delete by name and surname");
            System.out.println();
            System.out.println("\t0. back");

            System.out.print("\nenter submenu number: ");
            try {
                nav = EmployeeManagerInFile.sc.nextInt();
            } catch (Exception e) {
                System.out.println("\nInvalid format, try again (ex: 1)");
                Utilities.enterAnyValueToContinue();
            } finally {
                EmployeeManagerInFile.sc.nextLine();
            }

            switch (nav) {
                case 1:
                    this.deleteById();
                    break;
                case 2:
                    this.deleteByIdnp();
                    break;
                case 3:
                    this.deleteByNameAndSurname();
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

    public Employee updateStatement(int employeeIndex) {
        System.out.println("Editing employee " + EmployeeManagerInFile.employees.get(employeeIndex).getName()
                + " " + EmployeeManagerInFile.employees.get(employeeIndex).getSurname() + " (id: "
                + EmployeeManagerInFile.employees.get(employeeIndex).getId() + " / idnp: "
                + EmployeeManagerInFile.employees.get(employeeIndex).getIdnp() + ")...");
        System.out.print("name: " + EmployeeManagerInFile.employees.get(employeeIndex).getName() + " -> ");
        String name = EmployeeDataChecker.enterValidString();
        name = name.trim();
        name = Utilities.firstLetterUpperCase(name);

        System.out.print("surname: " + EmployeeManagerInFile.employees.get(employeeIndex).getSurname() + " -> ");
        String surname = EmployeeDataChecker.enterValidString();
        surname = surname.trim();
        surname = Utilities.firstLetterUpperCase(surname);

        System.out.print("birth date: " + EmployeeManagerInFile.employees.get(employeeIndex).getBirthDateFormatted() + " -> ");
        LocalDate birthDate = EmployeeDataChecker.enterBirthDate();

        System.out.print("idnp: " + EmployeeManagerInFile.employees.get(employeeIndex).getIdnp() + " -> ");
        String idnp = EmployeeDataChecker.enterIdnp(EmployeeManagerInFile.employees.get(employeeIndex).getIdnp());

        System.out.print("salary: " + EmployeeManagerInFile.employees.get(employeeIndex).getSalary() + " -> ");
        double salary = EmployeeDataChecker.enterSalary();

        System.out.print("job: " + Utilities.firstLetterUpperCase(EmployeeManagerInFile.employees.get(employeeIndex).getJob().toString()) + " -> ");
        Job job = EmployeeDataChecker.enterJob();

        return new Employee(name, surname, idnp, birthDate, salary, job, false);
    }

    public void update() {
        //submenu
        int nav = -1;
        do {
            Utilities.clearScreen();

            System.out.println("Select update method:");
            System.out.println();
            System.out.println("\t1. update by id");
            System.out.println("\t2. update by idnp");
            System.out.println("\t3. update by name and surname");
            System.out.println();
            System.out.println("\t0. back");

            System.out.print("\nenter submenu number: ");
            try {
                nav = EmployeeManagerInFile.sc.nextInt();
            } catch (Exception e) {
                System.out.println("\nInvalid format, try again (ex: 1)");
                Utilities.enterAnyValueToContinue();
            } finally {
                EmployeeManagerInFile.sc.nextLine();
            }

            switch (nav) {
                case 1:
                    this.updateById();
                    break;
                case 2:
                    this.updateByIdnp();
                    break;
                case 3:
                    this.updateByNameAndSurname();
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

    private void updateByIdnp() {
        Utilities.clearScreen();

        System.out.print("Enter employee's idnp: ");
        String idnp = sc.nextLine();
        idnp = idnp.trim();

        int employeeIndex = em.findByIdnp(idnp);
        if(employeeIndex >= 0) {
            Employee newEmployee = this.updateStatement(employeeIndex);
            em.update(employeeIndex, newEmployee);
            System.out.println("\nUpdated successfully!\n");
        } else {
            System.out.println("No such employee with indicated idnp (" + idnp + ").");
        }

        Utilities.enterAnyValueToContinue();
    }

    private void updateById() {
//        made a scanner here so we have less problems with reseting scanner
        Scanner scanner = new Scanner(System.in);

        Utilities.clearScreen();

        System.out.print("Enter employee's id: ");

        int id;
        do {
            try {
                id = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid id format, try again (ex: 123)");
                System.out.print("Enter id: ");
                scanner.nextLine();
            }
        } while (true);


        int employeeIndex = em.findById(id);
        if(employeeIndex >= 0) {
            Employee newEmployee = this.updateStatement(employeeIndex);
            em.update(employeeIndex, newEmployee);
            System.out.println("\nUpdated successfully!\n");
        } else {
            System.out.println("No such employee with indicated id (" + id + ").");
        }

        Utilities.enterAnyValueToContinue();
    }

    private void updateByNameAndSurname() {
        Utilities.clearScreen();

        System.out.print("Enter employee's name: ");
        String name = sc.nextLine();
        name = name.trim();
        System.out.print("Enter employee's surname: ");
        String surname = sc.nextLine();
        surname = surname.trim();

        int employeeIndex = em.findByNameAndSurname(name,surname);
        if(employeeIndex >= 0) {
            Employee newEmployee = this.updateStatement(employeeIndex);
            em.update(employeeIndex, newEmployee);
            System.out.println("\nUpdated successfully!\n");
        } else if (employeeIndex == -1) {
            System.out.println("No such employee with indicated name and surname (" + name + " " + surname + ")" +
                    ", verify name and surname before entering");
        } else {
            System.out.println("Too many employees with indicated name and surname " +
                    "(" + name + " " + surname + ")" + ", try other method.");
        }

        Utilities.enterAnyValueToContinue();
    }

    private void deleteMessage(Employee employee) {
        System.out.println("Employee " + employee.getName()
                + " " + employee.getSurname() /*+ " (id: "
                + employees.get(employeeIndex).getId() + " / idnp: "*/
                + " (idnp : " + employee.getIdnp() + ") was removed.");
    }

    private void deleteByIdnp() {
        Utilities.clearScreen();

        System.out.print("Enter employee's idnp: ");
        String idnp = sc.nextLine();
        idnp = idnp.trim();

        int employeeIndex = em.findByIdnp(idnp);
        if(employeeIndex >= 0) {
            Employee employeeToDelete = EmployeeManagerInFile.employees.get(employeeIndex);

            if (em.delete(employeeIndex)) {
                deleteMessage(employeeToDelete);
            }
        } else {
            System.out.println("No such employee with indicated idnp (" + idnp + ").");
        }

        Utilities.enterAnyValueToContinue();
    }

    private void deleteById() {
        //        made a scanner here so we have less problems with reseting scanner
        Scanner scanner = new Scanner(System.in);

        Utilities.clearScreen();

        System.out.print("Enter employee's id: ");

        int id;
        do {
//            System.out.print("Enter salary: ");
            try {
                id = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid id format, try again (ex: 123)");
                System.out.print("Enter id: ");
                scanner.nextLine();
            }
        } while (true);

        int employeeIndex = em.findById(id);
        if(employeeIndex >= 0) {
            Employee employeeToDelete = EmployeeManagerInFile.employees.get(employeeIndex);

            if (em.delete(employeeIndex)) {
                deleteMessage(employeeToDelete);
            }
        } else {
            System.out.println("No such employee with indicated id (" + id + ").");
        }

        Utilities.enterAnyValueToContinue();
    }

    private void deleteByNameAndSurname() {
        Utilities.clearScreen();

        System.out.print("Enter employee's name: ");
        String name = sc.nextLine();
        name = name.trim();
        System.out.print("Enter employee's surname: ");
        String surname = sc.nextLine();
        surname = surname.trim();

        int employeeIndex = em.findByNameAndSurname(name, surname);
        if(employeeIndex >= 0) {
            Employee employeeToDelete = EmployeeManagerInFile.employees.get(employeeIndex);

            if (em.delete(employeeIndex)) {
                deleteMessage(employeeToDelete);
            }
        } else if(employeeIndex == -2) {
            System.out.println("Too many employees with indicated name and surname " +
                    "(" + name + " " + surname + ")" + ", try other method.");
        } else {
            System.out.println("No such employee with indicated name and surname (" + name + " " + surname + ")" +
                    ", verify name and surname before entering");
        }

        Utilities.enterAnyValueToContinue();
    }

    public void close() {
        System.out.println("Closing application...");
        em.close();
    }
}
