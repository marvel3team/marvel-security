package com.marvel.web.mapper;

import com.marvel.web.po.CompanyIndustry;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Classname CompanyIndustryMapper
 * @Description t_company_industry
 * 企业行业信息Mapper操作类
 * @Date 2019/4/21 下午4:49
 * @Author zhongjie
 */
@Mapper
public interface CompanyIndustryMapper {

    /**
     * 根据id查询行业
     * @param industryId
     * @return
     */
    @Select("select * from t_company_industry where id = #{id}")
    CompanyIndustry getIndustryById(@Param("id") Long industryId);



    /**
     * 批量查询
     * @param industryIds
     * @return
     */
    @Select({
            "<script>",
            "select * from t_company_industry where id in",
            "<foreach collection='list' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<CompanyIndustry> getIndustryByIds(@Param("list") List<Long> industryIds);
}
