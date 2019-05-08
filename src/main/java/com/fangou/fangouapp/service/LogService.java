package com.fangou.fangouapp.service;

import com.fangou.fangouapp.vo.LoveLog;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@Service
public interface LogService {
    PageInfo showLoveLog(int pageNo);

    void upLogImg(MultipartFile[] files,HttpServletRequest request,String title,String username, String logtype, String message,String deleteindex);

    LoveLog queryIdLog(int id);

}
