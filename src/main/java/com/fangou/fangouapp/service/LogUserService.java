package com.fangou.fangouapp.service;

import com.fangou.fangouapp.vo.LogComment;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;

@Service
public interface LogUserService {

    /**
     * 新添加用户,用户存在就验证密码是否正确
     * @param response
     * @param logname
     * @param logpassword
     * @return
     */
    String addUserOrRegister(HttpServletResponse response,String logname, String logpassword);

    /**
     * 输入框输入时查询用户食是否存在
     * @param logname
     * @return
     */
    String existUserName(String logname);

    /**
     * 添加日志评论
     * @param comment
     * @param logname
     * @param logid
     */
    LogComment addLogComment(String comment, String logname, int logid);

    /**
     * 显示日志的评论
     * @param logid
     * @return
     */
    PageInfo showLogComment(int pageNo,int logid);
}
