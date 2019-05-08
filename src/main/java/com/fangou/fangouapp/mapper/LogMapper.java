package com.fangou.fangouapp.mapper;

import com.fangou.fangouapp.vo.LoveLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LogMapper {
    @Select("SELECT * FROM lovelogtab ORDER BY nowtime DESC")
    List<LoveLog> showLoveLog();

    @Insert("insert into lovelogtab (username,title,coversrc,nowtime,logtype,message,imgsrc) values(#{username},#{title},#{coversrc},#{nowtime},#{logtype},#{message},#{imgsrc})")
    void lovelogup(LoveLog loveLog);

    @Select("SELECT * FROM lovelogtab WHERE id=#{id}")
    LoveLog queryIdLog(int id);
}
