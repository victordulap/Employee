package com.step.data.employee;

//nu afisam nimic prin aceste functii, doar lucram cu datele
public interface IEmployeeManager {
    void insert(Employee e); // done

    //    public int update(Employee e);
    boolean updateByIdnp(String idnp); // done

    int updateByName(String name, String surname); // done
//
//    public List<Employee> read();
//    public int deleteByIdnp(Employee e);
//    public void deleteByName();
//    public void deleteAllEmployees();
//    public void close();
}
