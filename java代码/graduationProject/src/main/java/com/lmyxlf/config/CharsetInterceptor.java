package com.lmyxlf.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;


/**
 * 解决前后端乱码问题
 *
 * @author lmy
 */
@Configuration
public class CharsetInterceptor implements WebMvcConfigurer {
    /**
     * 设置请求与响应编码
     *
     * @param registry 拦截器注册对象
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加字符集拦截器
        registry.addInterceptor(new HandlerInterceptor(){
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UnsupportedEncodingException {
                //设置请求编码
                request.setCharacterEncoding("UTF-8");
                //设置响应编码
                response.setCharacterEncoding("UTF-8");
                return true;
            }
        });
    }
}