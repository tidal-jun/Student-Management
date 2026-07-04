package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Emp;
import org.example.pojo.EmpExpr;
import org.example.pojo.EmpQueryParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *  员工信息
 */
@Mapper
public interface EmpMapper {

//------------------------------------- 原始分页查询实现 -------------------------------------------
//
//            /**
//             * 查询总记录数
//             */
//            @Select("select count(*) from emp e left join dept d on d.id = e.dept_id")
//            public Long count();
//
//            /**
//             * 分页查询
//             */
//            @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//                    "order by e.update_time desc limit #{start}, #{pageSize}")
//            public List<Emp> list(Integer start, Integer pageSize);
//
//            /*
//                因为 d.name 也就是部门名称在Emp无对应封装，在Emp中只有部门id  deptId 可以封装
//                为此，我们需要到Emp中添加属性， 且为了让查询的字段与属性名相等，我们需要给字段起一个别名
//             */

/**
 //------------------------------------- PageHelper分页查询实现 -------------------------------------------
 * 剔除后面的limit，因为PageHelper会运行时自动添加进去,
 * PageHelper会调用我们传进去的两个参数，在SQL语句后面拼接limit ? , ?， ❗ ： 也正因如此，这里是注解SQL语句后面不能接 ";" 不然就会造成 ; limit ?, ? 的错误
 */
//    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")
//  这里的SQL语句过复杂，采用Mapper环境去写
//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);

    // 整理参数 ↓
/**
 *   条件查询员工信息
 */
  public List<Emp> list(EmpQueryParam empQueryParam);

  /**
   * 新增员工基本信息
   */
  //  使用数据库给我们生成的主键       将获得的主键数据存入到emp的id属性值中    ----称之为主键返还
  @Options(useGeneratedKeys = true, keyProperty = "id")
  @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
          "values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

  /**
   * 根据ID批量删除员工的基本信息
   */
    void deleteByIds(List<Integer> ids);

  /**
   * 根据ID查询员工信息以及工作经历信息
   */
  Emp getById(Integer id);

  /**
   * 根据ID更新员工的基本信息
   */
  void updateById(Emp emp);

  /**
   *  统计员工职位人数
   */
  @MapKey("pos")
  List<Map<String, Object>> countEmpJoDate();

  /**
   *  统计员工性别人数
   */
  @MapKey("name")
  List<Map<String, Object>> countEmpGenderData();

  /**
   *  根据用户名和密码查询员工信息
   */
  @Select("select id,username, name from emp where username = #{username} and password = #{password}")
  Emp selectByUsernameAndPassword(Emp emp);
}
