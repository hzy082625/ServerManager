package com.rgobj.generalproblemdemo.service;

import com.rgobj.generalproblemdemo.bean.TeacherBean;

import java.util.List;

/**
 * 教师类接口
 */

public interface TeacherUserService {

    //查询数据
    public List<TeacherBean> queryAll();

    //添加数据
    public int add(TeacherBean teacherBean);

    //根据用户查询数据
    public TeacherBean queryByName(String username);

    //登陆密码验证
    public TeacherBean loginCheck(String username,String password);

    //教师信息查询
    TeacherBean query(String username);

    //更新教师信息
    void update(String username, String password, String name);

    //删除教师信息
    void delete(String username);
}
