package com.jie.demo;

import org.junit.Test;

public class Math {

    private static final int MAX_VALUE = 10000000;


    @Test
    public void test11() {
        for (int i = 1; i <= MAX_VALUE; i++) {
            if (isMatch2(i)) {
                break;
            }
        }
    }


    private boolean isMatch1(int value) {
        if (value < 9) {
            return false;
        }

        if (value % 9 != 0) {
            return false;
        }

        if ((value + 1) % 8 != 0) {
            return false;
        }

        if ((value + 2) % 7 != 0) {
            return false;
        }

        System.out.println(value);
        return true;
    }


    private boolean isMatch2(int value) {
        if (value < 3) {
            return false;
        }

        if (value % 3 != 0) {
            return false;
        }

        if ((value + 1) % 5 != 0) {
            return false;
        }

        if ((value + 2) % 7 != 0) {
            return false;
        }

        if ((value + 3) % 9 != 0) {
            return false;
        }

        System.out.println(value);
        return true;
    }

    private boolean isMatch3(int value) {
        int ss = value/9;
        int yy = value%9;

        if (ss > 0 && ss == yy) {
            System.out.println(value);
            return true;
        }

        return false;
    }

}
