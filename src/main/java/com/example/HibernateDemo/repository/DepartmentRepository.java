package com.example.HibernateDemo.repository;


import com.example.HibernateDemo.Entity.Department;
import com.example.HibernateDemo.Entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department,Long> {

}
