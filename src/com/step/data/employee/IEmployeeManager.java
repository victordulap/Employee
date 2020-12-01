package com.step.data.employee;

import java.util.List;

public interface IEmployeeManager {
    public int insert(Employee e);
    public int update(Employee e);
    public List<Employee> read();
    public int deleteByIdnp(Employee e);
    public void deleteByName();
    public void deleteAllEmployees();
    public void close();
}
