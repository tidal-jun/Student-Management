package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.OperateLog;

import java.util.List;

@Mapper
public interface OperateLogMapper {

    /**
     * 查询日志
     */
    @Select("select id, operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time from operate_log")
    public List<OperateLog> pageSelect();

    //插入日志数据
    @Insert("insert into operate_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime});")
    public void insert(OperateLog log);
}
