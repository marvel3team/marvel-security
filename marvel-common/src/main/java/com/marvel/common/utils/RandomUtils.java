package com.marvel.common.utils;

/**
 * @Classname RandomUtils
 * @Description 随机数产生工具
 * @Date 2019-05-09 20:57
 * @Author zhongjie
 */
public class RandomUtils {

    /**
     * 4位随机种子
     */
    private static final int FOUR_SEEDS = 1000;

    /**
     * 随机产生4位数字
     * @return
     */
    public static int random4Bit() {
        return (int)((Math.random()*9+1)*FOUR_SEEDS);
    }
}
