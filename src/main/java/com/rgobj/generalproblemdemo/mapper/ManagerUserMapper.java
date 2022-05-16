package com.rgobj.generalproblemdemo.mapper;

import com.rgobj.generalproblemdemo.bean.ManagerBean;
import com.rgobj.generalproblemdemo.service.ManagerUserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ManagerUserMapper {

    List<ManagerUserService> queryAll();

    int add();

    ManagerBean queryByName(String username);

    ManagerBean loginCheck(String username, String password);
}
