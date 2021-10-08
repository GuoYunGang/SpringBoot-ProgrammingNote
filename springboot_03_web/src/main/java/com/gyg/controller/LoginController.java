package com.gyg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * 用户登录
 *
 * @author gyg
 */
@Controller
public class LoginController {

    @RequestMapping("/user/login")
//    @ResponseBody //返回json字符串
    public String login(@RequestParam("username") String username, @RequestParam("password") String password,
                        Model model, HttpSession session) {
//        如果用户名不为空，且密码正确，就跳转页面
        if (!StringUtils.isEmpty(username) && "666666".equals(password)) {
            session.setAttribute("loginUser", username);
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "用户名或密码输入错误！");
            return "index";
        }
     /*   model.addAttribute("username",username);
        model.addAttribute("password",password);*/

    }

    /**
     * 页面注销
     * @param session
     * @return
     */
    @RequestMapping("/user/logout")
    public String logout(HttpSession session){
//        使当前session作废
        session.invalidate();
        return "redirect:/index.html";
    }

}
