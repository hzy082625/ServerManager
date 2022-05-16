package com.rgobj.generalproblemdemo.service;

import com.rgobj.generalproblemdemo.bean.ProjectBean;
import com.rgobj.generalproblemdemo.bean.QuestionInfoBean;
import com.rgobj.generalproblemdemo.bean.QuestionsBean;
import com.rgobj.generalproblemdemo.bean.TeacherBean;

import java.util.List;

/**
 * 教师类接口
 */
public interface TeacherQsetService {

    //通过教师用户名查询套题信息
    public List<ProjectBean> queryByTeacherUser(String freelance);

    //通过套题ID查询套题信息
    public QuestionInfoBean queryByQsetCode(int code);

    //根据ID和输入的值修改套题信息
    public int updateInfo(String question_title, String question_type, int question_code);

    //修改每道题的内容
    public int upadateQset(String questions, String answer_a, String answer_b, String answer_c, String answer_d, String answer_right, int question_code, int questions_counter);

    //查询套题ID
    public int queryCode(String question_username, String question_time);

    //增加新题
    public int addQset(QuestionsBean questionsBean);

    //查询所有教师
    public List<TeacherBean> queryAll();

    int addInfo(String addName, String addTime, String addContent, String addPrice, String addDetail, String userInfo, String cunstomer);
}
