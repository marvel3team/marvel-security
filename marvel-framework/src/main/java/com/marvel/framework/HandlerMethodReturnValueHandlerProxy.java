package com.marvel.framework;

import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname HandlerMethodReturnValueHandlerProxy
 * @Description api返回值统一封装处理 {"code":1, "data":{}, "message":"success", "httpStatus":200}
 * @Date 2019/2/19 下午5:20
 * @Author zj
 */
public class HandlerMethodReturnValueHandlerProxy implements HandlerMethodReturnValueHandler {

    private HandlerMethodReturnValueHandler handler;

    public HandlerMethodReturnValueHandlerProxy(HandlerMethodReturnValueHandler handler) {
        this.handler = handler;
    }

    /**
     * Description: 支持的处理类型
     *
     * @param methodParameter 方法相关参数
     * @return boolean
     * @Date 下午6:11 2019/2/19
     * @Author zj
     **/
    @Override
    public boolean supportsReturnType(MethodParameter methodParameter) {
        return handler.supportsReturnType(methodParameter);
    }

    /**
     * Description: 返回结果统一处理入口，目前仅支持 String 和 Object 两种返回类型的处理
     *
     * @param returnValue 返回结果
     * @param methodParameter 方法相关信息
     * @param modelAndViewContainer 视图容器
     * @param nativeWebRequest webRequest
     * @return void
     * @Date 下午6:12 2019/2/19
     * @Author zj
     **/
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest) throws Exception {
        if (StringUtils.isEmpty(returnValue)) {
            handler.handleReturnValue("{\"code\":1,\"data\":{},\"message\":\"success\",\"httpStatus\":200}", methodParameter, modelAndViewContainer, nativeWebRequest);
        } else if (returnValue instanceof String) {
            StringBuilder result = new StringBuilder();
            result.append("{\"code\":1,").append("\"data\":").append(returnValue).append(",\"message\":\"success\",\"httpStatus\":200").append("}");
            handler.handleReturnValue(result.toString(), methodParameter, modelAndViewContainer, nativeWebRequest);
        } else{
            Map<String, Object> map = new HashMap<>(4);
            map.put("code", 1);
            map.put("data", returnValue);
            map.put("message", "success");
            map.put("httpStatus", 200);
            handler.handleReturnValue(map, methodParameter, modelAndViewContainer, nativeWebRequest);
        }
    }
}
