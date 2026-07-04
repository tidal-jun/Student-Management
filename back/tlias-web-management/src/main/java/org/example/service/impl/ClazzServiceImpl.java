package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.ClazzMapper;
import org.example.pojo.Clazz;
import org.example.pojo.ClazzQueryParam;
import org.example.pojo.PageResult;
import org.example.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        // 1. 开启分页（必须在执行 Mapper 查询前调用）
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());

        // 2. 执行数据库查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);

        // 3. 遍历结果集，进行内存中的业务逻辑处理
        for (Clazz clazz : clazzList) {
            // 3.1 处理 masterId 为 null 的情况，设置默认值为 1
            if (clazz.getMasterId() == null) {
                clazz.setMasterId(1);
            }

            // 3.2 根据时间判断并注入 status
            LocalDate now = LocalDate.now();

            if(clazz.getBeginDate().isAfter(now)){
                clazz.setStatus("未开班");
            }
            else if((clazz.getBeginDate().isBefore(now) && clazz.getEndDate().isAfter(now)) || clazz.getBeginDate().isEqual(now)){
                clazz.setStatus("在读");
            }
            else if(clazz.getEndDate().isBefore(now)){
                clazz.setStatus("已结课");
            }
        }

        // 4. 将修改后的 list 强转为 Page 对象以获取总记录数，封装返回结果
        Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    /**
     * 删除班级
     */
    @Override
    public void delete(Integer id) {
        clazzMapper.deleteById(id);
    }

    /**
     * 添加班级
     */
    @Override
    public void save(Clazz clazz) {
        //1. 补充基本信息
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        if(clazz.getMasterId() == null){
            clazz.setMasterId(1);
        }
        //2. 调用Mapper方法
        clazzMapper.save(clazz);
    }

    /**
     * 根据ID查询班级
     */
    @Override
    public Clazz getInfo(Integer id) {
        Clazz clazz = clazzMapper.selectById(id);
        return clazz;
    }

    /**
     * 修改班级
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Clazz clazz) {
        clazzMapper.deleteById(clazz.getId());
        clazz.setUpdateTime(LocalDateTime.now());
        if(clazz.getMasterId() == null){
            clazz.setMasterId(1);
        }
        clazzMapper.save(clazz);
    }

    /**
     * 查询全部班级信息
     */
    @Override
    public List<Clazz> list() {
        return clazzMapper.findall();
    }
}
