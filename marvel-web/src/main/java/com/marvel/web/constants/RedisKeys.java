package com.marvel.web.constants;

/**
 * @Classname RedisKeys
 * @Description Redis key
 * @Date 2019-04-22 19:26
 * @Author zhongjie
 */
public class RedisKeys {

    /**
     * 验证码
     */
    public static final String VERIFY_CODE = "v:c:u:";

    /**
     * key组装
     * @param prefix
     * @param last
     * @return
     */
    public static String getKey(String prefix, String last) {
        return prefix + last;
    }
}
