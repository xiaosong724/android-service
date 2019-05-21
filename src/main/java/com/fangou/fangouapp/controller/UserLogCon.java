package com.fangou.fangouapp.controller;

import com.fangou.fangouapp.service.JpushService;
import com.fangou.fangouapp.service.LogUserService;
import com.fangou.fangouapp.vo.LogComment;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserLogCon {

    @Autowired
    LogUserService logUserService;
    @Autowired
    Gson gson;

    @Autowired
    JpushService jpushService;
    @ResponseBody
    @RequestMapping("/loginandregister")
    public String loginAndRegister(HttpServletResponse response,String logname, String logpassword){
        String msg = logUserService.addUserOrRegister(response, logname, logpassword);
        return msg;
    }
    @ResponseBody
    @RequestMapping("/existlogname")
    public String existLogName(String logname){
        System.out.println(logname);
        String msg = logUserService.existUserName(logname);
        System.out.println(msg);
        return msg;
    }

    @ResponseBody
    @RequestMapping("/addcommert")
    public String addCommert(String comment,String logname,int logid,String title){
        LogComment logComment = logUserService.addLogComment(comment, logname, logid);
        System.out.println(title);
        String json = gson.toJson(logComment);
        Map<String, String> extrasMap = new HashMap<String, String>();
        jpushService.sendPush("有人评论了日志,ID:"+logid+"_标题:《"+title+"》",logname+":"+comment,extrasMap);
        return json;
    }

    @ResponseBody
    @RequestMapping("/showcomment")
    public String showComment(@RequestParam(defaultValue = "1")String page, int logid){
        int pageNo = Integer.parseInt(page);
        PageInfo pageInfo = logUserService.showLogComment(pageNo, logid);
        int prePage = pageInfo.getPrePage();
        int nextPage = pageInfo.getNextPage();
        List<LogComment> list = pageInfo.getList();
        String cmtlist = gson.toJson(list);
        String[] str={nextPage+"",prePage+"",cmtlist};
        String json = gson.toJson(str);
        System.out.println(pageInfo);
        return json;
    }

}
