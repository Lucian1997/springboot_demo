package com.arshcoo.demo.controller;

import com.arshcoo.demo.dto.AccountDto;
import com.arshcoo.demo.dto.EmployeeDto;
import com.arshcoo.demo.dto.ProjectDto;
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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;
    //所有员工信息
    @RequestMapping(value = "/showAllEmployee")
    public String getEmployee(Map<String, Object> map, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                              @RequestParam(value="pageSize",defaultValue="10")Integer pageSize) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("EmpInfo","all");
        PageHelper.startPage(pageNum, pageSize);
        List<EmployeeDto> employees = employeeService.getEmployee();
        PageInfo<EmployeeDto> pageEmployee = new PageInfo<EmployeeDto>(employees);
        map.put("employees",pageEmployee);
        List<String> areas = employeeService.getArea();
        List<String> work = employeeService.getWork();
        map.put("areas",areas);
        map.put("work",work);
        return "adminShowAllEmployee";
    }
    //添加个人信息页面
    @RequestMapping(value = "/addEmployeePage")
    public String addEmployeePage(HttpServletResponse response)throws IOException{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        AccountDto account = (AccountDto) session.getAttribute("account");
        EmployeeDto personal = employeeService.queryEmployeeByAccountId(account.getId());
        if(personal==null)
        return "normalModifyEmployee";
        else{
            /*session.setAttribute("Msg","你已有个人信息，无法添加，变更信息请删除后再添加");*/
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<script>alert('你已有个人信息，无法添加，变更信息请删除后再添加');window.location='/showEmployeeInfo';</script>");
            return null;
        }
    }
    //添加个人信息
    @RequestMapping(value = "/addEmployee")
    public String addEmployee(EmployeeDto employeeDto){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        AccountDto account = (AccountDto) session.getAttribute("account");
        employeeDto.setAccountId(account.getId());
        employeeDto.setName(account.getName());
        employeeService.addEmployee(employeeDto);
        return "redirect:/showEmployeeInfo";
    }
    //修改员工信息页面
    @RequestMapping(value = "/modifyEmployeeMes")
    public String modifyEmployeeMes(Integer id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        EmployeeDto employeeDto=employeeService.queryEmployee(id);
        session.setAttribute("empl",employeeDto);
        return "adminModifyEmployee";
    }
    /*@RequestMapping("/modifyEmployeeMes")
    public ModelAndView modifyEmployeeMes(int id){
        ModelAndView model=new ModelAndView();
        EmployeeDto employeeDto=employeeService.queryEmployee(id);
        model.addObject("id",employeeDto.getId());
        model.addObject("name",employeeDto.getName());
        model.addObject("work",employeeDto.getWork());
        model.addObject("position",employeeDto.getPosition());
        model.addObject("area",employeeDto.getArea());
        model.setViewName("adminModifyEmployee");
        return model;
    }*/
    //修改员工信息
    @RequestMapping(value = "/modifyEmployee")
    public String updateProject(@Validated EmployeeDto employeeDto, BindingResult bindingResult, Map<String , List> map) {
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
            return "adminModifyEmployee";
        }else{
            employeeService.updateEmployee(employeeDto);
            session.removeAttribute("empl");
            return "redirect:/showAllEmployee";
        }

    }
    /*@RequestMapping("/modifyEmployee")
    public String modifyAccount(EmployeeDto employeeDto, String work, String position, String area){
        employeeDto.setWork(work);
        employeeDto.setPosition(position);
        employeeDto.setArea(area);
        employeeService.updateEmployee(employeeDto);
        return "redirect:/showAllEmployee";
    }*/
    //修改员工信息页面(normal)
    @RequestMapping("/modifyEmployeeMes2")
    public ModelAndView modifyEmployeeMes1(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        AccountDto account = (AccountDto) session.getAttribute("account");
        ModelAndView model=new ModelAndView();
        EmployeeDto employeeDto=employeeService.queryEmployeeByAccountId(account.getId());
        model.addObject("id",employeeDto.getId());
        model.addObject("name",employeeDto.getName());
        model.addObject("work",employeeDto.getWork());
        model.addObject("position",employeeDto.getPosition());
        model.addObject("area",employeeDto.getArea());
        model.setViewName("normalModifyEmployee");
        return model;
    }
    //修改员工信息(normal)
    @RequestMapping("/modifyEmployee2")
    public String modifyAccount1(EmployeeDto employeeDto, String work, String position, String area){
        employeeDto.setWork(work);
        employeeDto.setPosition(position);
        employeeDto.setArea(area);
        employeeService.updateEmployee(employeeDto);

        return "redirect:/showEmployeeInfo";
    }
    //当前账号个人信息(normal)
    @RequestMapping("/showEmployeeInfo")
    public String getEmployeeByAccountId(Map<String, EmployeeDto> map) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        AccountDto account = (AccountDto) session.getAttribute("account");
        EmployeeDto personal = employeeService.queryEmployeeByAccountId(account.getId());
        map.put("personal",personal);
        return "normalShowEmployeeInfo";
    }
    //搜索员工个人信息按照地区和职位
    @RequestMapping("/queryEmployByAreaPosition")
    public String queryEmployByAreaPosition(String area, String work,Map<String, Object> map,
                                            @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                            @RequestParam(value="pageSize",defaultValue="10")Integer pageSize) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        String allArea = "全部地区";
        String allWork = "全部职位";
        List<String> areas = employeeService.getArea();
        map.put("areas",areas);
        List<String> works = employeeService.getWork();
        map.put("work",works);
        PageHelper.startPage(pageNum, pageSize);
        if (area.equals(allArea)&&work.equals(allWork)) {
            return "redirect:/showAllEmployee";
        }else if (area != allArea && work.equals(allWork)) {
            List<EmployeeDto> employees = employeeService.queryEmployeeByArea(area);
            PageInfo<EmployeeDto> pageEmployee = new PageInfo<EmployeeDto>(employees);
            map.put("employees",pageEmployee);
            session.setAttribute("EmpInfo","areaEmp");
            return "adminShowAllEmployee";
        }else if (area.equals(allArea) && work != allWork) {
            List<EmployeeDto> employees = employeeService.queryEmployeeByPosition(work);
            PageInfo<EmployeeDto> pageEmployee = new PageInfo<EmployeeDto>(employees);
            map.put("employees",pageEmployee);
            session.setAttribute("EmpInfo","workEmp");
            return "adminShowAllEmployee";
        }else if (area != allArea && work!=allWork) {
            List<EmployeeDto> employees = employeeService.queryEmployeeByAreaPosition(area, work);
            PageInfo<EmployeeDto> pageEmployee = new PageInfo<EmployeeDto>(employees);
            map.put("employees",pageEmployee);
            session.setAttribute("EmpInfo","area&workEmp");
            return "adminShowAllEmployee";
        }
        return "adminShowAllEmployee";
    }


    //微信小程序
    //查询所有员工信息
    @RequestMapping("/wxShowAllEmployees")
    @ResponseBody
    public List<EmployeeDto> wxShowAllEmployees(){
        List<EmployeeDto> employees = employeeService.getEmployee();
        return employees;
    }
    //修改员工信息
    @RequestMapping("/wxUpdateEmployee")
    @ResponseBody
    public boolean wxUpdateEmployee(EmployeeDto employeeDto){
        if(employeeService.updateEmployee(employeeDto)>0){
            return true;
        }
        return false;
    }
    //根据账号ID查找员工信息
    @RequestMapping("/wxSelectEmployee")
    @ResponseBody
    public EmployeeDto wxSelectEmployee(int id){
        EmployeeDto employeeDto = employeeService.queryEmployeeByAccountId(id);
        return employeeDto;
    }
    //根据地区查找员工信息
    @RequestMapping("/wxQueryAreaEmployee")
    @ResponseBody
    public List<String> wxQueryAreaEmployee(){
        List<String> area = employeeService.getArea();
        return area;
    }
    //显示地区所有信息
    @RequestMapping("/wxShowAreaEmployee")
    @ResponseBody
    public List<EmployeeDto> wxShowAreaEmployee(String area){
        List<EmployeeDto> employees = employeeService.queryEmployeeByArea(area);
        return employees;
    }

}


