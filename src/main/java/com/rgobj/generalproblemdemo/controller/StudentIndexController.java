package com.rgobj.generalproblemdemo.controller;

import com.rgobj.generalproblemdemo.bean.ProjectBean;
import com.rgobj.generalproblemdemo.bean.QuestionInfoBean;
import com.rgobj.generalproblemdemo.bean.QuestionsBean;
import com.rgobj.generalproblemdemo.bean.StuResultBean;
import com.rgobj.generalproblemdemo.service.serviceImpl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * 学生功能实现
 */
@Controller
public class StudentIndexController {

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

//    点击logo刷新
    @RequestMapping("/toStuIndex")
    public String toStudentIndex(){
        return "StudentIndex";
    }

//    跳转答题中心
    @RequestMapping("/toStuExam")
    public String toStuExam(HttpServletRequest request){

        //查询所有套题
        List<ProjectBean> projectBeans = projectServiceImpl.queryAll();

        request.getSession().setAttribute("projectInfo",projectBeans);

        return "StuExam";
    }

//    题目搜索
    @RequestMapping("/toQuestionSearch")
    public String toQuestionSearch(HttpServletRequest request){
        //拿到搜索信息
        String string = request.getParameter("table_search");
        //存放搜索出来的信息
        List<QuestionInfoBean> questionInfoBeans1 = new LinkedList<>();
        //获取所有套题
        List<QuestionInfoBean> questionInfoBeans = questionInfoServiceImpl.queryAll();
        //对套题进行排序显示，时间后的靠前
        questionInfoServiceImpl.setSorting(questionInfoBeans);
        //将套题的时间戳进行格式化
        for(QuestionInfoBean q:questionInfoBeans){
            q.setQuestion_time(questionInfoServiceImpl.getFormatDate(q.getQuestion_time()));
        }

        if (string.length() == 0) return "StuExam";                                          //空着不变

        //搜索操作
        //question_code    author   title   type
        for (QuestionInfoBean q:questionInfoBeans){
            boolean judge = true;
            int len = string.length();                                                            //获取数据长度
            StringBuffer stringcode = new StringBuffer(String.valueOf(q.getQuestion_code()));
            StringBuffer stringauthor = new StringBuffer(q.getQuestion_author());
            StringBuffer stringtitle = new StringBuffer(q.getQuestion_title());
            StringBuffer stringtype = new StringBuffer(q.getQuestion_type());

            for (int i = 0;i < stringcode.length() - len + 1 && judge;i++) {
                if (stringcode.substring(i,i+len).equals(string)) {
                    questionInfoBeans1.add(q);
                    judge = false;
                }
            }
            for (int i = 0;i < stringauthor.length() - len + 1 && judge;i++) {
                if (stringauthor.substring(i,i+len).equals(string)) {
                    questionInfoBeans1.add(q);
                    judge = false;
                }
            }
            for (int i = 0;i < stringtitle.length() - len + 1 && judge;i++) {
                if (stringtitle.substring(i,i+len).equals(string)) {
                    questionInfoBeans1.add(q);
                    judge = false;
                }
            }
            for (int i = 0;i < stringtype.length() - len + 1 && judge;i++) {
                if (stringtype.substring(i,i+len).equals(string)) questionInfoBeans1.add(q);
            }
        }
        if (questionInfoBeans1 == null) return "StuExam";

        request.getSession().setAttribute("questionInfo",questionInfoBeans1);

        return "StuExam";
    }

//    跳转答题界面
    @RequestMapping("/toQuestionsStart")
    public String toQuestionsStart(HttpServletRequest request){
        String id = request.getParameter("id");//获取ID

        System.out.println(id);
        int code = Integer.parseInt(id);//将ID转换为整形
        System.out.println(code);
        //存储相关信息状态
        System.out.println(projectServiceImpl.queryById(code).getDetail());
        request.getSession().setAttribute("detail",projectServiceImpl.queryById(code).getDetail());
        request.getSession().setAttribute("id",id);

        return "QuestionsStart";
    }

    @RequestMapping("turnintoQuesSta")
    public String turnintoQuesSta(HttpServletRequest request) {

        return "QuestionsStart";
    }

//    跳转情况查看中心
    @RequestMapping("/toStuExamResult")
    public String toStuExamResult(HttpServletRequest request){

        System.out.println("11111");
        //获取当前用户名
        String username =(String) request.getSession().getAttribute("username");

        System.out.println(username);
        //查询当前学生结果
        List<ProjectBean> projectBeans = projectServiceImpl.queryAllCusByName(username);

        System.out.println("222");
        request.getSession().setAttribute("ProjectByCus",projectBeans);

        return "StuExamResult";
    }

//    答题情况搜索
    @RequestMapping("/toQueSituation")
    public String toQueSituation(HttpServletRequest request){
        //拿到搜索内容
        String string = request.getParameter("table_search");
        //存放搜索出来的信息
        List<StuResultBean> stuResultBeans1 = new LinkedList<>();
        //查询当前学生结果
        List<StuResultBean> stuResultBeans = stuResultImpl.queryAllByName((String) request.getSession().getAttribute("username"));
        //按最新的答题时间排序
        stuResultImpl.setSorting(stuResultBeans);
        //对时间进行格式化
        for(StuResultBean s:stuResultBeans){
            s.setStu_finishtime(stuResultImpl.getFormatDate(s.getStu_finishtime()));
        }

        if (string.length() == 0) return "StuExamResult";                                          //空着不变

        //搜索操作
        //question_code    title
        for (StuResultBean q:stuResultBeans){
            boolean judge = true;
            int len = string.length();                                                            //获取数据长度
            StringBuffer stringcode = new StringBuffer(String.valueOf(q.getStu_questioncode()));
            StringBuffer stringtitle = new StringBuffer(q.getStu_questiontitle());
            for (int i = 0;i < stringcode.length() - len + 1 && judge;i++) {
                if (stringcode.substring(i,i+len).equals(string)) {
                    stuResultBeans1.add(q);
                    judge = false;
                }
            }
            for (int i = 0;i < stringtitle.length() - len + 1 && judge;i++) {
                if (stringtitle.substring(i,i+len).equals(string)) {
                    stuResultBeans1.add(q);
                }
            }
        }
        if (stuResultBeans1 == null) return "StuExamResult";
        request.getSession().setAttribute("StuResults",stuResultBeans1);

        return "StuExamResult";
    }

//    退出
    @GetMapping("/exit")
    public String exit(HttpServletRequest request){
        request.getSession().removeAttribute("userInfo");
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("loginTime");
        request.getSession().removeAttribute("questionInfo");
        return "redirect:/toLogin";
    }

//    功能导航搜索
    @RequestMapping("/toNavigateSearch")
    public String toNavigationSearch(HttpServletRequest request){
        StringBuffer stringBuffer = new StringBuffer(request.getParameter("Navigate_search"));
        int len = stringBuffer.length();
        String[] strings = new String[2];
        strings[0] = ("答题中心");
        strings[1] = ("答题情况");
        for (String string:strings){
            for (int i = 0;i < string.length() - len + 1;i++) {
                if (strings[0].substring(i,i+len).equals(stringBuffer.toString())) return toStuExam(request);
                else if (strings[1].substring(i,i+len).equals(stringBuffer.toString())) return toStuExamResult(request);
            }
        }
        return "StudentIndex";
    }

}
