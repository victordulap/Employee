package com.step.data.employee;

import java.time.LocalDate;

public class Employee {
//    private int id; // DB id
    private String name;
    private String surname;
    private String idnp;
    private LocalDate birthDate;
    private double salary;
    private static int totalEmployees;

    public Employee(String name, String surname, String idnp, LocalDate birthDate, double salary) {
//        this.id = totalEmployees;
        totalEmployees++;
        this.name = name;
        this.surname = surname;
        this.idnp = idnp;
        this.birthDate = birthDate;
        this.salary = salary;
    }

    //getters
    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getBirthDateFormatted() {
        return birthDate.format(EmployeeDataManager.dateTimeFormatter);
    }

    protected String getIdnp() {
        return idnp;
    }

//    public int getId() { return id; }
    //getters end
}
