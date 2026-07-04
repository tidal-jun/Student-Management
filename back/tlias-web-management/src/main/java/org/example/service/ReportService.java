package org.example.service;

import org.example.pojo.ClazzDataOption;
import org.example.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计员工职位人数
     */
    JobOption getEmpJoDate();

    /**
     * 统计员工性别人数
     */
    List<Map<String, Object>> getEmpGenderData();


    /**
     *  获取学生学历数据
     */
    List<Map<String, Object>> getStudentDegreeData();

    /**
     * 获取学生班级人数
     */
    ClazzDataOption getClazzData();
}
