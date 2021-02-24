package com.example.HibernateDemo.Service.impl;

import com.example.HibernateDemo.Entity.Department;
import com.example.HibernateDemo.Entity.Employee;
import com.example.HibernateDemo.Service.EmployeeInterface;
import com.example.HibernateDemo.dto.EmployeeRequestDto;
import com.example.HibernateDemo.dto.EmployeeResponseDto;
import com.example.HibernateDemo.repository.DepartmentRepository;
import com.example.HibernateDemo.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceimpl implements EmployeeInterface {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto)
    {
        Employee employee = new Employee();
        //copy fields from dto to employee
        BeanUtils.copyProperties(employeeRequestDto,employee);
        //fetch deapartment from database
        Optional <Department> departmentOptional = departmentRepository.findById(employee.getDepartment().getId());
        //save employee to db
        if(departmentOptional.isPresent())
        {
            employee.setDepartment(departmentOptional.get());
        }
        else
        {
            Department department = new Department();
            department.setName(employeeRequestDto.getDepartment().getName());
            employee.setDepartment(department);
        }
        //save employee to database
        Employee savedEmployee = employeeRepository.save(employee);
        //copy from employee to response dto
        EmployeeResponseDto responseDto = new EmployeeResponseDto();
        BeanUtils.copyProperties(savedEmployee,responseDto);
        responseDto.setDepartmentFromEntity(employee.getDepartment());
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
            responseDto.setDepartmentFromEntity(employeeOptional.get().getDepartment());
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
            //fetch department from db
            Optional<Department> departmentOptional = departmentRepository.findById(employeeRequestDto.getDepartment().getId());
            if(departmentOptional.isPresent())
            {
                employeeFromDb.setDepartment(employeeRequestDto.getDepartment());
            }
            else
            {
                Department department = new Department();
                department.setName(employeeRequestDto.getDepartment().getName());
                employeeFromDb.setDepartment(department);
            }
             Employee savedEmployee = employeeRepository.save(employeeFromDb);
             EmployeeResponseDto responseDto = new EmployeeResponseDto();
             BeanUtils.copyProperties(savedEmployee,responseDto);
            return responseDto;
        }

       return null;
    }
    @Override
    public EmployeeResponseDto deleteEmployeeById(long id)
    {
        Optional<Employee> employeeOptional =  employeeRepository.findById(id);
        if(employeeOptional.isPresent())
        {
            Employee employeeFromDb = employeeOptional.get();
            EmployeeResponseDto responseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(employeeFromDb,responseDto);
            responseDto.setDepartmentFromEntity(employeeFromDb.getDepartment());
            employeeRepository.deleteById(id);
            return responseDto;
        }
        return null;
    }

    @Override
    public List<EmployeeResponseDto> getEmployeeListByDepartment(long id) {
        List<Employee> employeeList = employeeRepository.getEmployeeListByNativeQuery(id);
        List<EmployeeResponseDto> employeeResponseDtoList = new ArrayList<>();
        for(Employee employee:employeeList)
        {
            EmployeeResponseDto  responseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(employee,responseDto);
            responseDto.setDepartmentFromEntity(employee.getDepartment());
            employeeResponseDtoList.add(responseDto);

        }
        return employeeResponseDtoList;
    }
}
