package com.jie.demo;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LockByCurator {/* 此demo使用的集群，所以有多个ip和端口*/
    private static String CONNECT_SERVER = "127.0.0.1:2181";/* session过期时间*/
    private static int SESSION_TIMEOUT = 3000;/* 连接超时时间*/
    private static int CONNECTION_TIMEOUT = 3000;/* 锁节点*/
    private static final String CURATOR_LOCK = "/dlock";

    /**
     * 获取锁操作 @param cf
     */
    public static void doLock(CuratorFramework cf) {
        InterProcessMutex mutex = new InterProcessMutex(cf, CURATOR_LOCK);
        try {
            mutex.acquire();
            System.out.println(Thread.currentThread().getName() + " 获取到了锁！-------");/* 业务操作*/
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {/* 释放锁*/
                mutex.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试 @param args
     */
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 15; i++) {
            service.execute(() -> {
                CuratorFramework framework = CuratorFrameworkFactory.newClient(CONNECT_SERVER, SESSION_TIMEOUT, CONNECTION_TIMEOUT, new RetryNTimes(10, 5000));/* 启动*/
                framework.start();
                doLock(framework);
            });
        }
        service.shutdown();
    }
}
