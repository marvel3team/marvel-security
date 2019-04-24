package com.marvel.web.mapper;

import com.marvel.web.po.Bureau;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Classname BureauMapper
 * @Description t_bureau_info
 * 科局人员信息表Mapper操作类
 * @Date 2019/4/21 下午5:05
 * @Author zhongjie
 */
@Mapper
public interface BureauMapper {



    @Select({
            "<script>",
            "select id,ares_id,company_id,name,mobile,remark from t_bureau_info where id in",
            "<foreach collection='list' item='id' open='(' separator=',' close=')'>",
            "#{id}",
            "</foreach>",
            "</script>"
    })
    List<Bureau> getBureauByIds(@Param("list") List<Long> bureauIds);


    @Select("select id,ares_id,company_id,name,mobile,remark from t_bureau_info where id = #{id}")
    Bureau getBureauById(@Param("id") Long id);

    @Update("update t_bureau_info set ares_id=#{param.aresId},company_id=#{param.companyId},name=#{param.name},mobile=#{param.mobile},remark=#{param.remark} where id=#{param.id}")
    int update(@Param("param") Bureau bureau);
}
