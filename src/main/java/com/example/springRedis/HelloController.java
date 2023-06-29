package com.example.springRedis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // 스프링에서제공하는 추상화된 redis template - String 을 가지고 작업
    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/helloRedis")
    public String HelloRedis() {
        return "Hello Redis!!";
    }
    
    @GetMapping("/setFruit")
    public String setFruit(@RequestParam String name) {
        ValueOperations<String, String> ops =  redisTemplate.opsForValue();
        ops.set("fruit", name);
        return "Saved,";
    }
    
    @GetMapping("/getFruit")
    public String getFruit() {
        ValueOperations<String, String> ops =  redisTemplate.opsForValue();
        String fruitName = ops.get("fruit");
        return fruitName;
    }
}
