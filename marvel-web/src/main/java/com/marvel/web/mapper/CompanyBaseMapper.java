package com.marvel.web.mapper;

import com.marvel.web.po.CompanyBase;
import com.marvel.web.po.CompanyStandard;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Classname CompanyBaseMapper
 * @Description t_company_base_info
 * 企业基础数据信息Mapper操作类
 * @Date 2019/4/21 下午4:49
 * @Author zhongjie
 */
@Mapper
public interface CompanyBaseMapper {

    /**
     * 根据id查询企业信息
     *
     * @param id
     * @return
     */
    @Select("select id, production_day as productionDay, total_invest as totalInvest, fixed_invest as fixedInvest, last_year_income as lastYearIncome, factory_area as factoryArea, " +
            "covered_area as coveredArea, raw_stock_dosage as rawStockDosage, accessories_dosage as accessoriesDosage, yearly_capacity as yearlyCapacity, output_unit as outputUnit,  " +
            "is_special_equipment as isSpecialEquipment, is_distribution_room as isDistributionRoom, is_transformer as isTransformer, is_stan_certificate as isStanCertificate,  " +
            "stan_certificate_type as stanCertificate, stan_certificate_id as stanCertificateId, is_declare_online as isDeclareOnline, is_safe_proof as isSafeProof,  " +
            "safe_proof_archive_no as safeProofArchiveNo, work_system as workSystem, production_department_peoples as productionDepartmentPeoples, office_peoples as officePeoples,  " +
            "work_days_yearly as workDaysYearly, industry_type as industryType, safety_level as safetyLevel, certificate_start_time as certificateStartTime, certificate_end_time as certificateEndTime, " +
            "major_risk_sources as majorRiskSources, higher_risk_sources as higherRiskSources, general_risk_sources as generalRiskSources, low_risk_sources as lowRiskSources from t_company_base_info where id = #{id}")
    CompanyBase getCompanBaseById(@Param("id") Long id);


    /**
     * 插入
     *
     * @param companyBase
     * @return
     */
    @InsertProvider(type = CompanyBaseSqlBuilder.class, method = "insert")
    int insertCompanyBase(@Param("companyBase") CompanyBase companyBase);


    /**
     * 更新
     *
     * @param companyBase
     * @return
     */
    @UpdateProvider(type = CompanyBaseSqlBuilder.class, method = "update")
    int updateCompanyBase(@Param("companyBase") CompanyBase companyBase);


    /**
     * 删除
     *
     * @param id
     * @return
     */
    @DeleteProvider(type = CompanyBaseSqlBuilder.class, method = "delete")
    int deleteCompanyBase(Long id);

