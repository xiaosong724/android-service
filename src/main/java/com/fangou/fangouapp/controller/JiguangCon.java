package com.fangou.fangouapp.controller;

import cn.jpush.api.push.PushResult;
import com.fangou.fangouapp.service.JpushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class JiguangCon {
    @Autowired
    JpushService jpushService;


    @ResponseBody
    @RequestMapping("/jiguang")
    public void jiguang() {
        String title = "繁狗";
        String content = "我推送要搞好了啊";
        Map<String, String> extrasMap = new HashMap<String, String>();
//此Map必须创建和实例化，但可以不添加内容,别名可以不写
       // extrasMap.put("日志", "你好啊");

//方式一：服务端控制推送内容方式
       PushResult fangou = jpushService.sendPush(title, content, extrasMap);
        System.out.println(fangou);
//方式二：服务端控制推送并带返回值得方式
        //jpushService.sendPushWithCallback(title, content, extrasMap, "你业务中的别名1");
//方式三：服务端仅推送内容，客户端自定义显示的方式
        // jpushService.sendCustomPush(title, content, extrasMap);
    }

}
