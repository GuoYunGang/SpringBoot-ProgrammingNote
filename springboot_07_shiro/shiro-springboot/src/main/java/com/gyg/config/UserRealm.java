package com.gyg.config;

import com.gyg.pojo.User;
import com.gyg.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义的realm，需要继承authorizaingRealm
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserServiceImpl userService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权 --> doGetAuthorizationInfo");

//        获取SimpleAuthorizationInfo
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        给访问的用户添加相应的权限
//        info.addStringPermission("user:add");

//        拿到用户当前登录的这个对象
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();  // 拿到user对象

//        从数据库中取出用户的权限
        if (user.getPerms() != null) {
            info.addStringPermission(user.getPerms());
        }

        return info;
    }


    /**
     * 认证
     *
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证 --> doGetAuthenticationInfo");

//        从数据库中取出用户名和密码

//        将token封装进行强转
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
//        判断用户名是否正确
        User user = userService.queryUserByName(userToken.getUsername());

//        如果用户查询到的是null，说明用户不存在
        if (user == null) {
            return null;
        }

        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }
}
