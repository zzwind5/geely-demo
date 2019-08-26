package com.example.demo.controller;

import com.example.demo.entities.Employee;
import com.example.demo.mapper.EmployeeMapper;
import com.example.demo.service.impl.EmployeeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeImpl empImpl;

    @GetMapping("/emp/all")
    public List<Employee> getAll() {
        return empImpl.getAll();
    }

    @GetMapping("/emp/add")
    public String addNew() {
        empImpl.addNew();
        return "Success";
    }

    @GetMapping("/emp/update")
    public String updateAge() {
        empImpl.addAge();
        return "Success Age";
    }
}
