package com.example.HibernateDemo.Service.impl;

import com.example.HibernateDemo.Entity.Employee;
import com.example.HibernateDemo.Service.EmployeeInterface;
import com.example.HibernateDemo.dto.EmployeeRequestDto;
import com.example.HibernateDemo.dto.EmployeeResponseDto;
import com.example.HibernateDemo.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceimpl implements EmployeeInterface {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto)
    {
        Employee employee = new Employee();
        //copy fields from dto to employee
        BeanUtils.copyProperties(employeeRequestDto,employee);
        //save employee to db
        Employee savedEmployee = employeeRepository.save(employee);
        //copy from employee to response dto
        EmployeeResponseDto responseDto = new EmployeeResponseDto();
        BeanUtils.copyProperties(savedEmployee,responseDto);
        return responseDto;
    }
    @Override
    public EmployeeResponseDto getEmployeeById(long id){
        Optional<Employee> employeeOptional =  employeeRepository.findById((id));
        if(employeeOptional.isPresent())
        {
            //copy from employee to response dto
            //EmployeeResponseDto responseDto = new EmployeeResponseDto();
            //BeanUtils.copyProperties(employeeOptional.get(),responseDto);
            //Employee employeeFromDb = employeeOptional.get();
            //employeeFromDb.setName(em.getName());
            //employeeFromDb.setDepartmentName(employeeRequestDto.getDepartmentName());
            //Employee savedEmployee = employeeRepository.save(employeeFromDb);
            EmployeeResponseDto responseDto=new EmployeeResponseDto();
            BeanUtils.copyProperties(employeeOptional.get(),responseDto);

            return responseDto;
        }
        return null;
    }
    @Override
    public EmployeeResponseDto updateEmployeeById(long id,EmployeeRequestDto employeeRequestDto)
    {
        Optional<Employee>  employeeOptional=employeeRepository.findById(id);

        if(employeeOptional.isPresent())
        {
            Employee employeeFromDb = employeeOptional.get();
            employeeFromDb.setName(employeeRequestDto.getName());
            employeeFromDb.setDepartmentName(employeeRequestDto.getDepartmentName());
            Employee savedEmployee = employeeRepository.save(employeeFromDb);
            EmployeeResponseDto responseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(savedEmployee,responseDto);
            return responseDto;
        }

       return null;
    }
}
