package com.example.demo.aop;

import com.example.demo.util.DBContextHolder;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.example.demo.annotation.Master) && (" +
                "execution(* com.example.demo.mapper..*.get*(..))" +
                " || execution(* com.example.demo.mapper..*.select*(..))" +
                ")"
    )
    public void readPointCut() {

    }

    @Pointcut("@annotation(com.example.demo.annotation.Master)" +
                " || execution(* com.example.demo.mapper..*.add*(..))" +
                " || execution(* com.example.demo.mapper..*.insert*(..))" +
                " || execution(* com.example.demo.mapper..*.update*(..))"
    )
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
        if (DBContextHolder.get() != null){
            System.out.println("read");
            DBContextHolder.slave();
        }
    }

    @After("readPointCut()")
    public void clearRead() {
        System.out.println("clear read");
        DBContextHolder.clear();
    }

    @Before("writePointcut()")
    public void write() {
        if (DBContextHolder.get())
        System.out.println("write");
        DBContextHolder.master();
    }

    @After("writePointcut()")
    public void clearWrite() {
        System.out.println("clear write");
        DBContextHolder.clear();
    }
}
