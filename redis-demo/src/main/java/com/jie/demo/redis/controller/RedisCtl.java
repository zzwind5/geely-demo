package com.jie.demo.redis.controller;

import com.jie.demo.redis.bo.Employee;
import com.jie.demo.redis.service.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class RedisCtl {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private Cache cache;

    @RequestMapping("/redis")
    public String getMsg() {
        return redisTemplate.opsForValue().get("kk");
    }

    @RequestMapping("/redis/{key}")
    public String getMsg(@PathVariable String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @RequestMapping("/cache")
    public Employee getCache() {
        return cache.getCache();
    }
}
