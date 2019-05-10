package com.fangou.fangouapp.controller;

import com.fangou.fangouapp.service.LogService;
import com.fangou.fangouapp.vo.Background;
import com.fangou.fangouapp.vo.LoveLog;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class UpdateLogCon {

    @Autowired
    Gson gson;
    @Autowired
    LogService logService;

    @ResponseBody
    @RequestMapping("/querylog")
    public String updatelog(String logid){
        int id = Integer.parseInt(logid);
        LoveLog loveLog = logService.queryIdLog(id);
        String logstr = gson.toJson(loveLog);
        return logstr;
    }

    @RequestMapping("/upadteback")
    public String updateback(Model model,@RequestParam(defaultValue  = "1") int page){
        System.out.println(page);
        PageInfo pageInfo = logService.showbackgroundAll(page);
        model.addAttribute("listimg",pageInfo);
        System.out.println(pageInfo);
        return "updatebackground";
    }

    @ResponseBody
    @RequestMapping("/radioupdate")
    public String radioupdate(int id){
        logService.updateBackground("show");
        logService.updateNewBackground(id);
        return "更新成功";
    }

    @ResponseBody
    @RequestMapping("/checkedupdate")
    public String checkedupdate(int id,String hightback){
        System.out.println("id="+id);
        System.out.println("hightback="+hightback);
        if(hightback.equals("100")){
            hightback+="%";
        }
        logService.updateNewBackgroundHight(id,hightback);
        return "更新背景高度属性";
    }


    @ResponseBody
    @RequestMapping("/uploadbackground")
    public String uploadbackground(HttpServletRequest request, @RequestParam("myfile")MultipartFile myfile, String yesback,String hightback){
        String filename= myfile.getOriginalFilename();
        String str="上传成功";

        if(yesback.equals("show")){
            logService.updateBackground(yesback);
            str="设置新的欢迎背景成功";
        }
        String yyyyMMddHHmm=new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + "_";
        logService.uploadBackground(myfile,request,new Background(filename,"/welcomeimg/mini/"+yyyyMMddHHmm+filename,"/welcomeimg/"+yyyyMMddHHmm+filename,yesback,hightback),yyyyMMddHHmm);
        System.out.println(myfile.getOriginalFilename());
        System.out.println(yesback);
        return str;
    }

}
