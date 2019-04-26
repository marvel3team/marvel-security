package com.marvel.web.service.impl;

import com.marvel.framework.context.RequestContext;
import com.marvel.web.exception.BusinessException;
import com.marvel.web.mapper.StaffMapper;
import com.marvel.web.po.Staff;
import com.marvel.web.service.StaffService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Classname StaffServiceImpl
 * @Description 员工service实现类
 * @Date 2019/4/24 下午11:37
 * @Author zhongjie
 */
@Service
public class StaffServiceImpl implements StaffService {

    @Resource
    private StaffMapper staffMapper;

    @Override
    public boolean update(RequestContext requestContext, Staff staff) {
        Long id = staff.getId();
        Staff originStaff = staffMapper.findById(id);
        if (Objects.isNull(originStaff)) {
            throw BusinessException.DATA_NOT_EXISTS;
        }
        replaceStaff(staff, originStaff);
        int update = staffMapper.update(staff);
        return update > 0;
    }

    /**
     * 更新最新内容
     * @param staff
     * @param originStaff
     */
    private void replaceStaff(Staff staff, Staff originStaff) {
        if (staff.getCompanyId() == null || staff.getCompanyId() <= 0) {
            staff.setCompanyId(originStaff.getCompanyId());
        }
        if (StringUtils.isBlank(staff.getName())) {
            staff.setName(originStaff.getName());
        }
        if (StringUtils.isBlank(staff.getMobile())) {
            staff.setMobile(originStaff.getMobile());
        }
        if (StringUtils.isBlank(staff.getIdCardNo())) {
            staff.setIdCardNo(originStaff.getIdCardNo());
        }
        if (staff.getDepartmentId() == null || staff.getDepartmentId() <= 0) {
            staff.setDepartmentId(originStaff.getDepartmentId());
        }
        if (staff.getWorkshopId() == null || staff.getWorkshopId() <= 0) {
            staff.setWorkshopId(originStaff.getWorkshopId());
        }
        if (staff.getTeamId() == null || staff.getTeamId() <= 0) {
            staff.setTeamId(originStaff.getTeamId());
        }
        if (staff.getProfessionId() == null || staff.getProfessionId() <= 0) {
            staff.setProfessionId(originStaff.getProfessionId());
        }
        if (staff.getIsSpecialPersonnel() == null || staff.getIsSpecialPersonnel() <= 0) {
            staff.setIsSpecialPersonnel(originStaff.getIsSpecialPersonnel());
        }
        if (staff.getSpecialPersonnelId() == null || staff.getSpecialPersonnelId() <= 0) {
            staff.setSpecialPersonnelId(originStaff.getSpecialPersonnelId());
        }
        if (StringUtils.isBlank(staff.getRemark())) {
            staff.setRemark(originStaff.getRemark());
        }
    }
}
