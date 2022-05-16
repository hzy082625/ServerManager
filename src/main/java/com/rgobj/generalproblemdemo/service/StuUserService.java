package com.rgobj.generalproblemdemo.service;

import com.rgobj.generalproblemdemo.bean.StuUserBean;

import java.util.List;

/**
 * 学生类接口
 */
public interface StuUserService {

    //查询
    public List<StuUserBean> queryAll();
    //添加数据
    public int add(StuUserBean stuUserBean);
    //根据用户名查询数据
    public StuUserBean queryByName(String username);
    //验证密码
    public StuUserBean loginCheck(String username,String password);
    //查询单个学生
    public StuUserBean query(String username);
    //更新学生信息
    public void update(String username, String password, String name);
    //删除学生信息
    public void delete(String username);
}
