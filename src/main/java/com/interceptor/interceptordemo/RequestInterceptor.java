package com.interceptor.interceptordemo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.util.Arrays;
import java.util.Collections;


@Component
public class RequestInterceptor implements HandlerInterceptor {
    @Autowired
    private TransformationService service;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("PreHandle");
        System.out.println("=======Request handling");
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Request PathInfo: " + request.getPathInfo());
        System.out.println("Request QueryString: " + request.getQueryString());
        System.out.println("Request headers: " + Collections.list(request.getHeaderNames()).stream().map(headerName -> headerName + " -> " + request.getHeader(headerName)).toList());
        System.out.println("Request ContextPath: " + request.getContextPath());
        System.out.println("Request InputStream: " + new String(request.getInputStream().readAllBytes()));

        System.out.println("Request Wrapper Content: " + new String(requestWrapper.getContentAsByteArray()));

        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        response.setStatus(201);
        response.setHeader("Slava", "Header");
        response.setHeader("Content-Type", "application/json");
        response.getWriter().write(service.testMethod());

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("PostHandle");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");

        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
