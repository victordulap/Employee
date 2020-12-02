package com.step.data.employee;

//nu afisam nimic prin aceste functii, doar lucram cu datele
public interface IEmployeeManager {
    void insert(Employee e); // done

    int findByIdnp(String idnp);

    //    public int update(Employee e);
    boolean updateByIdnp(String idnp); // done

    int updateByName(String name, String surname); // done

    Employee updateStatement(int employeeIndex);// almost done, TODO: need to re-do the update statement (it show stuff to console)

    boolean delete(int employeeIndex);
//    void deleteByName()
//    void deleteStatement(int employeeIndex)

//
//    public List<Employee> read();
//    public int deleteByIdnp(Employee e);
//    public void deleteByName();
//    public void deleteAllEmployees();
//    public void close();
}
