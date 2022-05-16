package com.rgobj.generalproblemdemo.service;

import com.rgobj.generalproblemdemo.bean.StuResultBean;

import java.util.List;

/**
 * 学生分数类接口
 */
public interface StuResultService {
    //添加数据
    public int add(StuResultBean stuResultBean);

    //查询
    public List<StuResultBean> queryAllByName(String stu_username);

    //删除
    public int deleteBySelect(String stu_username,String stu_finishtime);
}
