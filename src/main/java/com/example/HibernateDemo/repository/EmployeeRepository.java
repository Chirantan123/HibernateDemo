package com.example.HibernateDemo.repository;

import com.example.HibernateDemo.Entity.Department;
import com.example.HibernateDemo.Entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface  EmployeeRepository extends CrudRepository<Employee,Long> {

    //1st method
    List<Employee> findByDepartment(Department department);

    //2nd method
    List<Employee> findByDepartment_Id(Long departmentId);

    //query annotation
    @Query("FROM interns e WHERE e.id=?1")
    List<Employee> getEmployeeListByDepartment(Long departmentId);


    @Query(value="Select * from interns e WHERE e.id=?1",nativeQuery = true)
    List<Employee> getEmployeeListByNativeQuery(Long departmentId);
}
