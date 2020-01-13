package com.framework.dao.generator.godeGenerator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.dao.generator.godeGenerator.MybatisGeneratorMain
 * @Description Java代码生成器，dao，xml，entity
 * @DateTime 2019/10/11
 * @Version 1.0
 */
public class MybatisGeneratorMain {

    public MybatisGeneratorMain() {

    }

    public static void main(String args[]) throws InterruptedException, SQLException, IOException, XMLParserException, InvalidConfigurationException {
        List<String> warnings = new ArrayList();
        boolean overwrite = true;
        String path = new MybatisGeneratorMain().getPath();
        path = path.substring(1);
        path = path.substring(0, path.lastIndexOf("target"));
        StringBuilder file = new StringBuilder(path);
        file.append("\\src\\test\\resources\\mybatisGeneratorConfig.xml");
        File configFile = new File(file.toString());
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }

    public String getPath() {
        return this.getClass().getResource("/").getPath();
    }
}
