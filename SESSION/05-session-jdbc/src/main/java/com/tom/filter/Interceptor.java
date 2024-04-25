package com.tom.filter;

import org.springframework.web.servlet.HandlerInterceptor;

import com.tom.service.BisServletService;
import com.tom.service.ThreadDataPool;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Interceptor implements HandlerInterceptor {

	private static final String HTTP_SERVLET_REQUEST = "HTTP_SERVLET_REQUEST";

	@Autowired
    BisServletService bisServletService;
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("startTime", System.currentTimeMillis());
        //設置RemoteIp設定
        ThreadDataPool.setData(HTTP_SERVLET_REQUEST, request);

        String url = request.getRequestURL().toString();
        String uri = request.getRequestURI();

        String applicationId = request.getHeader("applicationId");
        log.info("Request applicationId = {}", applicationId);

        ThreadContext.put("RemoteIP", bisServletService.getRemoteIP());
        ThreadContext.put("applicationId", bisServletService.getApplicationId());
        ThreadContext.put("URL", url);
        ThreadContext.put("URI", uri);
        ThreadContext.put("STATUS", String.valueOf(response.getStatus()));

        RequestContextHolder.currentRequestAttributes().setAttribute("SESSION_ID", request.getSession().getId(), RequestAttributes.SCOPE_REQUEST);
        log.info("Request SessionID = {}",request.getSession().getId());
        return true;
    }
	
}
