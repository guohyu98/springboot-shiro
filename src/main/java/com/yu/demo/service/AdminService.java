package com.yu.demo.service;

import com.yu.demo.pojo.Admin;

public interface AdminService {
    Admin queryByName(String username);
}
