package DAO;

import java.util.List;

import ENTITY.Employee;

public interface EmployeeDAO {
    void create(Employee emp);
    void update(Employee emp);
    void deleteById(String id);
    Employee findById(String id);
    List<Employee> findAll();
}

