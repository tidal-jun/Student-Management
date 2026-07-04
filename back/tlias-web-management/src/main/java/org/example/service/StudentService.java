package org.example.service;

import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    /**
     * 分页查询
     */
    PageResult<Student> list(StudentQueryParam studentQueryParam);

    /**
     * 删除学员
     */
    void delete(List<Integer> ids);

    /**
     * 添加学员
     */
    void save(Student student);

    /**
     * 根据ID查询学员信息
     */
    Student getInfo(Integer id);

    /**
     * 修改学员
     */
    void update(Student student);

    /**
     * 违规处理
     */
    void violation(Integer id, Integer score);
}
