package com.example.HibernateDemo.Service;

import com.example.HibernateDemo.Entity.Department;
import com.example.HibernateDemo.dto.DepartmentRequestDto;
import com.example.HibernateDemo.dto.DepartmentResponseDto;
import com.example.HibernateDemo.dto.EmployeeResponseDto;

public interface DepartmentInterface {

    DepartmentResponseDto createDepartment(DepartmentRequestDto departmentRequestDto);


    DepartmentResponseDto updateDepartment(long id,DepartmentRequestDto departmentRequestDto);

     Department getDepartmentById(Long id);
 //
    //DepartmentResponseDto deleteDepartmentById(long id);
}
