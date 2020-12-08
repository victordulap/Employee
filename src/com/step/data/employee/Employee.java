package com.step.data.employee;

import com.step.data.menu.Utilities;

import java.io.*;
import java.time.LocalDate;

public class Employee implements Serializable {
    public static final long serialVersionUID = 2L;
    private static int lastId;
    private int id;
    private String name;
    private String surname;
    private String idnp;
    private LocalDate birthDate;
    private double salary;
    private Job job;

    public Employee(String name, String surname, String idnp, LocalDate birthDate, double salary, Job job) {
        lastId++;
        id = lastId;
        this.name = name;
        this.surname = surname;
        this.idnp = idnp;
        this.birthDate = birthDate;
        this.salary = salary;
        this.job = job;
    }

    /**
     * this constructor does not modify the id in any mode, used in updateStatement()
     *
     * @param modifyLastId the input (true or false) doesn't matter
     */
    public Employee(String name, String surname, String idnp, LocalDate birthDate, double salary, Job job, boolean modifyLastId) {
        this.name = name;
        this.surname = surname;
        this.idnp = idnp;
        this.birthDate = birthDate;
        this.salary = salary;
        this.job = job;
    }

    //getters
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    // setters
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    // end getters

    public String getBirthDateFormatted() {
        return birthDate.format(EmployeeManager.dateTimeFormatter);
    }

    protected String getIdnp() {
        return idnp;
    }

    public void setIdnp(String idnp) {
        this.idnp = idnp;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public int getId() {
        return id;
    }
    // end setters

    public static void saveLastIdToFile() {
        try {
            String path = ".\\data\\employeeLastId.dat"; // works
            File file = new File(path);
            if (!file.exists()) {
                try {
                    boolean fileCreatedSuccessfully = file.createNewFile();
                } catch (IOException e) {
                    System.out.println("Undetected error on file creating process.");
                    return;
                }
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(Employee.lastId);

            objectOutputStream.close();
            fileOutputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void readLastIdFromFile() {
        try {
            String path = ".\\data\\employeeLastId.dat"; // works
            File file = new File(path);
            if (!file.exists()) {
                try {
                    boolean fileCreatedSuccessfully = file.createNewFile();
                    return;
                } catch (IOException e) {
                    System.out.println("Undetected error on file creating process.");
                    return;
                }
            }

            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            Object o = objectInputStream.readObject();
            Employee.lastId = (int) o;

            objectInputStream.close();
            fileInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}