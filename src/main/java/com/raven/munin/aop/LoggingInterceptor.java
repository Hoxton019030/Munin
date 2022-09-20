package com.raven.munin.aop;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class LoggingInterceptor implements HandlerInterceptor {

    /**
     * ThreadContext 創建一個key-value組，供Log4j.xml使用，在這裡是產生一組UUID，讓log紀錄裡面的每一筆log都有獨特的UUID以供追蹤
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ThreadContext.put("requestid", UUID.randomUUID().toString());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
     ThreadContext.clearAll();
    }
}
