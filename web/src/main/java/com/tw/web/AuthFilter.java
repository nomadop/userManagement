package com.tw.web;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by nomadop on 15-6-30.
 */
@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest hreq = (HttpServletRequest) req;
        HttpServletResponse hresp = (HttpServletResponse) resp;
        HttpSession session = hreq.getSession();
        String requestUrl = hreq.getServletPath();
        System.out.println(requestUrl);

        if (session.getAttribute("currentUserName") != null) {
            if (requestUrl.equals("/user/enter") || requestUrl.equals("/user/login")) {
                hresp.sendRedirect("/web/user/");
            } else {
                chain.doFilter(req, resp);
            }
        } else {
            if (requestUrl.equals("/user/enter") || requestUrl.equals("/user/login")) {
                chain.doFilter(req, resp);
            } else {
                Cookie redirectUrl = new Cookie("redirectUrl", requestUrl);
                redirectUrl.setPath("/");
                redirectUrl.setMaxAge(-1);
                hresp.addCookie(redirectUrl);
                hresp.sendRedirect("/web/user/enter");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
