package com.marvel.web.mapper;

import com.marvel.web.po.ServiceInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname ServiceMapper
 * @Description 服务内容mapper
 * @Date 2019/4/24 下午10:16
 * @Author zhongjie
 */
@Mapper
public interface ServiceMapper {

    @Insert("INSERT INTO t_service_info(id, service_type, service_name, service_cycle, service_desc) VALUES(#{serviceInfo.id}, #{serviceInfo.serviceType}, #{serviceInfo.serviceName}, #{serviceInfo.serviceCycle}, #{serviceInfo.serviceDesc})")
    int save(@Param("serviceInfo") ServiceInfo serviceInfo);

    @SelectProvider(type = SqlBuilder.class, method = "buildFindByPage")
    @Results(id = "serviceMap", value = {
            @Result(property = "serviceType", column = "service_type"),
            @Result(property = "serviceName", column = "service_name"),
            @Result(property = "serviceCycle", column = "service_cycle"),
            @Result(property = "serviceDesc", column = "service_desc")
    })
    List<ServiceInfo> findByPage(String serviceName, Long cursor, Integer count);

    @Delete("DELETE FROM t_service_info WHERE id = #{id}")
    int deleteById(@Param("id") Long id);

    @Select("SELECT id, service_type, service_name, service_cycle, service_desc FROM t_service_info WHERE id = #{id}")
    ServiceInfo queryById(@Param("id") Long id);

    @Update("UPDATE t_service_info SET service_name = #{serviceInfo.serviceName}, service_desc = #{serviceInfo.serviceDesc} WHERE id = #{serviceInfo.id}")
    int update(@Param("serviceInfo") ServiceInfo serviceInfo);

    class SqlBuilder {
        public static String buildFindByPage(final String serviceName, final Long cursor, final Integer count) {
            StringBuilder sql = new StringBuilder("SELECT id, service_type, service_name, service_cycle, service_desc FROM t_service_info where 1=1");
            if (StringUtils.isNotBlank(serviceName)) {
                sql.append(" and ").append("service_name=").append(serviceName);
            }
            if (cursor != null && cursor > 0) {
                sql.append(" and id < " + cursor);
            }
            sql.append(" order by id desc limit " + count);
            return sql.toString();
        }
    }
}
