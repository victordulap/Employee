package com.step.data.employee.manager;

import com.step.data.employee.Employee;

import java.util.ArrayList;
import java.util.List;

// so we access employees through it in EmployeeShowInConsoleManager
public abstract class EmployeeManager implements IEmployeeManager {
    public static List<Employee> employees = new ArrayList<>();
}
