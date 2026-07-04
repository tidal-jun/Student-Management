package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.StudentMapper;
import org.example.pojo.PageResult;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    /**
     * 分页查询
     */
    @Override
    public PageResult<Student> list(StudentQueryParam studentQueryParam) {
        PageHelper.startPage(studentQueryParam.getPage(),studentQueryParam.getPageSize());
        List<Student> list = studentMapper.page(studentQueryParam);
        Page<Student> result = (Page<Student>) list;
        return new PageResult<>(result.getTotal(),result.getResult());
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(List<Integer> ids) {
        studentMapper.deleteByid(ids);
    }

    /**
     * 添加员工
     */
    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.save(student);
    }

    /**
     * 根据ID查找员工
     */
    @Override
    public Student getInfo(Integer id) {
        return studentMapper.findById(id);
    }

    /**
     * 修改员工信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Student student) {
        //注入基本信息
        student.setCreateTime(studentMapper.findById(student.getId()).getCreateTime());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.deleteByid(List.of(student.getId()));
        studentMapper.save(student);
    }

    /**
     * 违规处理
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void violation(Integer id, Integer score) {
        //获取当前学员信息
        Student student = studentMapper.findById(id);
        //处理新分数
        student.setViolationScore(student.getViolationScore() + score);
        //处理新次数
        student.setViolationCount(student.getViolationCount() + 1);
        //更新数据
        update(student);
    }
}
