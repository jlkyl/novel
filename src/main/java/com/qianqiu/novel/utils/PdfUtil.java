package com.qianqiu.novel.utils;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class PdfUtil {
    private static Font headfont;// 设置书名字体大小
    private static Font rollfont;// 设置卷标题字体大小
    private static Font keyfont;// 设置章节名字体大小
    private static Font textfont;// 设置章节内容字体大小

    static {
        BaseFont bfChinese;
        try {
            bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            headfont = new Font(bfChinese, 28, Font.BOLD);
            rollfont = new Font(bfChinese, 14, Font.BOLD);
            keyfont = new Font(bfChinese, 12, Font.BOLD);
            textfont = new Font(bfChinese, 12, Font.NORMAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 将书籍内容写到指定的位置为pdf
     *
     * @param book
     *            数据源
     * @throws Exception
     */
    public static void writePdf(Map<String,Object> book, HttpServletResponse response) throws Exception {
        String url = FileUtil.path+"download"+File.separator+book.get("bookname").toString()+".pdf";
        // 1.新建document对象
        // 第一个参数是页面大小。接下来的参数分别是左、右、上和下页边距。
        Document document = new Document(PageSize.A4);
        // 2.建立一个书写器(Writer)与document对象关联，通过书写器(Writer)可以将文档写入到磁盘中。
        // 创建 PdfWriter 对象 第一个参数是对文档对象的引用，第二个参数是文件的实际名称，在该名称中还会给出其输出路径。
        File pdf = new File(url);
        pdf.createNewFile();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(url));
        // 3.打开文档
        document.open();
        // 4.向文档中添加内容
        // 通过 Paragraph 来添加文本。可以用文本及其默认的字体、颜色、大小等等设置来创建一个默认段落
        Paragraph header = new Paragraph("更多内容  https://www.qidian.com",textfont);
        header.setAlignment(1);
        document.add(header);
        document.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n\n\n"));
        //添加图片
        String cover = book.get("cover").toString();
        Image image = null;
        if(cover.indexOf("http")!=-1){
            image = Image.getInstance(new URL(cover));
        }else{
            image = Image.getInstance(cover.replace("/img/",FileUtil.imgpath));
        }
        image.setAbsolutePosition(205f, 420f);
        image.scaleAbsolute(180, 240);
        document.add(image);
        document.add(new Paragraph("\n\n\n\n\n\n\n\n\n\n\n"));
        header = new Paragraph("《"+book.get("bookname").toString()+"》",headfont);
        header.setAlignment(1);
        document.add(header);
        document.add(new Paragraph("\n\n\n\n\n\n"));
        header = new Paragraph(book.get("pen").toString()+" 著",rollfont);
        header.setAlignment(1);
        document.add(header);
        document.add(new Paragraph("\n"));
        header = new Paragraph("千秋小说网",rollfont);
        header.setAlignment(1);
        document.add(header);
        //写入章节内容
        int chNum = 1;
        for(Map<String,Object> rolls : (List<Map<String,Object>>)book.get("rolls")){
            Chapter chapter = new Chapter(new Paragraph(rolls.get("rollname").toString(), rollfont),
                    chNum++);
            for(Map<String,Object> chapters : (List<Map<String,Object>>)rolls.get("chapters")){
                Section section = chapter.addSection(new Paragraph(chapters.get("chaptername").toString(), keyfont));
                section.setIndentation(10);
                section.setIndentationLeft(10);
                section.setBookmarkOpen(false);
                section.setNumberStyle(Section.NUMBERSTYLE_DOTTED_WITHOUT_FINAL_DOT);
                section.add(new Paragraph(FileUtil.read(chapters.get("url").toString()), textfont));
            }
            LineSeparator line = new LineSeparator(1, 100, new BaseColor(204, 204,
                    204), Element.ALIGN_CENTER, -2);
            Paragraph p_line = new Paragraph("分割线");
            p_line.add(line);
            chapter.add(p_line);
            document.add(chapter);
        }
        // 5.关闭文档
        document.close();

        String fileName = book.get("bookname").toString()+".pdf";
        OutputStream outputStream = null;
        FileInputStream in = null;
        try {
            // 防止中文乱码
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            outputStream = new BufferedOutputStream(response.getOutputStream());
            in = new FileInputStream(url);
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                outputStream.write(bufferOut, 0, bytes);
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
            response.reset();
            try {
                OutputStreamWriter writerr = new OutputStreamWriter(response.getOutputStream(), "UTF-8");
                String data = "<script language='javascript'>alert(\"\\u64cd\\u4f5c\\u5f02\\u5e38\\uff01\");</script>";
                writerr.write(data);
                writerr.close();
            }catch (IOException ie){
                e.printStackTrace();
            }
        }finally {
            try {
                if (null != in) {
                    in.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
