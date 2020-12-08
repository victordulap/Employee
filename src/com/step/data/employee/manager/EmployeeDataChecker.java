package com.step.data.employee.manager;

import com.step.data.employee.Employee;
import com.step.data.employee.Job;
import com.step.data.menu.Utilities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EmployeeDataChecker {

    public static LocalDate enterBirthDate() {
        Scanner sc = new Scanner(System.in);

        LocalDate birthDate;
        do {
            System.out.println("Enter birth date in format dd.MM.yyyy (ex: 31.01.1999)");
            System.out.print("birth date: ");
            String date = sc.nextLine();
            date = date.trim();

            try {
                birthDate = LocalDate.parse(date, EmployeeManagerInFile.dateTimeFormatter);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date. please try again (ex: 31.01.1999 (dd.MM.yyyy))");
                Utilities.enterAnyValueToContinue();
            }
        } while (true);

        return birthDate;
    }

    public static double enterSalary() {
        Scanner sc = new Scanner(System.in);

        double salary;

        do {
//            System.out.print("Enter salary: ");
            try {
                salary = sc.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Invalid salary format, try again (ex: 9999.9)");
                System.out.print("Enter salary: ");
                // reset scanner's line so it work properly,
                // it doesn't reset on any scanner nextXxx() methods other than nextLine()
                sc.nextLine();
            }

        } while (true);

        return salary;
    }

    public static String enterIdnp() {
        Scanner sc = new Scanner(System.in);

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
                for (Employee employee : EmployeeManagerInFile.employees) {
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

    public static String enterIdnp(String prevIdnp) {
        Scanner sc = new Scanner(System.in);

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
                for (Employee employee : EmployeeManagerInFile.employees) {
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

    public static Job enterJob() {
        Scanner sc = new Scanner(System.in);

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

    public static String enterValidString() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String str = sc.nextLine();
            str = str.trim();
            if (!str.equalsIgnoreCase("")) {
                return str;
            }
        }
    }
}
