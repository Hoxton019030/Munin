package com.raven.munin.filter;

import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller收到的請求主體(RequestBody)和回應主體(ResponseBody)
 * 分別由HttpServletRequest與HttpServletResponse的InputStream、OutputStream轉化而來，
 * 但資料流只能讀取一次，如果在Filter層就被讀掉，可能會導致後面都收不到資料
 * 為了保留主體中的資料，我們將請求主體與回應主體包裝成ContentCachingResponseWrapper ContentCachingRequestWrapper
 * 再如同往常傳入FilterChain
 */
public class PrintResponseRequest extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
    }
}
