package com.marvel.web.mapper;

import com.marvel.web.po.Problem;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname ProblemMapper
 * @Description 问题信息mapper操作类
 * @Date 2019/4/23 下午11:15
 * @Author zhongjie
 */
@Mapper
public interface ProblemMapper {

    @Insert("INSERT INTO t_problem_info(id, plan_id, rule_id, expert_id, project_id, project_name, problem_content, corrective_action, term, status, update_time) VALUES(#{problem.id}, #{problem.planId}, " +
            "#{problem.ruleId}, #{problem.expertId}, #{problem.projectId}, #{problem.projectName}, #{problem.problemContent}, #{problem.correctiveAction}, #{problem.term}, #{problem.status}, #{problem.updateTime})")
    int save(@Param("problem") Problem problem);

    @Select("SELECT id, plan_id, rule_id, expert_id, project_id, project_name, problem_content, corrective_action, term, status, update_time FROM t_problem_info WHERE id = #{id}")
    Problem findById(@Param("id") Long Id);

    @Update("UPDATE t_problem_info set plan_id = #{problem.planId}, rule_id = #{problem.ruleId}, expert_id = #{problem.expertId}, project_id = #{problem.projectId}, " +
            "project_name = #{problem.projectName}, problem_content = #{problem.problemContent}, corrective_action = #{problem.correctiveAction}, term = #{problem.term}, " +
            "status = #{problem.status}, update_time = #{problem.updateTime} WHERE id = #{problem.id}")
    int update(@Param("problem") Problem problem);

    @SelectProvider(type = SqlBuilder.class, method = "buildFindByPage")
    List<Problem> findByPage(Integer status, Long cursor, Integer count);

    class SqlBuilder {
        public static String buildFindByPage(final Integer status, final Long cursor, final Integer count) {
            StringBuilder sql = new StringBuilder("SELECT id, plan_id, rule_id, expert_id, project_id, project_name, problem_content, corrective_action, term, status, update_time FROM t_problem_info where 1=1");
            if (status != null && status > 0) {
                sql.append(" and status = " + status);
            }
            if (cursor != null && cursor > 0) {
                sql.append(" and id < " + cursor);
            }
            sql.append(" order by id desc limit " + count);
            return sql.toString();
        }
    }
}
