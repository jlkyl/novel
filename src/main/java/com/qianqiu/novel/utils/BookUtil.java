package com.qianqiu.novel.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class BookUtil {
    public static void main(String[] args) throws Exception {
        // 获取http客户端
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            //获取分类信息
            String url = "https://novel.juhe.im/sub-categories";
            String typejson = getUrl(client,url);
            Map<String,Object> typemap = GsonUtil.GsonToMaps(typejson.toString());
            if((boolean)typemap.get("ok")){
                List<Map<String,Object>> list = (List<Map<String,Object>>)typemap.get("male");
                for(Map<String,Object> map : list){
                    List<String> ss = (List)map.get("mins");
                    //根据分类获取对应书籍信息
                    if(ss.size()>0){
                        for(String s : ss){
                            url = "http://novel.juhe.im/category-info?gender=male&type=hot&major="+map.get("major")+"&minor="+s+"&start=0&limit=10";
                            getBooks(client,url);
                        }
                    }else{
                        url = "http://novel.juhe.im/category-info?gender=male&type=hot&major="+map.get("major")+"&minor=&start=0&limit=10";
                        getBooks(client,url);
                    }
                }
            }
        } finally {
            client.close();
        }
    }

    public static void getBooks(CloseableHttpClient client,String url) throws UnsupportedEncodingException {
        String bookjson = getUrl(client,url);
        System.out.println(bookjson);
        Map<String,Object> bookmap = GsonUtil.GsonToMaps(bookjson);
        if((Double)bookmap.get("total")>0){
            List<Map<String,Object>> booklist = (List<Map<String,Object>>)bookmap.get("books");
            if(booklist.size()>0) {
                for (Map<String,Object> book : booklist){
                    System.out.println(book.get("_id"));
                    System.out.println(book.get("author"));
                    System.out.println(book.get("majorCate"));
                    System.out.println(book.get("minorCate"));
                    System.out.println(book.get("title"));
                    System.out.println("http://statics.zhuishushenqi.com"+book.get("cover"));
                    System.out.println(book.get("shortIntro"));
                    System.out.println(book.get("tags"));
                    url = "http://api.zhuishushenqi.com/mix-atoc/"+book.get("_id")+"?view=chapters";
                    String chapterjson = getUrl(client,url);
                    Map<String,Object> chaptermap = GsonUtil.GsonToMaps(chapterjson);
                    if((Boolean) chaptermap.get("ok")){
                        System.out.println(((Map<String,Object>)chaptermap.get("mixToc")).get("updated"));
                        List<Map<String,Object>> list = (List<Map<String,Object>>)((Map<String,Object>)chaptermap.get("mixToc")).get("chapters");
                        for (Map<String,Object> map : list){
                            System.out.println(map.get("title"));
                            String link = URLEncoder.encode( map.get("link").toString(), "UTF-8" );
                            System.out.println(link);
                            url = "http://chapter2.zhuishushenqi.com/chapter/"+link;
                            String chapters = getUrl(client, url);
                            Map<String,Object> chapter = GsonUtil.GsonToMaps(chapters);
                            if((Boolean) chapter.get("ok")){
                                System.out.println(((Map<String,Object>)chapter.get("chapter")).get("body"));
                            }
                        }
                    }
                }
            }
        }
    }

    public static String getUrl(CloseableHttpClient client,String url) {
        // 通过httpget方式来实现我们的get请求
        HttpGet httpGet = new HttpGet(url);
        String str = "";
        try {
            // 通过client调用execute方法，得到我们的执行结果就是一个response，所有的数据都封装在response里面了
            CloseableHttpResponse Response = client.execute(httpGet);
            // HttpEntity
            // 是一个中间的桥梁，在httpClient里面，是连接我们的请求与响应的一个中间桥梁，所有的请求参数都是通过HttpEntity携带过去的
            // 所有的响应的数据，也全部都是封装在HttpEntity里面
            HttpEntity entity = Response.getEntity();
            if (entity != null) {
                // 通过EntityUtils 来将我们的数据转换成字符串
                str = EntityUtils.toString(entity, "UTF-8");
            }
            // 关闭
            Response.close();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }
}
