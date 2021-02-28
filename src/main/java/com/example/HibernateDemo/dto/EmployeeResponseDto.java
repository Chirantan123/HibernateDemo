package com.example.HibernateDemo.dto;


import com.example.HibernateDemo.Entity.Department;
import com.example.HibernateDemo.repository.DepartmentRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDto {
    private Long id;

    private String name;

    private String code;

    private Long YearsOfExperience;
    private DepartmentResponseDto department;

    public void setDepartmentFromEntity(Department departmentEntity) {
        DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
        departmentResponseDto.setId(departmentEntity.getId());
        departmentResponseDto.setName(departmentEntity.getName());
        this.department=departmentResponseDto;
    }
}
