package com.yu.demo.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.yu.demo.realm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 最简版测试
 */
//@Configuration
public class ShiroConfig {
    //3. shiroFilterfactaryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("getDefaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);//设置安全管理器
        shiroFilterFactoryBean.setLoginUrl("/tologin");//没有认证后跳到的页面
        /**
         * shiro的内置过滤器
         anon：无需认证就可以访问 默认
         authc：必须认证了才能访问
         user：必须拥有记住我功能才能访问
         perms：必须拥有对某个的权限才能访问
         role：拥有某个角色权限才能访问
         */
        //添加shiro的内置过滤器  设置要拦截的url
        Map<String,String> filterChainDefinitionMap=new LinkedHashMap<>();//拦截

        //注意过滤器配置顺序 不能颠倒
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
        filterChainDefinitionMap.put("/logout", "logout");
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/tologin", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/Unauthorized", "anon");
        filterChainDefinitionMap.put("/add", "authc,perms[user:add]");
        filterChainDefinitionMap.put("/delete", "authc,perms[user:delete]");
        filterChainDefinitionMap.put("/**", "authc");
//        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
//        shiroFilterFactoryBean.setLoginUrl("/unauth");
        // 登录成功后要跳转的链接
        //shiroFilterFactoryBean.setSuccessUrl("/index");

        /*filterChainDefinitionMap.put("/add","authc");// /add请求必须认证才能访问
        filterChainDefinitionMap.put("/del","authc");//del必须认证才能访问
        // filterChainDefinitionMap.put("user/**","authc");//支持通配符
        */

        //授权
//        filterChainDefinitionMap.put("/**","perms[]");//没有这个user:add权限的会被拦截下来
//        filterChainDefinitionMap.put("/del","perms[user:delete]");//没有这个user:delete权限的会被拦截下来

        //未授权的跳转的url
        shiroFilterFactoryBean.setUnauthorizedUrl("/Unauthorized");
        //设置注销的url
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);//把设置好的过滤设置到ShiroFilterFactoryBean
        return shiroFilterFactoryBean;
    }

    //2. DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm对象  userRealm
        securityManager.setRealm(userRealm);
        return  securityManager;
    }
    //1. 创建realm对象 自定义的·类
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //整合shiroDialect：用来整合shiro-thymeleaf
    @Bean
    public ShiroDialect getshiroDialect(){
        return new  ShiroDialect();
    }
}
