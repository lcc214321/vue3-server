package com.luocc.vue3.server.filter;

import com.luocc.vue3.server.api.ApiCode;
import com.luocc.vue3.server.exception.ServerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@WebFilter(filterName = "accessFilter", urlPatterns = {"/*"})
public class AccessFilter implements Filter {

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(
            new HashSet<>(
                    Arrays.asList(
                            "/user/login", "/doc.html", "/webjars", "/swagger-resources/configuration/ui", "/v2", "/swagger-resources", "/v2/api-docs"
                    )
            )
    );

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("accessFilter init success");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");

        log.info("path -> {}", path);
        boolean allowedPath = ALLOWED_PATHS.contains(path);

        if (allowedPath) {
            chain.doFilter(request, response);
            return;
        }

        // accessToken
        String accessToken = request.getHeader("Authorization");

        // 未携带令牌
        if(StringUtils.isEmpty(accessToken)) {
            throw new ServerException(ApiCode.UNAUTHORIZED);
        }

        // 令牌无效

        // 令牌过期

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        log.info("accessFilter destroyed");
    }

}
