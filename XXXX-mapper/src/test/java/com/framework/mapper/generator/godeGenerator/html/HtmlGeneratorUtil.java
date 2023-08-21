package com.framework.mapper.generator.godeGenerator.html;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author 龘鵺
 * @ClassName com.framework.mapper.generator.godeGenerator.html
 * @description html生成器配置
 * @date 2023/3/10 10:31
 * @Version 1.0
 */
public class HtmlGeneratorUtil {
    public static String getPath() {
        String path = new HtmlGeneratorUtil().getClass().getResource("/").getPath();
        path = path.substring(1);
        path = path.substring(0, path.lastIndexOf("XXXX-mapper"));
        return path;
    }

    public static void htmlGenerator(String content, String file, String fileName) {
        ///G:/install/idea-save/gitee/demo-framework-backstage/XXXX-mapper/target/test-classes/com/framework/mapper/generator/godeGenerator/html/
        ///G:/install/idea-save/gitee/demo-framework-backstage/XXXX-mapper/target/test-classes/
        String path = getPath() + file;
        String pathName = getPath() + file + "/" + fileName;
        FileOutputStream fileOutputStream = null;
        try {
            File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }
            fileOutputStream = new FileOutputStream(pathName);
            fileOutputStream.write(content.getBytes(StandardCharsets.UTF_8));
            fileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
