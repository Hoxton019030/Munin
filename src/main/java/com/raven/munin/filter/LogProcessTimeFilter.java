package com.raven.munin.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogProcessTimeFilter extends OncePerRequestFilter {
    /**
     * @param request     請求
     * @param response    回應
     * @param filterChain 過濾鏈 會將現有的filter給串聯起來，當請求進入後端，需要依序經過它們才會達到Controller，相對的，當回應離開Controller，則是按照相反的方向經過那些Filter
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        long startTime = System.currentTimeMillis();
        filterChain.doFilter(request, response); //doFilter:相當於將請求送至Controller。
        long endTime = System.currentTimeMillis();
        long processTime = endTime - startTime;
        System.out.println("processTime = " + processTime);
    }
}
