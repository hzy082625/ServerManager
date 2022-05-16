package com.rgobj.generalproblemdemo.service;

import com.rgobj.generalproblemdemo.bean.QuestionsBean;

import java.util.List;

/**
 * 问题结果类接口
 */
public interface QuestionsService {
    //查询
    public List<QuestionsBean> queryAll();

    //根据套题ID查询
    public List<QuestionsBean> queryAllByCode(int questions_code);
}
