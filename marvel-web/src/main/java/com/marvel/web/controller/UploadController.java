package com.marvel.web.controller;

import com.marvel.framework.annotation.MarvelCheck;
import com.marvel.web.service.QiniuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname UploadController
 * @Description 资源上传Controller
 * @Date 2019/6/13 下午10:23
 * @Author zhongjie
 */
@RestController
@RequestMapping("/v1/upload")
public class UploadController {

    @Autowired
    private QiniuService qiniuService;

    /***
     * Description: //生成资源上传token
     *
     * @return 
     * @Date 下午10:27 2019/6/13
     * @Author zhongjie
     **/
    @MarvelCheck(auth = true)
    @RequestMapping(value = "/get_upload_token.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String uploadToken() {
        String uploadToken = qiniuService.createUploadToken();
        return "{\"uploadToken\":\"" + uploadToken + "\"}";
    }

}
