package com.example.HibernateDemo.dto;


import com.example.HibernateDemo.Entity.Department;
import com.example.HibernateDemo.repository.DepartmentRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDto {
    private long id;

    private String name;

    private DepartmentResponseDto department;

    public DepartmentResponseDto setDepartmentFromEntity(Department departmentEntity) {
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        departmentResponseDto.setId(departmentEntity.getId());
        departmentResponseDto.setName(departmentEntity.getName());
        this.department=departmentResponseDto;
        return departmentResponseDto;
    }
}
