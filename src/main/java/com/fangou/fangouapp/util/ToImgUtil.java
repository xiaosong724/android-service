package com.fangou.fangouapp.util;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.io.FileOutputStream;
public class ToImgUtil {
    public static void main(String[] args) throws Exception {
      //  toSmaillImg("D:/img/img-734d5ad3f65e7880e6ea609d5d754c11.gif","D:/img/imgbig/img-734d5ad3f65e7880e6ea609d5d754c11.gif");
    }
    public static void toSmaillImg(String filePath,String thumbPath) throws Exception{
        String newurl =thumbPath;
        java.awt.Image bigJpg = javax.imageio.ImageIO.read(new java.io.File(filePath));
        float tagsize = 600;
        int old_w = bigJpg.getWidth(null);
        int old_h = bigJpg.getHeight(null);
        int new_w = 0;
        int new_h = 0;
        float tempdouble;
        tempdouble = old_w > old_h ? old_w/tagsize : old_h/tagsize;
        new_w = Math.round(old_w/tempdouble);
        new_h = Math.round(old_h/tempdouble);
        java.awt.image.BufferedImage tag = new java.awt.image.BufferedImage(new_w,new_h,java.awt.image.BufferedImage.TYPE_INT_RGB);
        tag.getGraphics().drawImage(bigJpg,0,0,new_w,new_h,null);
        FileOutputStream newimage = new FileOutputStream(newurl);
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
        encoder.encode(tag);
        newimage.close();
    }
}