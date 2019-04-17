package com.marvel.framework.configure;

import com.marvel.framework.filter.WebMethodHandlerFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @Classname WebMvcConfigurer
 * @Description web mvc配置类
 * @Date 2019/2/21 上午11:24
 * @Author zj
 */
//@EnableWebMvc
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurationSupport {

    /**
     * Description: 跨域处理
     *
     * @param registry
     * @return void
     * @Date 上午11:45 2019/2/21
     * @Author zj
     **/
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("GET", "POST")
                .maxAge(3600);
        super.addCorsMappings(registry);
    }

    /**
     * Description: 添加拦截器
     *
     * @param registry
     * @return void
     * @Date 下午2:50 2019/2/21
     * @Author zj
     **/
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new WebMethodHandlerFilter());
    }

    /**
     * Description: 设置response body中文编码
     *
     * @param converters
     * @return void
     * @Date 下午3:41 2019/2/21
     * @Author zj
     **/
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
        converters.add(new MappingJackson2HttpMessageConverter());
    }

    /**
     * Description: 覆盖StringHttpMessageConverter默认字符集
     *
     * @return org.springframework.http.converter.HttpMessageConverter<java.lang.String>
     * @Date 下午3:39 2019/2/21
     * @Author zj
     **/
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("utf-8"));
    }


}
