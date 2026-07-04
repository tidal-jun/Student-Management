package org.example.service.impl;

import org.example.mapper.EmpMapper;
import org.example.mapper.StudentMapper;
import org.example.pojo.ClazzDataOption;
import org.example.pojo.JobOption;
import org.example.service.ReportService;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    /**
     *  统计员工职位人数
     */
    @Override
    public JobOption getEmpJoDate() {
        //1. 调用mapper接口，获取统计数据
        List<Map<String, Object>> list = empMapper.countEmpJoDate();    //map: pos = 教研主管, num = 1

        //2. 组装结果，并返回       //将两个列表提取出来，成两个表
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList,dataList);
    }

    /**
     * 统计员工性别人数
     *       data: [
     *         { value: 1048, name: 'Search Engine' },
     *         { value: 735, name: 'Direct' },
     *         { value: 580, name: 'Email' },
     *       ]
     */
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }


    @Autowired
    private StudentMapper studentMapper;

    /**
     * 获取学生学历数据
     */
    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }

    /**
     * 获取班级人数信息
     */
    @Override
    public ClazzDataOption getClazzData() {
        //1. 调用mapper接口，获取统计数据
        List<Map<String,Object>> list = studentMapper.countClazzData();

        //2. 组装结果，并返回       //将两个列表提取出来成两个表
        List<Object> clazzList = list.stream().map(dataMape -> dataMape.get("clazzName")).toList();
        List<Object> numList = list.stream().map(dataMape -> dataMape.get("num")).toList();

        return new ClazzDataOption(clazzList, numList);
    }
}
