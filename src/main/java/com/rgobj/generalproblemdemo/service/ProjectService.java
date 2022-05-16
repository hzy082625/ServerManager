package com.rgobj.generalproblemdemo.service;

import com.rgobj.generalproblemdemo.bean.ProjectBean;

import java.util.List;

public interface ProjectService {
    ProjectBean queryById(int id);

    int updateInfo(String projectName, String projectTime, int id);

    List<ProjectBean> queryAll();

    int updateCustomer(String username, int id);

    int deleteById(String username, int id);

    List<ProjectBean> queryAllCusByName(String username);
}
