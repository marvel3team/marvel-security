package com.marvel.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Classname VerifyCode
 * @Description 验证码
 * @Date 2019-04-22 19:06
 * @Author zhongjie
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyCode {
    /**
     * 验证码
     */
    private String code;
    /**
     * 有效期
     */
    private Long duration;
}
