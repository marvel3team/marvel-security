package com.marvel.web.converts;

import com.marvel.web.po.User;
import com.marvel.web.vo.LoginUserVo;

/**
 * @Classname LoginUserVoConvert
 * @Description 转换器 PO->VO
 * @Date 2019/4/22 下午10:57
 * @Author zhongjie
 */
public class LoginUserVoConvert {
    /**
     * 对象转换
     * @param user
     * @param token
     * @return
     */
    public static LoginUserVo toLoginUserVo(User user, String token) {
        LoginUserVo vo = new LoginUserVo();
        vo.setToken(token);
        LoginUserVo.UserVo userVo = new LoginUserVo.UserVo();
        userVo.setMobile(user.getMobile());
        userVo.setType(user.getType());
        userVo.setUsername(user.getUsername());
        vo.setUser(userVo);
        LoginUserVo.Message message = new LoginUserVo.Message();
        vo.setMessage(message);
        return vo;
    }
}
