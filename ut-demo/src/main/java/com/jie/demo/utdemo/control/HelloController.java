package com.jie.demo.utdemo.control;

import com.jie.demo.utdemo.model.Employee;
import com.jie.demo.utdemo.resp.EmployeeResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private EmployeeResp empRep;

    @GetMapping("/create")
    public String create() {
        Employee employee = new Employee();
        employee.setName("zhang3");
        employee.setAge(10);
        empRep.save(employee);
        return "Success";
    }
}
