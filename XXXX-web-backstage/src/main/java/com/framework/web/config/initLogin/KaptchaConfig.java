package com.framework.web.config.initLogin;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.web.config.initLogin
 * @Description 验证码图片生成初始化配置
 * @DateTime 2019/12/21 11:09
 * @Version 1.0
 */
@Configuration
public class KaptchaConfig {

    /**
     * @return com.google.code.kaptcha.impl.DefaultKaptcha
     * @Titel 初始化图片验证码参数配置
     * @Description 初始化图片验证码参数配置
     * @Author 邋遢龘鵺
     * @DateTime 2019/12/28 18:27
     */
    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        properties.setProperty("kaptcha.border", "no");
        properties.setProperty("kaptcha.border.color", "105,179,90");
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        properties.setProperty("kaptcha.image.width", "110");
        properties.setProperty("kaptcha.image.height", "40");
        properties.setProperty("kaptcha.textproducer.font.size", "35");
        properties.setProperty("kaptcha.session.key", "code");
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
