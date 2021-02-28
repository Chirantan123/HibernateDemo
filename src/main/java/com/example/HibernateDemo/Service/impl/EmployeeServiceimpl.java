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
public class EmployeeServiceimpl<employeeResponseDtolist> implements EmployeeInterface {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee employee = new Employee();

        //copy fields from dto to employee
        BeanUtils.copyProperties(employeeRequestDto, employee);

        //fetch department from db
        Optional<Department> departmentOptional =
                departmentRepository.findById(employeeRequestDto.getDepartment().getId());
        if (departmentOptional.isPresent()){
            employee.setDepartment(departmentOptional.get());
        }else {
            Department department = new Department();
            department.setName(employeeRequestDto.getDepartment().getName());
            employee.setDepartment(department);
        }

        //save employee to db
        Employee savedEmployee = employeeRepository.save(employee);

        // copy from employee to response dto
        EmployeeResponseDto responseDto = new EmployeeResponseDto();
        BeanUtils.copyProperties(savedEmployee, responseDto);

        responseDto.setDepartmentFromEntity(employee.getDepartment());

        return responseDto;
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()){

            // copy from employee to response dto
            EmployeeResponseDto responseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(employeeOptional.get(), responseDto);
            responseDto.setDepartmentFromEntity(employeeOptional.get().getDepartment());
            return responseDto;
        }
        return null;
    }

    @Override
    public EmployeeResponseDto updateEmployeeById(Long id, EmployeeRequestDto employeeRequestDto) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()){
            //update employee
            Employee employeeFromDb = employeeOptional.get();
            employeeFromDb.setName(employeeRequestDto.getName());

            //fetch department from db
            Optional<Department> departmentOptional =
                    departmentRepository.findById(employeeRequestDto.getDepartment().getId());
            if (departmentOptional.isPresent()){
                employeeFromDb.setDepartment(departmentOptional.get());
            }else {
                Department department = new Department();
                department.setName(employeeRequestDto.getDepartment().getName());
                employeeFromDb.setDepartment(department);
            }

            //save to db
            Employee savedEmployee = employeeRepository.save(employeeFromDb);

            //copy from employee to response dto
            EmployeeResponseDto responseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(savedEmployee, responseDto);

            responseDto.setDepartmentFromEntity(savedEmployee.getDepartment());

            return responseDto;
        }
        return null;
    }

    @Override
    public EmployeeResponseDto deleteEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()){
            Employee employeeFromDb = employeeOptional.get();

            //delete employee from db
            employeeRepository.delete(employeeFromDb);

            EmployeeResponseDto responseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(employeeFromDb, responseDto);

            responseDto.setDepartmentFromEntity(employeeFromDb.getDepartment());
            return responseDto;
        }
        return null;
    }

    @Override
    public List<EmployeeResponseDto> getEmployeeListByDepartment(Long departmentId) {
//    Department department = departmentRepository.findById(departmentId).get();
//    List<Employee> employeeList = employeeRepository.findByDepartment(department);

//    List<Employee> employeeList = employeeRepository.findByDepartment_Id(departmentId);

//    List<Employee> employeeList = employeeRepository.getEmployeeListByDepartmentId(departmentId);
        List<Employee> employeeList = employeeRepository.getEmployeeListByNativeQuery(departmentId);
        List<EmployeeResponseDto> employeeResponseDtoList = new ArrayList<>();
        for (Employee employee : employeeList){
            EmployeeResponseDto responseDto = new EmployeeResponseDto();
            BeanUtils.copyProperties(employee, responseDto);
            responseDto.setDepartmentFromEntity(employee.getDepartment());
            employeeResponseDtoList.add(responseDto);
        }
        return employeeResponseDtoList;
    }

    @Override
    public List<EmployeeResponseDto> mostExperiencedEmployee() {
        List<Employee> list= employeeRepository.getExperienceByNativeQuery();
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
    //@Override
    //public EmployeeResponseDto mostExperiencedEmployee() {
      //  return null;
    //}
}
