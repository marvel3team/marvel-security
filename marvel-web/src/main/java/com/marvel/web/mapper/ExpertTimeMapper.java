package com.marvel.web.mapper;

import com.marvel.web.po.ExpertTime;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname ExpertTimeMapper
 * @Description 专家时间Mapper
 * @Date 2019/4/27 下午11:59
 * @Author zhongjie
 */
@Mapper
public interface ExpertTimeMapper {

    @Insert("<script>"  +
            "INSERT INTO t_expert_time(expert_id, expert_type,start_time,end_time) VALUES" +
            "<foreach collection=\"list\" item=\"e\" index=\"index\" separator=\",\">" +
            "(#{e.expertId},#{e.expertType},#{e.startTime},#{expert.endTime})" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("list") List<ExpertTime> expertTimes);

    @Delete("DELETE FROM t_expert_time where expert_id=#{expertId}")
    int deleteByExpertId(@Param("expertId") Long expertId);

    @Select("select id, expert_id, expert_type, start_time, end_time from t_expert_time where expert_id = #{expertId}")
    @Results(id = "expertTimeMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "expertId", column = "expert_id"),
            @Result(property = "expertType", column = "expert_type"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time")
    })
    List<ExpertTime> findByExpertId(@Param("expertId") Long expertId);
}
