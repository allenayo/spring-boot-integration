package com.cjp.sbr.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(final String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(final String key, Object value, Long expiredTime) {
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, expiredTime, TimeUnit.SECONDS);
    }

    public boolean hasKey(final String key) {
        return redisTemplate.persist(key);
    }

    public Long getExpiredTime(final String key) {
        return redisTemplate.getExpire(key);
    }

    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Set<String> listKeys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    public Set<String> listAllKey() {
        return listKeys("*");
    }

    public void del(final String key) {
        redisTemplate.delete(key);
    }

    public void delBatch(Collection<String> keys) {
        redisTemplate.delete(keys);
    }
}