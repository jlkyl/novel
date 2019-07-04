package com.qianqiu.novel;

import com.qianqiu.novel.entity.*;
import com.qianqiu.novel.service.*;
import com.qianqiu.novel.utils.FileUtil;
import com.qianqiu.novel.utils.GsonUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NovelApplicationTests {

    @Resource
    private UsersService us;
    @Resource
    private BooksService bs;
    @Resource
    private BooktypeService bts;
    @Resource
    private RollsService rs;
    @Resource
    private ChaptersService cs;
    @Resource
    private LabelsService ls;

    @Test
    public void addTypes() throws IOException {
        // 获取http客户端
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            String url = "https://novel.juhe.im/sub-categories";
            String json = getUrl(client,url);
            System.out.println(json);
            Map<String,Object> map = GsonUtil.GsonToMaps(json);
            List<Map<String,Object>> list = (List<Map<String,Object>>)map.get("male");
            for(Map<String,Object> m : list){
                Booktype b = new Booktype();
                b.setTypename(m.get("major").toString());
                bts.add(b);
                List<Object> oo = (List<Object>) m.get("mins");
                if(oo.size()>0) {
                    for (Object o : oo){
                        Booktype bt = new Booktype();
                        bt.setTypename(o.toString());
                        bt.setParentid(b.getTypeid());
                        bts.add(bt);
                    }
                }
            }
        } finally {
            client.close();
        }
    }

    @Test
    public void addAuthor() throws Exception {
        Users users = new Users();
        users.setPen("天蚕土豆");
        users.setAuthor(2);
        users.setUserid(4);
        // 获取http客户端
        CloseableHttpClient client = HttpClients.createDefault();
        try {
            //获取作者对应书籍信息
            String url = "https://novel.juhe.im/author-books?author="+users.getPen();
            getBooks(client,url,users.getUserid());
        } finally {
            client.close();
        }
    }

    public void getBooks(CloseableHttpClient client,String url,Integer userid) throws UnsupportedEncodingException {
        String json = getUrl(client,url);
        System.out.println(json);
        Map<String,Object> bookmap = GsonUtil.GsonToMaps(json);
        if((Boolean) bookmap.get("ok")){
            List<Map<String,Object>> booklist = (List<Map<String,Object>>)bookmap.get("books");
            if(booklist.size()>0) {
                for (Map<String,Object> book : booklist){
                    System.out.println(book.get("_id"));
                    url = "http://api.zhuishushenqi.com/book/"+book.get("_id");
                    json = getUrl(client,url);
                    book = GsonUtil.GsonToMaps(json);
                    Books books = bs.findByName(book.get("title").toString());
                    if(books==null) {
                        books = new Books();
                        books.setBookname(book.get("title").toString());
                        books.setCover("http://statics.zhuishushenqi.com" + book.get("cover"));
                        books.setUserid(userid);
                        books.setState(0);
                        books.setPutaway(1);
                        books.setDetails(book.get("longIntro").toString());
                        //添加书籍存放路径
                        FileUtil.File(books.getBookname());
                        //设置书籍分类
                        Object type = book.get("minorCate");
                        Object partype = book.get("majorCate");
                        if (type != null) {
                            Booktype pbt = bts.queryByName(partype.toString());
                            if (pbt != null) {
                                Booktype bt = bts.queryByName(type.toString());
                                if (bt != null) {
                                    books.setTypeid(bt.getTypeid());
                                }
                            }
                        } else {
                            Booktype pbt = bts.queryByName(partype.toString());
                            if (pbt != null) {
                                books.setTypeid(pbt.getTypeid());
                            }
                        }
                        bs.add(books);
                        //设置书籍标签
                        List<Object> oo = (List<Object>) book.get("tags");
                        if(oo.size()>0){
                            for(Object o:oo){
                                Labels l = ls.queryName(o.toString());
                                if(l==null){
                                    l = new Labels();
                                    l.setLabelname(o.toString());
                                    ls.add(l);
                                }
                                bs.addLabel(books.getBookid(),l.getLabelid());
                            }
                        }
                    }
                    System.out.println(books);
                    url = "http://api.zhuishushenqi.com/mix-atoc/"+book.get("_id")+"?view=chapters";
                    json = getUrl(client,url);
                    Map<String,Object> chaptermap = GsonUtil.GsonToMaps(json);
                    if((Boolean) chaptermap.get("ok")){
                        System.out.println(((Map<String,Object>)chaptermap.get("mixToc")).get("updated"));
                        List<Map<String,Object>> list = (List<Map<String,Object>>)((Map<String,Object>)chaptermap.get("mixToc")).get("chapters");
                        //获取书籍最后一卷
                        Integer rollid = null;
                        List<Rolls> rolls = rs.findByBookid(books.getBookid());
                        Chapters chapters = new Chapters();
                        chapters.setState(0);
                        Rolls roll = new Rolls();
                        if(rolls.size()>0){
                            roll = rolls.get(rolls.size()-1);
                            chapters.setRollid(roll.getRollid());
                        }
                        for (int i = bs.getChapterNums(books.getBookid()) ; i<list.size() ; i++){
                            if(i%100==0){
                                roll.setRollname("正文卷");
                                roll.setIsvip(0);
                                roll.setBookid(books.getBookid());
                                roll.setUrl(books.getBookname()+ File.separator +(roll.getRollname()+((i/100)+1)));
                                FileUtil.File(roll.getUrl());
                                rs.add(roll);
                                chapters.setRollid(roll.getRollid());
                            }
                            chapters.setChapternum(cs.getOrder(books.getBookid())+1);
                            String link = URLEncoder.encode( list.get(i).get("link").toString(), "UTF-8" );
                            url = "http://chapter2.zhuishushenqi.com/chapter/"+link;
                            json = getUrl(client, url);
                            Map<String,Object> chapter = GsonUtil.GsonToMaps(json);
                            if((Boolean) chapter.get("ok")){
                                chapters.setChaptername(list.get(i).get("title").toString());
                                String context = ((Map<String,Object>)chapter.get("chapter")).get("body").toString();
                                //计算章节字数
                                String regEx = "[\u4e00-\u9fa5]";
                                Pattern p = Pattern.compile(regEx);
                                Matcher m = p.matcher(context);
                                Integer count = 0;
                                while(m.find()){
                                    count ++;
                                }
                                chapters.setWordnum(count);
                                chapters.setUrl(roll.getUrl()+File.separator+chapters.getChaptername()+".txt");
                                FileUtil.write(chapters.getUrl(),context);
                                cs.add(chapters);
                                System.out.println(chapters);
                            }
                        }
                    }
                }
            }
        }
    }

    public String getUrl(CloseableHttpClient client,String url) {
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
