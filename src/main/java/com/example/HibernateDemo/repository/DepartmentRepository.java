package com.example.HibernateDemo.repository;


import com.example.HibernateDemo.Entity.Department;
import com.example.HibernateDemo.Entity.Employee;
import com.example.HibernateDemo.dto.DepartmentResponseDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DepartmentRepository extends CrudRepository<Department,Long> {




}
