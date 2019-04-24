package com.marvel.web.service.impl;

import com.marvel.web.exception.BusinessException;
import com.marvel.web.mapper.BureauMapper;
import com.marvel.web.po.Bureau;
import com.marvel.web.service.BureauService;
import com.marvel.web.vo.BureauInfoReqVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Classname BureauServiceImpl
 * @Description
 * @Author andy
 * @Date 2019/4/24 下午11:35
 * @Version 1.0
 */
@Service
public class BureauServiceImpl implements BureauService {


    @Resource
    private BureauMapper bureauMapper;

    @Override
    public String updateBureauInfo(BureauInfoReqVo bureauInfoReqVo) {
        Bureau bureau = bureauMapper.getBureauById(bureauInfoReqVo.getId());
        if (null == bureau){
            throw BusinessException.BUREAU_NOT_EXISTS;
        }
        bureau.setAresId(bureauInfoReqVo.getAreaId());
        bureau.setName(bureauInfoReqVo.getName());
        bureau.setMobile(bureauInfoReqVo.getMobile());
        bureau.setRemark(bureauInfoReqVo.getRemark());
        int update = bureauMapper.update(bureau);
        if (update < 1){
            throw BusinessException.UPDATE_ERROR;
        }
        return "{}";
    }
}
