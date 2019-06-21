package com.marvel.web.mapper;

import com.marvel.web.po.RespondPlan;
import org.apache.ibatis.annotations.*;

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

    /**
     * 计划维度查询
     * @param planId
     * @return
     */
    @Select("select * from t_respond_plan where plan_id = #{planId}")
    List<RespondPlan> getResponsePlan(@Param("planId") Long planId);

    @Insert("<script>"  +
            "INSERT INTO t_respond_plan(plan_id, expert_id, plan_time , type, status, remark) VALUES" +
            "<foreach collection=\"list\" item=\"e\" index=\"index\" separator=\",\">" +
            "(#{e.planId},#{e.expertId},#{e.planTime},#{e.type},#{e.status},#{e.remark})" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("list")List<RespondPlan> plans);

    /**
     * 更新状态
     * @param planId
     * @param status
     * @return
     */
    @Update("update t_respond_plan set status = #{status}, plan_time=#{planTime} where plan_id = #{planId}")
    int updateStatus(@Param("planId") Long planId, @Param("status") Integer status, @Param("planTime") Long planTime);
}
