package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Teacher;
import com.example.demo.service.TeacherService;
import com.example.demo.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/sms/teacherController")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    //http://localhost:8080/sms/teacherController/getTeachers/1/3
    @RequestMapping("/getTeachers/{pageNo}/{pageSize}")
    public Result getTeachers(@PathVariable("pageNo") Integer pageNo,
                              @PathVariable("pageSize") Integer pageSize,
                              @RequestParam(value = "name",required = false) String name,
                              @RequestParam(value = "clazzName",required = false) String clazzName){
        Page<Teacher> page = new Page<>(pageNo,pageSize);
        IPage<Teacher> iPage = teacherService.getTeachers(page,name,clazzName);
        return Result.ok(iPage);
    }

    //http://localhost:8080/sms/teacherController/saveOrUpdateTeacher
    @PostMapping("/saveOrUpdateTeacher")
    public Result saveOrUpdateTeacher(@RequestBody Teacher teacher){
        teacherService.saveOrUpdate(teacher);
        return Result.ok();
    }

    //http://localhost:8080/sms/teacherController/deleteTeacher
    @DeleteMapping("/deleteTeacher")
    public Result deleteTeacher(@RequestBody List<Integer> ids){
        teacherService.removeByIds(ids);
        return Result.ok();
    }
}
