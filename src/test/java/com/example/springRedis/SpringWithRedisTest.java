package com.example.springRedis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class SpringWithRedisTest {

    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void saveValue() throws Exception {
        //given

        //when

        //then
    }

}
