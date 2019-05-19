package com.fangou.fangouapp.service;

import com.fangou.fangouapp.mapper.LogMapper;
import com.fangou.fangouapp.vo.LoveLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class LogMapperService {
    @Resource
    private LogMapper logMapper;
    public int lovelogup(LoveLog loveLog){
        logMapper.lovelogup(loveLog);
        return loveLog.getId();
    }

}
