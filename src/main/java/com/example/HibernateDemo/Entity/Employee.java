package com.example.HibernateDemo.Entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;



@RedisHash(value="Employee")
@Getter
@Setter
public class Employee {

    private long id;

    private  String name;

    private String departmentName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
