package com.marvel.web.mapper;

import com.marvel.web.po.ServiceInfo;
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

    @Insert("INSERT INTO t_service_info(id, service_type, service_name, service_cycle) VALUES(#{serviceInfo.id}, #{serviceInfo.serviceType}, #{serviceInfo.serviceName}, #{serviceInfo.serviceCycle})")
    int save(@Param("serviceInfo") ServiceInfo serviceInfo);

    @SelectProvider(type = SqlBuilder.class, method = "buildFindByPage")
    @Results(id = "serviceMap", value = {
            @Result(property = "serviceType", column = "service_type"),
            @Result(property = "serviceName", column = "service_name"),
            @Result(property = "serviceCycle", column = "service_cycle")
    })
    List<ServiceInfo> findByPage(Long cursor, Integer count);

    class SqlBuilder {
        public static String buildFindByPage(final Long cursor, final Integer count) {
            StringBuilder sql = new StringBuilder("SELECT id, service_type, service_name, service_cycle FROM t_service_info where 1=1");
            if (cursor != null && cursor > 0) {
                sql.append(" and id < " + cursor);
            }
            sql.append(" order by id desc limit " + count);
            return sql.toString();
        }
    }
}
