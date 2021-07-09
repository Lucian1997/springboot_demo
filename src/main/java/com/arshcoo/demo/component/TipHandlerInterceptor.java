package com.arshcoo.demo.component;

import com.arshcoo.demo.dto.AccountDto;
import com.arshcoo.demo.service.AccountService;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class TipHandlerInterceptor implements HandlerInterceptor {
    @Resource
    AccountService accountService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        AccountDto accounttemp = (AccountDto) request.getSession().getAttribute("account");
        AccountDto account = accountService.queryAccount(accounttemp.getId());
        request.getSession().setAttribute("account",account);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
