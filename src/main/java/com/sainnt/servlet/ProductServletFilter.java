package com.sainnt.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = "product")
public class ProductServletFilter extends HttpFilter {
    @Override
    public void init(FilterConfig config) throws ServletException {

    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String idInfo = req.getPathInfo().substring(1);
        if(idInfo.matches("^[0-9]$"))
            chain.doFilter(req,res);
        else
            res.setStatus(400);

    }

    @Override
    public void destroy() {

    }
}
