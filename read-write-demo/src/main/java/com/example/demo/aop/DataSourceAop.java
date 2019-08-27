package com.example.demo.aop;

import com.example.demo.util.DBContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

//@Order(-99)
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.example.demo.annotation.Master) && (" +
                "execution(* com.example.demo.mapper..*.get*(..))" +
                " || execution(* com.example.demo.mapper..*.select*(..))" +
                ")"
    )
    public void readPointCut(){}

    @Pointcut("!@annotation(com.example.demo.annotation.Master) && (" +
                "execution(* com.example.demo.mapper..*.add*(..))" +
                " || execution(* com.example.demo.mapper..*.insert*(..))" +
                " || execution(* com.example.demo.mapper..*.update*(..))" +
                " || execution(* com.example.demo.mapper..*.edit*(..))" +
                " || execution(* com.example.demo.mapper..*.delete*(..))" +
                " || execution(* com.example.demo.mapper..*.remove*(..))" +
                ")"
    )
    public void writePointcut() {}

    @Pointcut("@annotation(com.example.demo.annotation.Master)")
    public void masterPointcut() {}

    @Around("masterPointcut()")
    public Object masterAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            DBContextHolder.master(true);
            Object obj = proceedingJoinPoint.proceed();
            return obj;
        } catch (Throwable t) {
            throw t;
        } finally {
            DBContextHolder.clear();
        }
    }

    @Before("readPointCut()")
    public void read() {
        if (DBContextHolder.isMasterAnno()) {
            return;
        }
        System.out.println("read");
        DBContextHolder.slave();
    }

    @After("readPointCut()")
    public void clearRead() {
        if (DBContextHolder.isMasterAnno()) {
            return;
        }
        System.out.println("clear read");
        DBContextHolder.clear();
    }

    @Before("writePointcut()")
    public void write() {
        if (DBContextHolder.isMasterAnno()) {
            return;
        }
        System.out.println("write");
        DBContextHolder.master(false);
    }

    @After("writePointcut()")
    public void clearWrite() {
        if (DBContextHolder.isMasterAnno()) {
            return;
        }
        System.out.println("clear write");
        DBContextHolder.clear();
    }
}
