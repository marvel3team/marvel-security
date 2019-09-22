package com.marvel.web.mapper;

import com.marvel.web.po.CompanyBase;
import com.marvel.web.po.CompanyStandard;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

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
            "stan_certificate_type as stanCertificateType, stan_certificate_id as stanCertificateId, is_declare_online as isDeclareOnline, is_safe_proof as isSafeProof,  " +
            "safe_proof_archive_no as safeProofArchiveNo, work_system as workSystem, production_department_peoples as productionDepartmentPeoples, office_peoples as officePeoples,  " +
            "work_days_yearly as yearlyWorkDays, industry_type as industryType, safety_level as safetyLevel, certificate_start_time as certificateStartTime, certificate_end_time as certificateEndTime, " +
            "major_risk_sources as majorRiskSources, higher_risk_sources as higherRiskSources, general_risk_sources as generalRiskSources, low_risk_sources as lowRiskSources from t_company_base_info where id = #{id}")
    CompanyBase getCompanBaseById(@Param("id") Long id);


    /**
     * 插入
     *
     * @param companyBase
     * @return
     */
    @Insert("insert into t_company_base_info values (" +
            "#{companyBase.id},#{companyBase.productionDay},#{companyBase.totalInvest},#{companyBase.fixedInvest},#{companyBase.lastYearIncome},#{companyBase.factoryArea},#{companyBase.coveredArea}," +
            "#{companyBase.rawStockDosage},#{companyBase.accessoriesDosage},#{companyBase.yearlyCapacity}," +
            "#{companyBase.outputUnit},#{companyBase.isSpecialEquipment},#{companyBase.isDistributionRoom},#{companyBase.isTransformer},#{companyBase.isStanCertificate}," +
            "#{companyBase.stanCertificateType},#{companyBase.stanCertificateId},#{companyBase.isDeclareOnline},#{companyBase.isSafeProof}," +
            "#{companyBase.safeProofArchiveNo},#{companyBase.workSystem},#{companyBase.productionDepartmentPeoples},#{companyBase.officePeoples}," +
            "#{companyBase.yearlyWorkDays},#{companyBase.industryType},#{companyBase.safetyLevel},#{companyBase.certificateStartTime},#{companyBase.certificateEndTime},#{companyBase.majorRiskSources}," +
            "#{companyBase.higherRiskSources},#{companyBase.generalRiskSources},#{companyBase.lowRiskSources})")
    int insertCompanyBase(@Param("companyBase") CompanyBase companyBase);


    /**
     * 更新
     *
     * @param companyBase
     * @return
     */
    @UpdateProvider(type = CompanyBaseSqlBuilder.class, method = "update")
    int updateCompanyBase(CompanyBase companyBase);


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
                    "stan_certificate_type as stanCertificateType, stan_certificate_id as stanCertificateId, is_declare_online as isDeclareOnline, is_safe_proof as isSafeProof,  " +
                    "safe_proof_archive_no as safeProofArchiveNo, work_system as workSystem, production_department_peoples as productionDepartmentPeoples, office_peoples as officePeoples,  " +
                    "work_days_yearly as yearlyWorkDays, industry_type as industryType, safety_level as safetyLevel, certificate_start_time as certificateStartTime, certificate_end_time as certificateEndTime, " +
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
                    if (StringUtils.isNotBlank(companyBase.getStanCertificateId())) {
                        SET("stan_certificate_id = " + companyBase.getStanCertificateId());
                    }
                    if (companyBase.getIsDeclareOnline() != null) {
                        SET("is_declare_online = " + companyBase.getIsDeclareOnline());
                    }
                    if (companyBase.getIsSafeProof() != null) {
                        SET("is_safe_proof = " + companyBase.getIsSafeProof());
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
                    if (StringUtils.isNotBlank(companyBase.getMajorRiskSources())) {
                        SET("major_risk_sources = " + companyBase.getMajorRiskSources());
                    }
                    if (StringUtils.isNotBlank(companyBase.getHigherRiskSources())) {
                        SET("higher_risk_sources = " + companyBase.getHigherRiskSources());
                    }
                    if (StringUtils.isNotBlank(companyBase.getGeneralRiskSources())) {
                        SET("general_risk_sources = " + companyBase.getGeneralRiskSources());
                    }
                    if (StringUtils.isNotBlank(companyBase.getLowRiskSources())) {
                        SET("low_risk_sources = " + companyBase.getLowRiskSources());
                    }

                    WHERE("id = " + companyBase.getId());
                }
            }.toString();
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
