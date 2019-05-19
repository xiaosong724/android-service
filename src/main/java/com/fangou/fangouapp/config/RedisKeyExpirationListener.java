package com.fangou.fangouapp.config;

import com.fangou.fangouapp.service.LogService;
import com.fangou.fangouapp.vo.Logimg;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * 监听所有db的过期事件
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    LogService logService;

    @Autowired
    Gson gson;



    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

 
    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        System.out.println("过期的key 是: "+expiredKey);
        String img = redisTemplate.opsForValue().get("path_"+expiredKey);
        Logimg logimg = gson.fromJson(img, Logimg.class);
        redisTemplate.delete("path_"+expiredKey);

        File file = new File(logimg.getImgpath());
        String img1 = logimg.getImgpath().replace("img"+File.separator+"mini", "img");
        File file2 = new File(img1);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除封面图片" + logimg.getImgpath() + "成功！");
            } else {
                System.out.println("删除原图" + logimg.getImgpath() + "失败！");
            }
        } else {
            System.out.println("删除单个文件" + logimg.getImgpath() + "失败！");
        }

        if (file2.exists() && file2.isFile()) {
            if (file2.delete()) {
                System.out.println("删除原图" + logimg.getImgpath() + "成功！");
            } else {
                System.out.println("删除原图" + logimg.getImgpath() + "失败！");
            }

        } else {
            System.out.println("删除单个文件" + logimg.getImgpath() + "失败");
        }
        //redisTemplate.* 取值操作
    }
}