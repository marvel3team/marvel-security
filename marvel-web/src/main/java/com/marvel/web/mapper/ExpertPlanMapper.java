package com.marvel.web.mapper;

import com.marvel.web.vo.PlanDetailVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Classname ExpertPlanMapper
 * @Description
 * @Author andy
 * @Date 2019/4/23 下午11:28
 * @Version 1.0
 */
@Mapper
public interface ExpertPlanMapper {


    @Select("select count(1) from t_expert_plan e left join t_plan p on e.plan_id = p.id where p.id = #{planId}")
    long getExpertPlanCount(@Param("planId") Long id);


    @Select("select" +
            "ep.plan_id as id," +
            "p.bureau_id as bureauId," +
            "p.plan_time as planTime," +
            "p.time_slot as timeSlot," +
            "p.superversion_level as superversionLevel," +
            "p.`status` as planStatus," +
            "p.plan_subject as planSubject," +
            "rp.type as respondType," +
            "rp.status as finishStatus," +
            "p.finish_time as finishTime," +
            "e.`name` as expertName" +
            "from t_expert_plan ep" +
            "left join t_plan p on ep.plan_id = p.id" +
            "left join t_expert_info e on e.id = ep.expert_id" +
            "left join t_respond_plan rp on rp.plan_id = ep.plan_id" +
            "where ep.expert_id = #{planId} order by ep.plan_id desc limit #{page},#{count}")

    List<PlanDetailVo> getExpertPlanList(@Param("planId") Long id, @Param("page") Integer page, @Param("count") Integer count);
}
