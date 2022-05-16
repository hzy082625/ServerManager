package com.rgobj.generalproblemdemo.service.serviceImpl;

import com.rgobj.generalproblemdemo.bean.ProjectBean;
import com.rgobj.generalproblemdemo.mapper.ProjectMapper;
import com.rgobj.generalproblemdemo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectMapper projectMapper;
    public ProjectBean queryById(int id) {
        System.out.println("again"+id);
        return projectMapper.queryById(id);
    }

    @Override
    public int updateInfo(String projectName, String projectTime, int id) {
        return projectMapper.updateInfo(projectName, projectTime, id);
    }

    @Override
    public List<ProjectBean> queryAll() {
        return projectMapper.queryAll();
    }

    @Override
    public int updateCustomer(String username, int id) {
        return projectMapper.updateCustomer(username, id);
    }

    @Override
    public int deleteById(String username, int id) {
        return projectMapper.deleteById(username, id);
    }

    @Override
    public List<ProjectBean> queryAllCusByName(String username) {
        return projectMapper.queryAllCusByName(username);
    }

}
