package com.example.HibernateDemo.repository;

import com.example.HibernateDemo.Entity.Department;
import com.example.HibernateDemo.Entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  EmployeeRepository extends CrudRepository<Employee,Long> {

    //by method name
    List<Employee> findByDepartment(Department department);

    List<Employee> findByDepartment_Id(Long departmentId);

    //by @Query annotation
    @Query("SELECT e FROM interns e WHERE e.department.id = ?1")
//  @Query("FROM Employee e WHERE e.department.id = ?1")
    List<Employee> getEmployeeListByDepartmentId(Long departmentId);

    @Query("SELECT e FROM interns e WHERE e.department.id = :departmentId")
        //  @Query("FROM Employee e WHERE e.department.id = ?1")
    List<Employee> getEmployeeListByDepartmentIdParam(@Param("departmentId") Long departmentId);



    //by native query

    @Query(value = "SELECT * FROM interns e WHERE e.department_id = ?1",
            nativeQuery = true)

    List<Employee> getEmployeeListByNativeQuery(Long departmentId);

    @Query(value ="SELECT * FROM interns e WHERE e.years_of_experience=(SELECT MAX(years_of_experience) FROM interns e )" ,
            nativeQuery = true)
    List<Employee> getExperienceByNativeQuery();

    @Query(value ="SELECT * FROM interns e INNER JOIN department d ON e.department_id=d.id WHERE (e.years_of_experience=(SELECT MAX(years_of_experience) FROM interns e WHERE e.department_id=?1) AND d.id=?1)" ,
            nativeQuery = true)
    List<Employee> getExperienceByNativeQuery(Long departmentId);
}
