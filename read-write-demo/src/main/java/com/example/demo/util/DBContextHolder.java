package com.example.demo.util;

public class DBContextHolder {

    private static final int SLAVE_COUNT = 2;

    private static ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();
    private static ThreadLocal<Boolean> annoHolder = new ThreadLocal<>();

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static boolean isMasterAnno() {
        return annoHolder.get() != null && annoHolder.get() == true;
    }

    public static void master(boolean isAnno) {
        contextHolder.set(DBTypeEnum.MASTER);
        annoHolder.set(isAnno ? true : false);
        System.out.println("切换到了master");
    }

    public static void slave() {
        long tid = Thread.currentThread().getId();
        int index = (int)(tid % SLAVE_COUNT);

        if (index == 0) {
            contextHolder.set(DBTypeEnum.SLAVE_1);
            System.out.println("切换到了slave_1");
        } else {
            contextHolder.set(DBTypeEnum.SLAVE_2);
            System.out.println("切换到了slave_2");
        }
    }

    public static void clear() {
        contextHolder.remove();
        annoHolder.remove();
    }
}
