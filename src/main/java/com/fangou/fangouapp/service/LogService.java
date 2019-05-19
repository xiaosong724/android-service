package com.fangou.fangouapp.service;

import com.fangou.fangouapp.vo.Background;
import com.fangou.fangouapp.vo.Logimg;
import com.fangou.fangouapp.vo.LoveLog;
import com.fangou.fangouapp.vo.Loveimg;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;


@Service
public interface LogService {
    PageInfo showLoveLog(int pageNo);

    void upLogImg(MultipartFile[] files,HttpServletRequest request,String title,String username, String logtype, String message);

    LoveLog queryIdLog(int id);

    Background showbackground(String yesback);

    PageInfo showbackgroundAll(int pageNo);

    void uploadBackground(MultipartFile files,HttpServletRequest request,Background background,String yyyyMMddHHmm);

    void updateBackground(String yesback);

    void updateNewBackground(int id);

    void updateNewBackgroundHight(int id,String hightback);

    List<Loveimg> showLoveImg(int logid);

    void deleteImgId(HttpServletRequest request,int imgid,String impath,int logid,String title);

    void updateLogtext(String username,String title,String logtype,String message,int id);

    void updateLogaddimg(HttpServletRequest request,MultipartFile[] file,int logid);

    List<Logimg> showLogimg(HashSet<Logimg> logimgList);
}
