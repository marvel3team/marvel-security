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
    @Select("select * from t_company_base_info where id = #{id}")
    CompanyBase getCompanBaseById(@Param("id") Long id);
}
