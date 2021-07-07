package com.cjp.sbr;

import com.cjp.sbr.entity.User;
import com.cjp.sbr.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootRedisApplicationTests {

    @Autowired
    private RedisService redisService;

    @Test
    void testSet() {
        redisService.set("aa", "bb");
    }

    @Test
    void testGet() {
        System.out.println(redisService.get("aa"));
    }

    @Test
    void testListAllKey() {
        redisService.listAllKey().forEach(System.out::println);
    }

    @Test
    void testDel() {
        redisService.del("aa");
    }

    @Test
    void testDelBatch() {
        redisService.delBatch(redisService.listAllKey());
    }

    @Test
    void testSetUser() {
        User user = new User(1L, "CJP", "CJP", "China", "Beijing", "Beijing");
        redisService.set("user", user);
    }

    @Test
    void testGetUser() {
        testSetUser();
        System.out.println(redisService.get("user"));
    }

    @Test
    void testSetExpiredTime() {
        redisService.set("aaa", "bbb", 300L);
    }

    @Test
    void testGetExpiredTime() {
        System.out.println(redisService.getExpiredTime("aaa"));
    }

}