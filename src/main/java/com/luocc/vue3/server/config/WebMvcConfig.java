package com.luocc.vue3.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletContext;

@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer, ServletContextInitializer {

    /** 应用名称 */
    @Value("${spring.application.name}")
    private String applicationName;

    /**
     * 设置跨域访问
     * 说明：
     * addMapping：可以被跨域的路径，”/**”表示无限制。
     * allowedMethods：允许跨域访问资源服务器的请求方式，如：POST、GET、PUT、DELETE等，“*”表示无限制。
     * allowedOrigins：”*”允许所有的请求域名访问跨域资源，当设置具体URL时只有被设置的url可以跨域访问。
     * 例如：allowedOrigins(“https://www.baidu.com”),则只有https://www.baidu.com能访问跨域资源。
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }

    /**
     * 配置路径访问忽略大小写
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        AntPathMatcher matcher = new AntPathMatcher();
        matcher.setCaseSensitive(false);
        configurer.setPathMatcher(matcher);
    }

    @Override
    public void onStartup(ServletContext servletContext) {
        log.info("server start up");
    }

}
