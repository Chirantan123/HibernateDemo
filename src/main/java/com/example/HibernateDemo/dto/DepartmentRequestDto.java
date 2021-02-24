package com.example.HibernateDemo.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequestDto {
    private long id;
    private String name;
    private DepartmentRequestDto departmentRequestDto;
}
