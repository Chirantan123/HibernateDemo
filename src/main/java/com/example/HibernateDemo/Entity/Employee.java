package com.example.HibernateDemo.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "interns")
@Getter
@Setter
public class Employee {

    @Id
    @GenericGenerator(name = "employee_id_seq", strategy = "increment")
    @GeneratedValue(generator = "employee_id_seq", strategy = GenerationType.AUTO)
    private Long id;

    private  String name;
    //private String departmentName;

    private Long YearsOfExperience;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Department department;

    public void setCode(String departmentCode) {
    }
}
