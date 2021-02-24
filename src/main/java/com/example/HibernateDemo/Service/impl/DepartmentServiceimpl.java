package com.example.HibernateDemo.Service.impl;


import com.example.HibernateDemo.Entity.Department;
import com.example.HibernateDemo.Entity.Employee;
import com.example.HibernateDemo.Service.DepartmentInterface;
import com.example.HibernateDemo.dto.DepartmentRequestDto;
import com.example.HibernateDemo.dto.DepartmentResponseDto;
import com.example.HibernateDemo.dto.EmployeeResponseDto;
import com.example.HibernateDemo.repository.DepartmentRepository;
import com.example.HibernateDemo.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceimpl implements DepartmentInterface {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    @Override
    public Department getDepartmentById(Long id)
    {
       /*Optional<Department> departmentOptional = departmentRepository.findById(id);
        if(departmentOptional.isPresent())
       {
           DepartmentResponseDto responseDto = new DepartmentResponseDto();
          BeanUtils.copyProperties(departmentOptional.get(),responseDto);
          return responseDto;
        }
      return null;*/
      return departmentRepository.findById(id).get();
   }

    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto departmentRequestDto)
    {
        Department department = new Department();
        BeanUtils.copyProperties(departmentRequestDto,department);
        Department savedDepartment = departmentRepository.save(department);
        DepartmentResponseDto responseDto = new DepartmentResponseDto();
        BeanUtils.copyProperties(savedDepartment,responseDto);
        return responseDto;
    }

    @Override
    public DepartmentResponseDto updateDepartment(long departmentId,DepartmentRequestDto departmentRequestDto)
    {
        Department department = departmentRepository.findById(departmentId).get();
        List<Employee> employeeList  =  employeeRepository.findByDepartment_Id(departmentId);
        //update department
        department.setName(departmentRequestDto.getName());
        Department savedDepartment = departmentRepository.save(department);
        DepartmentResponseDto responseDto = new DepartmentResponseDto();
        BeanUtils.copyProperties(savedDepartment,responseDto);
        return responseDto;
    }
}
