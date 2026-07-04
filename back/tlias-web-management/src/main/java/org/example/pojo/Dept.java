package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {
    private Integer id;
    private String name;
    //驼峰命名
    //在数据库中是create_time 和 update_time
    //下划线后第一个字母大写
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
