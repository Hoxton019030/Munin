package com.raven.munin.aop.filter;

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
 *
 * 這兩個Wrapper的特色是會在內部備份一個ByteArrayOutputStream，我們只要呼叫這兩個Wrapper的
 * getContentAsByteArray就可以無限制地取得主體內容
 */
public class PrintResponseRequest extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        filterChain.doFilter(requestWrapper, responseWrapper);
//        logApi(request, response);
        logBody(requestWrapper,responseWrapper);

        responseWrapper.copyBodyToResponse();
    }


    private void logApi(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int httpStatus = response.getStatus(); //200,403,404之類的
        String httpMethod = request.getMethod();
        String uri = request.getRequestURI();
        String params = request.getQueryString();
        if (params != null) {
            uri += "?" + params;
        }
        System.out.println(String.join(" ", String.valueOf(httpStatus), httpMethod, uri));
    }
    private void logBody(ContentCachingRequestWrapper request, ContentCachingResponseWrapper response) {
        String requestBody = getContent(request.getContentAsByteArray());
        System.out.println("Request: " + requestBody);

        String responseBody = getContent(response.getContentAsByteArray());
        System.out.println("Response: " + responseBody);
    }

    /**
     * @param content
     * @return 返回JSON字串
     */
    private String getContent(byte [] content){
        String body = new String(content);
        return body.replaceAll("[\n\t]", ""); //去除換行\n與定位符號\t
    }


}
