package com.marvel.web.controller;

import com.marvel.web.enums.UserType;
import com.marvel.web.exception.BusinessException;
import com.marvel.framework.annotation.MarvelCheck;
import com.marvel.framework.context.RequestContext;
import com.marvel.web.service.UserService;
import com.marvel.web.service.VerifyCodeService;
import com.marvel.web.vo.VerifyCode;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname UserController
 * @Description 用户相关接口入口类
 * @Date 2019/2/19 上午10:40
 * @Author zj
 */
@RestController
@RequestMapping("/v1/user")
public class UserController {

    /**
     * log对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private VerifyCodeService verifyCodeService;


    @MarvelCheck
    @RequestMapping(value = "/send_verify.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public VerifyCode getVerify(@RequestParam(name = "phoneNo") String phoneNo){
        if (StringUtils.isBlank(phoneNo)) {
            throw BusinessException.INVALID_PARAMS;
        }
        VerifyCode code = verifyCodeService.getVerify(RequestContext.getRequestContext(), phoneNo);
        return code;
    }

    /**
     * 用户登录
     * @param type 默认50游客登录，10应急管理部门，20应急专家，30企业客户，40员工
     * @param username 用户名
     * @param code 验证码
     * @return
     */
    @MarvelCheck
    @RequestMapping(value = "/login.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String login(@RequestParam(name = "type", required = false, defaultValue = "50") Integer type,
                        @RequestParam(name = "username") String username,
                        @RequestParam(name = "code") String code){
        checkParams(type, username, code);
        String token = userService.login(type, username, code);
        return "{\"token\":\"" + token + "\"}";
    }

    /**
     * 后台注册（接口需要授权 // todo）
     * @param type 默认50游客登录，10应急管理部门，20应急专家，30企业客户，40员工
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @MarvelCheck(auth = false)
    @RequestMapping(value = "/register.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String register(@RequestParam(name = "type", required = false, defaultValue = "50") Integer type,
                        @RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password){
        checkParams(type, username, password);
        boolean result = userService.register(type, username, password);
        if (!result) {
            throw BusinessException.REGISTER_FAIL;
        }
        return "{}";
    }

    /**
     * 登录参数校验
     * @param type
     * @param username
     * @param password
     */
    private void checkParams(Integer type, String username, String password) {
        if (UserType.valueOf(type) == null || StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw BusinessException.INVALID_PARAMS;
        }
    }

    /**
     * 重置密码
     * @param oldPassword 用户名
     * @param newPassword 密码
     * @return
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/reset_password.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String resetPassword(@RequestParam(name = "oldPassword") String oldPassword,
                           @RequestParam(name = "newPassword") String newPassword){
        RequestContext requestContext = RequestContext.getRequestContext();
        checkParams(oldPassword, newPassword);
        boolean result = userService.resetPassword(requestContext.getUid(), oldPassword, newPassword);
        if (!result) {
            throw BusinessException.PASSWORD_SET_FAIL;
        }
        return "{}";
    }

    /**
     * 密码重置参数检查
     * @param oldPassword
     * @param newPassword
     */
    private void checkParams(String oldPassword, String newPassword) {
        if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)) {
            throw BusinessException.INVALID_PARAMS;
        }
        if (oldPassword.equals(newPassword)) {
            throw BusinessException.PASSWORD_TWICE_SAME;
        }
    }
}
