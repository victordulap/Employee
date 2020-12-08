package com.step.data.employee;

import java.util.List;

//nu afisam nimic prin aceste functii, doar lucram cu datele
public interface IEmployeeManager {
    void insert(Employee e);

    int findByIdnp(String idnp);

    int findByNameAndSurname(String name, String surname);

    int findById(int id);

    List<Employee> filterById(int minId, int maxId);

    boolean update(int employeeIndex, Employee newEmployee);

    boolean delete(int employeeIndex); // done

//    public List<Employee> read();

//    public void close();
}
