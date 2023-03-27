package com.example.demo.controller;

import com.example.demo.pojo.Student;
import com.example.demo.pojo.User;
import com.example.demo.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/redisTest")
public class TestController {
    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("/set")
    public Result set(@RequestBody User user) {
        redisTemplate.opsForValue().set("user", user);
        return Result.ok();
    }

    @GetMapping("/get/{key}")
    public Result get(@PathVariable("key") String key) {
        User user = (User) redisTemplate.opsForValue().get(key);
        log.info(String.valueOf(user));
        return Result.ok(user);
    }
}
