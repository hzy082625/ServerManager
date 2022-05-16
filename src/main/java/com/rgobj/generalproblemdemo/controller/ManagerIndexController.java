package com.rgobj.generalproblemdemo.controller;

import com.rgobj.generalproblemdemo.bean.*;
import com.rgobj.generalproblemdemo.service.StuUserService;
import com.rgobj.generalproblemdemo.service.TeacherUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * 管理员管理
 */

@Controller
public class ManagerIndexController {

    @Autowired
    StuUserService stuUserService;//学生业务层实现

    @Autowired
    TeacherUserService teacherUserService;//教师业务层实现

    //学生管理界面
    @RequestMapping("/toManageStudent")
    public String toStudentManage(HttpServletRequest request) {
        List<StuUserBean> stuUserBeans = stuUserService.queryAll();
        //按名字升序排序
        stuUserBeans.sort(Comparator.comparing(StuUserBean::getName));
        stuUserBeans.get(0).getUsername();
        request.getSession().setAttribute("student_info", stuUserBeans);

        return "ManageStudent";
    }

    //学生信息修改前获取界面
    @RequestMapping("/toGetStu")
    public String toGetStu(HttpServletRequest request) {
        //获取当前学生用户名
        String username = request.getParameter("username");
//        查询到当前学生信息
        StuUserBean stuUserBean = stuUserService.query(username);

        //存入状态
        request.getSession().setAttribute("StudentInfo", stuUserBean);

        return "UpdateStu";
    }

    //学生信息修改
    @RequestMapping("/toUpdateStu")
    public String toUpdateStu(HttpServletRequest request) {
        //拿到修改后的学生信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        //保存
        stuUserService.update(username,password,name);
        return toStudentManage(request);
    }

    //学生信息删除
    @RequestMapping("/toDeleteStu")
    public String toDeleteStu(HttpServletRequest request) {
        //拿到删除学生
        String username = request.getParameter("username");
        //删除
        stuUserService.delete(username);
        return toStudentManage(request);
    }

    //教师管理界面
    @RequestMapping("toManageTeacher")
    public String toTeacherManage(HttpServletRequest request) {
        List<TeacherBean> teacherBeans = teacherUserService.queryAll();
        //按名字升序排序
        teacherBeans.sort(Comparator.comparing(TeacherBean::getName));
        request.getSession().setAttribute("teacher_info", teacherBeans);

        return "ManageTeacher";
    }

    //教师信息修改前获取界面
    @RequestMapping("/toGetTea")
    public String toGetTea(HttpServletRequest request) {
        //拿到当前教师信息
        String username = request.getParameter("username");
        //查询当前教师信息
        TeacherBean teacherBean = teacherUserService.query(username);

        //存入状态
        request.getSession().setAttribute("TeacherInfo", teacherBean);

        return "UpdateTea";
    }

    //教师信息修改
    @RequestMapping("/toUpdateTea")
    public String toUpdateTea(HttpServletRequest request) {
        //拿到修改后的信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        //更新
        teacherUserService.update(username, password, name);
        return toTeacherManage(request);
    }

    //教师信息删除
    @RequestMapping("/toDeleteTea")
    public String toDeleteTea(HttpServletRequest request) {
        //拿到删除教师
        String username = request.getParameter("username");
        //删除
        teacherUserService.delete(username);
        return toTeacherManage(request);
    }

    //搜索功能导航
    @RequestMapping("toSearch")
    public String toSearch(HttpServletRequest request) {
        //拿到搜索的内容
        String string = request.getParameter("Tea_search");
        String strtea = request.getParameter("Stu_search");
        if (strtea != null && strtea.length() != 0) {
            //获取所有学生的信息
            List<StuUserBean> stuUserBeans = stuUserService.queryAll();
            //存放找到的学生信息
            List<StuUserBean> stuUserBeans1 = new LinkedList<>();
            //按名字升序排序
            stuUserBeans.sort(Comparator.comparing(StuUserBean::getUsername));
            //查询输出到stuUserBeans1
            for (StuUserBean t:stuUserBeans){
                String stringuser = t.getUsername();
                String stringname = t.getName();
                boolean judge = true;
                //查询username中符合条件的学生
                System.out.println(stringuser);
                for (int i = 0;i < stringuser.length() - strtea.length() + 1;i++) {
                    if (stringuser.substring(i,i+strtea.length()).equals(strtea)) {
                        stuUserBeans1.add(t);
                        judge = false;
                    }
                }
                //查询name中符合条件的学生
                for (int i = 0;i < stringname.length() - strtea.length() + 1 && judge;i++) {
                    if (stringname.substring(i,i+strtea.length()).equals(strtea)) {
                        stuUserBeans1.add(t);
                    }
                }
            }
            //返回到前端
            request.getSession().setAttribute("student_info",stuUserBeans1);
            return "ManageStudent";
        }
        else if (string != null && string.length() != 0){
            System.out.println(string.length());
            //获取所有教师的信息
            List<TeacherBean> teacherBeans = teacherUserService.queryAll();
            //存放找到的教师信息
            List<TeacherBean> teacherBeans1 = new LinkedList<>();
            //按名字升序排序
            teacherBeans.sort(Comparator.comparing(TeacherBean::getUsername));
            //查询输出到teaacherBeans1
            for (TeacherBean t:teacherBeans){
                String stringuser = t.getUsername();
                String stringname = t.getName();
                boolean judge = true;
                //查询username中符合条件的教师
                for (int i = 0;i < stringuser.length() - string.length() + 1;i++) {
                    if (stringuser.substring(i,i+string.length()).equals(string)) {
                        teacherBeans1.add(t);
                        judge = false;
                    }
                }
                //查询name中符合条件的教师
                for (int i = 0;i < stringname.length() - string.length() + 1 && judge;i++) {
                    if (stringname.substring(i,i+string.length()).equals(string)) {
                        teacherBeans1.add(t);
                        judge = false;
                    }
                }
            }
            //返回到前端
            request.getSession().setAttribute("teacher_info",teacherBeans1);
            return "ManageTeacher";
        }
        else if (strtea != null) return "ManageStudent";
        else return "ManageTeacher";
    }

    //功能导航搜索
    @RequestMapping("/toNavigaSearch")
    public String toNavigaSearch(HttpServletRequest request){
        StringBuffer stringBuffer = new StringBuffer(request.getParameter("Naviga_search"));
        int len = stringBuffer.length();
        String[] strings = new String[2];
        strings[0] = ("学生管理");
        strings[1] = ("教师管理");
        for (String string:strings){
            for (int i = 0;i < string.length() - len + 1;i++) {
                if (strings[0].substring(i,i+len).equals(stringBuffer.toString())) return toStudentManage(request);
                else if (strings[1].substring(i,i+len).equals(stringBuffer.toString())) return toTeacherManage(request);
            }
        }
        return "ManagerIndex";
    }
}
