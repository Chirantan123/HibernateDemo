package com.example.HibernateDemo.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDto {
    private long id;

    private String name;

    private String departmentName;
}
