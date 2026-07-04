package org.example.exception;

import org.example.pojo.Result;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 *  全局异常处理器
 *  异常的处理的顺序会根据继承关系排序，越细化的异常先被处理
 */

@Slf4j
@RestControllerAdvice
public class GloblExceptionHandler {

    @ExceptionHandler
    public Result handleExcpetion(Exception e){
        log.error("程序出错啦~", e);
        return Result.error("出错啦，请练习管理员~");
    }

    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e){
        log.error("程序出错啦~", e);
        String message = e.getMessage();
        /* 类似抓取：
            org.springframework.dao.DuplicateKeyException:
            ### Error updating database. Cause: java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '18800991212' for key 'emp.phone'
            ### The error may exist in com/itheima/mapper/EmpMapper.xml
         */
        int i = message.indexOf("Duplicate entry");
        String errMag = message.substring(i);       //Duplicate entry '18800991212' for key 'emp.phone'
        String[] arr = errMag.split(" ");     //根据空格切割
        return Result.error(arr[2] + "已存在");
    }
}
