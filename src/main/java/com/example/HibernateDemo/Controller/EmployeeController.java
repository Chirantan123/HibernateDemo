package com.example.HibernateDemo.Controller;

import com.example.HibernateDemo.Service.EmployeeInterface;
import com.example.HibernateDemo.Service.impl.EmployeeServiceimpl;
import com.example.HibernateDemo.dto.EmployeeRequestDto;
import com.example.HibernateDemo.dto.EmployeeResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    //Post-/employee
    @Autowired
    private EmployeeInterface employeeInterface;

    @PostMapping
    public EmployeeResponseDto createEmployee(@RequestBody EmployeeRequestDto employeeRequestDto)
    {
        return employeeInterface.createEmployee(employeeRequestDto);
    }

    @GetMapping(value = "/{id}")
    public EmployeeResponseDto findById(@PathVariable("id") long id)
    {
        return employeeInterface.getEmployeeById(id);
    }

    @PutMapping(value="/{id}")
    public EmployeeResponseDto updateEmployee(@PathVariable("id") long id,@RequestBody EmployeeRequestDto employeeRequestDto) {
        return employeeInterface.updateEmployeeById(id, employeeRequestDto);
    }

}
