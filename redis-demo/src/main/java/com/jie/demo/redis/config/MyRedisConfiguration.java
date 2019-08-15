package com.jie.demo.redis.config;

import com.jie.demo.redis.bo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class MyRedisConfiguration {

//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private RedisTemplate redisTemplate;

    @Primary
    @Bean
    public RedisCacheManager empCacheManager() {
        RedisTemplate<Object, Employee> temp = createRedisTemplate(Employee.class);
        RedisCacheManager cacheManager = new RedisCacheManager(temp);
        cacheManager.setUsePrefix(true);
        return cacheManager;
    }

    private <T> RedisTemplate<Object, T> createRedisTemplate(Class<T> clazz) {
        RedisTemplate<Object, T> template = new RedisTemplate<>();
        template.setConnectionFactory(redisTemplate.getConnectionFactory());

        Jackson2JsonRedisSerializer<T> jackson2Parser = new Jackson2JsonRedisSerializer<>(clazz);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(jackson2Parser);
        template.setHashValueSerializer(jackson2Parser);
        template.afterPropertiesSet();
        return template;
    }
}
