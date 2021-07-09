package com.arshcoo.demo.dao;

import com.arshcoo.demo.dto.ProjectDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProjectDao {
    //获取任务的所有信息
    List<ProjectDto> findAll();
    //根据任务id获取任务信息
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
