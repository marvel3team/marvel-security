package com.marvel.web.mapper;

import com.marvel.web.po.ExpertInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
     * @param id
     * @return
     */
    @Select("select * from t_expert_info where id = #{id}")
    ExpertInfo getExpertInfoById(@Param("id") Long id);


    /**
     * 查询专家总数量
     * @return
     */
    @Select("select count(1) from t_expert_info")
    long getExpertCount();

    /**
     * 分页查询专家信息
     * @param page
     * @param count
     * @return
     */
    @Select("select * from t_expert_info order by id desc limit #{page},#{count}")
    List<ExpertInfo> getExpertListByPage(@Param("page") int page,@Param("count") int count);

    /**
     * 更新
     * @param expert
     * @return
     */
    @Update("UPDATE t_expert_info set name = #{expert.name}, id_card_no = #{expert.idCardNo}, company_id = #{expert.companyId}, work_company = #{expert.workCompany}, " +
            "work_address = #{expert.workAddress}, work_life = #{expert.workLife}, positional_title = #{expert.positionalTitle}, is_syndic = #{expert.isSyndic}, level = #{expert.level}, " +
            "evaluate_range = #{expert.evaluateRange}, collage = #{expert.collage}, home_address = #{expert.homeAddress}, mobile = #{expert.mobile}, remark = #{expert.remark}, " +
            "sign_url = #{expert.signUrl} WHERE id = #{expert.id}")
    int update(@Param("expert") ExpertInfo expert);
}
