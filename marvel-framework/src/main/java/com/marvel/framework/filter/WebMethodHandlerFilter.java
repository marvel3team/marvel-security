package com.marvel.framework.filter;

import com.marvel.framework.annotation.MarvelCheck;
import com.marvel.framework.context.RequestContext;
import com.marvel.framework.exception.ExceptionFactory;
import com.marvel.framework.utils.HeaderUtils;
import com.marvel.framework.utils.MauthUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Classname WebMethodHandlerFilter
 * @Description controller方法拦截器
 * @Date 2019/2/21 下午2:31
 * @Author zj
 */
public class WebMethodHandlerFilter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestContext requestContext = RequestContext.getRequestContext();
        requestContext.setRequest(request);
        requestContext.setResponse(response);
        requestContext.setIp(HeaderUtils.getIp(request));
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod)handler;
            MarvelCheck marvelCheck = method.getMethodAnnotation(MarvelCheck.class);
            if (Objects.isNull(marvelCheck) || !marvelCheck.auth()) {
                return true;
            }
            String mAuth = HeaderUtils.getMauth(request);
            boolean access = MauthUtils.canAuth(mAuth);
            if (!access) {
                throw ExceptionFactory.INVALID_TOKEN;
            }
            Long uid = MauthUtils.getUid(mAuth);
            requestContext.setUid(uid);
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        RequestContext.clearRequestContext();
    }

}
