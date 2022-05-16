package com.rgobj.generalproblemdemo.service.serviceImpl;

import com.rgobj.generalproblemdemo.bean.ManagerBean;
import com.rgobj.generalproblemdemo.mapper.ManagerUserMapper;
import com.rgobj.generalproblemdemo.service.ManagerUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerUserServiceImpl implements ManagerUserService {

    //将DAO注入Service层
    @Autowired
    ManagerUserMapper managerUserMapper;

    @Override
    public List<ManagerUserService> queryAll() {
        return managerUserMapper.queryAll();
    }

    @Override
    public int add(ManagerBean managerBean) {
        return managerUserMapper.add();
    }

    @Override
    public ManagerBean queryByName(String username) {
        return managerUserMapper.queryByName(username);
    }

    @Override
    public ManagerBean loginCheck(String username, String password) {
        return managerUserMapper.loginCheck(username, password);
    }

}
