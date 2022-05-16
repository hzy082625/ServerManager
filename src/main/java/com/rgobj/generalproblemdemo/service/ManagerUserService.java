package com.rgobj.generalproblemdemo.service;

import com.rgobj.generalproblemdemo.bean.ManagerBean;

import java.util.List;

public interface ManagerUserService {
    //查询
    public List<ManagerUserService> queryAll();
    //添加数据
    public int add(ManagerBean managerBean);
    //根据用户名查询数据
    public ManagerBean queryByName(String username);
    //验证密码
    public ManagerBean loginCheck(String username,String password);
}
