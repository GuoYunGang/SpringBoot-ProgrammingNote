package com.gyg.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    /**
     * 1、设置realm，需要自定义用户realm
     *
     * @return
     */
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    /**
     * 2、配置DefaultWebSecurityManager
     * 使用@Qualifier注解，来接收bean中的userRealm
     *
     * @return
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 3、配置ShiroFilterFactoryBean
     *
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
//        添加shiro的内置过滤器

//        拦截
        Map<String, String> filterMap = new LinkedHashMap<>();

//        只有拥有add权限的用户才可以访问该页面，正常情况下会跳转到一个未授权页面
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");


//        如果未授权就发送这个请求
        bean.setUnauthorizedUrl("/unauth");

        filterMap.put("/user/*", "authc");
        bean.setFilterChainDefinitionMap(filterMap);

//        实现登录验证
        bean.setLoginUrl("/toLogin");
        return bean;
    }

    /**
     * 整合ShiroDialect，用来整合shiro thymeleaf
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }


}
