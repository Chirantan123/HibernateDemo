package com.example.HibernateDemo.repository;

import com.example.HibernateDemo.Entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface  EmployeeRepository extends CrudRepository<Employee,Long> {
}
