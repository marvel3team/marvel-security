package com.marvel.web.service.impl;

import com.marvel.web.service.QiniuService;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

/**
 * @Classname QiniuServiceImpl
 * @Description QiniuServiceImpl
 * @Date 2019/6/13 下午10:19
 * @Author zhongjie
 */
@Service
public class QiniuServiceImpl implements QiniuService {

    private static String AK = "pAdfCKNB7iLdWS1YmOZqak1L3tqKGuzUclPaQsh8";
    private static String SK = "LxSnRJ-UFcU0Y1LkXlwVvu747iQZ_4Sljvpjay-D";
    private static String BUCKET = "images";

    @Override
    public String createUploadToken() {
        return Auth.create(AK, SK).uploadToken(BUCKET);
    }
}
