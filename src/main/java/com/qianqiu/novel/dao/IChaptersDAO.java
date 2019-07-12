package com.qianqiu.novel.dao;

import java.util.List;
import java.util.Map;

import com.qianqiu.novel.entity.Chapters;
import com.qianqiu.novel.entity.Rolls;
import org.apache.ibatis.annotations.*;

@Mapper
public interface IChaptersDAO {
    @SelectKey(keyProperty = "chapterid",keyColumn = "chapterid",before = false,resultType = Integer.class,statement = "select max(chapterid) from chapters")
    @Insert("INSERT INTO `novel`.`chapters` (`chapterid`, `chaptername`, `url`, `chapternum`, `state`, `wordnum`, `updatetime`, `rollid`) VALUES (NULL, #{chaptername}, #{url}, #{chapternum}, #{state}, #{wordnum}, now(), #{rollid});")
    Integer add(Chapters chapters);
    @Select("select * from chapters where chaptername = #{param1} and rollid in (select rollid from rolls where bookid=#{param2})")
    Chapters findByName(String chaptername,Integer bookid);
    @Select("select COALESCE(max(chapternum),0) from chapters where rollid in (select rollid from rolls where bookid=#{bookid})")
    Integer getOrder(Integer bookid);
    //查询某卷的章节
    @Select("select * from chapters where rollid=#{rollid}")
    List<Chapters> findByRollid(Integer rollid);
    //根据章节状态显示某卷的章节信息
    @Select("select * from chapters where  rollid =#{rollid} and state=#{state}")
    List<Chapters> findName(@Param("rollid") Integer rollid,@Param("state")Integer state);
    //修改章节状态 0发布 1未发布 2待审核 4 回收站
    @Update("update chapters set state=#{state} where chapterid=#{chapterid}")
    Integer updChapter(@Param("state") Integer state,@Param("chapterid")Integer chapterid);
    //根据ID查询章节信息
    @Select("select * from chapters where chapterid=#{chapterid}")
    Chapters queryById(@Param("chapterid") Integer chapterid);
    //修改章节（章节名，文章信息，根据ID）
    @Update("update chapters set chaptername=#{chaptername},url=#{url},state=#{state},wordnum=#{wordnum} where chapterid=#{chapterid}")
    Integer updChapterInfo(@Param("chaptername") String chaptername,@Param("url") String url,@Param("state") Integer state,@Param("chapterid") Integer chapterid,@Param("wordnum")Integer wordnum);
    @Update("update chapters set state=#{state} where chapterid=#{chapterid}")
    Integer updChapDel(@Param("state") Integer state,@Param("chapterid")Integer chapterid);
    @Select("select * from chapters c " +
            "LEFT JOIN rolls r on c.rollid=r.rollid " +
            "LEFT JOIN books b on r.bookid=b.bookid " +
            "where r.bookid=#{bookid} and c.state=#{state}")
    List<Chapters> findAll(@Param("bookid") Integer bookid,@Param("state") Integer state);

    @Select("select c.chapterid,b.bookid, b.details,b.cover,b.bookname,u.pen,bt.typename,r.rollname,c.chaptername,c.chapternum,count(chapternum) num,c.updatetime,SUM(wordnum) zi,b.clicknum,r.rollid from books b LEFT JOIN users u on b.userid = u.userid LEFT JOIN booktype bt on b.typeid = bt.typeid \n" +
            "LEFT JOIN rolls r on b.bookid = r.bookid LEFT JOIN chapters c on r.rollid = c.rollid where b.bookid=#{bookid}")
    List<Map<String,Object>> queryAll(Integer bookid);

    @Select("select r.isvip,COUNT(chaptername) num,r.rollid,r.rollname,SUM(c.wordnum) zi from chapters c LEFT JOIN rolls r on c.rollid = r.rollid where r.bookid = #{bookid} GROUP BY r.rollid ")
    List<Map<String,Object>> queryChapter(Integer bookid);

    @Select("<script>" +
            "select c.*,b.bookid,b.bookname,b.userid,b.putaway,\n" +
            "(select pen from users where userid=b.userid) pen,\n" +
            "COALESCE((select chapterid from chapters where chapternum=c.chapternum-1 and rollid in (select rollid from rolls where bookid = b.bookid)),0) beforeid,\n" +
            "COALESCE((select chapterid from chapters where chapternum=c.chapternum+1 and rollid in (select rollid from rolls where bookid = b.bookid)),0) nextid," +
            "(select isvip from rolls where rollid=c.rollid) isvip," +
            "<if test=\"userid!=null\">" +
            "(select count(*) from bookrack where bookid=b.bookid and userid=#{userid}) israck,\n" +
            "(select count(*) from expenses where chapid=c.chapterid and userid=#{userid}) isbuy\n" +
            "</if>" +
            "<if test=\"userid==null\">" +
            "(select 0) israck,\n" +
            "(select 0) isbuy\n" +
            "</if>" +
            "from chapters c\n" +
            "LEFT JOIN rolls r\n" +
            "on c.rollid=r.rollid\n" +
            "LEFT JOIN books b\n" +
            "on r.bookid=b.bookid\n" +
            "where chapterid=#{chapterid}" +
            "</script>")
    Map<String,Object> queryId(@Param("chapterid") Integer chapterid,@Param("userid") Integer userid);

