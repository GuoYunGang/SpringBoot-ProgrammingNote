package com.gyg.controoler;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "Hello,Shiro!");
        return "index";
    }

    @RequestMapping("/user/add")
    public String toAdd() {
        return "/user/add";
    }

    @RequestMapping("/user/update")
    public String toUpdate() {
        return "/user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    /**
     * 进入登录管理界面
     *
     * @param username
     * @param password
     * @param model
     * @return
     */
    @RequestMapping("/login")
    public String login(String username, String password, Model model) {
//        获取到当前用户
        Subject subject = SecurityUtils.getSubject();
//        封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
//            执行登录操作，如果没有异常就说明成功了！
            subject.login(token);
//            Session session = subject.getSession();
//            session.setAttribute("loginUser",username);
//            将用户名传递给前端
            model.addAttribute("username", username);

            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名不存在!");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码不存在！");
            return "login";
        }

    }

    /**
     * 进入未授权页面
     *
     * @return
     */
    @RequestMapping("/unauth")
    @ResponseBody
    public String unauthorized() {
        return "没有权限，不能访问！";
    }


    @RequestMapping("/toloogout")
    public String toLogOut() {
        Subject subject = SecurityUtils.getSubject();
//        实现用户注销，但是会请求到logout
        subject.logout();
        return "index";
    }

    /**
     * 处理注销返回的请求logout，
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
        return "index";
    }

}
