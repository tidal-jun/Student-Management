package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 根据信息动态查询班级信息
     */
    List<Clazz> list(ClazzQueryParam clazzMapper);

    /**
     * 删除班级根据ID
     */
    @Delete("delete from clazz where clazz.id = #{id}")
    void deleteById(Integer id);

    /**
     * 添加班级
     */
    @Insert("Insert into clazz (id, name, room, begin_date, end_date, master_id, subject, create_time, update_time) value (#{id}, #{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void save(Clazz clazz);

    /**
     * 根据ID查询班级信息
     */
    @Select("select c.id, c.name, c.room, c.begin_date, c.end_date, c.master_id, c.subject, c.create_time, c.update_time, e.name  master_name from clazz c left join emp e on c.master_id = e.id where c.id = #{id}" )
    Clazz selectById(Integer id);

    /**
     * 查询全部班级信息
     */
    @Select("select c.id, c.name, c.room, c.begin_date, c.end_date, c.master_id, c.subject, c.create_time, c.update_time from clazz c left join emp e on c.master_id = e.id" )
    List<Clazz> findall();
}
