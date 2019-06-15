package com.marvel.web.mapper;

import com.marvel.web.po.ExpertInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname ExpertInfoMapper
 * @Description
 * @Author andy
 * @Date 2019/4/22 下午11:47
 * @Version 1.0
 */
@Mapper
public interface ExpertInfoMapper {


    /**
     * 根据id查询专家信息
     *
     * @param id
     * @return
     */
    @SelectProvider(type = ExpertSqlBuilder.class, method = "selectById")
    ExpertInfo getExpertInfoById(Long id);


    /**
     * 查询专家总数量
     *
     * @return
     */
    @SelectProvider(type = ExpertSqlBuilder.class, method = "findPageCount")
    long getExpertCount();

    /**
     * 分页查询专家信息
     *
     * @param page
     * @param count
     * @return
     */
    @SelectProvider(type = ExpertSqlBuilder.class, method = "findByPage")
    List<ExpertInfo> getExpertListByPage(Long page, Integer count);

    /**
     * 更新
     *
     * @param expert
     * @return
     */
    @UpdateProvider(type = ExpertSqlBuilder.class, method = "update")
    int update(@Param("expert") ExpertInfo expert);


    class ExpertSqlBuilder {


        public static String selectById(final Long id) {
            StringBuilder sql = new StringBuilder("SELECT id, name, id_card_no as idCardNo, company_id as companyId, work_company as workCompany, work_address as workAddress, work_life as workLife, positional_title as positionalTitle, is_syndic as isSyndic, level, evaluate_range as evaluate, collage, home_address as homeAddress, mobile, remark, sign_url as signUrl, sex, nation, highest_degree as highestDegree, job_resume as jobResume, categories, honor FROM t_expert_info where 1=1");
            sql.append(" and id = ").append(id);
            return sql.toString();
        }

        public static String findPageCount() {
            StringBuilder sql = new StringBuilder("select count(1) from t_expert_info");
            return sql.toString();
        }

        public static String findByPage(final Long cursor, final Integer count) {
            StringBuilder sql = new StringBuilder("SELECT id, name, id_card_no as idCardNo, company_id as companyId, work_company as workCompany, work_address as workAddress, work_life as workLife, positional_title as positionalTitle, is_syndic as isSyndic, level, evaluate_range as evaluate, collage, home_address as homeAddress, mobile, remark, sign_url as signUrl, sex, nation, highest_degree as highestDegree, job_resume as jobResume, categories, honor FROM t_expert_info where 1=1");
            if (cursor != null && cursor > 0) {
                sql.append(" and id < " + cursor);
            }
            sql.append(" order by id desc limit " + count);
            return sql.toString();
        }

        public static String update(ExpertInfo expertInfo) {
            StringBuilder sql = new StringBuilder("update t_expert_info set ");
            if (StringUtils.isNotBlank(expertInfo.getName())) {
                sql.append("name = '").append(expertInfo.getName()).append("',");
            }
            if (StringUtils.isNotBlank(expertInfo.getIdCardNo())) {
                sql.append("id_card_no = '").append(expertInfo.getIdCardNo()).append("',");
            }
            if (expertInfo.getCompanyId() != null) {
                sql.append("company_id = ").append(expertInfo.getCompanyId()).append(",");
            }
            if (StringUtils.isNotBlank(expertInfo.getWorkCompany())) {
                sql.append("work_company = '").append(expertInfo.getWorkCompany()).append("',");
            }
            if (StringUtils.isNotBlank(expertInfo.getWorkAddress())) {
                sql.append("work_address = '").append(expertInfo.getWorkAddress()).append("',");
            }
            if (expertInfo.getWorkLife() != null) {
                sql.append("work_life = ").append(expertInfo.getWorkLife()).append(",");
            }
            if (StringUtils.isNotBlank(expertInfo.getPositionalTitle())) {
                sql.append("positional_title = '").append(expertInfo.getPositionalTitle()).append("',");
            }
            if (expertInfo.getIsSyndic() != null) {
                sql.append("is_syndic = ").append(expertInfo.getIsSyndic()).append(",");
            }
            if (expertInfo.getLevel() != null) {
                sql.append("level = ").append(expertInfo.getLevel()).append(",");
            }
            if (StringUtils.isNotBlank(expertInfo.getEvaluateRange())) {
                sql.append("evaluate_range = '").append(expertInfo.getEvaluateRange()).append("',");
            }
            if (StringUtils.isNotBlank(expertInfo.getCollage())) {
                sql.append("collage = '").append(expertInfo.getCollage()).append("',");
            }
            if (StringUtils.isNotBlank(expertInfo.getHomeAddress())) {
                sql.append("home_address = '").append(expertInfo.getHomeAddress()).append("',");
            }
            if (StringUtils.isNotBlank(expertInfo.getMobile())) {
                sql.append("mobile = '").append(expertInfo.getMobile()).append("',");
            }
            if (StringUtils.isNotBlank(expertInfo.getRemark())) {
                sql.append("remark = '").append(expertInfo.getRemark()).append("',");
            }
            if (StringUtils.isNotBlank(expertInfo.getSignUrl())) {
                sql.append("sign_url = '").append(expertInfo.getSignUrl()).append("',");
            }
            if (expertInfo.getSex()==null) {
                sql.append("sex = '").append(expertInfo.getSex()).append("',");
            }
            if (StringUtils.isNotBlank(expertInfo.getNation())) {
                sql.append("nation = '").append(expertInfo.getNation()).append("',");
            }
            if (StringUtils.isNotBlank(expertInfo.getHighestDegree())) {
                sql.append("highest_degree = '").append(expertInfo.getHighestDegree()).append("',");
            }
            if (StringUtils.isNotBlank(expertInfo.getJobResume())) {
                sql.append("job_resume = '").append(expertInfo.getJobResume()).append("',");
            }
            if (StringUtils.isNotBlank(expertInfo.getCategories())) {
                sql.append("categories = '").append(expertInfo.getCategories()).append("',");
            }
            if (StringUtils.isNotBlank(expertInfo.getHonor())) {
                sql.append("honor = '").append(expertInfo.getHonor()).append("',");
            }

            sql.deleteCharAt(sql.length() - 1);
            sql.append(" where id = ").append(expertInfo.getId());
            return sql.toString();
        }
    }

}
