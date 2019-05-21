package com.fangou.fangouapp.service;

import com.fangou.fangouapp.mapper.LogMapper;
import com.fangou.fangouapp.vo.LogComment;
import com.fangou.fangouapp.vo.LogUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Service
public class LogUserServiceIml implements LogUserService{

    @Resource
    LogMapper logMapper;

    @Autowired
    LogMapperService logMapperService;

    /**
     * 新添加用户,用户存在就验证密码是否正确
     * @param response
     * @param logname
     * @param logpassword
     * @return
     */
    @Override
    public String addUserOrRegister(HttpServletResponse response,String logname, String logpassword) {
        String msg="";
        Cookie userCookie =null;
        LogUser logUsermsg = logMapper.logUserMsg(logname);
        if (logUsermsg==null){
            logMapper.addUser(new LogUser(logname,logpassword,0));
            userCookie=new Cookie("user_name_log",logname);
            userCookie.setMaxAge(60*60*24*90);
            userCookie.setPath("/");
            response.addCookie(userCookie);
            System.out.println("设置cookie成功");
            msg="新用户注册成功!";
        }else{
            if(logpassword.equals(logUsermsg.getlogpassword())){
                msg="登陆成功!";
                userCookie = new Cookie("user_name_log",logname);
                userCookie.setMaxAge(60*60*24*90);
                userCookie.setPath("/");
                response.addCookie(userCookie);
                System.out.println("设置cookie成功");
            }else {
                msg="用户存在,但是密码不正确";
            }
        }
        return msg;
    }

    /**
     * 输入框输入时查询用户食是否存在
     * @param logname
     * @return
     */
    @Override
    public String existUserName(String logname) {
        String msg="用户名不存在";
        LogUser logUser = logMapper.logUserMsg(logname);
        if(logUser!=null){
            msg=logUser.getLogname()+"存在";
            return msg;
        }
        return msg;
    }

    /**
     * 添加日志的评论
     * @param comment
     * @param logname
     * @param logid
     */
    @Override
    public LogComment addLogComment(String comment,String logname,int logid) {
        LogComment logComment = new LogComment(logid, logname, comment, new Date());
        int logCommentid = logMapperService.getLogCommentid(logComment);
        logComment.setId(logCommentid);
        return logComment;
    }

    /**
     * 显示日志的评论
     * @param logid
     * @return
     */
    @Override
    public PageInfo showLogComment(int pageNo,int logid) {
        PageHelper.startPage(pageNo, 6);
        List<LogComment> logComments = logMapper.showLogComment(logid);
        PageInfo<LogComment> pageInfo = new PageInfo<>(logComments);
        return pageInfo;
    }
}
