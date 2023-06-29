package com.example.springRedis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/helloRedis")
    public String HelloRedis() {
        return "Hello Redis!!";
    }
}
