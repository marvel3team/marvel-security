package com.marvel.web.controller;

import com.marvel.web.enums.UserType;
import com.marvel.web.exception.BusinessException;
import com.marvel.framework.annotation.MarvelCheck;
import com.marvel.framework.context.RequestContext;
import com.marvel.web.po.User;
import com.marvel.web.service.UserService;
import com.marvel.web.service.VerifyCodeService;
import com.marvel.web.vo.LoginUserVo;
import com.marvel.web.vo.VerifyCode;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private UserService userService;

    @Autowired
    private VerifyCodeService verifyCodeService;


    @MarvelCheck(auth = true)
    @RequestMapping(value = "/send_verify.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public VerifyCode getVerify(){
        User user = userService.getUser(RequestContext.getRequestContext().getUid());
        VerifyCode code = verifyCodeService.getVerify(user.getId());
        return code;
    }

    /**
     * 用户登录
     * @param mobile 手机号
     * @param password 验证码
     * @return
     */
    @MarvelCheck
    @RequestMapping(value = "/login.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public LoginUserVo login(@RequestParam(name = "mobile") String mobile,
                             @RequestParam(name = "password") String password){
        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)) {
            throw BusinessException.INVALID_PARAMS;
        }
        return userService.login(mobile, password);
    }

    /**
     * 后台注册（接口需要授权 // todo）
     * @param type 默认1企业 2政府 3专家 4公司业务员
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @MarvelCheck(auth = false)
    @RequestMapping(value = "/register.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String register(@RequestParam(name = "type", required = false, defaultValue = "1") Integer type,
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
     * @param code 验证码
     * @param password 密码
     * @return
     */
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/reset_password.json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String resetPassword(@RequestParam(name = "code") String code,
                           @RequestParam(name = "password") String password){
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(password)) {
            throw BusinessException.INVALID_PARAMS;
        }
        boolean result = userService.resetPassword(RequestContext.getRequestContext(), code, password);
        if (!result) {
            throw BusinessException.PASSWORD_SET_FAIL;
        }
        return "{}";
    }
}
