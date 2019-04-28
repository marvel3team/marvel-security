package com.marvel.web.mapper;

import com.marvel.web.po.CompanyStandard;
import com.marvel.web.po.Problem;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

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
    @Select("SELECT count(1) FROM t_company_standard where type = #{type}")
    long getCompanyCount(@Param("type") Integer companyType);

    /**
     * 分页查询列表
     * @param type
     * @param cursor
     * @param count
     * @return
     */
    @SelectProvider(type = CompanySqlBuilder.class, method = "findByPage")
    List<CompanyStandard> getCompanyListPage( Integer type, Long cursor, Integer count);
    class CompanySqlBuilder {
        public static String findByPage(final Integer type, final Long cursor, final Integer count) {
            StringBuilder sql = new StringBuilder("SELECT id, name, area_id, type, industry_id, registed_capital, legal_person, legal_person_mobile, bussiness_type_code, email, business_license_id FROM t_company_standard where 1=1");
            if (type != null && type > 0) {
                sql.append(" and type = " + type);
            }
            if (cursor != null && cursor > 0) {
                sql.append(" and id < " + cursor);
            }
            sql.append(" order by id desc limit " + count);
            return sql.toString();
        }
    }

    /**
     * 根据id查询信息
     *
     * @param id
     * @return
     */
    @Select("select id, name, area_id, type, industry_id, registed_capital, legal_person, legal_person_mobile, bussiness_type_code, email, business_license_id from t_company_standard where id =#{id}")
    @Results(id="defaultResultMap", value={
            @Result(column="id", property="id", jdbcType= JdbcType.BIGINT, javaType = Long.class,id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR, javaType = String.class),
            @Result(column="area_id ", property="areaId", jdbcType=JdbcType.INTEGER, javaType = Integer.class),
            @Result(column="type ", property="type", jdbcType=JdbcType.INTEGER, javaType = Integer.class),
            @Result(column="industry_id ", property="industryId", jdbcType=JdbcType.BIGINT, javaType = Long.class),
            @Result(column="registed_capital ", property="registedCapital", jdbcType=JdbcType.BIGINT, javaType = Long.class),
            @Result(column="legal_person ", property="legalPerson", jdbcType=JdbcType.VARCHAR, javaType = String.class),
            @Result(column="legal_person_mobile ", property="legalPersonMobile", jdbcType=JdbcType.VARCHAR, javaType = String.class),
            @Result(column="bussiness_type_code ", property="bussinessTypeCode", jdbcType=JdbcType.VARCHAR, javaType = String.class),
            @Result(column="email ", property="email", jdbcType=JdbcType.VARCHAR, javaType = String.class),
            @Result(column="business_license_id ", property="businessLicenseId", jdbcType=JdbcType.VARCHAR, javaType = String.class)
    })
    CompanyStandard getCompanyById(@Param("id") Long id);


    /**
     * 批量查询
     *
     * @param companyIds
     * @return
     */
    @Select({
            "<script>",
            "select id, name, area_id, type, industry_id, registed_capital, legal_person, legal_person_mobile, bussiness_type_code, email, business_license_id from t_company_standard where id in",
            "<foreach collection='list' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    @ResultMap(value = "defaultResultMap")
    List<CompanyStandard> getCompanyByIds(@Param("list") List<Long> companyIds);


    @Update("update t_company_standard" +
            "set name = #{param.name}," +
            "area_id = #{param.areaId}," +
            "industry_id=#{param.industryId}," +
            "registed_capital=#{param.registedCapital}," +
            "legal_person = #{param.legalPerson}," +
            "legal_person_mobile =#{param.legalPersonMobile}," +
            "bussiness_type_code=#{param.bussinessTypeCode}," +
            "business_license_id=#{param.businessLicenseId}," +
            "email=#{param.email}" +
            "where id=#{param.id}")
    int updateCompanyStandard(@Param("param") CompanyStandard companyStandard);
}
