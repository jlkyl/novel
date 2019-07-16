package com.qianqiu.novel.utils;

import org.json.JSONObject;

import java.io.*;
import java.util.Map;


public class AudioUtil {
    /**
     * 审核文本内容
     * @param str
     * @return
     */
    public static int audioTxt(String str){
        JSONObject res = Sample.getAip().antiSpam(str, null);
        String result = res.get("result").toString();
        Map<String,Object> maps = (Map)GsonUtil.GsonToMaps(result);
        System.out.println(maps.get("spam"));
        return Integer.parseInt(new java.text.DecimalFormat("0").format(maps.get("spam")));
    }

    /**
     * 审核图像内容
     * @param image
     * @return
     */
    public static boolean audioImg(byte[] image){
        // 参数为本地图片文件二进制数组
        String resp = Sample.getAip().imageCensorUserDefined(image, null).toString();
        System.out.println(resp);
        //截取conclusionType的值
        String result = resp.substring(resp.length() - 2,resp.length()-1);
        if (result.equals("1")) {
            return true;
        }else  {
            return false;
        }
    }
    public static byte[] toByteArray(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(audioImg(toByteArray("D:\\picture\\壁纸\\demo-card-1.jpg")));
    }
}