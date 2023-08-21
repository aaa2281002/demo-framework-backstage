package com.framework.web.config.initSecurityConfig.kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.web.config.initLogin
 * @description 验证码图片生成初始化配置
 * @datetime 2019/12/21 11:09
 */
@Configuration
public class KaptchaConfig {

    /**
     * @return com.google.code.kaptcha.impl.DefaultKaptcha
     * @titel 初始化图片验证码参数配置
     * @description 初始化图片验证码参数配置
     * @author 邋遢龘鵺
     * @datetime 2019/12/28 18:27
     */
    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "no");//图片边框
        properties.setProperty("kaptcha.border.color", "105,179,90");//边框颜色
//        properties.setProperty("kaptcha.textproducer.font.color", "0,0,255");//字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "0,0,255");//字体颜色
        properties.setProperty("kaptcha.noise.color", "black");//干扰 颜色，合法值： r,g,b 或者 white,black,blue.
        properties.setProperty("kaptcha.image.width", "110");// 图片宽
        properties.setProperty("kaptcha.image.height", "40");// 图片高
        properties.setProperty("kaptcha.textproducer.font.size", "35");//字体大小
        properties.setProperty("kaptcha.session.key", "code");//session key
        properties.setProperty("kaptcha.textproducer.char.length", "4");//验证码长度
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");//字体
        properties.setProperty("kaptcha.background.clear.from", "248,248,248");//背景颜色渐变，开始颜色
        properties.setProperty("kaptcha.background.clear.to", "248,248,248");//背景颜色渐变，结束颜色
        //图片样式：<br />水纹 com.google.code.kaptcha.impl.WaterRipple <br />
        //鱼眼 com.google.code.kaptcha.impl.FishEyeGimpy <br />
        //阴影 com.google.code.kaptcha.impl.ShadowGimpy
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.WaterRipple");
        properties.setProperty("kaptcha.textproducer.char.space", "3");//文字间隔
//        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");//干扰实现类
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.DefaultNoise");//干扰实现类
        properties.setProperty("kaptcha.word.impl", "com.google.code.kaptcha.text.impl.DefaultWordRenderer");//	文字渲染器
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
    
}
