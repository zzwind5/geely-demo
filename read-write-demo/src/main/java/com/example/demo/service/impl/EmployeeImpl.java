package com.example.demo.service.impl;

import com.example.demo.annotation.Master;
import com.example.demo.entities.Employee;
import com.example.demo.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeImpl {

    @Autowired
    private EmployeeMapper mapper;

    public List<Employee> getAll() {
        return mapper.selectList(null);
    }

    public void addNew() {
        Employee employee = new Employee();
        String name = UUID.randomUUID().toString().substring(0, 6);
        employee.setLastName(name)
                .setEmail(name + "@126.com")
                .setGender('1')
                .setAge(28);

        mapper.insert(employee);
    }

    public void addAge() {
        Employee employee = mapper.selectById(1);
        System.out.println(employee);
        employee.setAge(employee.getAge() + 1);

        mapper.updateById(employee);
    }
}
