package com.step.data.employee;

import com.step.data.menu.Utilities;

import java.io.*;
import java.time.LocalDate;

public class EmployeeDataReader {
    //file
    //import
    protected static void importFromFile() {
        String path = ".\\data\\employees.txt"; // works
        File file = new File(path);
        if (!file.exists()) {
            try {
                boolean fileCreatedSuccessfully = file.createNewFile();
                System.out.println("There was no file before, so a new file was created.");
                Utilities.enterAnyValueToContinue();
                return;
            } catch (IOException e) {
                System.out.println("Undetected error on file creating process.");
            }
        }

        EmployeeDataManager.employees.clear();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
                EmployeeDataManager.employees.add(convertFromCSVToEmployee(line));
            }
        } catch (IOException e) {
            System.out.println("Undetected error on file reading process.");
        }

        System.out.println("Imported successfully!");
        Utilities.enterAnyValueToContinue();
    }
    //import end

    //export
    protected static void exportToFile() {
        String path = ".\\data\\employees.txt";
        File file = new File(path);
        if (!file.exists()) {
            try {
                boolean fileCreatedSuccessfully = file.createNewFile();
                System.out.println("No data, instead created employees.txt");
                return;
            } catch (IOException e) {
                System.out.println("Undetected error on file creating process.");
            }
        }

        try {
            FileWriter writer = new FileWriter(file);

            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Employee employee: EmployeeDataManager.employees) {
                bufferedWriter.write(convertFromEmployeeToCSV(employee) + "\n");
            }
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        System.out.println("Exported successfully!");
        Utilities.enterAnyValueToContinue();
    }
    //export end

    //csv
    private static String convertFromEmployeeToCSV(Employee employee) {
        return employee.getName() + "," +
                employee.getSurname() + "," +
                employee.getIdnp() + "," +
                employee.getBirthDate() + "," +
                employee.getSalary() + "," +
                employee.getJob();
    }

    private static Employee convertFromCSVToEmployee(String csvLine) {
        String[] parameters = csvLine.split(",");

        String name = parameters[0];
        String surname = parameters[1];
        String idnp = parameters[2];
        LocalDate birthDate = LocalDate.parse(parameters[3]);
        double salary = Double.parseDouble(parameters[4]);
        Job job = Job.valueOf(parameters[5]);

        return new Employee(name, surname, idnp, birthDate, salary, job);
    }
    //csv
    // file end
}
