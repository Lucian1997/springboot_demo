package com.arshcoo.demo.controller;

import com.arshcoo.demo.dto.AccountDto;
import com.arshcoo.demo.dto.NoticeDto;
import com.arshcoo.demo.service.AccountService;
import com.arshcoo.demo.service.NoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class NoticeController {

    @Resource
    NoticeService noticeService;

    @Resource
    AccountService accountService;
    //显示所有通知
    @RequestMapping("/adminShowAllNotices")
    public String adminShowAllNotices(Map<String, Object> map, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                      @RequestParam(value="pageSize",defaultValue="10")Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<NoticeDto> notcies = noticeService.getNotices();
        PageInfo<NoticeDto> pageNotices = new PageInfo<NoticeDto>(notcies);
        map.put("notcies",pageNotices);
        return "adminShowAllNotices";
    }
    //显示所有通知(normal)
    @RequestMapping("/normalShowAllNotices")
    public String normalShowAllNotices(Map<String, Object> map, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                       @RequestParam(value="pageSize",defaultValue="10")Integer pageSize){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        AccountDto account = (AccountDto) session.getAttribute("account");
        PageHelper.startPage(pageNum, pageSize);
        List<NoticeDto> notcies = noticeService.getNotices();
        PageInfo<NoticeDto> pageNotices = new PageInfo<NoticeDto>(notcies);
        map.put("notcies",pageNotices);
        accountService.clearNotices(account.getId());
        return "normalShowAllNotices";
    }
    //删除通知
    @RequestMapping("/deleteNotice")
    public String deleteNotice(int id){
        noticeService.delete(id);
        return "redirect:/adminShowAllNotices";
    }
    //查看通知
    @RequestMapping("/adminSelectNotice")
    public ModelAndView adminSelectNotice(int id){
        ModelAndView model=new ModelAndView();
        NoticeDto noticeDto = noticeService.queryNotice(id);
        model.addObject("title",noticeDto.getTitle());
        model.addObject("context",noticeDto.getContext());
        model.addObject("date",noticeDto.getDate());
        model.setViewName("adminViewNotice");
        return model;
    }
    //查看通知(normal)
    @RequestMapping("/normalSelectNotice")
    public ModelAndView normalSelectNotice(int id){
        ModelAndView model=new ModelAndView();
        NoticeDto noticeDto = noticeService.queryNotice(id);
        model.addObject("title",noticeDto.getTitle());
        model.addObject("context",noticeDto.getContext());
        model.addObject("date",noticeDto.getDate());
        model.setViewName("normalViewNotice");
        return model;
    }
    //发布通知页面
    @RequestMapping("/addNoticePage")
    public String addNoticePage(){
        return "/adminAddNotice";
    }
    //发布通知
    @RequestMapping(value = "/addNotice")
    public String addNotice(@Validated NoticeDto noticeDto, BindingResult bindingResult, Map<String , List> map) {
        if(bindingResult.hasErrors()){
            List<String> errlist = new ArrayList<String>();
            List<ObjectError> list = bindingResult.getAllErrors();
            for(ObjectError err:list){
                FieldError fieldError = (FieldError) err;
                String msg = fieldError.getDefaultMessage();
                errlist.add(msg);
            }
            map.put("errlist",errlist);
            return "adminAddNotice";
        }else {
            Date date = new Date();
            noticeDto.setDate(date);
            noticeService.addNotice(noticeDto);
            accountService.updateNotices();
            return "redirect:/adminShowAllNotices";
        }
    }

    //微信小程序
    //发布通知
    @RequestMapping("/wxInsertNotice")
    @ResponseBody
    public boolean wxInsertNotice(NoticeDto noticeDto){
        Date date = new Date();
        noticeDto.setDate(date);
        if(noticeService.addNotice(noticeDto)>0&&accountService.updateNotices()>0){
            return true;
        }
        return false;
    }
    //显示所有通知
    @RequestMapping("/wxShowAllNotices")
    @ResponseBody
    public List<NoticeDto> wxShowAllNotices(){
        List<NoticeDto> notices = noticeService.getNotices();
        return notices;
    }
    //删除通知
    @RequestMapping("/wxDeleteNotice")
    @ResponseBody
    public boolean wxDeleteNotice(int id) {
        if (noticeService.delete(id) > 0) {
            return true;
        }
        return false;
    }
}
