package com.step.data.employee.manager.database;

import com.step.data.employee.Employee;
import com.step.data.employee.manager.EmployeeDataChecker;
import com.step.data.employee.manager.EmployeeManager;
import com.step.data.employee.manager.IEmployeeManager;

import java.util.List;

public class EmployeeManagerInDB extends EmployeeManager implements IEmployeeManager {
    public static EmployeeDAO dao = new EmployeeDAO();

    @Override
    public void insert(Employee e) {
        dao.insert(e);
    }

    @Override
    public int findByIdnp(String idnp) {
        return 0;
    }

    @Override
    public int findByNameAndSurname(String name, String surname) {
        return 0;
    }

    @Override
    public int findById(int id) {
        return 0;
    }

    @Override
    public List<Employee> filterById(int minId, int maxId) {
        return null;
    }

    @Override
    public boolean update(int employeeIndex, Employee newEmployee) {
        return false;
    }

    @Override
    public boolean delete(int employeeIndex) {
        return false;
    }

    @Override
    public void onCloseApp() {

    }

    @Override
    public void onOpenApp() {

    }
}
