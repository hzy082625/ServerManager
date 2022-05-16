package com.rgobj.generalproblemdemo.controller;

import com.rgobj.generalproblemdemo.bean.ProjectBean;
import com.rgobj.generalproblemdemo.bean.QuestionInfoBean;
import com.rgobj.generalproblemdemo.bean.QuestionsBean;
import com.rgobj.generalproblemdemo.mapper.ProjectMapper;
import com.rgobj.generalproblemdemo.mapper.QuestionInfoMapper;
import com.rgobj.generalproblemdemo.service.QuestionsService;
import com.rgobj.generalproblemdemo.service.serviceImpl.ProjectServiceImpl;
import com.rgobj.generalproblemdemo.service.serviceImpl.QuestionInfoServiceImpl;
import com.rgobj.generalproblemdemo.service.serviceImpl.QuestionsServiceImpl;
import com.rgobj.generalproblemdemo.service.serviceImpl.TeacherQsetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 教师增删改题
 */

@Controller
public class TeacherIndexController {
    int i = 0;

    @Autowired
    ProjectServiceImpl projectServiceImpl;//项目业务层实现

    @Autowired
    TeacherQsetServiceImpl teacherQsetServiceImpl;//教师套题信息业务层实现

    @Autowired
    QuestionInfoServiceImpl questionInfoServiceImpl;//套题业务层实现

    @Autowired
    QuestionsServiceImpl questionsServiceImpl;//具体答题业务层实现

    //查看教师用户自己出的套题
    @RequestMapping("/toTeacherQset")
    public String toTeacherQset(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute("username");//获取当前登录的教师用户名
        System.out.println(username);
        List<ProjectBean> projectBeans = teacherQsetServiceImpl.queryByTeacherUser(username);//根据用户名获取相应的套题信息
        System.out.println(projectBeans);

        request.getSession().setAttribute("questionInfo_tea", projectBeans);

        return "TeacherQset";
    }

//    //题目搜索
//    @RequestMapping("/toSubjiectSearch")
//    public String toQuestionsSearch(HttpServletRequest request) {
//        //获取当前登录的教师用户名
//        String username = (String) request.getSession().getAttribute("username");
//        //拿到搜索信息
//        StringBuffer stringBuffer = new StringBuffer(request.getParameter("table_search"));
//        //根据用户名获取相应的套题信息
//        List<QuestionInfoBean> questionInfoBeans = teacherQsetServiceImpl.queryByTeacherUser(username);
//        //存放搜索出来的信息
//        List<QuestionInfoBean> questionInfoBeans1 = new LinkedList<>();
//
//        if (stringBuffer.length() == 0) return "TeacherQset";                                          //空着不变
//
//        //搜索操作
//        //question_code    author   title   type
//        for (QuestionInfoBean q : questionInfoBeans) {
//            boolean judge = true;
//            int len = stringBuffer.length();                                                            //获取数据长度
//            StringBuffer stringcode = new StringBuffer(String.valueOf(q.getQuestion_code()));
//            StringBuffer stringauthor = new StringBuffer(q.getQuestion_author());
//            StringBuffer stringtitle = new StringBuffer(q.getQuestion_title());
//            StringBuffer stringtype = new StringBuffer(q.getQuestion_type());
//
//            for (int i = 0; i < stringcode.length() - len + 1 && judge; i++) {
//                if (stringcode.substring(i, i + len).equals(stringBuffer.toString())) {
//                    questionInfoBeans1.add(q);
//                    judge = false;
//                }
//            }
//            for (int i = 0; i < stringauthor.length() - len + 1 && judge; i++) {
//                if (stringauthor.substring(i, i + len).equals(stringBuffer.toString())) {
//                    questionInfoBeans1.add(q);
//                    judge = false;
//                }
//            }
//            for (int i = 0; i < stringtitle.length() - len + 1 && judge; i++) {
//                if (stringtitle.substring(i, i + len).equals(stringBuffer.toString())) {
//                    questionInfoBeans1.add(q);
//                    judge = false;
//                }
//            }
//            for (int i = 0; i < stringtype.length() - len + 1 && judge; i++) {
//                if (stringtype.substring(i, i + len).equals(stringBuffer.toString())) questionInfoBeans1.add(q);
//            }
//        }
//
//        //按时间排序，时间后的靠前
//        teacherQsetServiceImpl.setSorting(questionInfoBeans1);
//        for (QuestionInfoBean q : questionInfoBeans1) {
//            q.setQuestion_time(teacherQsetServiceImpl.getFormatDate(q.getQuestion_time()));
//        }
//
//        request.getSession().setAttribute("questionInfo_tea", questionInfoBeans1);                   //发还给前端
//
//        return "TeacherQset";
//    }

    //套题修改界面
    @RequestMapping("/toUpdateQset")
    public String toUpdateQset(HttpServletRequest request) {
        //获取当前套题ID
        String qsetCode = request.getParameter("id");
//      将ID转换为int类型
        int id = Integer.parseInt(qsetCode);
//      查询到当前套题信息
        ProjectBean projectBean = projectServiceImpl.queryById(id);
        //存入状态
        request.getSession().setAttribute("CurrentInfoQset", projectBean);

        return "TeacherQestUpdate";
    }

