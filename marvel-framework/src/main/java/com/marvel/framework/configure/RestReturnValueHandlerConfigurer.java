package com.marvel.framework.configure;

import com.marvel.framework.HandlerMethodReturnValueHandlerProxy;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname RestReturnValueHandlerConfigurer
 * @Description 返回值统一处理配置类
 * @Date 2019/2/19 下午5:24
 * @Author zj
 */
@Configuration
public class RestReturnValueHandlerConfigurer implements InitializingBean {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    /**
     * Description: 覆盖返回值处理方法
     *
     * @return void
     * @Date 下午6:31 2019/2/19
     * @Author zj
     **/
    @Override
    public void afterPropertiesSet() throws Exception {
        List<HandlerMethodReturnValueHandler> list = handlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> newList = null;
        if (null != list) {
            newList = new ArrayList<>(list.size());
            for (HandlerMethodReturnValueHandler valueHandler: list) {
                if (valueHandler instanceof RequestResponseBodyMethodProcessor) {
                    HandlerMethodReturnValueHandlerProxy proxy = new HandlerMethodReturnValueHandlerProxy(valueHandler);
                    newList.add(proxy);
                } else {
                    newList.add(valueHandler);
                }
            }
        }
        handlerAdapter.setReturnValueHandlers(newList);
    }
}
