package com.fangou.fangouapp.controller;

import com.fangou.fangouapp.service.LogService;
import com.fangou.fangouapp.vo.LoveLog;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
