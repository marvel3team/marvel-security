package com.marvel.web.mapper;

import com.marvel.web.po.RespondPlan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Classname RespondPlanMapper
 * @Description
 * @Author andy
 * @Date 2019/4/23 下午11:28
 * @Version 1.0
 */
@Mapper
public interface RespondPlanMapper {


    /**
     * 批量查询
     * @param planIds
     * @return
     */
    @Select({
            "<script>",
            "select * from t_respond_plan where plan_id in",
            "<foreach collection='list' item='planId' open='(' separator=',' close=')'>",
            "#{planId}",
            "</foreach>",
            "</script>"
    })
    List<RespondPlan> getRespondPlanByPlanIds(@Param("list") List<Long> planIds);
}
