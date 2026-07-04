package org.example.service.impl;

import org.example.mapper.DeptMapper;
import org.example.pojo.Dept;
import org.example.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service    //交给IOC容器管理
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    /**
     * 查询部门
     */
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    /**
     * 删除部门
     */
    @Override
    public void deleteById(Integer id) {
        deptMapper.delectById(id);
    }

    /**
     * 新增部门
     */
    @Override
    public void add(Dept dept) {
        //1. 补全基本数据 - createTime，updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //2. 调用Mapper接口插入数值
        deptMapper.insert(dept);
    }

    /**
     *  根据ID查询部门数据
     */
    @Override
    public Dept getById(Integer id) {
       return deptMapper.getById(id);
    }

    /**
     * 修改部门
     */
    @Override
    public void update(Dept dept){
        //1. 补充基本数据
        dept.setUpdateTime(LocalDateTime.now());
        //2. 调用Mapper接口修改部门
        deptMapper.update(dept);
    }
}
