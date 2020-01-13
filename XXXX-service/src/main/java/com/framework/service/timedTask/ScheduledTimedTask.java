package com.framework.service.timedTask;

import com.framework.common.util.date.DateStyleUtil;
import com.framework.common.util.other.NumeralUtil;
import com.framework.common.util.redis.RedisKeyUtil;
import com.framework.common.util.redis.RedisUtil;
import com.framework.common.util.system.DictUtil;
import com.framework.model.entity.system.SystemDict;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Date;

/**
 * @Author 邋遢龘鵺
 * @ClassName com.framework.service.timedTask
 * @Description 测试定时任务
 * @DateTime 2019/10/11
 * @Version 1.0
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
        Object obj = redisUtil.getString(RedisKeyUtil.getPermissionDictKey(DictUtil.DICT_KEY_TEST));
        if (obj == null) {
            return;
        }
        SystemDict sd = (SystemDict) obj;
        if (sd.getIsEnable() != NumeralUtil.POSITIVE_ONE) {
            return;
        }
        System.out.println(DateFormatUtils.format(new Date(), DateStyleUtil.FORMAT_YYYY_MM_DD_HH_MM_SS) + ":执行了");
    }
}
