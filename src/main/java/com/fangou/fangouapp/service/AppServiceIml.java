package com.fangou.fangouapp.service;

import com.fangou.fangouapp.mapper.LogMapper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AppServiceIml implements AppService {

    @Resource
    LogMapper logMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    Gson gson;
    @Override
    public String showLogimg() {
        List<String> list= new ArrayList<>();
        Set<String> keys = redisTemplate.keys("del_*");
        for (String str:keys) {
            String val = redisTemplate.opsForValue().get(str);
            list.add(val);
        }
      ;
        return list.toString();
    }
}
