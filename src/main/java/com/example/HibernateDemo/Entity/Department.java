package com.example.HibernateDemo.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity(name="department")
@Getter
@Setter
public class Department {
    @Id
    @GenericGenerator(name="department_id_seq",strategy="increment")
    @GeneratedValue(generator = "department_id_seq",strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @JoinColumn(referencedColumnName = "id",name = "department_Id")
    @OneToMany
    List<Employee> employeeList;
}
