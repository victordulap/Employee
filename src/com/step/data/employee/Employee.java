package com.step.data.employee;

import com.sun.scenario.effect.impl.prism.PrImage;

import java.time.LocalDate;

public class Employee {
    private String name;
    private String surname;
    private String idnp;
    private LocalDate birthDate;
    private double salary;
    private Job job;

    public Employee(String name, String surname, String idnp, LocalDate birthDate, double salary, Job job) {
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

    public Job getJob() {
        return job;
    }

    //    public int getId() { return id; }
    //getters end
}