package com.rgobj.generalproblemdemo.mapper;

import com.rgobj.generalproblemdemo.bean.ProjectBean;
import com.rgobj.generalproblemdemo.bean.QuestionInfoBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectMapper {
    ProjectBean queryById(int id);

    int updateInfo(String name, String time, int id);

    int addInfo(String name, String time, String content, String price, String detail, String freelance, String customer);

    List<ProjectBean> queryByTeacherUser(String freelance);

    List<ProjectBean> queryAll();

    int updateCustomer(String name, int id);

    int deleteById(String customer, int id);

    List<ProjectBean> queryAllCusByName(String customer);
}
