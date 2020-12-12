package com.step.data.employee.manager.memory;

import com.step.data.employee.Employee;
import com.step.data.employee.manager.IEmployeeManager;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class EmployeeManagerInMemory implements IEmployeeManager {
    public static List<Employee> employees = new ArrayList<>();

    /**
     * @return  index in list: <br>
     *          -1, if, employee not found <br>
     *          0..n, if, employee found
     */
    @Override
    public int findByIdnp(String idnp) {
        int employeeIndex = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getIdnp().equals(idnp)) {
                employeeIndex = i;
                break;
            }
        }

        return employeeIndex;
    }

    /**
     * @return  index in list: <br>
     *          -2, if, Too many employees with indicated name and surname <br>
     *          -1, if, employee not found <br>
     *          0..n, if, employee found
     */
    @Override
    public int findByNameAndSurname(String name, String surname) {
        int countFoundEmployees = 0;
        int employeeIndex = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getName().equalsIgnoreCase(name) && employees.get(i).getSurname().equalsIgnoreCase(surname)) {
                countFoundEmployees++;
                if (countFoundEmployees > 1) {
                    employeeIndex = -2;
                    break;
                }
                employeeIndex = i;
            }
        }

        return employeeIndex;
    }

    /**
     * @return  index in list: <br>
     *          -1, if, employee not found <br>
     *          0..n, if, employee found
     */
    @Override
    public int findById(int id) {
        int employeeIndex = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                employeeIndex = i;
                break;
            }
        }

        return employeeIndex;
    }

    @Override
    public List<Employee> filterById(int minId, int maxId) {
        Map<Integer, Employee> employeeMap = new HashMap<>();

        for(Employee employee: employees) {
            employeeMap.put(employee.getId(), employee);
        }

        List<Employee> employeesFilteredById = new ArrayList<>();
        for(int id: employeeMap.keySet()) {
            if(id >= minId && id <= maxId) {
                employeesFilteredById.add(employeeMap.get(id));
            }
        }

        return employeesFilteredById;
    }

    /**
     * @param employeeIndex the index of employee to delete <br>
     * @return true - if deleted successfully <br>
     * false - if not deleted
     */
    @Override
    public boolean delete(int employeeIndex) {
        if (employeeIndex >= 0) {
            employees.remove(employeeIndex);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onCloseApp() {
        // no code cuz no writing to external sources
    }

    @Override
    public void onOpenApp() {
        // no code cuz no reading from external sources
    }

    /**
     * @param employeeIndex the index of employee to delete <br>
     * @param newEmployee the data of employee, to replace the old one
     * @return true - if deleted successfully <br>
     * false - if not deleted
     */
    public boolean update(int employeeIndex, Employee newEmployee) {
        if (employeeIndex >= 0) {
            employees.get(employeeIndex).setName(newEmployee.getName());
            employees.get(employeeIndex).setSurname(newEmployee.getSurname());
            employees.get(employeeIndex).setBirthDate(newEmployee.getBirthDate());
            employees.get(employeeIndex).setIdnp(newEmployee.getIdnp());
            employees.get(employeeIndex).setSalary(newEmployee.getSalary());
            employees.get(employeeIndex).setJob(newEmployee.getJob());
            return true;
        } else {
            return false;
        }
    }

    public void insert(Employee e) {
        employees.add(e);
    }
}