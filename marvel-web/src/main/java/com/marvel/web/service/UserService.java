package com.marvel.web.service;

import com.marvel.framework.context.RequestContext;
import com.marvel.web.po.User;
import com.marvel.web.vo.LoginUserVo;

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
     * @param mobile
     * @param password
     * @return
     * @Date 下午10:12 2019/3/27
     * @Author zhongjie
     **/
    LoginUserVo login(String mobile, String password);

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
     * @param requestContext
     * @param code
     * @param password
     * @return
     * @Date 下午11:29 2019/3/27
     * @Author zhongjie
     **/
    boolean resetPassword(RequestContext requestContext, String code, String password);

    /**
     * 根据用户ID获取用户
     * @param uid
     * @return
     */
    User getUser(Long uid);
}
