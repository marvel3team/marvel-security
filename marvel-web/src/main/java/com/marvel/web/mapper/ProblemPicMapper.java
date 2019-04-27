package com.marvel.web.mapper;

import com.marvel.web.po.ProblemPic;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname ProblemMapper
 * @Description 问题信息mapper操作类
 * @Date 2019/4/23 下午11:15
 * @Author zhongjie
 */
@Mapper
public interface ProblemPicMapper {

    @Insert("<script>"  +
            "INSERT INTO t_problem_pic(problem_id, url) VALUES" +
            "<foreach collection=\"list\" item=\"e\" index=\"index\" separator=\",\">" +
            "(#{e.problemId},#{e.url})" +
            "</foreach>" +
            "</script>")
    int batchInsert(@Param("list") List<ProblemPic> pics);

    @Delete("DELETE FROM t_problem_pic where problem_id=#{problemId}")
    int deleteByProblemId(@Param("problemId") Long problemId);

    @Select("select problem_id, url from t_problem_pic where id = #{id}")
    @Results(id = "problemMap", value = {
            @Result(property = "problemId", column = "problem_id"),
            @Result(property = "url", column = "url")
    })
    ProblemPic findById(@Param("id") Long id);

    @Select({
            "<script>",
            "select problem_id, url from t_problem_pic where problem_id in",
            "<foreach collection='list' item='problemId' open='(' separator=',' close=')'>",
            "#{problemId}",
            "</foreach>",
            "</script>"
    })
    @ResultMap(value = "problemMap")
    List<ProblemPic> batchGet(@Param("list") List<Long> problemIds);

}
