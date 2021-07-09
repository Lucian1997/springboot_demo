package com.arshcoo.demo.controller;

import com.arshcoo.demo.dto.AccountDto;
import com.arshcoo.demo.dto.EmployeeDto;
import com.arshcoo.demo.dto.ProjectDto;
import com.arshcoo.demo.service.AccountService;
import com.arshcoo.demo.service.EmployeeService;
import com.arshcoo.demo.service.ProjectsService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ProjectsController {

    @Resource
    private ProjectsService projectsService;
    @Resource
    private AccountService accountService;
    //显示所有任务
    @RequestMapping(value = "/showAllProjects")
    public String showAllProjects(Map<String, Object> map, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                  @RequestParam(value="pageSize",defaultValue="10")Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProjectDto> projects = projectsService.findAll();
        PageInfo<ProjectDto> pageProjects = new PageInfo<ProjectDto>(projects);
        map.put("projects", pageProjects);
        return "adminShowAllProjects";
    }
    //发布任务页面
    @RequestMapping("/addProjectPage")
    public ModelAndView addProject(int id){
        ModelAndView model = new ModelAndView();
        AccountDto accounts = accountService.queryAccount(id);
        model.addObject("accounts",accounts);
        model.setViewName("adminAddProjects");
        return model;
    }
    //发布任务
    @RequestMapping(value = "/addProject")
    public String addProject(@Validated ProjectDto pro, BindingResult bindingResult, Map<String , Object> map, int account_id) {
        AccountDto acc = accountService.queryAccount(account_id);
        map.put("accounts",acc);
        if(bindingResult.hasErrors()){
            List<String> errlist = new ArrayList<String>();
            List<ObjectError> list = bindingResult.getAllErrors();
            for(ObjectError err:list){
                FieldError fieldError = (FieldError) err;
                String msg = fieldError.getDefaultMessage();
                errlist.add(msg);
            }
            map.put("errlist",errlist);
            return "adminAddProjects";
        }else {
            pro.setAccount_id(acc.getId());
            pro.setPrincipal(acc.getName());
            Date date = new Date();
            pro.setDate(date);
            projectsService.addProject(pro);
            accountService.updateProjectsNum(acc.getId());
            return "redirect:/showAllProjects";
        }
    }
    //根据当前任务ID查询返回修改任务页面
    @RequestMapping(value = "/selectID")
    public String selectID(Integer id) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        ProjectDto pro = projectsService.selectId(id);
        session.setAttribute("proj",pro);
        return "adminModifyProjects";
    }
    /*@RequestMapping(value = "/selectID")
    public ModelAndView selectID(Integer id) {
        ModelAndView model = new ModelAndView();
        ProjectDto pro = projectsService.selectId(id);
        model.addObject("principal", pro.getPrincipal());
        model.addObject("project", pro.getProject());
        model.addObject("context", pro.getContext());
        model.addObject("date", pro.getDate());
        model.addObject("id",pro.getId());
        model.setViewName("adminModifyProjects");
        return model;
    }*/
    //修改任务信息
    @RequestMapping(value = "/updateProject")
    public String updateProject(@Validated ProjectDto projects, BindingResult bindingResult,Map<String , List> map) {
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
            return "adminModifyProjects";
        }else{
            projectsService.updateProject(projects);
            session.removeAttribute("proj");
            return "redirect:/showAllProjects";}

    }
    /*@RequestMapping(value = "/updateProject")
    public String updateProject(@Validated ProjectDto projects, BindingResult bindingResult, Map<String , List> map) {
        if(bindingResult.hasErrors()){
            List<String> errlist = new ArrayList<String>();
            List<ObjectError> list = bindingResult.getAllErrors();
            for(ObjectError err:list){
                FieldError fieldError = (FieldError) err;
                String msg = fieldError.getDefaultMessage();
                errlist.add(msg);
            }
            map.put("errlist",errlist);
            return "adminModifyProjects";
        }else {
            projectsService.updateProject(projects);
            return "redirect:/showAllProjects";
        }
    }*/
    //删除任务
    @RequestMapping(value = "/deleteProject")
    public String deleteProject(Integer id) {
        projectsService.deleteProject(id);
        return "redirect:/showAllProjects";
    }
    //根据负责人名字查询任务(normal)
    @RequestMapping("/showProjectsInfo")
    public String getProjectByPrincipal(Map<String, Object> map, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                        @RequestParam(value="pageSize",defaultValue="10")Integer pageSize) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        AccountDto account = (AccountDto) session.getAttribute("account");
        PageHelper.startPage(pageNum, pageSize);
        List<ProjectDto> projects = projectsService.findAllByPrincipal(account.getId());
        PageInfo<ProjectDto> pageProjects = new PageInfo<ProjectDto>(projects);
        map.put("projects", pageProjects);
        accountService.clearProjectsNum(account.getId());
        return "normalShowProjectsInfo";
    }
    //查看任务
    @RequestMapping("/adminlSelectProject")
    public ModelAndView adminSelectProject(int id){
        ModelAndView model=new ModelAndView();
        ProjectDto projectDto = projectsService.selectId(id);
        model.addObject("title",projectDto.getProject());
        model.addObject("principal",projectDto.getPrincipal());
        model.addObject("context",projectDto.getContext());
        model.addObject("date",projectDto.getDate());
        model.setViewName("adminViewProject");
        return model;
    }
    //查看任务(normal)
    @RequestMapping("/normalSelectProject")
    public ModelAndView normalSelectProject(int id){
        ModelAndView model=new ModelAndView();
        ProjectDto projectDto = projectsService.selectId(id);
        model.addObject("title",projectDto.getProject());
        model.addObject("context",projectDto.getContext());
        model.addObject("date",projectDto.getDate());
        model.setViewName("normalViewProject");
        return model;
    }

    //微信小程序
    //查看所有任务
    @RequestMapping("/wxShowAllProjects")
    @ResponseBody
    public List<ProjectDto> wxShowAllProjects(){
        List<ProjectDto> projects = projectsService.findAll();
        return projects;
    }
    //删除任务
    @RequestMapping("/wxDeleteProject")
    @ResponseBody
    public boolean wxDeleteProject(int id){
        if(projectsService.deleteProject(id)>0){
            return true;
        }
        return false;
    }
    //删除任务
    @RequestMapping("/wxInsertProject")
    @ResponseBody
    public boolean wxInsertProject(ProjectDto projectDto){
        Date date = new Date();
        projectDto.setDate(date);
        if(projectsService.addProject(projectDto)>0&&accountService.updateProjectsNum(projectDto.getAccount_id())>0){
            return true;
        }
        return false;
    }

}