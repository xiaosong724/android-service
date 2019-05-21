package com.fangou.fangouapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GameCon {

    @RequestMapping("/showgame")
    public String showcilckimg(Model model){

        return "pk_page";
    }
}
