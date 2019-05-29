package com.fangou.fangouapp.config;

import com.fangou.fangouapp.mapper.GameMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    @Resource
    GameMapper gameMapper;
    //3.添加定时任务
    @Scheduled(cron = "0 0 23 * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        List<Integer> queryid = gameMapper.queryid();
        for (int i = 0; i <queryid.size() ; i++) {
            Integer integer = queryid.get(i);
            gameMapper.updateEveryDay(integer);
            System.err.println("执行静态定时任务时间将id为: " +queryid.get(i)+",恢复了签到和PK次数." );

        }
    }
}