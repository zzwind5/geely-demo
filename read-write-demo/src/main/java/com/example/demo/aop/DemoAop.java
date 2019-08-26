package com.example.demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: Jie Zhang
 * @Date: 2019/8/26 11:01
 * @Version 1.0
 */
@Aspect
@Component
public class DemoAop {

//    @Pointcut("execution(* com.example.demo.service.impl.EmployeeImpl.getAll(..))")
//    public void helloPointcut() {
//
//    }

//    @Pointcut("@annotation(com.jie.demo.annotation.Master)")
//    public void goodbyPointcut() {
//
//    }

//    @Before("helloPointcut()")
//    public void hello() {
//        System.out.println("Before getAll pointcut.......");
//    }

//    @Before("goodbyPointcut()")
//    public void goodby() {
//        System.out.println("Say Goodby.......");
//    }
}
