package com.marvel.web.mapper;

import com.marvel.web.po.CompanyStandard;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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
     * 根据id查询信息
     *
     * @param id
     * @return
     */
    @SelectProvider(type = CompanySqlBuilder.class, method = "selectById")
    @Results(id = "standardMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "areaId", column = "area_id"),
            @Result(property = "province", column = "province"),
            @Result(property = "city", column = "city"),
            @Result(property = "otherCity", column = "other_city"),
            @Result(property = "address", column = "address"),
            @Result(property = "type", column = "type"),
            @Result(property = "industryId", column = "industry_id"),
            @Result(property = "registedCapital", column = "registed_capital"),
            @Result(property = "legalPerson", column = "legal_person"),
            @Result(property = "legalPersonMobile", column = "legal_person_mobile"),
            @Result(property = "businessTypeCode", column = "business_type_code"),
            @Result(property = "email", column = "email"),
            @Result(property = "businessLicenseId", column = "business_license_id")
    })
    CompanyStandard getCompanyById(Long id);

    @Insert("INSERT INTO t_company_standard(id, name, area_id, province, city, other_city, address, type, industry_id, registed_capital, legal_person, legal_person_mobile, business_type_code, email, business_license_id) VALUES(" +
            "#{standard.id}, #{standard.name}, #{standard.areaId}, #{standard.province}, #{standard.city}, #{standard.otherCity}, #{standard.address}, #{standard.type}, #{standard.industryId}, #{standard.registedCapital}, #{standard.legalPerson}, #{standard.legalPersonMobile}, #{standard.businessTypeCode}" +
            ", #{standard.email}, #{standard.businessLicenseId})")
    int save(@Param("standard") CompanyStandard companyStandard);

    /**
     * @Title:
     * @Description: 查询所有的企业总条数
     * @param:
     * @return:
     * @throws:
     * @author: andy
     * @date: 2019/4/22 下午10:45
     */
    @SelectProvider(type = CompanySqlBuilder.class, method = "findPageCount")
    long getCompanyCount(Integer type);

    /**
     * 分页查询列表
     *
     * @param type
     * @param cursor
     * @param count
     * @return
     */
    @SelectProvider(type = CompanySqlBuilder.class, method = "findByPage")
    @ResultMap(value = "standardMap")
    List<CompanyStandard> getCompanyListPage(Integer type, Long cursor, Integer count);

    /**
     * 批量查询
     *
     * @param companyIds
     * @return
     */
    @SelectProvider(type = CompanySqlBuilder.class, method = "selectByIds")
    @ResultMap(value = "standardMap")
    List<CompanyStandard> getCompanyByIds(@Param("list") List<Long> companyIds);


    /**
     * 更新
     *
     * @param companyStandard
     * @return
     */
    @UpdateProvider(type = CompanySqlBuilder.class, method = "update")
    int updateCompanyStandard(CompanyStandard companyStandard);


    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteProvider(type = CompanySqlBuilder.class, method = "delete")
    int deleteCompanyStandard(Long id);

    /**
     * 根据name查询信息
     *
     * @param name
     * @return
     */
    @SelectProvider(type = CompanySqlBuilder.class, method = "selectByName")
    @ResultMap(value = "standardMap")
    CompanyStandard getCompanyByName(String name);


    class CompanySqlBuilder {

        /**
         * 分页查询
         *
         * @param type
         * @param cursor
         * @param count
         * @return
         */
        public static String findByPage(final Integer type, final Long cursor, final Integer count) {
            StringBuilder sql = new StringBuilder("SELECT id, name, area_id, type, industry_id, registed_capital, legal_person, legal_person_mobile, business_type_code, email, business_license_id FROM t_company_standard where 1=1");
            if (type != null && type > 0) {
                sql.append(" and type = " + type);
            }
            if (cursor != null && cursor > 0) {
                sql.append(" and id < " + cursor);
            }
            sql.append(" order by id desc limit " + count);
            return sql.toString();
        }

        /**
         * 查询
         * @param map
         * @return
         */
        public static String selectByIds(Map map) {
            List<Long> companyIds = (List<Long>) map.get("list");
            StringBuilder sql = new StringBuilder("SELECT id, name, area_id, province, city, other_city, address, type, industry_id, registed_capital, legal_person, legal_person_mobile, business_type_code, email, business_license_id FROM t_company_standard where ");
            sql.append(" id in ").append("(");
            for (Long id : companyIds) {
                sql.append(id).append(",");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
            return sql.toString();
        }

        /**
         * 根据id查询
         *
         * @param id
         * @return
         */
        public static String selectById(final Long id) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id, name, area_id, province, city, other_city, address, type, industry_id, registed_capital, legal_person, legal_person_mobile, business_type_code, email, business_license_id");
            sql.append(" from  t_company_standard where ");
            sql.append("id = ").append(id);
            return sql.toString();
        }

        /**
         * 根据name查询
         *
         * @param name
         * @return
         */
        public static String selectByName(final String name) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT id, name, area_id, province, city, other_city, address, type, industry_id, registed_capital, legal_person, legal_person_mobile, business_type_code, email, business_license_id");
            sql.append(" from  t_company_standard where ");
            sql.append("name = '").append(name).append("'");
            sql.append(" limit 1");
            return sql.toString();
        }

        public static String findPageCount(final Integer type) {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT count(1) FROM t_company_standard where 1=1");
            if (Objects.nonNull(type)) {
                sql.append(" and type = ").append(type);
            }
            return sql.toString();
        }

        /**
         * 更新
         *
         * @param companyStandard
         * @return
         */
        public static String update(final CompanyStandard companyStandard) {
            return new SQL(){
                {
                    UPDATE("t_company_standard");
                    if (companyStandard.getAreaId() != null) {
                        SET("area_id = " + companyStandard.getAreaId() + "'");
                    }
                    if (StringUtils.isNotBlank(companyStandard.getProvince())) {
                        SET("province = '" + companyStandard.getProvince() + "'");
                    }
                    if (StringUtils.isNotBlank(companyStandard.getCity())) {
                        SET("city = '" + companyStandard.getCity() + "'");
                    }
                    if (StringUtils.isNotBlank(companyStandard.getOtherCity())) {
                        SET("other_city = '" + companyStandard.getCity() + "'");
                    }
                    if (StringUtils.isNotBlank(companyStandard.getAddress())) {
                        SET("address = '" + companyStandard.getAddress() + "'");
                    }
                    if (companyStandard.getIndustryId() != null) {
                        SET("industry_id = " + companyStandard.getIndustryId());
                    }
                    if (StringUtils.isNotBlank(companyStandard.getName())) {
                        SET("name = '" + companyStandard.getName() + "'");
                    }
                    if (companyStandard.getRegistedCapital() != null) {
                        SET("registed_capital = " + companyStandard.getRegistedCapital());
                    }
                    if (StringUtils.isNotBlank(companyStandard.getLegalPerson())) {
                        SET("legal_person = '" + companyStandard.getLegalPerson() + "'");
                    }
                    if (StringUtils.isNotBlank(companyStandard.getLegalPersonMobile())) {
                        SET("legal_person_mobile = '" + companyStandard.getLegalPersonMobile() + "'");
                    }
                    if (StringUtils.isNotBlank(companyStandard.getBusinessTypeCode())) {
                        SET("business_type_code = '" + companyStandard.getBusinessTypeCode() + "'");
                    }
                    if (StringUtils.isNotBlank(companyStandard.getBusinessLicenseId())) {
                        SET("business_license_id = '" + companyStandard.getBusinessLicenseId() + "'");
                    }
                    if (StringUtils.isNotBlank(companyStandard.getEmail())) {
                        SET("email = '" + companyStandard.getEmail() + "'");
                    }
                    WHERE("id = " + companyStandard.getId());
                }
            }.toString();
        }


        /**
         * 删除
         * @param id
         * @return
         */
        public static String delete(final Long id){
            StringBuilder sql = new StringBuilder();
            sql.append("delete from t_company_standard where id = ").append(id);
            return sql.toString();
        }
    }
}
