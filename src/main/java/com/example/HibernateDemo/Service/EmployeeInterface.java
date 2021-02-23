package com.example.HibernateDemo.Service;

import com.example.HibernateDemo.Entity.Employee;
import com.example.HibernateDemo.dto.EmployeeRequestDto;
import com.example.HibernateDemo.dto.EmployeeResponseDto;
import org.springframework.web.bind.annotation.PathVariable;

public interface EmployeeInterface {
    EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto);
    EmployeeResponseDto getEmployeeById(long id);

    EmployeeResponseDto updateEmployeeById(long id, EmployeeRequestDto employeeRequestDto);
}
