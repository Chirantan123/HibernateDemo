package com.example.HibernateDemo.Controller;

import com.example.HibernateDemo.Entity.Department;
import com.example.HibernateDemo.Service.DepartmentInterface;
import com.example.HibernateDemo.Service.EmployeeInterface;
import com.example.HibernateDemo.dto.DepartmentRequestDto;
import com.example.HibernateDemo.dto.DepartmentResponseDto;
import com.example.HibernateDemo.dto.EmployeeResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping (value="/department")
public class DepartmentController {

    @Autowired
    private DepartmentInterface departmentInterface;

    //@GetMapping(value="/{id}")
    //public DepartmentResponseDto findById(@PathVariable("id") long id)
    //{
      //  return departmentInterface.getDepartmentById(id);
    //
    // }
    @PostMapping
    public DepartmentResponseDto createDepartment(@RequestBody DepartmentRequestDto departmentRequestDto) {
        return departmentInterface.createDepartment(departmentRequestDto);
    }
    @GetMapping(value="/{id}")
   public Department getDepartmentById(@PathVariable ("id") Long id)
    {
      return departmentInterface.getDepartmentById(id);
    }
    @PutMapping(value="/{id}")
    public DepartmentResponseDto updateDepartment(@PathVariable("id") Long departmentId,@RequestBody DepartmentRequestDto departmentRequestDto)
    {
        return departmentInterface.updateDepartment(departmentId,departmentRequestDto);
    }

    @GetMapping(value="/{id}/employee/mostExperienced")
    public List<EmployeeResponseDto> getExperienceByDepartmentId(@PathVariable("id") Long id)
    {
        return departmentInterface.getExperienceByDepartmentId(id);
    }
}
