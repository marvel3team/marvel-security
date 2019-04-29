package com.marvel.web.mapper;

import com.marvel.web.po.Bureau;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Classname BureauMapper
 * @Description t_bureau_info
 * 科局人员信息表Mapper操作类
 * @Date 2019/4/21 下午5:05
 * @Author zhongjie
 */
@Mapper
public interface BureauMapper {


    @SelectProvider(type = BureauSqlBuilder.class,method = "selectByIds")
    List<Bureau> getBureauByIds(@Param("list") List<Long> bureauIds);


    @SelectProvider(type = BureauSqlBuilder.class,method = "selectById")
    Bureau getBureauById( Long id);

    @UpdateProvider(type = BureauSqlBuilder.class,method = "update")
    int update(Bureau bureau);


    class BureauSqlBuilder {

        /**
         * 查询
         * @param map
         * @return
         */
        public static String selectByIds(Map map) {
            List<Long> ids = (List<Long>) map.get("list");
            StringBuilder sql = new StringBuilder("select id,area_id as areaId,company_id as companyId,name,mobile,remark");
            sql.append(" from t_bureau_info where");
            sql.append(" id in ").append("(");
            for (Long id : ids) {
                sql.append(id).append(",");
            }
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
            return sql.toString();
        }

        public static String selectById(final Long id){
            StringBuilder sql = new StringBuilder("select id,area_id as areaId,company_id as companyId,name,mobile,remark ");
            sql.append(" from t_bureau_info ");
            sql.append("where id = ").append(id);
            return sql.toString();
        }

        public static String update(Bureau bureau) {
            StringBuilder sql = new StringBuilder("update t_bureau_info set ");
            if (bureau.getAreaId() != null) {
                sql.append("area_id = ").append(bureau.getAreaId()).append(",");
            }
            if (bureau.getCompanyId() != null){
                sql.append("company_id").append(bureau.getCompanyId()).append(",");
            }
            if (StringUtils.isNotBlank(bureau.getName())){
                sql.append("name").append(bureau.getName()).append(",");
            }
            if (StringUtils.isNotBlank(bureau.getMobile())){
                sql.append("mobile").append(bureau.getMobile()).append(",");
            }
            if (StringUtils.isNotBlank(bureau.getRemark())){
                sql.append("remark").append(bureau.getRemark()).append(",");
            }
            sql.deleteCharAt(sql.length() -1);
            sql.append(" where id = ").append(bureau.getId());
            return sql.toString();
        }
    }
}
