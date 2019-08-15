package com.jie.demo.utdemo;

import com.jie.demo.utdemo.model.Employee;
import com.jie.demo.utdemo.resp.EmployeeResp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UtDemoApplicationTests {

    private Employee emp;

    @Autowired
    private EmployeeResp empRep;

    @Before
    public void init() {
        Employee employee = new Employee();
        employee.setName("li4");
        employee.setAge(20);
        emp = empRep.save(employee);
    }

    @Test
    public void queryTest() {
        Employee one = empRep.getOne(emp.getId());
        Assert.assertEquals("li4", one.getName());
        Assert.assertEquals(Integer.valueOf(20), one.getAge());
    }

    @Test
    public void updateTest() {
        Employee one = empRep.getOne(emp.getId());
        one.setAge(30);
        empRep.save(one);

        Employee two = empRep.getOne(emp.getId());
        Assert.assertEquals("li4", two.getName());
        Assert.assertEquals(Integer.valueOf(30), two.getAge());
    }

    @Test
    public void deleteTest() {
        empRep.deleteById(emp.getId());

        Optional<Employee> one = empRep.findById(emp.getId());
        Assert.assertFalse(one.isPresent());
    }

    @Test
    public void contextLoads() {
    }

}
