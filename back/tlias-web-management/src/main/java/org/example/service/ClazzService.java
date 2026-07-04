package org.example.service;

import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    /**
     * 分页查询
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 删除班级
     */
    void delete(Integer id);

    /**
     * 添加班级
     */
    void save(Clazz clazz);

    /**
     * 根据ID查询班级信息
     */
    Clazz getInfo(Integer id);

    /**
     * 修改班级信息
     */
    void update(Clazz clazz);

    /**
     * 查询全部班级信息
     */
    List<Clazz> list();
}
