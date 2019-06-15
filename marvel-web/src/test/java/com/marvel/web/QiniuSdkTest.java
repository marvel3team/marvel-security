package com.marvel.web;

import com.qiniu.util.Auth;

/**
 * @Classname QiniuSdkTest
 * @Description 七牛SDK
 * @Date 2019/6/13 下午9:58
 * @Author zhongjie
 */
public class QiniuSdkTest {

    private static String AK = "pAdfCKNB7iLdWS1YmOZqak1L3tqKGuzUclPaQsh8";
    private static String SK = "LxSnRJ-UFcU0Y1LkXlwVvu747iQZ_4Sljvpjay-D";

    public static void main(String[] args) {

        Auth auth = Auth.create(AK, SK);
        System.out.println(auth.uploadToken("images"));

    }

}
