package com.marvel.web.service;


import com.marvel.common.models.PageBean;
import com.marvel.web.vo.CompanyDetailVo;
import com.marvel.web.vo.CompanyListVo;

/**
 * @Classname CompanyService
 * @Description 
 * @Author andy
 * @Date  2019/4/22 下午10:01
 */
public interface CompanyService {


    /**
    * @Title: getCompanyList
    * @Description: 分页查询公司信息
    * @param cursor
    * @param count
    * @return:
    * @throws:
    * @author: andy
    * @date: 2019/4/22 下午10:42
    */
    PageBean<CompanyListVo> getCompanyList(Integer cursor, Integer count);


    /**
    * @Title
    * @Description 查询企业详情
    * @param id
    * @return
    * @throws
    * @author andy
    * @date 2019/4/22 下午11:20
    */
    CompanyDetailVo getCompanyInfo(Long id);
}
