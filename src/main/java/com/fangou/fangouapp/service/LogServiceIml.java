package com.fangou.fangouapp.service;

import com.fangou.fangouapp.mapper.LogMapper;
import com.fangou.fangouapp.util.ToImgUtil;
import com.fangou.fangouapp.vo.Background;
import com.fangou.fangouapp.vo.LoveLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@Service
public class LogServiceIml implements LogService {
    @Resource
    LogMapper logMapper;
    @Override
    public PageInfo showLoveLog(int pageNo) {
        PageHelper.startPage(pageNo,4);
        List<LoveLog> loveLogs = logMapper.showLoveLog();
        PageInfo<LoveLog> pageInfo = new PageInfo<>(loveLogs);
        return pageInfo;
    }

    @Override
    public void upLogImg(MultipartFile[] file, HttpServletRequest request, String title, String username, String logtype, String message,String deleteindex) {
        String coversrc="";//封面图
        String imgsrc="";//图片列表
        int[] ints=null;
        System.out.println(deleteindex);
        System.out.println(file.length);
        if(!deleteindex.equals("")){
            String[] str = deleteindex.split(",");
            ints = new int[str.length];
            for (int i = 0; i<str.length ; i++) {
                ints[i]=Integer.parseInt(str[i]);
            }
        }
        for (int i = 0; i<file.length ; i++){
            int num=0;
            if(ints!=null){
                for (int j = 0; j <ints.length ; j++) {
                    if(ints[j]==i){
                        num=1;
                    }
                }
                if(num==1){
                    continue;
                }
            }
            if (file[i].isEmpty()) {
                System.out.println("上传文件不可为空");
                continue;
            }
            // 获取文件名
            String filepath = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + "_";
            String fileName = file[i].getOriginalFilename();
            fileName = filepath + fileName;
            String path = request.getServletContext().getRealPath("/img/")+fileName;
            String path2 = request.getServletContext().getRealPath("/img/mini/")+fileName;
            //每次循环时间会变加判断
            if(i==0){
                coversrc= filepath+file[0].getOriginalFilename();
            }
            imgsrc+="<a class=\"song_a\" href=\""+"/img/"+filepath+file[i].getOriginalFilename()+"\"><img class=\"songsize\" src=\""+"/img/mini/"+filepath+file[i].getOriginalFilename()+"\"></a>";
            System.out.print("保存文件绝对路径"+path+"\n");
            System.out.println("浓缩图文件绝对路径"+path2+"\n");
            //创建文件路径
            File dest = new File(path);
            //判断文件是否已经存在
            if (dest.exists()) {
                System.out.println("文件已经存在");
            }
            //判断文件父目录是否存在
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdir();
            }
            try {
                //上传文件
                file[i].transferTo(dest); //保存文件
                ToImgUtil.toSmaillImg(path,path2);
                System.out.println("视频上传成功");
            } catch (IOException e) {
                System.out.println("上次失败");
            } catch (Exception e) {

            }
        }
        String pa1="/img/mini/";
        if(coversrc.equals("")&&imgsrc.equals("")){
            pa1="";
        }
        logMapper.lovelogup(new LoveLog(username,title,pa1+coversrc,new Date(),logtype,message,imgsrc));
    }

    @Override
    public LoveLog queryIdLog(int id) {
        LoveLog loveLog = logMapper.queryIdLog(id);
        return loveLog;
    }

    @Override
    public Background showbackground(String yesback) {
        Background showbackground = logMapper.showbackground(yesback);
        return showbackground;
    }

    @Override
    public PageInfo showbackgroundAll(int num) {
        PageHelper.startPage(num,4);
        List<Background> backgrounds = logMapper.showbackgroundAll();
        PageInfo<Background> pageInfo = new PageInfo<>(backgrounds);
        return pageInfo;
    }

    @Override
    public void uploadBackground(MultipartFile files,HttpServletRequest request,Background background,String yyyyMMddHHmm) {
        String originalFilename = files.getOriginalFilename();
        String path=request.getServletContext().getRealPath("/welcomeimg/")+yyyyMMddHHmm+originalFilename;
        String path2=request.getServletContext().getRealPath("/welcomeimg/mini/")+yyyyMMddHHmm+originalFilename;
        try {
            ToImgUtil.toSmaillImgback(files,path,2400);
            ToImgUtil.toSmaillImgback(files,path2,600);
            logMapper.uploadBackground(background);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBackground(String yesback) {
        logMapper.updateBackground(yesback);
    }

    @Override
    public void updateNewBackground(int id) {
        logMapper.updateNewBackground(id);
    }

    @Override
    public void updateNewBackgroundHight(int id, String hightback) {
        logMapper.updateNewBackgroundHight(hightback,id);
    }
}
