package com.fangou.fangouapp.mapper;

import com.fangou.fangouapp.vo.Background;
import com.fangou.fangouapp.vo.LoveLog;
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
     * 写入一篇日志
     * @param loveLog
     */
    @Insert("insert into lovelogtab (username,title,coversrc,nowtime,logtype,message,imgsrc) values(#{username},#{title},#{coversrc},#{nowtime},#{logtype},#{message},#{imgsrc})")
    void lovelogup(LoveLog loveLog);

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
     * 修改ID它高是否拉伸
     * @param id
     * @param hightback
     */
    @Update("UPDATE backgroundtab SET hightback = '${hightback}' WHERE id = #{id}")
    void updateNewBackgroundHight(@Param("hightback")String hightback,@Param("id")int id);


}
