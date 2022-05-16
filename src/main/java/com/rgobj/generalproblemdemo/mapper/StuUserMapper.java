package com.rgobj.generalproblemdemo.mapper;

import com.rgobj.generalproblemdemo.bean.QuestionInfoBean;
import com.rgobj.generalproblemdemo.bean.StuUserBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 学生类接口
 */

@Mapper
@Repository
public interface StuUserMapper {

    //查询
    public List<StuUserBean> queryAll();

    //添加数据
    public int add(StuUserBean stuUserBean);

    //根据用户查询数据
    public StuUserBean queryByName(String username);

    //用户密码验证
    public StuUserBean loginCheck(String username,String password);

    //查询单个学生
    public StuUserBean query(String username);

    void update(String username, String password, String name);

    void delete(String username);

    List<QuestionInfoBean> queryByStuName(String username);
}
