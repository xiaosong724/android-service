package com.fangou.fangouapp.controller;

import com.fangou.fangouapp.service.AppService;
import com.fangou.fangouapp.vo.Logimg;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppApiCon {
    @Autowired
    AppService appService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    Gson gson;
    @RequestMapping("/apilogdelimg")
    public String apilogdelimg(){
        String json = appService.showLogimg();
        return json;
    }
    @RequestMapping(value = "/recoverimg")
    public String recoverImg(String imgid){
        System.out.println(imgid);
        String img = redisTemplate.opsForValue().get("del_" + imgid);
        redisTemplate.delete("path_del_"+imgid);
        redisTemplate.delete("del_"+imgid);
        Logimg logimg = gson.fromJson(img, Logimg.class);
        appService.recoverLogimg(logimg);
        return "恢复成功";
    }
}
