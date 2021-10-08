package com.gyg.config;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 国际化配置
 * @author 郭运刚
 */
public class MyLocaleResolver implements LocaleResolver {

    /**
     * 解析请求
     *
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
//        获取请求中的语言参数链接
        String language = request.getParameter("l");
//        如果没有就使用默认的
        Locale locale = Locale.getDefault();
//        判断请求中获取到的语言参数链接是否为空，如果不为空，就使用请求的链接
        if (!StringUtils.isEmpty(language)) {
//            zh_CN
            String[] splits = language.split("_");
//            国家  地区
            locale =  new Locale(splits[0], splits[1]);

        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}
