package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private Integer id;               //id
    private String name;              //学员姓名
    private String no;                //学号
    private Integer gender;           //性别
    private String phone;             //手机号
    private Integer degree;           //学历
    private String idCard;            //身份证号
    private Integer isCollege;        //是否是院校学生
    private String address;           //联系地址
    private LocalDate graduationDate;    //毕业时间
    private Integer violationCount = 0;   //违规次数
    private Integer violationScore = 0;   //违规扣分
    private Integer clazzId;          //所属班级ID
    private String clazzName;         //班级名称
    private LocalDateTime createTime;        //创建时间
    private LocalDateTime updateTime;        //更新时间

}
