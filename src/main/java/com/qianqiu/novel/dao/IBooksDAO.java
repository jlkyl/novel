package com.qianqiu.novel.dao;

import java.util.List;
import java.util.Map;

import com.qianqiu.novel.entity.Books;
import com.qianqiu.novel.entity.Chapters;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface IBooksDAO {
    @SelectKey(keyProperty = "bookid",keyColumn = "bookid",before = false,resultType = Integer.class,statement = "select max(bookid) from books")
    @Insert("INSERT INTO `novel`.`books` (`bookname`, `cover`, `userid`, `typeid`, `state`, `putaway`, `details`, `createtime`, `url`) VALUES (#{bookname}, #{cover}, #{userid}, #{typeid}, #{state}, #{putaway}, #{details}, now(), #{url});")
    Integer add(Books books);
    @Select("select * from books where bookname = #{bookname}")
    Books findByName(String bookname);
    @Insert("INSERT INTO `novel`.`book_label` (`bookid`, `labelid`) VALUES (#{param1}, #{param2});")
    Integer addLabel(Integer bookid,Integer labelid);
    @Select("select count(*) from chapters where rollid in (select rollid from rolls where bookid=#{bookid})")
    Integer getChapterNums(Integer bookid);
    @Select("select b.*,(select typename from booktype where typeid=b.typeid) typename,(select max(chapternum) from chapters where rollid in (select rollid from rolls where bookid=b.bookid)) chapternum,(select sum(wordnum) from chapters where rollid in (select rollid from rolls where bookid=b.bookid)) wordnum from books b where userid = #{userid}")
    List<Map<String,Object>> findAll(Integer userid);
    @Select("select t.*,b.*,r.*,c.* from books b " +
            "LEFT JOIN booktype t on b.typeid=t.typeid " +
            "LEFT JOIN rolls r on r.bookid=b.bookid " +
            "LEFT JOIN chapters c on c.rollid=r.rollid where b.userid=#{userid} GROUP BY t.typeid ")
    List<Map<String,Object>> booksAll(@Param("userid")Integer userid);
    @Select("select * from chapters where chapternum = (select max(chapternum) from chapters " +
            "                       where rollid in (select rollid from rolls where bookid =#{bookid})) and rollid in (select rollid from rolls where bookid =#{bookid})")
    Chapters queryTime(@Param("bookid") Integer bookid);
    @Update("update books set state=1 where bookid=#{bookid}")
    Integer updBookstate(Integer bookid);
    @Select("select * from books where bookid=#{bookid}")
    Books querybyId(@Param("bookid") Integer bookid);
    @Update("update books set putaway=#{param1} where bookid=#{param2}")
    Integer updPutaway(Integer putaway,Integer bookid);
    @Select("<script>" +
            "select *,(clicknum+votenum*5+racknum*2) potential from \n" +
            "(select *,\n" +
            "(select typename from booktype where typeid = COALESCE((select parentid from booktype where typeid=b.typeid),b.typeid)) typename,\n" +
            "(select pen from users where userid=b.userid) pen,\n" +
            "COALESCE((select count(userid) from expenses where exptypeid=3 and bookid=b.bookid GROUP BY userid),0) buynum,\n" +
            "COALESCE((select avg(level) from evaluate where bookid=b.bookid),0) score,\n" +
            "COALESCE((select sum(expmoney) from expenses where exptypeid=3 and bookid=b.bookid and exptime BETWEEN getMonday(CURDATE()) and getSunday(CURDATE())),0) buymoney,\n" +
            "COALESCE((select sum(nums) from votes where bookid=b.bookid and votetime BETWEEN getMonday(CURDATE()) and getSunday(CURDATE())),0) votenum,\n" +
            "COALESCE((select sum(expmoney) from expenses where exptypeid=2 and bookid=b.bookid and exptime BETWEEN getMonday(CURDATE()) and getSunday(CURDATE())),0) givemoney,\n" +
            "COALESCE((select sum(wordnum) from chapters where rollid in (select rollid from rolls where bookid = b.bookid)),0) wordcount," +
            "(select count(*) from bookrack where bookid=b.bookid and racktime BETWEEN getMonday(CURDATE()) and getSunday(CURDATE())) racknum\n" +
            "from books b) a " +
            "<where>" +
            "<if test=\"putaway!=null\">" +
            " and putaway=#{putaway} " +
            "</if>" +
            "<if test=\"state!=null\">" +
            " and state=#{state} " +
            "</if>" +
            "</where>" +
            "<if test=\"choose!=null\">" +
            "<if test=\"choose==1\">" +
            "order by buynum desc" +
            "</if>" +
            "<if test=\"choose==2\">" +
            "order by score desc" +
            "</if>" +
            "<if test=\"choose==3\">" +
            "order by buymoney desc" +
            "</if>" +
            "<if test=\"choose==4\">" +
            "order by votenum desc" +
            "</if>" +
            "<if test=\"choose==5\">" +
            "order by givemoney desc" +
            "</if>" +
            "<if test=\"choose==6\">" +
            "order by racknum desc" +
            "</if>" +
            "<if test=\"choose==7\">" +
            "order by potential desc" +
            "</if>" +
            "<if test=\"choose==8\">" +
            "order by createtime,buynum desc" +
            "</if>" +
            "<if test=\"choose==9\">" +
            "order by createtime,potential desc" +
            "</if>" +
            "<if test=\"choose==10\">" +
            "order by endtime desc" +
            "</if>" +
            "</if>" +
            "</script>")
    List<Map<String,Object>> query(@Param("choose") Integer choose,@Param("putaway") Integer putaway,@Param("state") Integer state);

    @Select("select b.*,updatetime,isvip,chapterid,chaptername,\n" +
            "(select pen from users where userid = b.userid) pen,\n" +
            "(select typename from booktype where typeid = b.typeid) typename from books b\n" +
            "LEFT JOIN rolls r on r.bookid = b.bookid \n" +
            "LEFT JOIN chapters c on c.rollid = r.rollid\n" +
            "where chapternum =\n" +
            "(select max(chapternum) from chapters where rollid in (select rollid from rolls where bookid = b.bookid))\n" +
            "ORDER BY updatetime desc\n")
    List<Map<String,Object>> queryUpdate();

    @Select("select u.*,\n" +
            "(select COALESCE(sum(expmoney),0) from expenses where bookid in (select bookid from books where userid=u.userid)) m,\n" +
            "(select bookname from books where bookid = (select bookid from books where userid=u.userid limit 1)) bookname,\n" +
            "(select bookid from books where userid=u.userid limit 1) bookid " +
            "from users u\n" +
            "where author!=0\n" +
            "order by m desc " +
            "limit 3")
    List<Map<String,Object>> queryAuthor();

    @Select("select b.bookid, b.details,b.cover,b.bookname,u.pen,bt.typename,r.rollname,c.chaptername,c.chapternum,count(chapternum) num,c.updatetime,SUM(wordnum) zi,b.clicknum,r.rollid from books b LEFT JOIN users u on b.userid = u.userid LEFT JOIN booktype bt on b.typeid = bt.typeid \n" +
            "LEFT JOIN rolls r on b.bookid = r.bookid LEFT JOIN chapters c on r.rollid = c.rollid where b.bookid=#{bookid}")
    List<Map<String,Object>> queryAll(Integer bookid);

    @Select("select r.isvip,COUNT(chaptername) num,r.rollid,r.rollname,SUM(c.wordnum) zi from chapters c LEFT JOIN rolls r on c.rollid = r.rollid where r.bookid = #{bookid} GROUP BY r.rollid ")
    List<Map<String,Object>> queryChapter(Integer bookid);

    @Select("select * from chapters")
    List<Chapters> queryC();

    @Select("select * from books b LEFT JOIN users u on b.userid = u.userid LEFT JOIN booktype bt on b.typeid = bt.typeid \n" +
            "            LEFT JOIN rolls r on b.bookid = r.bookid LEFT JOIN chapters c on r.rollid = c.rollid where b.bookid=1 ORDER BY c.chapterid ASC LIMIT 1\n" +
            " \n")
    List<Map<String,Object>> find(Integer bookid);

    @Select("select * from books")
    List<Books> queryB();

    @Update("update books set bookname=#{bookname} where bookid=#{bookid}")
    Integer updBookname(@Param("bookname") String bookname,@Param("bookid") Integer bookid);
}
