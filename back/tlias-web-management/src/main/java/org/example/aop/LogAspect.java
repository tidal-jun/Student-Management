package org.example.aop;

import org.example.mapper.OperateLogMapper;
import org.example.pojo.OperateLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.utils.CurrenHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    /**
     * 环绕通知：拦截 org.example.controller 包下的所有方法，记录操作日志
     */
    @Around("@annotation(org.example.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 记录操作开始时间
        long beginTime = System.currentTimeMillis();

        // 2. 执行目标方法（即原始的增删改业务方法），并获取返回值
        Object returnValue = joinPoint.proceed();

        // 3. 记录操作结束时间，并计算耗时
        long endTime = System.currentTimeMillis();
        long costTime = endTime - beginTime;

        // 4. 封装日志信息到 OperateLog 实体类中
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getEmpId());
        operateLog.setOperateTime(LocalDateTime.now());
        
        // 获取目标类的全类名
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());
        // 获取目标方法的方法名
        operateLog.setMethodName(joinPoint.getSignature().getName());
        // 获取方法运行时参数（转为字符串）
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        // 获取方法返回值（转为字符串）
        operateLog.setReturnValue(returnValue.toString());
        // 设置方法执行耗时
        operateLog.setCostTime(costTime);

        //保存日志
        log.info("记录保存日志：{}", operateLog);

        // 5. 调用 Mapper 接口，将日志数据保存到数据库
        operateLogMapper.insert(operateLog);

        // 6. 返回原始方法的执行结果，确保业务流程不受影响
        return returnValue;
    }

    private Integer getEmpId() {
        return CurrenHolder.getCurrentLocal();
    }
}