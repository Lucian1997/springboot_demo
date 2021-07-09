package com.arshcoo.demo.service.impl;

import com.arshcoo.demo.dao.ProjectDao;
import com.arshcoo.demo.dto.ProjectDto;
import com.arshcoo.demo.service.ProjectsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectsServiceImpl implements ProjectsService {

    @Resource
    private ProjectDao projectDao;

    @Override
    public List<ProjectDto> findAll() {
        return projectDao.findAll();
    }

    @Override
    public ProjectDto selectId(Integer id) {
        return projectDao.selectId(id);
    }

    @Override
    public int deleteProject(Integer id) {
        return projectDao.deleteProject(id);
    }

    @Override
    public void updateProject(ProjectDto pro) {
        projectDao.updateProject(pro);
    }

    @Override
    public int addProject(ProjectDto pro) {
        return projectDao.addProject(pro);
    }

    @Override
    public List<ProjectDto> findAllByPrincipal(Integer account_id) {
        return projectDao.findAllByPrincipal(account_id);
    }
}