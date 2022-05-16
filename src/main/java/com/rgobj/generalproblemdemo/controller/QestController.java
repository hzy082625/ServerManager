package com.rgobj.generalproblemdemo.controller;

import com.rgobj.generalproblemdemo.bean.QuestionsBean;
import com.rgobj.generalproblemdemo.bean.StuResultBean;
import com.rgobj.generalproblemdemo.service.serviceImpl.ProjectServiceImpl;
import com.rgobj.generalproblemdemo.service.serviceImpl.QuestionsServiceImpl;
import com.rgobj.generalproblemdemo.service.serviceImpl.StuResultImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * 答题数据提交
 */
@Controller
public class QestController {

    @Autowired
    ProjectServiceImpl projectServiceImpl;

    @Autowired
    QuestionsServiceImpl questionsServiceImpl;//套题业务层实现

    @Autowired
    StuResultImpl stuResultImpl;//学生结果业务层实现

    @RequestMapping("/answerable")
    public String answerable(HttpServletRequest request){
        System.out.println("111");
        projectServiceImpl.updateCustomer((String) request.getSession().getAttribute("username"), Integer.valueOf((String) request.getSession().getAttribute("id")));
        request.getSession().removeAttribute("id");
        return "StudentIndex";
    }

    //学生答题情况界面
    @RequestMapping("/answerResult")
    public String answerResult(HttpServletRequest request) {
        return "StudentIndex";
    }

}
