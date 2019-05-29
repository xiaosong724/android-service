package com.fangou.fangouapp.controller;

import com.fangou.fangouapp.mapper.GameMapper;
import com.fangou.fangouapp.service.GameService;
import com.fangou.fangouapp.vo.Pet;
import com.fangou.fangouapp.vo.Pkmsg;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GameCon {
    @Autowired
    GameService gameService;
    @Resource
    GameMapper gameMapper;
    @Autowired
    Gson gson;
    @RequestMapping("/showgame")
    public String showcilckimg(HttpServletRequest request,Model model){
        String mname="";
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("user_name_log")){
                mname=cookie.getValue();
            }
        }
        List<Pet> pets = gameMapper.queryPet();
        Pet petme = null;
        Pet petto = null;
        for (int i = 0; i < pets.size(); i++) {
            if (pets.get(i).getMname().equals(mname)) {
                petme = pets.get(i);
            }else {
                petto = pets.get(i);
            }

        }
        model.addAttribute("petme",petme);
        model.addAttribute("petto",petto);
        return "pk_page";
    }
    @ResponseBody
    @RequestMapping("/pkgame")
    public String pkGame(String mname){

        List<Pkmsg> list = gameService.gamgPk(mname);
        String json = gson.toJson(list);
        return json;
    }
}
