package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.anno.Log;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 分页查询
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam){
        log.info("分页查询:{}",studentQueryParam);
        PageResult<Student> list =  studentService.list(studentQueryParam);
        return Result.success(list);
    }

    /**
     * 删除员工
     */
    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        log.info("删除员工：{}", ids);
        studentService.delete(ids);
        return Result.success();
    }

    /**
     * 添加学员
     */
    @Log
    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("添加学员：{}",student);
        studentService.save(student);
        return Result.success();
    }

    /**
     * 根据ID查询学员
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询ID为：{} 的学员信息",id);
        Student student =  studentService.getInfo(id);
        return Result.success(student);
    }

    /**
     * 修改员工信息
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改员工信息:{}",student);
        studentService.update(student);
        return Result.success();
    }

    /**
     * 违规处理
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable("id") Integer id, @PathVariable("score") Integer score){
        log.info("id为{}的学员扣除分数{}",id,score);
        studentService.violation(id,score);
        return Result.success();
    }
}
