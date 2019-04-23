package com.marvel.web.mapper;

import com.marvel.web.po.Plan;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname
 * @Description
 * @Author andy
 * @Date 2019/4/23 下午10:30
 * @Version 1.0
 */
@Mapper
public interface PlanMapper {

    /**
     * 插入计划
     * @param plan
     * @return
     */
    @Insert("INSERT INTO t_plan(id, company_id, bureau_id, plan_time, time_slot, staff_id, plan_subject, superversion_level, status) VALUES(#{param.id}, #{param.companyId}," +
            "#{param.bureauId}, #{param.planTime}, #{param.timeSlot}, #{param.staffId}, #{param.planSubject}, #{param.superversionLevel},#{param.status})")
    int insert(@Param("param") Plan plan);


    /**
     * 根据id查询计划
     * @param id
     * @return
     */
    @Select("select * from t_plan where id = #{id}")
    Plan selectById(@Param("id") Long id);


    /**
     * 更新计划
     * @param plan
     * @return
     */
    @Update("update t_plan set company_id = #{param.companyId},bureau_id =#{param.bureauId},plan_time=#{param.planTime},time_slot =#{param.timeSlot},staff_id=#{param.staffId},plan_subject=#{param.planSubject}," +
            "superversion_level=#{param.superversionLevel},status=#{param.status}")
    int update(@Param("param") Plan plan);


    /**
     * 查询公司下的计划条数
     * @param id
     * @return
     */
    @Select("select count(1) from t_plan where company_id = #{companyId}")
    int getCompanyPlanCount(@Param("companyId") Long id);


    /**
     * 分页查询
     * @param id
     * @param page
     * @param count
     * @return
     */
    @Select("select * from t_plan where company_id=#{companyId} order by id desc limit #{page},#{count}")
    List<Plan> getCompanyPlanList(@Param("companyId") Long id, @Param("page") Integer page, @Param("count") Integer count);
}