    //修改套题信息
    @RequestMapping("/toUpdateInfo")
    public String toUpdateInfo(HttpServletRequest request) {
        //获取当前套题ID
        String setid = (String) request.getParameter("Id");
        //将ID转换为int类型
        int id = Integer.parseInt(setid);
        //获取用户输入框的值(标题和类型)
        String projectName = request.getParameter("ProjectName");
        String projectTime = request.getParameter("ProjectTime");

        System.out.println(projectName+projectTime+id);
        //修改调用
        projectServiceImpl.updateInfo(projectName, projectTime, id);


//        查询到当前套题信息
        QuestionInfoBean questionInfoBean = teacherQsetServiceImpl.queryByQsetCode(id);

//        查询具体答题
        List<QuestionsBean> questionsBeans = questionsServiceImpl.queryAllByCode(id);

        //存入状态
        request.getSession().setAttribute("CurrentInfoQset", questionInfoBean);
        request.getSession().setAttribute("CurrentQsetNow", questionsBeans);
        request.getSession().setAttribute("QsetCode", id);

        return toTeacherQset(request);
    }

    //修改每题
    @RequestMapping("/UpdateQset")
    public String UpdateQset(HttpServletRequest request) {

        //获取当前套题ID
        String qsetCode = request.getParameter("QsetCode");
        //将ID转换为int类型
        int code = Integer.parseInt(qsetCode);
        //获取用户修改的输入框的值
        String currentQuestions = request.getParameter("CurrentQuestions");
        String currentAnswerA = request.getParameter("CurrentAnswerA");
        String currentAnswerB = request.getParameter("CurrentAnswerB");
        String currentAnswerC = request.getParameter("CurrentAnswerC");
        String currentAnswerD = request.getParameter("CurrentAnswerD");
        String currentRight = request.getParameter("CurrentRight");
        String currentCounter = request.getParameter("CurrentCounter");
        //转换题记号的值
        int counter = Integer.parseInt(currentCounter);

        //修改
        teacherQsetServiceImpl.upadateQset(currentQuestions, currentAnswerA, currentAnswerB, currentAnswerC, currentAnswerD, currentRight, code, counter);


        //查询到当前套题信息
        QuestionInfoBean questionInfoBean = teacherQsetServiceImpl.queryByQsetCode(code);
        //查询具体答题
        List<QuestionsBean> questionsBeans = questionsServiceImpl.queryAllByCode(code);

        //存入状态
        request.getSession().setAttribute("CurrentInfoQset", questionInfoBean);
        request.getSession().setAttribute("CurrentQsetNow", questionsBeans);
        request.getSession().setAttribute("QsetCode", code);

        return "TeacherQestUpdate";
    }

    //增加套题界面
    @RequestMapping("/toAddQset")
    public String toAddQset(HttpServletRequest request) {
        return "AddQsetInfo";
    }

    //跳转具体题目设置
    @RequestMapping("/toAddQsetSet")
    public String toAddQsetSet(HttpServletRequest request) {
        //获取当前登录的教师用户名
        String username = (String) request.getSession().getAttribute("username");
        String userInfo = (String) request.getSession().getAttribute("userInfo");

        //获取套题信息相关
        String addName = request.getParameter("AddName");//名称
        String addTime = request.getParameter("AddTime");//时间
        String addContent = request.getParameter("AddContent");//内容
        String addPrice = request.getParameter("AddPrice");//价格
        String addDetail = request.getParameter("AddDetail");

        System.out.println("start");
        //获取当前时间戳
        String AddQsetTime = teacherQsetServiceImpl.resDate(teacherQsetServiceImpl.SetQsetTime());

        System.out.println(addName + addTime + addContent + addPrice);
        String customer = "空";
        //添加信息
        teacherQsetServiceImpl.addInfo(addName, addTime, addContent, addPrice, addDetail, username, customer);

        //状态存入
        request.getSession().setAttribute("AddsetName", addName);
        request.getSession().setAttribute("AddsetTime", addTime);
        request.getSession().setAttribute("AddsetContent", addContent);
        request.getSession().setAttribute("AddsetPrice", addPrice);
        request.getSession().setAttribute("AddsetDetail", addDetail);
        request.getSession().setAttribute("AddsetAuthor", userInfo);

        //获取添加的套题ID

        return "AddQsets";
    }

    //增加题目信息
    @RequestMapping("/AddComplete")
    public String AddComplete(HttpServletRequest request) {
        return "TeacherIndex";
    }

    //功能导航搜索
    @RequestMapping("/toNavigationSearch")
    public String toNavigationSearch(HttpServletRequest request) {
        StringBuffer stringBuffer = new StringBuffer(request.getParameter("Navigation_search"));
        int len = stringBuffer.length();
        String[] strings = new String[2];
        strings[0] = ("我的套题");
        strings[1] = ("增加套题");
        for (String string : strings) {
            for (int i = 0; i < string.length() - len + 1; i++) {
                if (strings[0].substring(i, i + len).equals(stringBuffer.toString())) return toTeacherQset(request);
                else if (strings[1].substring(i, i + len).equals(stringBuffer.toString())) return "AddQsetInfo";
            }
        }
        return "TeacherIndex";
    }
}
