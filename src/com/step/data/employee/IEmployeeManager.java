package com.step.data.employee;

import java.util.List;

public interface IEmployeeManager {
    public int insert(Employee e);
    public int update(Employee e);
    public List<Employee> read();
    public int delete(Employee e);
    public void close();
}
