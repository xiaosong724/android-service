package com.fangou.fangouapp.mapper;

import com.fangou.fangouapp.vo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LogMapper {
    /**
     * 显示所有日志
     * @return
     */
    @Select("SELECT * FROM lovelogtab ORDER BY nowtime DESC")
    List<LoveLog> showLoveLog();

    /**
     * 查询某个日志的图片
     * @param logid
     * @return
     */
    @Select("SELECT * FROM logimgtab WHERE logid=#{logid}")
    List<Loveimg> showLoveImg(int logid);

    /**
     * 查询单个日志
     * @param id 日志id
     * @return
     */
    @Select("SELECT * FROM lovelogtab WHERE id=#{id}")
    LoveLog queryIdLog(int id);

    /**
     *  查询当前是背景的图片信息(取决于yesback是否是show)
     * @param yesback
     * @return
     */
    @Select("SELECT * FROM backgroundtab WHERE yesback=#{yesback}")
    Background showbackground(String yesback);

    /**
     * 显示所有背景图片
     * @return
     */
    @Select("SELECT * FROM backgroundtab ORDER BY id DESC")
    List<Background>showbackgroundAll();

    /**
     * 显示所有的日志图片
     * @return
     */
    @Select("SELECT l.*,lg.title FROM logimgtab l,lovelogtab lg WHERE l.logid=lg.id")
    List<Logimg>showLogimg();

    /**
     * 每删除一次查询本日志是否还有图片
     * @param logid
     * @return
     */
    @Select("SELECT l.imgpath,MIN(imgid) FROM logimgtab l WHERE logid=#{logid}")
    ImgidAndImgpath queryIdAndpath(int logid);

    /**
     * 查询用户信息
     * @param logname
     * @return
     */
    @Select("SELECT * FROM loguser WHERE logname=#{logname}")
    LogUser logUserMsg(String logname);

    /**
     * 显示当前日志的评论
     * @return
     */
    @Select("SELECT * FROM logcmttab WHERE logid=#{logid} ORDER BY texttime DESC")
    List<LogComment> showLogComment(int logid);

    /**
     * 写入一篇日志
     * @param loveLog
     */
    @Insert("INSERT INTO lovelogtab (username,title,coversrc,nowtime,logtype,message,viewcount) VALUES(#{username},#{title},#{coversrc},#{nowtime},#{logtype},#{message},#{viewcount})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    int lovelogup(LoveLog loveLog);

    /**
     * 新添加用户
     */
    @Insert("INSERT INTO loguser (logname,logpassword,logcount) VALUES(#{logname},#{logpassword},#{logcount})")
    void addUser(LogUser logUser);

    /**
     * 给日志添加评论
     * @param logComment
     */
    @Insert("INSERT INTO logcmttab (logid,logname,comment,texttime) VALUES(#{logid},#{logname},#{comment},#{texttime})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addLogComment(LogComment logComment);

    /**
     * 写入一篇日志时插入图片list
     * @param loveimg
     */
    @Insert("INSERT INTO logimgtab (logid,imgpath,filetype) VALUES(#{logid},#{imgpath},#{filetype})")
    void loveimgup(Loveimg loveimg);

    /**
     * 添加一张欢迎界面图片
     * @param background
     */
    @Insert("insert into backgroundtab (backname,smbackpath,backpath,yesback,hightback) values(#{backname},#{smbackpath},#{backpath},#{yesback},#{hightback})")
    void uploadBackground(Background background);

    /**
     * 修改现在yesback属性为show的图片,已有新的图片取代
     * @param yesback
     */
    @Update("UPDATE backgroundtab SET yesback = 'hide' WHERE yesback = #{yesback}")
    void updateBackground(String yesback);

    /**
     * 设置新的背景图,在此之前执行了updateBackground(),所有不显示,后设置新的显示
     * @param id
     */
    @Update("UPDATE backgroundtab SET yesback = 'show' WHERE id = #{id}")
    void updateNewBackground(int id);

    /**
     * 添加点击日志的浏览次数
     * @param id
     */
    @Update("UPDATE lovelogtab SET viewcount = viewcount+1 WHERE id = #{id}")
    void updateViewTimeAdd(int id);

    /**
     * 修改上传背景图ID它高是否拉伸
     * @param id
     * @param hightback
     */
    @Update("UPDATE backgroundtab SET hightback = '${hightback}' WHERE id = #{id}")
    void updateNewBackgroundHight(@Param("hightback")String hightback,@Param("id")int id);

    /**
     * 每次日志中的图片删除,更新封面没有设为空
     * @param coversrc
     * @param id
     */
    @Update("UPDATE lovelogtab SET coversrc = '${coversrc}' WHERE id = #{id}")
    void updateLogimg(@Param("coversrc")String coversrc,@Param("id")int id);

    /**
     *  更新当前日志文本信息
     * @param username
     * @param title
     * @param logtype
     * @param message
     * @param id
     */
    @Update("UPDATE lovelogtab SET username = '${username}',title = '${title}',logtype = '${logtype}',message = '${message}' WHERE id = #{id}")
    void updateLogtext(@Param("username")String username,@Param("title")String title,@Param("logtype")String logtype,@Param("message")String message,@Param("id")int id);

    @Delete("DELETE FROM logimgtab WHERE imgid=#{imgid}")
    void deleteImgId(int imgid);
}
