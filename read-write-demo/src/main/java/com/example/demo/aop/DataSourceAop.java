package com.example.demo.aop;

import com.example.demo.util.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {

    @Pointcut("execution(* com.example.demo.mapper..*.get*(..))" +
                " || execution(* com.example.demo.mapper..*.select*(..))")
    public void readPointCut() {

    }

    @Pointcut("execution(* com.example.demo.mapper..*.add*(..))" +
                " || execution(* com.example.demo.mapper..*.insert*(..))")
    public void writePointcut() {

    }
//    @Pointcut("!@annotation(com.example.demo.annotation.Master) " +
//            "&& (execution(* com.example.demo.service.impl..*.select*(..)) " +
//            "|| execution(* com.example.demo.service.impl..*.get*(..)))")
//    public void readPointCut() {
//
//    }

//    @Pointcut("@annotation(com.example.demo.annotation.Master) " +
//            "|| execution(* com.example.demo.service.impl..*.insert*(..)) " +
//            "|| execution(* com.example.demo.service.impl..*.add*(..)) " +
//            "|| execution(* com.example.demo.service.impl..*.update*(..)) " +
//            "|| execution(* com.example.demo.service.impl..*.edit*(..)) " +
//            "|| execution(* com.example.demo.service.impl..*.delete*(..)) " +
//            "|| execution(* com.example.demo.service.impl.EmployeeImpl.getAll(..)) " +
//            "|| execution(* com.example.demo.service.impl.EmployeeImpl.(..))")
//    public void writePointCut() {
//
//    }

    @Before("readPointCut()")
    public void read() {
        System.out.println("read");
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }
}
