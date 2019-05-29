package com.fangou.fangouapp.service;

import com.fangou.fangouapp.vo.Logimg;
import org.springframework.stereotype.Service;


@Service
public interface AppService {

    String showLogimg();
    void recoverLogimg(Logimg logimg);

}