    @Select(" SELECT * FROM chapters WHERE state=0 and chapterid IN (SELECT CASE\n" +
            "            WHEN SIGN(chapterid - #{chapterid}) > 0 THEN MIN(chapterid)\n" +
            "            WHEN SIGN(chapterid - #{chapterid}) < 0 THEN MAX(chapterid)\n" +
            "            ELSE\n" +
            "            chapterid\n" +
            "            END AS chapterid\n" +
            "            FROM\n" +
            "            chapters\n" +
            "            GROUP BY\n" +
            "            SIGN(chapterid - #{chapterid})\n" +
            "            ORDER BY\n" +
            "            SIGN(chapterid - #{chapterid})\n" +
            "            ) and rollid=#{rollid} \n" +
            "            ORDER BY chapterid ASC;")
    List<Chapters> upDow(Integer chapterid,Integer rollid);

    @Select("<script>" +
            "select *," +
            "(select isvip from rolls where rollid = c.rollid) isvip," +
            "<if test=\"userid==null\">" +
            "(select 0) isbuy " +
            "</if>" +
            "<if test=\"userid!=null\">" +
            "(select count(*) from expenses where chapid=c.chapterid and exptypeid=3 and userid=#{userid}) isbuy " +
            "</if>" +
            "from chapters c " +
            "where state=0" +
            "<if test=\"bookid!=null\">" +
            "and c.rollid in (select rollid from rolls where bookid=#{bookid}) " +
            "</if>" +
            "<if test=\"rollid!=null\">" +
            "and c.rollid = #{rollid} " +
            "</if>" +
            "order by chapternum" +
            "</script>")
    List<Map<String,Object>> queryBuy(@Param("bookid") Integer bookid,@Param("rollid") Integer rollid,@Param("userid") Integer userid);

    @Insert("insert into browses VALUES(NULL,#{param1},(select bookid from rolls where rollid = (select rollid from chapters where chapterid=#{param2})),#{param2},now())")
    Integer addBrowses(Integer userid,Integer chapid);

    @Update("update browses\n" +
            "set chapid=#{param2}\n" +
            "where userid=#{param1} and bookid=(select bookid from rolls where rollid = (select rollid from chapters where chapterid=#{param2}))")
    Integer updBrowses(Integer userid,Integer chapid);

    @Select("SELECT b.*,bookname,o.userid authorid,typeid," +
            "(select chaptername from chapters where chapterid=b.chapid) chaptername," +
            "(select typename from booktype where typeid=o.typeid) typename," +
            "(select pen from users where userid = o.userid) pen," +
            "(select count(*) from bookrack where bookid=b.bookid and userid=b.userid) israck " +
            "FROM `browses` b LEFT JOIN books o on b.bookid=o.bookid " +
            "where b.userid=#{userid} ORDER BY browsetime DESC LIMIT 50")
    List<Map<String,Object>> queryBrowses(Integer userid);

    @Select("<script>select chapid from browses " +
            "where userid=#{param1} and bookid=" +
            "<if test=\"param2!=null\">" +
            "(select bookid from rolls where rollid = (select rollid from chapters where chapterid=#{param2}))" +
            "</if>" +
            "<if test=\"param3!=null\">" +
            "#{param3}"+
            "</if>" +
            "</script>")
    Integer getChapid(Integer userid,Integer chapid,Integer bookid);
    //后台审核查询
    @Select("select b.bookname,c.chaptername,c.url,c.chapterid,c.updatetime,c.wordnum from chapters c " +
            "LEFT JOIN rolls r on c.rollid=r.rollid " +
            "LEFT JOIN books b on r.bookid=b.bookid where c.state=#{state}")
    List<Map<String,Object>> querybackSH(@Param("state") Integer state);
    //后台审核模糊查询
    @Select("<script>" +
            "select b.bookname,c.chaptername,c.url,c.chapterid,c.updatetime,c.wordnum from chapters c " +
            "LEFT JOIN rolls r on c.rollid=r.rollid " +
            "LEFT JOIN books b on r.bookid=b.bookid " +
            "<where> " +
            "<if test=\"state!=null\">" +
            "and c.state=#{state}" +
            "</if>" +
            "<if test=\"bookname!=null\">" +
            " and b.bookname like concat ('%',#{bookname},'%')" +
            "</if>" +
            "</where>" +
            "</script>")
    List<Map<String,Object>> querybackSHMH(@Param("state") Integer state,@Param("bookname") String bookname);

    @Select("select (select count(userid) from users) usernum,\n" +
            "       (select count(userid) from users where AUTHOR=1) authornum,\n" +
            "       (select count(chapterid) from chapters where state=2) shchap,\n" +
            "       (select count(bookid) from books) booknum,\n" +
            "       (select count(comid) from complaint where state=0) comnum,count(empid) empnum from emps")
    List<Map<String,Object>> cxsy();
}
