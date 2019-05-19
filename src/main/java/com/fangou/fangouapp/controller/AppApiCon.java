package com.fangou.fangouapp.controller;

import com.fangou.fangouapp.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppApiCon {
    @Autowired
    AppService appService;

    @RequestMapping("/apilogdelimg")
    public String apilogdelimg(){
        String json = appService.showLogimg();
        return json;
    }
}