    @Select({
            "<script>",
            "select id, production_day as productionDay, total_invest as totalInvest, fixed_invest as fixedInvest, last_year_income as lastYearIncome, factory_area as factoryArea, " +
                    "covered_area as coveredArea, raw_stock_dosage as rawStockDosage, accessories_dosage as accessoriesDosage, yearly_capacity as yearlyCapacity, output_unit as outputUnit,  " +
                    "is_special_equipment as isSpecialEquipment, is_distribution_room as isDistributionRoom, is_transformer as isTransformer, is_stan_certificate as isStanCertificate,  " +
                    "stan_certificate_type as stanCertificate, stan_certificate_id as stanCertificateId, is_declare_online as isDeclareOnline, is_safe_proof as isSafeProof,  " +
                    "safe_proof_archive_no as safeProofArchiveNo, work_system as workSystem, production_department_peoples as productionDepartmentPeoples, office_peoples as officePeoples,  " +
                    "work_days_yearly as workDaysYearly, industry_type as industryType, safety_level as safetyLevel, certificate_start_time as certificateStartTime, certificate_end_time as certificateEndTime, " +
                    "major_risk_sources as majorRiskSources, higher_risk_sources as higherRiskSources, general_risk_sources as generalRiskSources, low_risk_sources as lowRiskSources from t_company_base_info where id in",
            "<foreach collection='list' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<CompanyBase> getCompanyBases(@Param("list") List<Long> companyIds);

    class CompanyBaseSqlBuilder {

        /**
         * 更新
         *
         * @param companyBase
         * @return
         */
        public static String update(final CompanyBase companyBase) {
            return new SQL() {
                {
                    UPDATE("t_company_base_info");
                    if (companyBase.getProductionDay() != null) {
                        SET("production_day = " + companyBase.getProductionDay());
                    }
                    if (companyBase.getTotalInvest() != null) {
                        SET("total_invest = " + companyBase.getTotalInvest());
                    }
                    if (companyBase.getFixedInvest() != null) {
                        SET("fixed_invest = " + companyBase.getFixedInvest());
                    }
                    if (companyBase.getLastYearIncome() != null) {
                        SET("last_year_income = " + companyBase.getLastYearIncome());
                    }
                    if (companyBase.getFactoryArea() != null) {
                        SET("factory_area = " + companyBase.getFactoryArea());
                    }
                    if (companyBase.getCoveredArea() != null) {
                        SET("covered_area = " + companyBase.getCoveredArea());
                    }
                    if (companyBase.getRawStockDosage() != null) {
                        SET("raw_stock_dosage = " + companyBase.getRawStockDosage());
                    }
                    if (companyBase.getAccessoriesDosage() != null) {
                        SET("accessories_dosage = " + companyBase.getAccessoriesDosage());
                    }
                    if (companyBase.getYearlyCapacity() != null) {
                        SET("yearly_capacity = " + companyBase.getYearlyCapacity() );
                    }
                    if (companyBase.getOutputUnit() != null) {
                        SET("output_unit = " + companyBase.getOutputUnit());
                    }
                    if (companyBase.getIsSpecialEquipment() != null) {
                        SET("is_special_equipment = " + companyBase.getIsSpecialEquipment());
                    }
                    if (companyBase.getIsDistributionRoom() != null) {
                        SET("is_distribution_room = " + companyBase.getIsDistributionRoom());
                    }
                    if (companyBase.getIsTransformer() != null) {
                        SET("is_transformer = " + companyBase.getIsTransformer());
                    }
                    if (companyBase.getIsStanCertificate() != null) {
                        SET("is_stan_certificate = " + companyBase.getIsStanCertificate());
                    }
                    if (StringUtils.isNotBlank(companyBase.getStanCertificateType())) {
                        SET("stan_certificate_type = '" + companyBase.getStanCertificateType() + "'");
                    }
                    if (companyBase.getStanCertificateId() != null) {
                        SET("stan_certificate_id = " + companyBase.getStanCertificateId());
                    }
                    if (companyBase.getIsDeclareOnline() != null) {
                        SET("is_declare_online = " + companyBase.getIsDeclareOnline());
                    }
                    if (companyBase.getIsSafeProof() != null) {
                        SET("is_safe_proof = '" + companyBase.getIsSafeProof() + "'");
                    }
                    if (StringUtils.isNotBlank(companyBase.getSafeProofArchiveNo())) {
                        SET("safe_proof_archive_no = '" + companyBase.getSafeProofArchiveNo() + "'");
                    }
                    if (companyBase.getWorkSystem() != null) {
                        SET("work_system = " + companyBase.getWorkSystem());
                    }
                    if (companyBase.getProductionDepartmentPeoples() != null) {
                        SET("production_department_peoples = " + companyBase.getProductionDepartmentPeoples());
                    }
                    if (companyBase.getOfficePeoples() != null) {
                        SET("office_peoples = " + companyBase.getOfficePeoples());
                    }
                    if (companyBase.getYearlyWorkDays() != null) {
                        SET("work_days_yearly = " + companyBase.getYearlyWorkDays());
                    }
                    if (companyBase.getIndustryType() != null) {
                        SET("industry_type = " + companyBase.getIndustryType());
                    }
                    if (companyBase.getSafetyLevel() != null) {
                        SET("safety_level = " + companyBase.getSafetyLevel());
                    }
                    if (companyBase.getCertificateStartTime() != null) {
                        SET("certificate_start_time = " + companyBase.getCertificateStartTime());
                    }
                    if (companyBase.getCertificateEndTime() != null) {
                        SET("certificate_end_time = " + companyBase.getCertificateEndTime());
                    }
                    if (companyBase.getMajorRiskSources() != null) {
                        SET("major_risk_sources = " + companyBase.getMajorRiskSources());
                    }
                    if (companyBase.getHigherRiskSources() != null) {
                        SET("higher_risk_sources = " + companyBase.getHigherRiskSources());
                    }
                    if (companyBase.getGeneralRiskSources() != null) {
                        SET("general_risk_sources = " + companyBase.getGeneralRiskSources());
                    }
                    if (companyBase.getLowRiskSources() != null) {
                        SET("low_risk_sources = " + companyBase.getLowRiskSources());
                    }

                    WHERE("id = " + companyBase.getId());
                }
            }.toString();
        }

        /**
         * 新增
         *
         * @param companyBase
         * @return
         */
        public static String insert(final CompanyBase companyBase) {
            StringBuilder sql = new StringBuilder();
            sql.append("insert into t_company_base_info values (");

            if (companyBase.getProductionDay() != null) {
                sql.append(companyBase.getProductionDay()).append(",");
            }
            if (companyBase.getTotalInvest() != null) {
                sql.append(companyBase.getTotalInvest());
            }
            if (companyBase.getFixedInvest() != null) {
                sql.append(companyBase.getFixedInvest());
            }
            if (companyBase.getLastYearIncome() != null) {
                sql.append(companyBase.getLastYearIncome());
            }
            if (companyBase.getFactoryArea() != null) {
                sql.append(companyBase.getFactoryArea()).append(",");
            }
            if (companyBase.getCoveredArea() != null) {
                sql.append(companyBase.getCoveredArea()).append(",");
            }
            if (companyBase.getRawStockDosage() != null) {
                sql.append(companyBase.getRawStockDosage()).append(",");
            }
            if (companyBase.getAccessoriesDosage() != null) {
                sql.append(companyBase.getAccessoriesDosage()).append(",");
            }
            if (companyBase.getYearlyCapacity() != null) {
                sql.append(companyBase.getYearlyCapacity()).append(",");
            }
            if (companyBase.getOutputUnit() != null) {
                sql.append(companyBase.getOutputUnit()).append(",");
            }
            if (companyBase.getIsSpecialEquipment() != null) {
                sql.append(companyBase.getIsSpecialEquipment()).append(",");
            }
            if (companyBase.getIsDistributionRoom() != null) {
                sql.append(companyBase.getIsDistributionRoom()).append(",");
            }
            if (companyBase.getIsTransformer() != null) {
                sql.append(companyBase.getIsTransformer()).append(",");
            }
            if (companyBase.getIsStanCertificate() != null) {
                sql.append(companyBase.getIsStanCertificate()).append(",");
            }
            if (StringUtils.isNotBlank(companyBase.getStanCertificateType())) {
                sql.append(companyBase.getStanCertificateType()).append(",");
            }
            if (companyBase.getStanCertificateId() != null) {
                sql.append(companyBase.getStanCertificateId()).append(",");
            }
            if (companyBase.getIsDeclareOnline() != null) {
                sql.append(companyBase.getIsDeclareOnline()).append(",");
            }
            if (companyBase.getIsSafeProof() != null) {
                sql.append(companyBase.getIsSafeProof()).append(",");
            }
            if (StringUtils.isNotBlank(companyBase.getSafeProofArchiveNo())) {
                sql.append(companyBase.getSafeProofArchiveNo()).append(",");
            }
            if (companyBase.getWorkSystem() != null) {
                sql.append(companyBase.getWorkSystem()).append(",");
            }
            if (companyBase.getProductionDepartmentPeoples() != null) {
                sql.append(companyBase.getProductionDepartmentPeoples()).append(",");
            }
            if (companyBase.getOfficePeoples() != null) {
                sql.append(companyBase.getOfficePeoples()).append(",");
            }
            if (companyBase.getYearlyWorkDays() != null) {
                sql.append(companyBase.getYearlyWorkDays()).append(",");
            }
            if (companyBase.getIndustryType() != null) {
                sql.append(companyBase.getIndustryType()).append(",");
            }
            if (companyBase.getSafetyLevel() != null) {
                sql.append(companyBase.getSafetyLevel()).append(",");
            }
            if (companyBase.getCertificateStartTime() != null) {
                sql.append(companyBase.getCertificateStartTime()).append(",");
            }
            if (companyBase.getCertificateEndTime() != null) {
                sql.append(companyBase.getCertificateEndTime()).append(",");
            }
            if (companyBase.getMajorRiskSources() != null) {
                sql.append(companyBase.getMajorRiskSources()).append(",");
            }
            if (companyBase.getHigherRiskSources() != null) {
                sql.append(companyBase.getHigherRiskSources()).append(",");
            }
            if (companyBase.getGeneralRiskSources() != null) {
                sql.append(companyBase.getGeneralRiskSources()).append(",");
            }
            if (companyBase.getLowRiskSources() != null) {
                sql.append(companyBase.getLowRiskSources()).append(",");
            }
            sql.append(")");
            return sql.toString();
        }


        /**
         * 删除
         *
         * @param id
         * @return
         */
        public static String delete(final Long id) {
            StringBuilder sql = new StringBuilder();
            sql.append("delete from t_company_standard where id = ").append(id);
            return sql.toString();
        }
    }
}
