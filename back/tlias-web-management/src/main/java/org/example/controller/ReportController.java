package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.pojo.ClazzDataOption;
import org.example.pojo.JobOption;
import org.example.pojo.Result;
import org.example.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计员工职位人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJoDate(){
        log.info("统计员工职位人数");
        JobOption jobOption = reportService.getEmpJoDate();
        return Result.success(jobOption);
    }

    /**
     *  统计员工性别人数
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别人数");
        List<Map<String, Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    /**
     * 获取学生学历数据
     */
    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData(){
        log.info("统计学生学历数据");
        List<Map<String, Object>> degreeList = reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }

    /**
     * 获取学生班级数据
     */
    @GetMapping("/studentCountData")
    public Result getClazzData(){
        log.info("获取学生班级数据");
        ClazzDataOption clazzDataOption = reportService.getClazzData();
        return Result.success(clazzDataOption);
    }
}
