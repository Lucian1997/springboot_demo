package com.arshcoo.demo.config;

import com.arshcoo.demo.component.LoginHandlerInterceptor;
import com.arshcoo.demo.component.PowerHandlerInterceptor;
import com.arshcoo.demo.component.TipHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Resource
    private TipHandlerInterceptor tipHandlerInterceptor;

    String[] excludes = new String[]{"/loginPage","/mainPage","/static/**","/exit"};
    String[] wxFunction = new String[]{"/wxLogin","/wxInsertAccount","/wxShowAllAccounts","/wxDeleteAccount","/wxUpdatesAccount","/wxShowAllEmployees","/wxUpdateEmployee","/wxSelectEmployee","/wxQueryAreaEmployee","/wxShowAreaEmployee","/wxInsertNotice","/wxShowAllNotices","/wxDeleteNotice","/wxShowAllProjects","/wxDeleteProject","/wxInsertProject"};
    String[] normalPage = new String[]{"/returnNormalMain","/modifyEmployeeMes2","/modifyEmployee2","/showEmployeeInfo","/normalShowAllNotices","/normalSelectNotice","/showProjectsInfo","/normalSelectProject"};

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns(excludes).excludePathPatterns(wxFunction);
        registry.addInterceptor(tipHandlerInterceptor).addPathPatterns("/**").excludePathPatterns(excludes).excludePathPatterns(wxFunction);
        registry.addInterceptor(new PowerHandlerInterceptor()).addPathPatterns("/**").excludePathPatterns(excludes).excludePathPatterns(normalPage).excludePathPatterns(wxFunction);
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations(
                ResourceUtils.CLASSPATH_URL_PREFIX+"/static/"
        );
        super.addResourceHandlers(registry);
    }
}
