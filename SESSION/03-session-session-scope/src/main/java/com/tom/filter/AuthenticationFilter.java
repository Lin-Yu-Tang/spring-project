package com.tom.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter {

	public FilterConfig config;

    public void init(FilterConfig filterConfig) throws ServletException {
        config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rsp= (HttpServletResponse) response;

        rsp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        rsp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        rsp.setHeader("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE");
        rsp.setHeader("Access-Control-Max-Age","3600");
        rsp.setHeader("Access-Control-Allow-Credentials","true");
        rsp.addCookie(new Cookie("JSSESIONID",req.getSession().getId()));
        chain.doFilter(request, rsp);
    }

    public void destroy() {
        this.config = null;
    }

}
