package com.qianqiu.novel.utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;

public class FileUtil {
    //存放路径
    public static final String path="D:\\Study\\Writerread\\";
    /**
     * 创建文件夹
     * @param file
     */
    public static void File(String file) {
        try
        { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw
            String pathname = path+file;
            // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            if(!filename.exists()){
                filename.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void  write(String files,String content){
        try {
            String pathname = path+files;
            File writename = new File(pathname);
            writename.createNewFile(); // 创建新文件
            BufferedWriter out = new BufferedWriter(new FileWriter(writename));
            out.write(content); // \r\n即为换行
            out.flush(); // 把缓存区内容压入文件
            out.close(); // 最后记得关闭文件
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String  read(String files){
        String str="";
        try {
            String pathname = path+files; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename)); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = "";

            line = br.readLine();

            while (line != null) {
                str+=line+"\n";
                line = br.readLine(); // 一次读入一行数据
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return str;
    }
    public static void upFileName(String oldFileName,String newFileName){
        String pathname = "F:\\books\\"+oldFileName;
        File oldFile = new File(pathname);
        pathname = "F:\\books\\"+newFileName;
        File newFile = new File(pathname);
        oldFile.renameTo(newFile);
    }
    public static String createImage(String name,String pen){
        String fileLocation="";
        UUID uuid=null;
        try{
            int imageWidth = 600;// 图片的宽度
            int imageHeight = 800;// 图片的高度
            BufferedImage image = new BufferedImage(imageWidth, imageHeight,
                    BufferedImage.TYPE_INT_RGB);
            Graphics graphics = image.getGraphics();
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, imageWidth, imageHeight);
            graphics.setColor(Color.black);
            Font f = new Font("宋体", Font.BOLD, 75);
            FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(f);
            int stringWidth = fm.stringWidth(name);
            graphics.setFont(f);
            graphics.drawString(name, 300-(stringWidth/2), 600);

            f = new Font("宋体", Font.BOLD, 20);
            fm = sun.font.FontDesignMetrics.getMetrics(f);
            stringWidth = fm.stringWidth(pen+" 著");
            graphics.setFont(f);
            graphics.drawString(pen+" 著", 300-(stringWidth/2), 650);

            f = new Font("宋体", Font.BOLD, 30);
            fm = sun.font.FontDesignMetrics.getMetrics(f);
            stringWidth = fm.stringWidth("千秋小说网");
            graphics.setFont(f);
            graphics.drawString("千秋小说网", 300-(stringWidth/2), 750);

            graphics.setFont(new Font("宋体", Font.BOLD, 20));

            uuid = UUID.randomUUID();
            fileLocation="D:\\Study\\S3\\IDEA_idea\\novel\\src\\main\\resources\\static\\img\\"+uuid+".png";
            FileOutputStream fos = new FileOutputStream(fileLocation);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
            encoder.encode(image);
            bos.close();
            fos.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return uuid+".png";
    }
    public static void delPaper(String oldName){
        File file = new File(oldName);
        System.out.println(file);
        file.delete();
    }
    public  void upPaperName(String oldName,String newName){
        System.out.println(oldName+"\n"+newName);
        System.out.println(222);
        System.out.println(oldName+"\n"+newName);
        File f=new File("f:\\\\books\\\\"+oldName+".txt");
        String c=f.getParent();
        System.out.println(c);
        File mm=new File("f:\\\\books\\\\"+newName+".txt");
        if(f.renameTo(mm))
        {
            System.out.println("修改成功!");
        }
        else
        {
            System.out.println("修改失败");
        }
    }
}
