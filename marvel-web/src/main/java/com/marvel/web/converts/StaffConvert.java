package com.marvel.web.converts;

import com.marvel.web.controller.req.StaffInfoReq;
import com.marvel.web.po.Staff;

/**
 * @Classname StaffConvert
 * @Description Staff转换器
 * @Date 2019/4/24 下午11:26
 * @Author zhongjie
 */
public class StaffConvert {

    /**
     * 转换
     * @param staffInfoReq
     * @return
     */
    public static Staff convert(StaffInfoReq staffInfoReq) {
        Staff staff = new Staff();
        staff.setId(staffInfoReq.getId());
        staff.setCompanyId(staffInfoReq.getCompanyId());
        staff.setName(staffInfoReq.getName());
        staff.setMobile(staffInfoReq.getMobile());
        staff.setIdCardNo(staffInfoReq.getIdCardNo());
        staff.setDepartmentId(staffInfoReq.getDepartmentId());
        staff.setWorkshopId(staffInfoReq.getWorkshopId());
        staff.setTeamId(staffInfoReq.getTeamId());
        staff.setProfessionId(staffInfoReq.getProfessionId());
        staff.setSpecialPersonnelId(staffInfoReq.getSpecialPersonnelId());
        staff.setIsSpecialPersonnel(staff.getSpecialPersonnelId() > 0 ? 1 : 2);
        staff.setRemark(staffInfoReq.getRemark());
        return staff;
    }
}
