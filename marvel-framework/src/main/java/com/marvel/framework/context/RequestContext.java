package com.marvel.framework.context;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname RequestContext
 * @Description Request内容，如uid，ip
 * @Date 2019/2/24 下午8:38
 * @Author zhongjie
 */
@Data
@NoArgsConstructor
public class RequestContext {

    /**
     * ThreadLocal
     */
    private static final ThreadLocal<RequestContext> CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 用户id
     */
    private long uid;

    /**
     * 客户端ip
     */
    private String ip;

    /**
     * HttpServletRequest
     */
    private HttpServletRequest request;

    /**
     * HttpServletResponse
     */
    private HttpServletResponse response;

    /**
     * 获取RequestContext实例
     * @return
     */
    public static RequestContext getRequestContext() {
        RequestContext requestContext = CONTEXT_THREAD_LOCAL.get();
        if (requestContext == null) {
            requestContext = new RequestContext();
            CONTEXT_THREAD_LOCAL.set(requestContext);
        }
        return requestContext;
    }

    /**
     * ThreadLocal清理
     */
    public static void clearRequestContext() {
        CONTEXT_THREAD_LOCAL.remove();
    }

}
