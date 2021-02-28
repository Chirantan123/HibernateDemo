package com.example.HibernateDemo.Service;

import com.example.HibernateDemo.Entity.Employee;
import com.example.HibernateDemo.dto.EmployeeRequestDto;
import com.example.HibernateDemo.dto.EmployeeResponseDto;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface EmployeeInterface {
    EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto getEmployeeById(Long id);

    EmployeeResponseDto updateEmployeeById(Long id, EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto deleteEmployeeById(Long id);

    List<EmployeeResponseDto> getEmployeeListByDepartment(Long departmentId);

    List<EmployeeResponseDto> mostExperiencedEmployee();
}