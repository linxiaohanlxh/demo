package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.pojo.Clazz;
import com.example.demo.service.ClazzService;
import com.example.demo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sms/clazzController")
public class ClazzController {

    @Autowired
    ClazzService clazzService;
    //http://localhost:8080/sms/clazzController/getClazzsByOpr/1/3
    @GetMapping("getClazzsByOpr/{pageNo}/{pageSize}")
    public Result getClazzsByOpr(@PathVariable("pageNo") Integer pageNo,
                                 @PathVariable("pageSize") Integer pageSize,
                                 @RequestParam(value = "name",required = false) String name,
                                 @RequestParam(value = "gradeName",required = false) String gradeName){
        Page<Clazz> page = new Page<>(pageNo,pageSize);
        IPage<Clazz> iPage = clazzService.getClazzsByOpr(page,name,gradeName);
        return Result.ok(iPage);
    }

    //http://localhost:8080/sms/clazzController/saveOrUpdateClazz
    @PostMapping("/saveOrUpdateClazz")
    public Result saveOrUpdateClazz(@RequestBody Clazz clazz){
        clazzService.saveOrUpdate(clazz);
        return  Result.ok();
    }

    //http://localhost:8080/sms/clazzController/deleteClazz
    @DeleteMapping("/deleteClazz")
    public Result deleteClazz(@RequestBody List<Integer> ids){
        clazzService.removeByIds(ids);
        return  Result.ok();
    }

    //http://localhost:8080/sms/clazzController/getClazzs
    @GetMapping("/getClazzs")
    public Result getClazzs(){
        List<Clazz> clazzes = clazzService.getClazzs();
        return Result.ok(clazzes);
    }
}
