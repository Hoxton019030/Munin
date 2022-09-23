package com.raven.munin.aop.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

/**
 * @author Hoxton
 * @version 1.1.0
 */
@Slf4j
@Component
@WebFilter(filterName = "f1",urlPatterns = {"/"})
public class HiFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      log.info("Hello Hoxton");
      ThreadContext.put("requestid", UUID.randomUUID().toString());
      chain.doFilter(request,response);
    }
}
