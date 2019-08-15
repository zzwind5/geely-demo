package com.jie.demo.redis.service;

import com.jie.demo.redis.bo.Employee;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class Cache {

    @Cacheable(cacheNames="emp", key = "'jie'", cacheManager = "empCacheManager")
    public Employee getCache() {
        return new Employee("jie", 40);
    }
}
