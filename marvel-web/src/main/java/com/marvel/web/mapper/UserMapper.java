package com.marvel.web.mapper;

import com.marvel.web.po.User;
import org.apache.ibatis.annotations.*;

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
    @Insert("INSERT INTO t_user(uid, username, password, status, type, create_time, update_time) VALUES(#{user.uid}, #{user.username}, " +
            "#{user.password}, #{user.status}, #{user.type}, #{user.createTime}, #{user.updateTime})")
    int save(@Param("user") User user);

    /**
     * Description: 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return com.marvel.scarecrows.po.User
     * @Date 上午11:05 2019/2/19
     * @Author zj
     **/
    @Select("SELECT uid, username, password, status, type, create_time, update_time FROM t_user WHERE username = #{username}")
    User findByName(@Param("username") String username);

    /**
     * Description: 根据用户Id查询用户信息
     *
     * @param uid 用户名
     * @return com.marvel.scarecrows.po.User
     * @Date 上午11:05 2019/2/19
     * @Author zj
     **/
    @Select("SELECT uid, username, password, status, type, create_time, update_time FROM t_user WHERE uid = #{uid}")
    User findByUid(@Param("uid") Long uid);

    /**
     * Description: 更新用户状态，密码
     *
     * @param user
     * @return int
     * @Date 下午10:24 2019/3/27
     * @Author zhongjie
     **/
    @Update("UPDATE t_user SET password = #{user.password}, status = #{user.status}, update_time = #{user.updateTime} WHERE uid = #{user.uid}")
    int update(@Param("user") User user);
}
