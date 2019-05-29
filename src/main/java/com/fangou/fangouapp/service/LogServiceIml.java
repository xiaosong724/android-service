package com.fangou.fangouapp.service;

import com.fangou.fangouapp.mapper.LogMapper;
import com.fangou.fangouapp.util.ToImgUtil;
import com.fangou.fangouapp.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class LogServiceIml implements LogService {
    private static final Logger LOG = LoggerFactory.getLogger(JpushService.class);
    @Resource
    LogMapper logMapper;

    @Autowired
    LogMapperService logMapperService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    Gson gson;

    @Override
    public PageInfo showLoveLog(int pageNo) {
        PageHelper.startPage(pageNo, 4);
        List<LoveLog> loveLogs = logMapper.showLoveLog();
        PageInfo<LoveLog> pageInfo = new PageInfo<>(loveLogs);
        return pageInfo;
    }

    @Override
    public void upLogImg(MultipartFile[] file, HttpServletRequest request, String title, String username, String logtype, String message) {
        String coversrc = "";//封面图
        String imgsrc = "";//图片列表
        String pa1 = "";
        String filepath = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + "_";
        String pathnew = request.getServletContext().getRealPath("/img/mini/");
        File filenew = new File(pathnew);
        if (!filenew.exists()) {
            filenew.mkdirs();
        }
        if (file.length > 0) {
            coversrc = filepath + file[0].getOriginalFilename();
            pa1 = "/img/mini/";
        }
        int logid = logMapperService.lovelogup(new LoveLog(username, title, pa1 + coversrc, new Date(), logtype, message, 0));

        for (int i = 0; i < file.length; i++) {

            if (file[i].isEmpty()) {
                System.out.println("上传文件不可为空");
                continue;
            }
            // 获取文件名

            String fileName = file[i].getOriginalFilename();
            fileName = filepath + fileName;
            String path = request.getServletContext().getRealPath("/img/") + fileName;
            String path2 = request.getServletContext().getRealPath("/img/mini/") + fileName;

            imgsrc = filepath + file[i].getOriginalFilename();
            System.out.print("保存文件绝对路径" + path + "\n");
            System.out.println("浓缩图文件绝对路径" + path2 + "\n");
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
                if(file[i].getContentType().indexOf("image/")==0){
                    ToImgUtil.toSmaillImg(path, path2);
                    logMapper.loveimgup(new Loveimg(logid, imgsrc,"img"));
                }else {
                    logMapper.loveimgup(new Loveimg(logid, imgsrc,"video"));

                }
                System.out.println("上传成功");
            } catch (IOException e) {
                System.out.println("上次失败");
            } catch (Exception e) {

            }
        }

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
        PageHelper.startPage(num, 4);
        List<Background> backgrounds = logMapper.showbackgroundAll();
        PageInfo<Background> pageInfo = new PageInfo<>(backgrounds);
        return pageInfo;
    }

    @Override
    public void uploadBackground(MultipartFile files, HttpServletRequest request, Background background, String yyyyMMddHHmm) {
        String originalFilename = files.getOriginalFilename();
        String path = request.getServletContext().getRealPath("/welcomeimg/") + yyyyMMddHHmm + originalFilename;
        String path2 = request.getServletContext().getRealPath("/welcomeimg/mini/") + yyyyMMddHHmm + originalFilename;
        try {
            ToImgUtil.toSmaillImgback(files, path, 2400);
            ToImgUtil.toSmaillImgback(files, path2, 600);
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
        logMapper.updateNewBackgroundHight(hightback, id);
    }

    /**
     * ajax显示当前点击的日志图片并且当前日志浏览次数加1
     * @param logid
     * @return
     */
    @Override
    public List<Loveimg> showLoveImg(int logid) {
        List<Loveimg> loveimgs = logMapper.showLoveImg(logid);
        logMapper.updateViewTimeAdd(logid);
        return loveimgs;
    }

    @Override
    public void deleteImgId(HttpServletRequest request, int imgid, String impath, int logid,String title) {
        logMapper.deleteImgId(imgid);
        String newlogimg = "";

        DateFormat bf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();//创建时间
        String format = bf.format(new Date(date.getTime() + (long)90* 24 * 60 * 60 * 1000));//格式化 bf.format(date);
        Logimg logimg = new Logimg(imgid, logid, impath, title,format);
        String valredis = gson.toJson(logimg);
        redisTemplate.opsForValue().set("del_"+imgid,valredis,90* 24 * 60 * 60,TimeUnit.SECONDS);
        logimg.setImgpath(request.getServletContext().getRealPath("/img/mini/")+impath);
        String valredisdel = gson.toJson(logimg);
        redisTemplate.opsForValue().set("path_del_"+imgid,valredisdel);

        System.out.println("删除id:"+imgid+",添加到redis 中");
        ImgidAndImgpath imgidAndImgpath = logMapper.queryIdAndpath(logid);
        if (imgidAndImgpath!= null) {
            newlogimg = imgidAndImgpath.getImgpath();
            logMapper.updateLogimg("/img/mini/" + newlogimg, logid);
            System.out.println("删除日志图片操作:更新了日志封面");
            return;
        }
        logMapper.updateLogimg(newlogimg, logid);
        System.out.println("删除日志图片操作:没有图片了日志封面设置空");



    }

    @Override
    public void updateLogtext(String username, String title, String logtype, String message, int id) {

        logMapper.updateLogtext(username, title, logtype, message, id);
    }

    @Override
    public void updateLogaddimg(HttpServletRequest request, MultipartFile[] file, int logid) {
        String imgsrc = "";//图片列表
        String filepath = new SimpleDateFormat("yyyyMMddHHmm").format(new Date()) + "_";
        String pathnew = request.getServletContext().getRealPath("/img/mini/");
        File filenew = new File(pathnew);
        if (!filenew.exists()) {
            filenew.mkdirs();
        }
        for (int i = 0; i < file.length; i++) {
            System.out.println( file[i].getContentType());
            if (file[i].isEmpty()) {
                System.out.println("上传文件不可为空");
                continue;
            }
            // 获取文件名

            String fileName = file[i].getOriginalFilename();
            fileName = filepath + fileName;
            String path = request.getServletContext().getRealPath("/img/") + fileName;
            String path2 = request.getServletContext().getRealPath("/img/mini/") + fileName;

            imgsrc = filepath + file[i].getOriginalFilename();
            System.out.print("新加日志图文件绝对路径" + path + "\n");
            System.out.println("新加日志浓缩图文件绝对路径" + path2 + "\n");
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
                System.out.println("上传成功");
                if(file[i].getContentType().indexOf("image/")==0){
                    ToImgUtil.toSmaillImg(path, path2);
                    logMapper.loveimgup(new Loveimg(logid, imgsrc,"img"));
                }else {
                    logMapper.loveimgup(new Loveimg(logid, imgsrc,"video"));
                }
                logMapper.updateLogimg("/img/mini/" + fileName, logid);
            } catch (IOException e) {
                System.out.println("上次失败");
            } catch (Exception e) {

            }
        }

    }

    @Override
    public List<Logimg> showLogimg(HashSet<Logimg> logimgList) {
        List<Logimg> logimgs = logMapper.showLogimg();

        System.out.println(logimgList.size());
        if (logimgs.size() > 10) {
            while (true) {
                int img = (int) (Math.random() * logimgs.size());
                logimgList.add(logimgs.get(img));
                if (logimgList.size() == 10) {
                    break;
                }
            }
            System.out.println(logimgList.size());
            List<Logimg> logimgList1= new ArrayList<Logimg>(logimgList);
            return logimgList1;
        }

        //System.out.println(logimgList);
        return logimgs;
    }
}
