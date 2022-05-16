package com.rgobj.generalproblemdemo.service;

import com.rgobj.generalproblemdemo.bean.QuestionInfoBean;

import java.util.List;

/**
 * 问题类接口
 */
public interface QuestionInfoService {
    //查询
    public List<QuestionInfoBean> queryAll();

}
