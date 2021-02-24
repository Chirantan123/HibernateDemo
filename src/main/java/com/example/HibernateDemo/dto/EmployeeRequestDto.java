package com.example.HibernateDemo.dto;


import com.example.HibernateDemo.Entity.Department;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequestDto {
    private long  id;
    private String name;
    private Department department;

}
