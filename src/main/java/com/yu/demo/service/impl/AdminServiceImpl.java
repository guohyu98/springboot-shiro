package com.yu.demo.service.impl;

import com.yu.demo.dao.AdminDao;
import com.yu.demo.pojo.Admin;
import com.yu.demo.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminDao adminDao;

    @Override
    public Admin queryByName(String username) {
        return adminDao.queryByName(username);
    }
}
