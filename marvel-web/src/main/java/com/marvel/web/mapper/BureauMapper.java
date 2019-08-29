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

    @SelectProvider(type = BureauSqlBuilder.class,method = "selectById")
    @Results(id = "bureauMap",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "areaId",column = "area_id"),
            @Result(property = "companyId",column = "company_id"),
            @Result(property = "name",column = "name"),
            @Result(property = "mobile",column = "mobile"),
            @Result(property = "remark",column = "remark")
    })
    Bureau getBureauById( Long id);

    @SelectProvider(type = BureauSqlBuilder.class, method = "selectByIds")
    @ResultMap("bureauMap")
    List<Bureau> getBureauByIds(@Param("list") List<Long> bureauIds);

    @UpdateProvider(type = BureauSqlBuilder.class, method = "update")
    int update(Bureau bureau);


    @InsertProvider(type = BureauSqlBuilder.class, method = "insert")
    int insert(Bureau bureau);

    @DeleteProvider(type = BureauSqlBuilder.class, method = "delete")
    int delete(Long id);


    class BureauSqlBuilder {

        /**
         * 查询
         * @param map
         * @return
         */
        public static String selectByIds(Map map) {
            List<Long> ids = (List<Long>) map.get("list");
            StringBuilder sql = new StringBuilder("select id, area_id,name,company_id ,mobile,remark");
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
            StringBuilder sql = new StringBuilder("select id,area_id,company_id,name,mobile,remark ");
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
                sql.append("company_id = ").append(bureau.getCompanyId()).append(",");
            }
            if (StringUtils.isNotBlank(bureau.getName())){
                sql.append("name = '").append(bureau.getName()).append("',");
            }
            if (StringUtils.isNotBlank(bureau.getMobile())){
                sql.append("mobile = '").append(bureau.getMobile()).append("',");
            }
            if (StringUtils.isNotBlank(bureau.getRemark())){
                sql.append("remark = '").append(bureau.getRemark()).append("',");
            }
            sql.deleteCharAt(sql.length() -1);
            sql.append(" where id = ").append(bureau.getId());
            return sql.toString();
        }

        public static String insert(Bureau bureau) {
            StringBuilder sql = new StringBuilder("insert into t_bureau_info values (");
            if (bureau.getId() != null) {
                sql.append(bureau.getId()).append(",");
            }
            if (bureau.getAreaId() != null) {
                sql.append(bureau.getAreaId()).append(",");
            }
            if (bureau.getCompanyId() != null){
                sql.append(bureau.getCompanyId()).append(",");
            }
            if (StringUtils.isNotBlank(bureau.getName())){
                sql.append("'").append(bureau.getName()).append("',");
            }
            if (StringUtils.isNotBlank(bureau.getMobile())){
                sql.append("'").append(bureau.getMobile()).append("',");
            }
            if (StringUtils.isNotBlank(bureau.getRemark())){
                sql.append("'").append(bureau.getRemark()).append("',");
            }
            sql.deleteCharAt(sql.length() -1);
            sql.append(")");
            return sql.toString();
        }

        public static String delete(final Long id){
            StringBuilder sql = new StringBuilder("delete from t_bureau_info where id = ");
            sql.append(id);
            return sql.toString();
        }
    }
}
