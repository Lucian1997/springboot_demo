package com.arshcoo.demo.controller;
import com.arshcoo.demo.dto.AccountDto;
import com.arshcoo.demo.dto.EmployeeDto;
import com.arshcoo.demo.service.AccountService;
import com.arshcoo.demo.service.EmployeeService;
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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AccountController implements WebMvcConfigurer {

    @Resource
    AccountService accountService;
    @Resource
    EmployeeService employeeService;
    //登录页面
    @RequestMapping("/loginPage")
    public String loginPage(){
        return "login";
    }
    //返回admin主页
    @RequestMapping("/returnAdminMain")
    public String returnAdminMain(){
        return "adminMain";
    }
    //返回normal主页
    @RequestMapping("/returnNormalMain")
    public String returnNormalMain(){
        return "normalMain";
    }

    //登录
    @RequestMapping("/mainPage")
    public ModelAndView login(AccountDto accountDto,Map<String, Object> map){
        System.out.println("****");
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        ModelAndView modelAndView = new ModelAndView();;
        modelAndView.setViewName("adminMain");
        String errorMsg;
        if(accountDto.getUsername().equals("")||accountDto.getPassword().equals("")){
            errorMsg = "用户名或密码不能为空";
            modelAndView.addObject("errorMsg",errorMsg);
            modelAndView.setViewName("login");
            return modelAndView;
        }
        accountDto = accountService.login(accountDto);
        if(accountDto == null){
            errorMsg = "用户名或密码错误";
            modelAndView.addObject("errorMsg",errorMsg);
            modelAndView.setViewName("login");
            return modelAndView;
        }else{
            session.setAttribute("account", accountDto);
            if(accountDto.getPower().equals("admin")){
                return modelAndView;
            }
            modelAndView.setViewName("normalMain");
            return modelAndView;
        }
    }
    //退出
    @RequestMapping("/exit")
    public String eixt(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/loginPage";
    }
    //显示所有账号
    @RequestMapping("/showAllAccounts")
    public String ShowAllAccounts(Map<String, Object> map, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                  @RequestParam(value="pageSize",defaultValue="10")Integer pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<AccountDto> accounts = accountService.getAccounts();
        PageInfo<AccountDto> pageAccounts = new PageInfo<AccountDto>(accounts);
        map.put("accounts",pageAccounts);
        return "adminShowAllAccounts";
    }
    //新增账号页面
    @RequestMapping("/registerPage")
    public String registerPage(){
        return "adminAddAccount";
    }
    //新增账号
    @RequestMapping("/register")
    public String register(@Validated AccountDto accountInfo, BindingResult bindingResult, Map<String , List> map){
        if(bindingResult.hasErrors()){
            List<String> errlist = new ArrayList<String>();
            List<ObjectError> list = bindingResult.getAllErrors();
            for(ObjectError err:list){
                FieldError fieldError = (FieldError) err;
                String msg = fieldError.getDefaultMessage();
                errlist.add(msg);
            }
            map.put("errlist",errlist);
            return "adminAddAccount";
        }else {
            accountService.register(accountInfo);
            EmployeeDto employeeDto = new EmployeeDto();
            employeeDto.setAccountId(accountInfo.getId());
            employeeDto.setName(accountInfo.getName());
            employeeDto.setWork("无");
            employeeDto.setPosition("无");
            employeeDto.setArea("无");
            employeeService.addEmployee(employeeDto);
            return "redirect:/showAllAccounts";
        }
    }
    //删除账号
    @RequestMapping("/deleteAccount")
    public String delete(int id){
        employeeService.deleteEmployee(id);
        int accountId = id ;
        accountService.delete(accountId);
        return "redirect:/showAllAccounts";
    }
    //修改账号页面
    @RequestMapping("/modifyAccountMes")
    public String modifyAccountMes(int id){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        AccountDto acc =accountService.queryAccount(id);
        session.setAttribute("acc",acc);
        return "adminModifyAccount";
    }
    /*@RequestMapping("/modifyAccountMes")
    public ModelAndView modifyAccountMes(int id){
        ModelAndView model=new ModelAndView();
        AccountDto accountDto =accountService.queryAccount(id);
        model.addObject("name",accountDto.getName());
        model.addObject("username",accountDto.getUsername());
        model.addObject("password",accountDto.getPassword());
        model.addObject("id",accountDto.getId());
        model.setViewName("adminModifyAccount");
        return model;
    }*/
    //修改账号
    @RequestMapping("/modifyAccount")
    public String modifyAccount(@Validated AccountDto accountDto, BindingResult bindingResult,Map<String , List> map){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        if(bindingResult.hasErrors()){
            List<String> errlist = new ArrayList<String>();
            List<ObjectError> list = bindingResult.getAllErrors();
            for(ObjectError err:list){
                FieldError fieldError = (FieldError) err;
                String msg = fieldError.getDefaultMessage();
                errlist.add(msg);
            }
            map.put("errlist",errlist);
            return "adminModifyAccount";
        }else {
            accountService.modify(accountDto);
            session.removeAttribute("acc");
            return "redirect:/showAllAccounts";
        }
    }
    //微信小程序功能
    //登录
    @RequestMapping("/wxLogin")
    @ResponseBody
    public AccountDto wxLogin (AccountDto accountDto){
        System.out.println ( "微信小程序调用接口！！！用户名:" + accountDto.getUsername() + "密码:" + accountDto.getPassword() );
        accountDto = accountService.login(accountDto);
        return accountDto;
    }
    //添加账号
    @RequestMapping("/wxInsertAccount")
    @ResponseBody
    public boolean wxInsertAccount(AccountDto accountDto,EmployeeDto employeeDto){
        if(accountService.register(accountDto)>0){
            employeeDto.setAccountId(accountDto.getId());
            employeeDto.setName(accountDto.getName());
            employeeDto.setWork("无");
            employeeDto.setPosition("无");
            employeeDto.setArea("无");
            if(employeeService.addEmployee(employeeDto)>0){
                return true;
            }
            return false;
        }
        return false;
    }
    //显示所有账号
    @RequestMapping("/wxShowAllAccounts")
    @ResponseBody
    public List<AccountDto> wxShowAllAccounts(){
        List<AccountDto> accounts = accountService.getAccounts();
        return accounts;
    }
    //删除账号
    @RequestMapping("/wxDeleteAccount")
    @ResponseBody
    public boolean wxDeleteAccount(int id){
        if(accountService.delete(id)>0){
            int accountId = id;
            if(employeeService.deleteEmployee(accountId)>0){
                return true;
            }
            return false;
        }
        return false;
    }
    //修改账号
    @RequestMapping("/wxUpdatesAccount")
    @ResponseBody
    public boolean wxUpdatesAccount(AccountDto accountDto){
        if(accountService.modify(accountDto)>0){
            return true;
        }
        return false;
    }
}
