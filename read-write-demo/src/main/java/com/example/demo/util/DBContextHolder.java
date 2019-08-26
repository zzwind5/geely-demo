package com.example.demo.util;

import java.util.concurrent.atomic.AtomicInteger;

public class DBContextHolder {

    private static ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    private static AtomicInteger counter = new AtomicInteger();

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void set(DBTypeEnum dbType) {
        contextHolder.set(dbType);
    }

    public static void master() {
        set(DBTypeEnum.MASTER);
        System.out.println("切换到了master");
    }

    public static void slave() {
        int index = counter.getAndIncrement() % 2;

        if (counter.get() == Integer.MAX_VALUE) {
            counter.set(0);
        }

        if (index == 0) {
            set(DBTypeEnum.SLAVE_1);
            System.out.println("切换到了slave_1");
        } else {
            set(DBTypeEnum.SLAVE_2);
            System.out.println("切换到了slave_2");
        }
    }

    public static void clear() {
        contextHolder.remove();
    }
}
