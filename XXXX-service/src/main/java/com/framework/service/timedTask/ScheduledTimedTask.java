package com.framework.service.timedTask;

import com.alibaba.fastjson.JSONObject;
import com.framework.common.util.date.DateStyleUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.common.util.redis.RedisUtil;
import com.framework.common.util.system.DictUtil;
import com.framework.model.system.SystemDict;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;

/**
 * @author 邋遢龘鵺
 * @version 1.0
 * @className com.framework.service.timedTask
 * @description 测试定时任务
 * @datetime 2019/10/11
 */
@Async //异步
//@Configurable //自动注入bean`
@Configuration //等同于spring的XML配置文件；使用Java代码可以检查类型安全。
@EnableScheduling //配置类注解开启对计划任务的支持
public class ScheduledTimedTask {
    @Autowired
    private RedisUtil redisUtil;

    //每隔10秒执行一次
//    @Scheduled(cron = "0/10 * * * * *")
    public void scheduled() {
        String obj = redisUtil.getString(RedisKeyUtil.getPermissionDictKey(DictUtil.DICT_KEY_TEST));
        if (StringUtils.isEmpty(obj)) {
            return;
        }
        SystemDict sd = JSONObject.parseObject(obj, SystemDict.class);
        if (sd.getIsEnable() != NumeralUtil.POSITIVE_ONE) {
            return;
        }
        System.out.println(DateFormatUtils.format(new Date(), DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS) + ":执行了");
    }
}
