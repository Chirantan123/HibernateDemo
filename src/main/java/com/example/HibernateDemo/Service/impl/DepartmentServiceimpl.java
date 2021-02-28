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

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentServiceimpl implements DepartmentInterface {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto departmentRequestDto) {
        Department department = new Department();

        BeanUtils.copyProperties(departmentRequestDto, department);

        Department savedDepartment = departmentRepository.save(department);

        DepartmentResponseDto responseDto = new DepartmentResponseDto();
        BeanUtils.copyProperties(savedDepartment, responseDto);

        return responseDto;
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).get();
    }

    @Override
    public List<EmployeeResponseDto> getExperienceByDepartmentId(Long departmentId) {
        List<Employee> list= employeeRepository.getExperienceByNativeQuery(departmentId);
        List<EmployeeResponseDto> employeeResponseDtoList = new ArrayList<>();
        for(Employee employee:list)
        {
            EmployeeResponseDto responseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(employee, responseDto);
            responseDto.setDepartmentFromEntity(employee.getDepartment());
            employeeResponseDtoList.add(responseDto);
        }
        return employeeResponseDtoList;
    }

    @Override
    @Transactional
    public DepartmentResponseDto updateDepartment(Long departmentId,
                                                  DepartmentRequestDto departmentRequestDto) {
        Department department = departmentRepository.findById(departmentId).get();
        List<Employee> employeeList = employeeRepository.findByDepartment_Id(departmentId);

        //update department
        department.setName(departmentRequestDto.getName());
        Department savedDepartment = departmentRepository.save(department);

        //append departmentCode to employee code

        for (Employee employee : employeeList) {
            employee.setCode(departmentRequestDto.getDepartmentCode());
        }
        employeeRepository.saveAll(employeeList);


        DepartmentResponseDto responseDto = new DepartmentResponseDto();
        BeanUtils.copyProperties(savedDepartment, responseDto);
        return responseDto;
    }
}
