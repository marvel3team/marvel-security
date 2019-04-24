package com.marvel.framework.utils;

import com.marvel.common.utils.encrypt.AESEncrypter;
import com.marvel.framework.exception.ExceptionFactory;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Mauth工具类
 * @author zz
 */
public class MauthUtils {

    private static final AESEncrypter ENCRYPTER = AESEncrypter.getInstance();

    /**
     * 一天+五分钟
     */
    private static final long EXPIRES_TIME = 86700000;

    /**
     * token按分隔符切分的数组长度
     */
    private static final int MAUTH_LENTH = 3;

    /**
     * token拼接分割符
     */
    private static final String CONNECT = ":";

    /**
     * token头拼接
     */
    private static final String MAUTH_HEAD = "MAuth";

    /**
     * token拼接符
     */
    private static final String SPACES = "-";


    /**
     * 是否可以验证
     * @param mauth mauth
     * @return  true可以使用mauth验证 false不可使用
     */
    public static final boolean canAuth(String mauth) {
        return !StringUtils.isBlank(mauth) && mauth.toLowerCase().startsWith(MAUTH_HEAD.toLowerCase() + SPACES)
                && mauth.split(SPACES).length == MAUTH_LENTH;
    }

    /**
     * 生成Mauth
     * @param uid
     * @return
     */
    public static final String create(long uid){
        long expiringDate = System.currentTimeMillis() + EXPIRES_TIME;
        String tempMauth = expiringDate + CONNECT + uid;
        return MAUTH_HEAD + SPACES + ENCRYPTER.encrypt(tempMauth) + SPACES + MAUTH_HEAD;
    }

    /**
     * 获取用户id
     * @param mauth
     * @return
     */
    public static final Long getUid(String mauth) {
        String[] ss = mauth.split(SPACES);
        if (ss.length == MAUTH_LENTH) {
            return getAuthModel(ss[1]).getUid();
        }
        throw ExceptionFactory.INVALID_TOKEN;
    }

    /**
     * 解析token数据
     * @param mauth token数据
     * @return  用户数据
     */
    public static final AuthModel getAuthModel(String mauth) {
        AuthModel authModel = new AuthModel();
        try {
            String decryptedString = ENCRYPTER.decryptAsString(mauth);
            String[] timeAndUid = decryptedString.split(CONNECT);
            long time = NumberUtils.toLong(timeAndUid[0], 0);
            long now = System.currentTimeMillis();
            if (now > time) {
                throw ExceptionFactory.TOKEN_EXPIRE;
            }
            long uid = NumberUtils.toLong(timeAndUid[1], 0);
            if (uid <= 0) {
                throw ExceptionFactory.INVALID_UID;
            }
            authModel.setUid(uid);
            authModel.setExpiringDate(time);
            return authModel;
        } catch (Exception e) {
            throw e;
        }
    }


    @Data
    public static class AuthModel {

        /**
         * 用户ID
         */
        private long uid;

        /**
         * mauth过期时间
         */
        private long expiringDate;
    }

}
