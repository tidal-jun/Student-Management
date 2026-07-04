package org.example.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.pojo.OperateLog;
import org.example.pojo.PageResult;
import org.example.pojo.Result;
import org.example.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/log")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 添加日志管理
     */
    @GetMapping("/page")
    public Result getLogs(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize){
        log.info("查询日志记录");
        PageResult<OperateLog> operateLog = logService.page(page, pageSize);
        return Result.success(operateLog);
    }


}
