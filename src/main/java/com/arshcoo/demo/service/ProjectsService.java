package com.arshcoo.demo.service;

import com.arshcoo.demo.dto.ProjectDto;

import java.util.List;

    public interface ProjectsService {
        //获取任务的所有信息
        List<ProjectDto> findAll();
        //添加任务
        ProjectDto selectId(Integer id);
        //删除任务
        int deleteProject(Integer id);
        //修改任务
        void updateProject(ProjectDto pro);
        //发布任务
        int addProject(ProjectDto pro);
        //根据负责人名字获取任务
        List<ProjectDto> findAllByPrincipal(Integer account_id);
    }

