package com.marvel.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname LoginUserVo
 * @Description 用户登录后
 * @Date 2019/4/22 下午10:34
 * @Author zhongjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserVo {

    /**
     * token
     */
    private String token;
    /**
     * 用户信息
     */
    private UserVo user;
    /**
     * 消息标记
     */
    private Message message;

    @Data
    @NoArgsConstructor
    public static class UserVo{
        private String username;
        private String mobile;
        private int type;
    }

    @Data
    @NoArgsConstructor
    public static class Message{
        private int hasCheck;
        private int hasPlan;
    }
}
