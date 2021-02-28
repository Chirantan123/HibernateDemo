package com.example.HibernateDemo.Service;

import com.example.HibernateDemo.Entity.Department;
import com.example.HibernateDemo.dto.DepartmentRequestDto;
import com.example.HibernateDemo.dto.DepartmentResponseDto;
import com.example.HibernateDemo.dto.EmployeeResponseDto;

import java.util.List;

public interface DepartmentInterface {

    DepartmentResponseDto createDepartment(DepartmentRequestDto departmentRequestDto);


    DepartmentResponseDto updateDepartment(Long departmentId,DepartmentRequestDto departmentRequestDto);

     Department getDepartmentById(Long id);
     List<EmployeeResponseDto> getExperienceByDepartmentId(Long departmentId);
 //
    //DepartmentResponseDto deleteDepartmentById(long id);
}
