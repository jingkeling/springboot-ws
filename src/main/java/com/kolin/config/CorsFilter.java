package com.kolin.config;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 用GET请求控制台提示跨域问题403，但是请求是200正常拿到数据
 * POST请求就完全403，不能访问
 */
@Component
public class CorsFilter implements Filter {

  /*  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Max-Age", "1800");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,HEAD,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With,Authorization,Accept,Origin,Access-Control,Access-Control-Request-Method,Access-Control-Request-Headers,User-Operation-InfoA,Last-Modified,X-Auth-Token");
        chain.doFilter(req, res);

    }*/

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}

    /**
     * @Description 服务器通过返回响应头进行权限控制
     * @Date 2018/2/27 16:30
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @return void
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        //控制那些外部请求可以访问该资源(跨域主要靠这个)
        res.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        //控制客户端可以发送的请求方法（如：POST），多个值使用逗号分隔
        res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        res.setHeader("Access-Control-Max-Age", "0");
        //控制客户端可以发送的额外头部信息，多个值使用逗号分隔
        res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        //结合客户端 xmlhttprequest 的 withCredentials 属性可以控制是否发送 cookie 等验证信息
        res.setHeader("Access-Control-Allow-Credentials", "true");
        //IE8及以上跨域设置这个，以下
        res.setHeader("XDomainRequestAllowed","1");
        filterChain.doFilter(servletRequest,servletResponse);
    }


}