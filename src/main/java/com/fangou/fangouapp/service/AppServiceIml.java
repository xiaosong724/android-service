package com.fangou.fangouapp.service;

import com.fangou.fangouapp.mapper.LogMapper;
import com.fangou.fangouapp.vo.Logimg;
import com.fangou.fangouapp.vo.LoveLog;
import com.fangou.fangouapp.vo.Loveimg;
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
        return list.toString();
    }

    @Override
    public void recoverLogimg(Logimg logimg) {
        Loveimg loveLog= new Loveimg();
        loveLog.setLogid(logimg.getLogid());
        loveLog.setImgpath(logimg.getImgpath());
        logMapper.loveimgup(loveLog);
        logMapper.updateLogimg("/img/mini/"+logimg.getImgpath(),logimg.getLogid());
        System.out.println("恢复日志图片成功:"+logimg.getImgpath()+",并把它更新为了日志封面");

    }
}
