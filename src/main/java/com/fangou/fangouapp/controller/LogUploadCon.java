package com.fangou.fangouapp.controller;

import com.fangou.fangouapp.service.JpushService;
import com.fangou.fangouapp.service.LogService;
import com.fangou.fangouapp.vo.Loveimg;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class LogUploadCon {
    @Autowired
    LogService logService;

    @Autowired
    Gson gson;

    @Autowired
    JpushService jpushService;

    /**
     * 上传了新的日志,并发送推送
     * @param request
     * @param file
     * @param title
     * @param username
     * @param logtype
     * @param message
     * @return
     */
    @ResponseBody
    @RequestMapping("/lovelogup")
    public String uploadLog(HttpServletRequest request, @RequestParam("file")MultipartFile[] file, String title,String username, String logtype, String message){
        //设置图片路径
        System.out.println("标题="+title+",作者="+username+",类型="+logtype+"message="+message);
        logService.upLogImg(file,request,title,username,logtype,message);
        Map<String, String> extrasMap = new HashMap<String, String>();
        jpushService.sendPush("["+username+"]发表一篇新的日志","标题:《"+title+"》",extrasMap);
        return "发表成功!";
    }

    @ResponseBody
    @RequestMapping("/showloglist")
    public String showloglist(Model model,int logid){
        List<Loveimg> loveimgs = logService.showLoveImg(logid);
        String toJson = gson.toJson(loveimgs);
        System.out.println(toJson);

        return toJson;
    }


}
