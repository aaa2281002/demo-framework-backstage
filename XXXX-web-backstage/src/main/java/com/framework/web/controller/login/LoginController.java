package com.framework.web.controller.login;

import com.framework.common.response.ResponseResult;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.common.util.redis.RedisUtil;
import com.framework.service.init.LoginService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.awt.image.BufferedImage;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.controller.login
 * @Description 登录请求控制类
 * @DateTime 2019/10/11
 * @Version 1.0
 */
@Api(tags = "登录", description = "登录")
@Controller
@RequestMapping(value = "/logon")
public class LoginController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private LoginService loginServiceImpl;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * @return java.lang.String
     * @Titel 退出跳转
     * @Description 退出跳转
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/28 18:41
     */
    @GetMapping(value = "/out")
    @ResponseBody
    @ApiOperation(value = "退出", httpMethod = "GET", produces = "application/json", consumes = "text/html", response = ResponseResult.class)
    public ResponseResult loginOut() {
        return loginServiceImpl.loginOut();
    }

    /**
     * @param httpServletRequest  1 请求对象
     * @param httpServletResponse 2 响应对象
     * @param grapCode            3 图形标识代码
     * @Titel 获得验证码图片
     * @Description 获得验证码图片
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/28 18:41
     */
    @GetMapping("/default/captcha")
    @ApiOperation(value = "获得验证码图片", httpMethod = "GET", produces = "application/json", consumes = "text/html", response = ResponseResult.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grapCode", value = "图形标识代码,前台随机每次请求生成的32位字符串", required = true,  paramType = "query", example = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"),
//            @ApiImplicitParam(name = "token", value = "令牌", required = false, paramType = "header"),
    })
    public void defaultCaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                               @NotBlank(message = "图形标识代码不能为空")
                               @Size(min = NumeralUtil.POSITIVE_THIRTY_TWO, max = NumeralUtil.POSITIVE_THIRTY_TWO, message = "错误图形标识代码")
                                       String grapCode)
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
//        CaptchaImage ic = new CaptchaImage(capText, CaptchaUtil.CAPTCHA_EXPIRED_TIME);
//        httpServletRequest.getSession().setAttribute(CaptchaUtil.CAPTCHA_SESSION_KEY, ic);
//        StringBuffer key = new StringBuffer(RedisPrefixUtil.REDIS_KEY_SYSTEM_NAME);
//        key.append(SymbolUtil.NO_INPUT_METHOD_COLON);
//        key.append(CaptchaUtil.CAPTCHA_SESSION_KEY);
//        key.append(SymbolUtil.NO_INPUT_METHOD_COLON);
//        key.append(grapCode);
        String key = RedisKeyUtil.getCaptchaKey(grapCode);
        redisUtil.setString(key, capText, NumeralUtil.POSITIVE_GREAT_HUNDRED);

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
