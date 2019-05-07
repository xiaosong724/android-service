package com.fangou.fangouapp.controller;

import com.fangou.fangouapp.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;


@Controller
public class LogUploadCon {
    @Autowired
    LogService logService;

    @RequestMapping("/lovelogup")
    public ModelAndView uploadLog(HttpServletRequest request, @RequestParam("file")MultipartFile[] file, String title,String username, String logtype, String message,String deleteindex){
        //设置图片路径
        System.out.println("标题="+title+",作者="+username+",类型="+logtype+"message="+message);
        logService.upLogImg(file,request,title,username,logtype,message,deleteindex);
        return new ModelAndView("redirect:/home");
    }


}
