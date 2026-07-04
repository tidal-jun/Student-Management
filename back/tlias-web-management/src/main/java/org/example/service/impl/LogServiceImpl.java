package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.mapper.OperateLogMapper;
import org.example.pojo.OperateLog;
import org.example.pojo.PageResult;
import org.example.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 查询日志
     *
     * @return
     */
    @Override
    public PageResult<OperateLog> page(Integer page, Integer paseSize) {
        PageHelper.startPage(page, paseSize);
        List<OperateLog> list = operateLogMapper.pageSelect();
        Page<OperateLog> result = (Page<OperateLog>) list;
        return new PageResult<>(result.getTotal(), result.getResult());
    }
}
