package org.example.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.pojo.Student;
import org.example.pojo.StudentQueryParam;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    /**
     * 分页查询
     */
    List<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 批量删除员工
     */
    void deleteByid(List<Integer> ids);

    /**
     * 添加员工
     */
    @Insert("insert into student (name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, violation_count, violation_score, create_time, update_time) " +
            "VALUES (#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, #{degree},#{ graduationDate}, #{clazzId}, #{violationCount}, #{violationScore}, #{createTime}, #{updateTime})")
    void save(Student student);

    /**
     * 根据ID查询员工
     */
    @Select("select student.id, student.name, student.no, student.gender, student.phone, student.id_card, student.is_college, student.address, student.degree, student.graduation_date, student.violation_count, student.violation_score, student.clazz_id, student.create_time, student.update_time from student where student.id = #{id}")
    Student findById(Integer id);

    /**
     * 获取学生学历数据
     */
    @MapKey("String")
    List<Map<String, Object>> countStudentDegreeData();

    /**
     * 获取班级人数信息
     */
    @MapKey("String")
    List<Map<String, Object>> countClazzData();
}
