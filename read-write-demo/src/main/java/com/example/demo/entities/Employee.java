package com.example.demo.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("tbl_employee")
public class Employee {

    private int id;
    private String lastName;
    private String email;
    private char gender;
    private int age;
}
