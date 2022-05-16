package com.rgobj.generalproblemdemo.controller;

import com.rgobj.generalproblemdemo.service.serviceImpl.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 学生删除记录
 */

@Controller
public class StuResultController {

    @Autowired
    ProjectServiceImpl projectServiceImpl;

    @Autowired
    StuUserServiceImpl stuUserServiceImpl;//学生业务层实现

    @Autowired
    QuestionInfoServiceImpl questionInfoServiceImpl;//套题信息业务层实现

    @Autowired
    QuestionsServiceImpl questionsServiceImpl;//套题业务层实现

    @Autowired
    StuResultImpl stuResultImpl;//学生结果业务层实现

    //删除记录
    @RequestMapping("/toDeleted")
    public String toDeleted(Model model, HttpServletRequest request){
        int id = Integer.valueOf(request.getParameter("id"));

//        删除操作
        projectServiceImpl.deleteById("空", id);

        return "redirect:/toStuExamResult";
    }

}
