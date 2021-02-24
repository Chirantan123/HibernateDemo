package com.example.HibernateDemo.Controller;

import com.example.HibernateDemo.Entity.Employee;
import com.example.HibernateDemo.Service.EmployeeInterface;
import com.example.HibernateDemo.Service.impl.EmployeeServiceimpl;
import com.example.HibernateDemo.dto.EmployeeRequestDto;
import com.example.HibernateDemo.dto.EmployeeResponseDto;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


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

    @DeleteMapping(value = "/{id}")
    public EmployeeResponseDto deleteEmployee(@PathVariable("id") long id)
    {
      return employeeInterface.deleteEmployeeById(id);
    }

    @GetMapping("department/{id}")
    public List<EmployeeResponseDto> getEmployeeListByDepartment(long id)
    {
        return employeeInterface.getEmployeeListByDepartment(id);
    }

}
