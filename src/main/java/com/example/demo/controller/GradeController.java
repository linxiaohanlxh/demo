package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Grade;
import com.example.demo.service.GradeService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sms/gradeController")
public class GradeController {
    @Autowired
    private GradeService gradeService;

    @GetMapping("/getGrades/{pageNo}/{pageSize}")
    public Result getGrade(
            @PathVariable("pageNo") Integer pageNo,
            @PathVariable("pageSize") Integer pageSize,
            @RequestParam(value = "gradeName",required = false) String gradeName){
        //分页查询
        Page<Grade> page = new Page<>(pageNo,pageSize);
        IPage<Grade> iPage = gradeService.getGradeByOpr(page,gradeName);
        return Result.ok(iPage);
    }

    //    http://localhost:8080/sms/gradeController/saveOrUpdateGrade
    @PostMapping("/saveOrUpdateGrade")
    public Result saveOrUpdateGrade(@RequestBody Grade grade){
        gradeService.saveOrUpdate(grade);
        return Result.ok();
    }

//    http://localhost:8080/sms/gradeController/deleteGrade
    @DeleteMapping("/deleteGrade")
    public Result deleteGrade(@RequestBody List<Integer> ids){
        gradeService.removeByIds(ids);
        return Result.ok();
    }

    //http://localhost:8080/sms/gradeController/getGrades
    @GetMapping("/getGrades")
    public Result getGrades(){
        List<Grade> grades = gradeService.getGrades();
        return Result.ok(grades);
    }
}
