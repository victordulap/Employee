package com.step.data.employee;

import java.time.LocalDate;

public class Employee {
    private int id; // DB id
    private String name;
    private String surname;
    private String idnp;
    private LocalDate birthDate;
    private double salary;

    public Employee(int id, String name, String surname, String idnp, LocalDate birthDate, double salary) {
        this.id = id;
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

    protected String getIdnp() {
        return idnp;
    }

    public int getId() { return id; }
    //getters end

    //setters
    protected void setName(String name) {
        this.name = name;
    }

    protected void setSurname(String surname) {
        this.surname = surname;
    }

    protected void setIdnp(String idnp) {
        this.idnp = idnp;
    }

    protected void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    protected void setSalary(double salary) {
        this.salary = salary;
    }
    //setters end
}
