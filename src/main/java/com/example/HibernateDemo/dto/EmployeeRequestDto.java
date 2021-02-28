package com.example.HibernateDemo.dto;


import com.example.HibernateDemo.Entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDto {
    private Long  id;
    private String name;
    private String code;
    private Long YearsOfExperience;
    private DepartmentRequestDto department;

}
