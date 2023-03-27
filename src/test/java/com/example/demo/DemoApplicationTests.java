package com.example.demo;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.GradeMapper;
import com.example.demo.service.GradeService;
import com.example.demo.service.impl.GradeServiceImpl;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.*;
import java.util.function.BiFunction;

import static org.mockito.Mockito.mock;


//@SpringBootTest
class DemoApplicationTests {

    @Resource
    GradeService gradeService;

    @Transactional
    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
        Solution solution = new Solution();
        solution.duplicateZeros(new int[]{8, 4, 5, 0, 0, 0, 0, 7});
    }


}

class Solution {
    public void duplicateZeros(int[] arr) {
        int i = 0;
        int top = 0;
        while (top < arr.length) {
            if (arr[i] == 0) {
                top++;
            }
            top++;
            i++;
        }
        if (top > arr.length) {
            top--;
            top--;
            arr[top--] = 0;
            i--;
        }
        while (i >= 0) {
            if (arr[i] == 0) {
                arr[top--] = 0;
                arr[top--] = 0;
            } else {
                arr[top--] = arr[i];
            }
            i--;
        }
    }
}








