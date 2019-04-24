package com.marvel.web.mapper;

import com.marvel.web.po.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Classname UserMapper
 * @Description 用户mapper类，方法与sql映射（注：不能用方法构造，方法重名会报错）
 * @Date 2019/2/19 上午10:40
 * @Author zj
 */
@Mapper
public interface UserMapper {

    /**
     * Description: 保存用户信息
     *
     * @param user 用户数据对象
     * @return int
     * @Date 下午12:24 2019/2/19
     * @Author zj
     **/
    @Insert("INSERT INTO t_user(id, username, password, mobile, status, type, create_time, update_time, remark) VALUES(#{user.id}, #{user.username}, " +
            "#{user.password}, #{user.mobile}, #{user.status}, #{user.type}, #{user.createTime}, #{user.updateTime}, #{user.remark})")
    int save(@Param("user") User user);

    /**
     * Description: 根据用户Id查询用户信息
     *
     * @param id 用户ID
     * @return com.marvel.scarecrows.po.User
     * @Date 上午11:05 2019/2/19
     * @Author zj
     **/
    @Select("SELECT id, username, password, mobile, status, type, create_time, update_time, remark FROM t_user WHERE id = #{id}")
    User findByUid(@Param("id") Long id);

    /**
     * Description: 根据手机号查询用户信息
     *
     * @param mobile 用户名
     * @return com.marvel.scarecrows.po.User
     * @Date 上午11:05 2019/2/19
     * @Author zj
     **/
    @Select("SELECT id, username, password, mobile, status, type, create_time, update_time, remark FROM t_user WHERE mobile = #{mobile}")
    User findByMobile(@Param("mobile") String mobile);

    /**
     * Description: 更新用户状态，密码
     *
     * @param user
     * @return int
     * @Date 下午10:24 2019/3/27
     * @Author zhongjie
     **/
    @Update("UPDATE t_user SET password = #{user.password}, status = #{user.status}, update_time = #{user.updateTime} WHERE id = #{user.id}")
    int update(@Param("user") User user);

    /**
     * Description: 获取最近变动的前100用户数据
     *
     * @return com.marvel.scarecrows.po.User
     * @Date 上午11:05 2019/2/19
     * @Author zj
     **/
    @Select("SELECT id, username, password, mobile, status, type, create_time, update_time, remark FROM t_user order by update_time desc limit 100")
    List<User> getHotUsers();
}
