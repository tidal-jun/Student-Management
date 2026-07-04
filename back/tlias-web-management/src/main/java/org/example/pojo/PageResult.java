package org.example.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 分页结果封装类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private long total;     //总记录数

    private List<T> rows;   //当前页数据
}


/*
    前端传递给后端的分页参数？
    •页码：page
    •每页展示记录数：pageSize
    |           ↑
    |           |
    |           |
    ↓           |
    后端给前端返回数据？
    •数据列表：List row      select * from emp e ... limit ?,?;
    •总记录数：Long total    select count(*) from emp e ...;
 */