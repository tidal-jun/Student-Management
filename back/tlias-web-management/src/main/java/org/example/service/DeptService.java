package org.example.service;

import org.example.pojo.Dept;

import java.util.List;

public interface DeptService {

    /**
     * 查询所有部门
     */
    List<Dept> findAll();

    /**
     * 删除指定部门
     */
    void deleteById(Integer id);

    /**
     * 新增部门
     */
    void add(Dept dept);

    /**
     *  根据ID查询部门数据
     */
    Dept getById(Integer id);

    /**
     * 修改部门
     */
    void update(Dept dept);
}
