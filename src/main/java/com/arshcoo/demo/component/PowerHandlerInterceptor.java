package com.arshcoo.demo.component;

import com.arshcoo.demo.dto.AccountDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@Component
public class PowerHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AccountDto account = (AccountDto) request.getSession().getAttribute("account");
        if (!account.getPower().equals("admin")) {
            request.setAttribute("msg", "您不是管理员，请重新登陆");
            request.getRequestDispatcher("/loginPage").forward(request, response);
            return false;
        }else {
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
