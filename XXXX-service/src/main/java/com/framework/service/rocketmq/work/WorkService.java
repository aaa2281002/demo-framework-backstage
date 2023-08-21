package com.framework.service.rocketmq.work;

import com.framework.common.enums.mq.RocketmqWorkEnum;
import com.framework.service.rocketmq.work.ConsumerService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 龘鵺
 * @version 1.0
 * @className com.framework.service.rocketmq.work
 * @description 业务调度类
 * @datetime 2023/4/9 16:17
 */
@Service("workService")
public class WorkService {
    private Map<String, ConsumerService> serviceMap = new HashMap<String, ConsumerService>();

    @PostConstruct
    public void init() {
        serviceMap.put(RocketmqWorkEnum.code1.getCode(), null);
    }


    /**
     * @param code 1 代码
     * @return com.framework.service.rocketmq.work.ConsumerService
     * @Titel 根据代码获取业务实现类
     * @description 根据代码获取业务实现类
     * @author 邋遢龘鵺
     * @datetime 2020/3/29 18:40
     */
    public ConsumerService getCodeByService(String code) {
        ConsumerService p = serviceMap.get(code);
        return p;
    }
}
