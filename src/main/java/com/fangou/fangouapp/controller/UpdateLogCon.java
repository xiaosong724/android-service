package com.fangou.fangouapp.controller;

import com.fangou.fangouapp.service.JpushService;
import com.fangou.fangouapp.service.LogService;
import com.fangou.fangouapp.vo.Background;
import com.fangou.fangouapp.vo.LoveLog;
import com.fangou.fangouapp.vo.Loveimg;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UpdateLogCon {

    @Autowired
    Gson gson;

    @Autowired
    LogService logService;

    @Autowired
    JpushService jpushService;

    Map<String, String> extrasMap = new HashMap<String, String>();
    @ResponseBody
    @RequestMapping("/querylog")
    public String updatelog(String logid){
        int id = Integer.parseInt(logid);
        LoveLog loveLog = logService.queryIdLog(id);
        List<Loveimg> loveimgs = logService.showLoveImg(id);
        Object obj= new Object[]{loveLog,loveimgs};
        String logstr = gson.toJson(obj);
        return logstr;
    }

    @ResponseBody
    @RequestMapping("/deleteimgid")
    public String deleteimgid(HttpServletRequest requestint,int imgid,String impath,int logid,String title){
        logService.deleteImgId(requestint,imgid,impath,logid,title);
        jpushService.sendPush("修改了ID为:"+logid+"的日志","删除了:["+impath+"]",extrasMap);
        return "删除成功";
    }

    @RequestMapping("/upadteback")
    public String updateback(Model model,@RequestParam(defaultValue  = "1") int page){
        System.out.println(page);
        PageInfo pageInfo = logService.showbackgroundAll(page);
        model.addAttribute("listimg",pageInfo);
        System.out.println(pageInfo);
        return "updatebackground";
    }

    /**
     * 在背景页面修改了欢迎背景图
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/radioupdate")
    public String radioupdate(int id){
        logService.updateBackground("show");
        logService.updateNewBackground(id);
        jpushService.sendPush("修改了新的欢迎背景图","打开繁狗APP看看吧~",extrasMap);
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

    /**
     * 修改日志,或添加新的日志图片
     * @param request
     * @param file
     * @param id
     * @param username
     * @param title
     * @param logtype
     * @param message
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadimgupdate")
    public String uploadimgupdate(HttpServletRequest request,MultipartFile[] file,int id,String username,String title,String logtype,String message){
        System.out.println("本子文件上传的数量:"+file.length);
        System.out.println(username);
        System.out.println(title);
        System.out.println(logtype);
        System.out.println(message);
        String ifuploadimg=",并上传了新的图片!";
        if(file.length<1){
        ifuploadimg="";
        }
        logService.updateLogtext(username, title, logtype, message, id);
        logService.updateLogaddimg(request,file,id);

        jpushService.sendPush("["+username+"]修改了日志,专属ID:"+id,"修改的日志标题:《"+title+"》"+ifuploadimg,extrasMap);
        return "修改成功";
    }

    /**
     * 上传新的欢迎背景,修改或只上传
     * @param request
     * @param myfile
     * @param yesback
     * @param hightback
     * @return
     */
    @ResponseBody
    @RequestMapping("/uploadbackground")
    public String uploadbackground(HttpServletRequest request, @RequestParam("myfile")MultipartFile myfile, String yesback,String hightback){
        String filename= myfile.getOriginalFilename();
        String str="上传成功";
        String backupstr="上传了新的界面图,未更新";
        if(yesback.equals("show")){
            logService.updateBackground(yesback);
            str="设置新的欢迎背景成功";
            backupstr="上传并修改了新的欢迎背景图";
        }
        String yyyyMMddHHmm=new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + "_";
        logService.uploadBackground(myfile,request,new Background(filename,"/welcomeimg/mini/"+yyyyMMddHHmm+filename,"/welcomeimg/"+yyyyMMddHHmm+filename,yesback,hightback),yyyyMMddHHmm);
        System.out.println(myfile.getOriginalFilename());
        System.out.println(yesback);
        jpushService.sendPush(backupstr,"打开繁狗APP看看吧~",extrasMap);
        return str;
    }

}
