package com.springboot.cruddemo.dao;

import com.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        Query query = entityManager.createQuery("from Employee");
        List<Employee> employees = query.getResultList();
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        Employee employee = entityManager.find(Employee.class, theId);
        return employee;
    }

    @Override
    public void save(Employee theEmployee) {
        Employee employee = entityManager.merge(theEmployee);
        theEmployee.setId(employee.getId());
    }

    @Override
    public void deleteById(int theId) {
        Query query = entityManager.createQuery("delete from Employee where id=:empId");
        query.setParameter("empId" , theId);
        query.executeUpdate();
    }
}
