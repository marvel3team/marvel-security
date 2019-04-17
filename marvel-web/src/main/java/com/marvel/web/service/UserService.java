package com.marvel.web.service;

/**
 * @Classname UserService
 * @Description 用户service接口类
 * @Date 2019/2/19 上午10:40
 * @Author zj
 */
public interface UserService {

    /**
     * Description: 用户登录
     *
     * @param type
     * @param username
     * @param password
     * @return
     * @Date 下午10:12 2019/3/27
     * @Author zhongjie
     **/
    String login(Integer type, String username, String password);

    /**
     * Description: 后台注册
     *
     * @param type
     * @param username
     * @param password
     * @return
     * @Date 下午10:54 2019/3/27
     * @Author zhongjie
     **/
    boolean register(Integer type, String username, String password);

    /**
     * Description: 更新密码
     *
     * @param uid
     * @param oldPassword
     * @param newPassword
     * @return
     * @Date 下午11:29 2019/3/27
     * @Author zhongjie
     **/
    boolean resetPassword(long uid, String oldPassword, String newPassword);
}
