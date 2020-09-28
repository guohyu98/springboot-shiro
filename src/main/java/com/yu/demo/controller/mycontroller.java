package com.yu.demo.controller;

import com.yu.demo.pojo.Admin;
import com.yu.demo.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class mycontroller {
    @Autowired
    AdminService adminService;

    @RequestMapping({"/", "tologin"})
    public String tologin(){
        return "login";
    }

    @RequestMapping("/showuser")
    @ResponseBody
    public void showuser(){
        Admin admin = adminService.queryByName("yu");
        System.out.println(admin);
    }

    @RequestMapping("/login")
    public String login(String username, String password){
        try{
            //获取当前用户
            Subject subject = SecurityUtils.getSubject();
            //封装用户的登录数据
            UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
            subject.login(usernamePasswordToken);
            return "index";
        }catch(UnknownAccountException e){
            e.printStackTrace();
            System.out.println("用户名不存在");
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
        }
        return "login";
    }

    @RequestMapping("/add")
    public String add(){//跳转页面
        return "add";
    }

    @RequestMapping("/del")
    public String delete(){//跳转页面
        return "delete";
    }

    @RequestMapping("/Unauthorized")
    public String Unauthorized(){//没有权限跳转的url
        return "Unauthorized";
    }

    @RequestMapping("/logout")
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("loginuser", null);
        return "login";
    }
}
