package com.marvel.common.http.template;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Classname ApiHttpClient
 * @Description HttpClient
 * @Date 2019-04-22 14:28
 * @Author zhongjie
 */
@Component
public class ApiHttpClient {

    /**
     * log对象
     */
    private static final Logger LOG = LoggerFactory.getLogger(ApiHttpClient.class);
    /**
     * 媒体类型
     */
    private final MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");

    @Autowired
    private RestTemplate restTemplate;

    /**
     * get请求
     * @param url 请求url
     * @return
     */
    public String doGet(String url) {
        return doGet(url, null);
    }

    /**
     * get请求
     * @param url 请求url
     * @param params 请求参数
     * @return
     */
    public String doGet(String url, Map params) {
        return doGet(url, null, params);
    }

    /**
     * get请求
     * @param url 请求url
     * @param headers 请求头
     * @param params 请求参数
     * @return
     */
    public String doGet(String url, Map headers, Map params) {
        return doGet(url, type, headers, params);
    }

    /**
     * get请求
     * @param url 请求url
     * @param contentType 内容类型，对应请求头Content-Type
     * @param headers 请求头
     * @param params 请求参数
     * @return
     */
    public String doGet(String url, MediaType contentType, Map<String, String> headers, Map params) {
        HttpEntity<String> entity = new HttpEntity<String>(buildHttpHeaders(contentType, headers));
        System.out.println(buildUrl(url, params));
        return doCall(entity, HttpMethod.GET, buildUrl(url, params));
    }

    /**
     * post请求
     * @param url 请求url
     * @param params 请求参数
     * @return
     */
    public String doPost(String url, Map params) {
        return doPost(url, null, params);
    }

    /**
     * post请求
     * @param url 请求url
     * @param headers 请求头
     * @param params 请求参数
     * @return
     */
    public String doPost(String url, Map headers, Map params) {
        return doPost(url, type, headers, params);
    }

    /**
     * post请求
     * @param url 请求url
     * @param contentType 内容类型，对应请求头Content-Type
     * @param headers 请求头
     * @param params 请求参数
     * @return
     */
    public String doPost(String url, MediaType contentType, Map<String, String> headers, Map<String, Object> params) {
        //post方式，参数需要用MultiValueMap进行接收，Map接收方式不起作用，会提示参数未传
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                paramMap.add(entry.getKey(), entry.getValue());
            }
        }
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(paramMap, buildHttpHeaders(contentType, headers));
        return doCall(entity, HttpMethod.POST, url);
    }

    /**
     * call请求
     * @param entity httpEntity
     * @param httpMethod 请求方法
     * @param url 请求url
     * @return
     */
    private String doCall(HttpEntity<?> entity, HttpMethod httpMethod, String url) {
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, httpMethod, entity, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            }
        } catch (Exception e) {
            LOG.error("ApiHttpClient#doCall error. url:" + url + ",entity:" + entity.toString(), e);
        }
        return null;
    }

    /**
     * 组装url
     * @param url
     * @param params
     * @return
     */
    private String buildUrl(String url, Map<String, Object> params) {
        if (CollectionUtils.isEmpty(params)) {
            return url;
        }
        List<String> queryStrings = new ArrayList<>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            Object value = entry.getValue();
            if (value != null) {
                queryStrings.add(entry.getKey() + "=" + value);
            }
        }
        if (url.contains("?")) {
            return url + "&" + String.join("&", queryStrings);
        } else {
            return url + "?" + String.join("&", queryStrings);
        }
    }

    /**
     * 组装header
     * @param contentType
     * @param headers
     * @return
     */
    private HttpHeaders buildHttpHeaders(MediaType contentType, Map<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(contentType);
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                httpHeaders.add(entry.getKey(), entry.getValue());
            }
        }
        return httpHeaders;
    }

}
