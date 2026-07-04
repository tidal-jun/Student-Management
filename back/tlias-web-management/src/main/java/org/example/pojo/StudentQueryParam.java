package org.example.pojo;

import com.github.pagehelper.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentQueryParam {
    private String name;        //学员姓名
    private Integer degree;     //学历
    private Integer clazzId;    //班级ID
    private Integer page = 1;           //页码
    private Integer pageSize = 10;      //每页个数
}
