package org.example.service;

import org.example.pojo.OperateLog;
import org.example.pojo.PageResult;

public interface LogService {
    /**
     * 查询日志记录
     */
    PageResult<OperateLog> page(Integer page, Integer pageSize);
}
