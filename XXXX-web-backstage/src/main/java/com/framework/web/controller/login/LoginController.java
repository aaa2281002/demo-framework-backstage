package com.framework.web.controller.login;

import com.framework.common.model.CaptchaImage;
import com.framework.common.util.system.CaptchaUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.controller.login
 * @description 登录请求控制类
 * @datetime 2019/10/11
 */
@Controller
@RequestMapping(value = "/")
public class LoginController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    /**
     * @return org.springframework.web.servlet.ModelAndView
     * @title 登录页跳转
     * @description 登录页跳转
     * @author 邋遢龘鵺
     * @datetime 2019/12/28 18:41
     */
    @RequestMapping(value = {"/login/page", "/login"})
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

//    /**
//     * @return java.lang.String
//     * @title 退出跳转
//     * @description 退出跳转
//     * @author 邋遢龘鵺
//     * @datetime 2019/12/28 18:41
//     */
//    @GetMapping(value = "/log/out")
//    public String loginOut() {
//        return "loginOut";
//    }

    /**
     * @param httpServletRequest  1 请求对象
     * @param httpServletResponse 2 响应对象
     * @title 获得验证码图片
     * @description 获得验证码图片
     * @author 邋遢龘鵺
     * @datetime 2019/12/28 18:41
     */
    @GetMapping("/default/captcha")
    public void defaultCaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws Exception {
        // 设置http响应头控制浏览器禁止缓存当前文档内容
        httpServletResponse.setDateHeader("Expires", 0);
        // 设置标准的HTTP / 1.1无缓存标头。
        httpServletResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // 设置IE扩展的HTTP / 1.1无缓存标头（使用addHeader）。
        httpServletResponse.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // 设置标准的HTTP / 1.0无缓存标头。
        httpServletResponse.setHeader("Pragma", "no-cache");
        // 设置返回格式
        httpServletResponse.setContentType("image/jpeg");
        // 创建图像文字对象
        String capText = defaultKaptcha.createText();
        // 用图像文字创建图片
        BufferedImage bi = defaultKaptcha.createImage(capText);
        // 存放session缓存
        CaptchaImage ic = new CaptchaImage(capText, CaptchaUtil.CAPTCHA_EXPIRED_TIME);
        httpServletRequest.getSession().setAttribute(CaptchaUtil.CAPTCHA_SESSION_KEY, ic);
        // 推送一个JPG格式图片流
        ServletOutputStream out = httpServletResponse.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        // 关闭流
        try {
            out.flush();
        } finally {
            out.close();
        }
    }
}
