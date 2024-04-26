package com.tom.service;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service(BisServletService.BEAN_DEFAULT)
public class BisServletServiceImpl implements BisServletService {
    protected Logger logger = LoggerFactory.getLogger(BisServletServiceImpl.class);
    private static final String HTTP_REQUEST_HEADER_X_FORWARDED_FOR = "x-forwarded-for";
    private static final String HTTP_REQUEST_HEADER_PROXY_CLIENT_IP = "Proxy-Client-IP";
    private static final String HTTP_REQUEST_HEADER_WL_PROXY_CLIENT_IP = "WL-Proxy-Client-IP";
    private static final String HTTP_REQUEST_HEADER_HTTP_X_FORWARDED_FOR = "HTTP_X_FORWARDED_FOR";
    private static final String HTTP_REQUEST_HEADER_HTTP_X_FORWARDED = "HTTP_X_FORWARDED";
    private static final String HTTP_REQUEST_HEADER_HTTP_X_CLUSTER_CLIENT_IP = "HTTP_X_CLUSTER_CLIENT_IP";
    private static final String HTTP_REQUEST_HEADER_HTTP_CLIENT_IP = "HTTP_CLIENT_IP";
    private static final String HTTP_REQUEST_HEADER_HTTP_FORWARDED_FOR = "HTTP_FORWARDED_FOR";
    private static final String HTTP_REQUEST_HEADER_HTTP_FORWARDED = "HTTP_FORWARDED";
    private static final String HTTP_REQUEST_HEADER_HTTP_VIA = "HTTP_VIA";
    private static final String HTTP_REQUEST_HEADER_REMOTE_ADDR = "REMOTE_ADDR";
    private static final String HTTP_REQUEST_VALUE_UNKNOW = "unknown";
    private static final String HTTP_SERVLET_REQUEST = "HTTP_SERVLET_REQUEST";

    @Override
    public String getRemoteIP() {
        // 使用ThreadDataPool取得Request(LoginInterceptor放入)
        HttpServletRequest request = (HttpServletRequest) ThreadDataPool.getData(HTTP_SERVLET_REQUEST);

        String ip = request.getHeader(HTTP_REQUEST_HEADER_X_FORWARDED_FOR);
        logger.info("---------------------------- IP FROM HEADER_X_FORWARDED_FOR:{}", ip);
        if (ip == null || ip.length() == 0 || HTTP_REQUEST_VALUE_UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_REQUEST_HEADER_PROXY_CLIENT_IP);
            logger.info("---------------------------- IP FROM PROXY_CLIENT_IP:{}", ip);
        }
        if (ip == null || ip.length() == 0 || HTTP_REQUEST_VALUE_UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_REQUEST_HEADER_WL_PROXY_CLIENT_IP);
            logger.info("---------------------------- IP FROM WL_PROXY_CLIENT_IP:{}", ip);
        }
        if (ip == null || ip.length() == 0 || HTTP_REQUEST_VALUE_UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_REQUEST_HEADER_HTTP_X_FORWARDED_FOR);
            logger.info("---------------------------- IP FROM HTTP_X_FORWARDED_FOR:{}", ip);
        }
        if (ip == null || ip.length() == 0 || HTTP_REQUEST_VALUE_UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_REQUEST_HEADER_HTTP_X_FORWARDED);
            logger.info("---------------------------- IP FROM HTTP_X_FORWARDED:{}", ip);
        }
        if (ip == null || ip.length() == 0 || HTTP_REQUEST_VALUE_UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_REQUEST_HEADER_HTTP_X_CLUSTER_CLIENT_IP);
            logger.info("---------------------------- IP FROM HTTP_X_CLUSTER_CLIENT_IP:{}", ip);
        }
        if (ip == null || ip.length() == 0 || HTTP_REQUEST_VALUE_UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_REQUEST_HEADER_HTTP_CLIENT_IP);
            logger.info("---------------------------- IP FROM HTTP_CLIENT_IP:{}", ip);
        }
        if (ip == null || ip.length() == 0 || HTTP_REQUEST_VALUE_UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_REQUEST_HEADER_HTTP_FORWARDED_FOR);
            logger.info("---------------------------- IP FROM HTTP_FORWARDED_FOR:{}", ip);
        }
        if (ip == null || ip.length() == 0 || HTTP_REQUEST_VALUE_UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_REQUEST_HEADER_HTTP_FORWARDED);
            logger.info("---------------------------- IP FROM HTTP_FORWARDED:{}", ip);
        }
        if (ip == null || ip.length() == 0 || HTTP_REQUEST_VALUE_UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_REQUEST_HEADER_HTTP_VIA);
            logger.info("---------------------------- IP FROM HTTP_VIA:{}", ip);
        }
        if (ip == null || ip.length() == 0 || HTTP_REQUEST_VALUE_UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getHeader(HTTP_REQUEST_HEADER_REMOTE_ADDR);
            logger.info("---------------------------- IP FROM REMOTE_ADDR:{}", ip);
        }
        if (ip == null || ip.length() == 0 || HTTP_REQUEST_VALUE_UNKNOW.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            logger.info("---------------------------- IP FROM getRemoteAddr():{}", ip);
        }

        int endIndex = ip.indexOf(",");
        if (endIndex >= 0)
            ip = ip.substring(0, endIndex).trim();

        return ip;
    }

    @Override
    public String getApplicationId() {
        HttpServletRequest request = (HttpServletRequest) ThreadDataPool.getData(HTTP_SERVLET_REQUEST);
        return request.getHeader("applicationId");
    }

}
