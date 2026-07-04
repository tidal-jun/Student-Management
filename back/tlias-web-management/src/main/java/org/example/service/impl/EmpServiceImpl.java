package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.EmpExprMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.*;
import org.example.service.EmpLogService;
import org.example.service.EmpService;
import org.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;


    //------------------------------------- 原始分页查询实现 -------------------------------------------
    //            @Override
    //            public PageResult<Emp> page(Integer page, Integer pageSize) {
    //                //1. 调用mapper接口， 查询总记录数
    //                Long total = empMapper.count();
    //
    //                //2. 调用mapper接口， 查询结果列表
    //                Integer start = (page - 1) * pageSize;
    //                List<Emp> rows = empMapper.list( start , pageSize);
    //
    //                //3. 封装结果，PageResult
    //                return new PageResult<>(total, rows);


    //------------------------------------- PageHelper分页查询实现 -------------------------------------------
    /* 注意事项：
    *   1. 定义的SQL语句结尾不能加分号;
    *   2. PageHelper仅仅能对紧跟在其后的第一个查询语句进行分页处理
    * */
       /*
            @Override
            public PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end) {
                //1. 设置分页参数, 告诉PageHelper查询的参数值
                PageHelper.startPage(page, pageSize);

            /*
                //2. PageHelper 执行查询, 此处就不用传参了，因为SQL用不上参数
                List<Emp> empList = empMapper.list();       //拦截
                List<Emp> empList2 = empMapper.list();      //不拦截
            */
        /*
                //2. 多条件查询  -- 不用传page和pageSize
                List<Emp> empList = empMapper.list(name, gender, begin, end);

                //3. 解析查询结果，并封装
                //实际empList本身就是Page类型（由PageHelper提供的集合），能用List封装 且可以强转 是因为，Page继承了List
                Page<Emp> p = (Page<Emp>) empList;

                // 我们可以通过 Page 集合获取到元素总数 和 结果列表
                return new PageResult<Emp>(p.getTotal(),p.getResult());
            }
        */

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        List<Emp> empList = empMapper.list(empQueryParam);
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

    /**
     * 新增员工
     */

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        try {
            //1. 保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //2. 保存员工工作经历信息
            // 但是保存员工工作经历的ID是数据库分配的，前端不会传递，而我们又需要ID为工作s经历的empId赋值
            // 解决方案：设置主键返还，将上面emp注入到数据库，获取到完整的数据后返回数据完善emp的id
            // 需要在对应的SQL语句中开放返回数据的开关，也就是EmpMapper.java
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                //返还后emp内就存在了ID，这时候就要给集合里面的每个元素都赋值
                exprList.forEach( empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                // 这里正常应该是empExprMapper.insertBatct，但是不知道为什么无法读取，于是在empMapper重新实现读取
                empExprMapper.insertBatct(exprList);
            }
        } finally { // 不管回滚还是执行，也就是不管失败还是正常，用finally都可以让日志正常记录
            // 记录操作日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工：" + emp);
            empLogService.insertLog(empLog);
        }
    }
    /**
     * 批量删除员工
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //1. 批量删除员工的基本信息
        empMapper.deleteByIds(ids);

        //2. 批量删除员工的工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    /**
     * 根据ID查询用户
     */
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    /**
     * 修改用户
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp) {
        //1. 根据ID修改用户的基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2. 根据ID修改员工的工作经历信息
        //2.1 先根据员工ID删除原有的工作经历
        empExprMapper.deleteByEmpIds(List.of(emp.getId()));

        //2.2 再添加这个员工新的工作经历
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatct(exprList);
        }
    }

    @Override
    public Logininfo login(Emp emp) {
        //1.调用mapper接口，根据用户名和密码查询用户
        Emp e = empMapper.selectByUsernameAndPassword(emp);

        //2.判断：判断是否存在这个员工，如果存在，组装成功登录信息
        if (e != null){
            log.info("登录成功，员工信息：{}", e);
            //生成JWT令牌

            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);

            return new Logininfo(e.getId(), e.getUsername(), e.getName(), jwt);
        }

        //3. 不存在，返回NULL
        return null;
    }

}

