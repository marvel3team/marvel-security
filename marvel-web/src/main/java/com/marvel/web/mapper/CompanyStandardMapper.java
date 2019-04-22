package com.marvel.web.mapper;

import com.marvel.web.po.CompanyStandard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Classname CompanyStandardMapper
 * @Description 公司基本信息
 * @Author andy
 * @Date 2019/4/22 下午10:33
 * @Version 1.0
 */
@Mapper
public interface CompanyStandardMapper {

    /**
    * @Title:
    * @Description: 查询所有的企业总条数
    * @param:
    * @return:
    * @throws:
    * @author: andy
    * @date: 2019/4/22 下午10:45
    */
    @Select("SELECT count(1) FROM t_company_standard")
    long getCompanyCount();

    /**
     * 分页查询列表
     * @param page
     * @param count
     * @return
     */
    @Select("Select * from t_company_standard order by id desc limit #{page},#{count}")
    List<CompanyStandard> getCompanyListPage(@Param("page") Integer page, @Param("count") Integer count);

    /**
     * 根据id查询信息
     * @param id
     * @return
     */
    @Select("select * from t_company_standard where id =#{id}")
    CompanyStandard getCompanyById(@Param("id") Long id);


    /**
     * 批量查询
     * @param companyIds
     * @return
     */
    @Select({
        "<script>",
                "select * from t_company_standard where id in",
                "<foreach collection='list' item='id' open='(' separator=',' close=')'>",
                "#{id}",
                "</foreach>",
                "</script>"
    })
    List<CompanyStandard> getCompanyByIds(@Param("list") List<Long> companyIds);
}
