package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Student;
import com.example.demo.service.StudentService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sms/studentController")
public class StudentController {

    @Autowired
    StudentService studentService;
    //http://localhost:8080/sms/studentController/getStudentByOpr/1/3
    @GetMapping("/getStudentByOpr/{pageNo}/{pageSize}")
    public Result getStudentByOpr(@PathVariable("pageNo") Integer pageNo,
                                  @PathVariable("pageSize") Integer pageSize,
                                  @RequestParam(value = "clazzName",required = false) String clazzName,
                                  @RequestParam(value = "name",required = false) String name){
        Page<Student> page = new Page<>(pageNo,pageSize);
        IPage<Student> iPage = studentService.getStudentByOpr(page,name,clazzName);
        return Result.ok(iPage);
    }

    //http://localhost:8080/sms/studentController/addOrUpdateStudent
    @PostMapping("/addOrUpdateStudent")
    public Result addOrUpdateStudent(@RequestBody Student student){
        studentService.saveOrUpdate(student);
        return Result.ok();
    }

    //http://localhost:8080/sms/studentController/delStudentById
    @DeleteMapping("/delStudentById")
    public Result delStudentById(@RequestBody List<Integer> ids){
        studentService.removeByIds(ids);
        return Result.ok();
    }
}
