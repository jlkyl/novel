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
    @Update("update chapters set chaptername=#{chaptername},url=#{url},state=#{state} where chapterid=#{chapterid}")
    Integer updChapterInfo(@Param("chaptername") String chaptername,@Param("url") String url,@Param("state") Integer state,@Param("chapterid") Integer chapterid);
    @Update("update chapters set state=#{state} where chapterid=#{chapterid}")
    Integer updChapDel(@Param("state") Integer state,@Param("chapterid")Integer chapterid);



    @Select("select c.chapterid,b.bookid, b.details,b.cover,b.bookname,u.pen,bt.typename,r.rollname,c.chaptername,c.chapternum,count(chapternum) num,c.updatetime,SUM(wordnum) zi,b.clicknum,r.rollid from books b LEFT JOIN users u on b.userid = u.userid LEFT JOIN booktype bt on b.typeid = bt.typeid \n" +
            "LEFT JOIN rolls r on b.bookid = r.bookid LEFT JOIN chapters c on r.rollid = c.rollid where b.bookid=#{bookid}")
    List<Map<String,Object>> queryAll(Integer bookid);

    @Select("select r.isvip,COUNT(chaptername) num,r.rollid,r.rollname,SUM(c.wordnum) zi from chapters c LEFT JOIN rolls r on c.rollid = r.rollid where r.bookid = #{bookid} GROUP BY r.rollid ")
    List<Map<String,Object>> queryChapter(Integer bookid);

    @Select("select * from chapters where chapterid = #{chapterid} and rollid = #{rollid}")
    Chapters queryId(Integer chapterid,Integer rollid);

    @Select(" SELECT * FROM chapters WHERE chapterid IN (SELECT CASE\n" +
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

}
