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
    @Select("select id, production_day as productionDay, total_invest as totalInvest, fixed_invest as fixedInvest, last_year_income as lastYearIncome, factory_area as factoryArea,\n" +
            "covered_area as coveredArea, raw_stock_dosage as rawStockDosage, accessories_dosage as accessoriesDosage, yearly_capacity as yearlyCapacity, output_unit as outputUnit, \n" +
            "is_special_equipment as isSpecialEquipment, is_distribution_room as isDistributionRoom, is_transformer as isTransformer, is_stan_certificate as isStanCertificate, \n" +
            "stan_certificate_type as stanCertificate, stan_certificate_id as stanCertificateId, is_declare_online as isDeclareOnline, is_safe_proof as isSafeProof, \n" +
            "safe_proof_archive_no as safeProofArchiveNo, work_system as workSystem, production_department_peoples as productionDepartmentPeoples, office_peoples as officePeoples, \n" +
            "work_days_yearly as workDaysYearly from t_company_base_info where id = #{id}")
    CompanyBase getCompanBaseById(@Param("id") Long id);
}
