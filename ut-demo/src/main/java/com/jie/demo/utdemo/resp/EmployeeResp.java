package com.jie.demo.utdemo.resp;

import com.jie.demo.utdemo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeResp extends JpaRepository<Employee, Integer> {

}
