package com.example.HibernateDemo.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequestDto {
    private Long id;
    private String name;
    private String departmentCode;
}
