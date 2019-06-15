package com.marvel.web.mapper;

import com.marvel.web.po.CompanyBase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}
