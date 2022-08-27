package com.raven.munin.config;

import com.raven.munin.filter.LogApiFilter;
import com.raven.munin.filter.LogProcessTimeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {
    //test
    @Bean
    public FilterRegistrationBean logProcessTimeFilter() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new LogProcessTimeFilter()); //設定想要使用哪一個Filter
        bean.addUrlPatterns("/*"); //設置哪些url會觸發Filter，設置成/* 就代表全部都會吃到，/user/*就代表/user開頭的都會吃到
        bean.setName("logProcessTimeFilter"); //設置要叫什麼名字
        bean.setOrder(0); //設定過濾器的執行順序
        return bean;
    }

    @Bean
    public FilterRegistrationBean logApiFilter() {
        FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new LogApiFilter()); //設定想要使用哪一個Filter
        bean.addUrlPatterns("/*"); //設置哪些url會觸發Filter，設置成/* 就代表全部都會吃到，/user/*就代表/user開頭的都會吃到
        bean.setName("logApiFilter"); //設置要叫什麼名字
        bean.setOrder(1); //設定過濾器的執行順序
        return bean;
    }
}
