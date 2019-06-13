package com.marvel.web.service;

/**
 * @Classname QiniuService
 * @Description 七牛云SDK
 * @Date 2019/6/13 下午10:18
 * @Author zhongjie
 */
public interface QiniuService {

    /***
     * Description: //生成上传token
     *
     * @return
     * @Date 下午10:20 2019/6/13
     * @Author zhongjie
     **/
    String createUploadToken();

}
