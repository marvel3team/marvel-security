package com.marvel.web.mapper;

import com.marvel.web.po.Staff;
import org.apache.ibatis.annotations.*;

/**
 * @Classname StaffMapper
 * @Description 员工Mapper
 * @Date 2019/4/24 下午11:01
 * @Author zhongjie
 */
@Mapper
public interface StaffMapper {

    @Select("SELECT id, company_id, name, mobile, id_card_no, department_id, workshop_id, team_id, profession_id, is_special_personnel, special_personnel_id, remark FROM t_company_staff")
    @Results(id = "staffMap", value = {
            @Result(property = "id", column = "id"),
            @Result(property = "companyId", column = "company_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "idCardNo", column = "id_card_no"),
            @Result(property = "departmentId", column = "department_id"),
            @Result(property = "workshopId", column = "workshop_id"),
            @Result(property = "teamId", column = "team_id"),
            @Result(property = "professionId", column = "profession_id"),
            @Result(property = "isSpecialPersonnel", column = "is_special_personnel"),
            @Result(property = "specialPersonnelId", column = "special_personnel_id"),
            @Result(property = "remark", column = "remark"),
    })
    Staff findById(@Param("id") Long id);

    @Update("UPDATE t_company_staff set company_id = #{staff.companyId}, name = #{staff.name}, mobile = #{staff.mobile}, id_card_no = #{staff.idCardNo}, " +
            "department_id = #{staff.departmentId}, workshop_id = #{staff.workshopId}, team_id = #{staff.teamId}, profession_id = #{staff.professionId}, " +
            "is_special_personnel = #{staff.isSpecialPersonnel}, special_personnel_id = #{staff.specialPersonnelId} WHERE id = #{staff.id}")
    int update(@Param("staff") Staff staff);

    @Insert("INSERT INTO t_company_staff(id, company_id, name, mobile, id_card_no, department_id, workshop_id, team_id, profession_id, is_special_personnel, special_personnel_id, remark) VALUES(" +
            "#{staff.id}, #{staff.companyId}, #{staff.name}, #{staff.mobile}, #{staff.idCardNo}, #{staff.departmentId}," +
            "#{staff.workshopId}, #{staff.teamId}, #{staff.professionId}, #{staff.isSpecialPersonnel}, #{staff.specialPersonnelId}," +
            "#{staff.remark})")
    int insert(@Param("staff") Staff staff);

}
