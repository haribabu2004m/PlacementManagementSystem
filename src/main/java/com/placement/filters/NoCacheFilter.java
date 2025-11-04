package com.placement.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")  // apply to all pages
public class NoCacheFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("The is no cache filter");
        HttpServletResponse res = (HttpServletResponse) response;

        // Set headers to disable caching
        res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
        res.setHeader("Pragma", "no-cache"); // HTTP 1.0
        res.setDateHeader("Expires", 0); // Proxies

        chain.doFilter(request, response);
    }
}
