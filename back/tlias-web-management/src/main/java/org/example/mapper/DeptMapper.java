package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Dept;

import java.util.List;

@Mapper //应用程序在运行时， 会自动的为该接口创建一个实现类对象（代理对象）， 并且会自动将该实现类对象存入IOC容器 - bean
public interface DeptMapper {

    /**
     * 查询所有部门数据
     */
    @Select("select id, name, create_time, update_time from dept order by update_time desc ")
    List<Dept> findAll();

    /**
     * 根据ID删除部门
     */
    @Delete("delete from dept where id = #{ id }")
    void delectById(Integer id);

    /**
     * 新增部门
     */
    @Insert("insert into dept(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    /**
     * 查询部门
     */
    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    /**
     * 修改部门
     */
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void update(Dept dept);
}
