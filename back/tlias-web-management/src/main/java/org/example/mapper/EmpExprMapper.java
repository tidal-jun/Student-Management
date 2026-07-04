package org.example.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.EmpExpr;

import java.util.List;

/**
 * 员工工作信息
 */
@Mapper
public interface EmpExprMapper {
    /**
     * 批量保存员工的工作经历信息
     */
    void insertBatct(List<EmpExpr> exprList);

    /**
     * 批量删除员工的工作经历信息
     */
    void deleteByEmpIds(List<Integer> empIds);
}
