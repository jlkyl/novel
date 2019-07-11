package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Expnses;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExpnsesDao {
    @Select(value = "<script>select *," +
            "(select username from users where userid=e.userid) username," +
            "(select bookname from books where bookid=e.bookid) bookname," +
            "(select chaptername from chapters where chapterid=e.chapid) chaptername " +
            " from expenses e" +
            "<where>" +
            "<if test=\"exptypeid!=null\">" +
            " and exptypeid=#{exptypeid} " +
            "</if> " +
            "<if test=\"userid!=null\">" +
            " and userid=#{userid} " +
            "</if> " +
            "<if test=\"bookid!=null\">" +
            " and bookid=#{bookid} " +
            "</if> " +
            "</where>" +
            " order by exptime desc</script>")
    List<Map<String,Object>> query(@Param("exptypeid") Integer exptypeid,@Param("userid") Integer userid,@Param("bookid") Integer bookid);
    @Insert(value = "INSERT INTO `novel`.`expenses` ( `userid`,`bookid`,`chapid`,`exptypeid`,`expmoney`, `exptime`, `money`)" +
            " VALUES ( #{userid},#{bookid},#{chapid},#{exptypeid},#{expmoney}, now(),#{money})")
    int add(Expnses e);

    @Insert("<script>" +
            "insert into expenses ( `userid`,`bookid`,`chapid`,`exptypeid`,`expmoney`, `exptime`) values" +
            "<foreach collection=\"chapid\" separator=\",\" item=\"chap\" index=\"i\" close=\";\">" +
            " (#{userid},#{bookid},${chapid[i]},3,${expmoney[i]},now())" +
            "</foreach>" +
            "</script>")
    Integer buyrecord(@Param("userid") Integer userid,@Param("bookid") Integer bookid,@Param("chapid") Integer[] chapid,@Param("expmoney") Integer[] expmoney);

    @Insert("insert into votes values (null,#{userid},#{bookid},#{nums},now())")
    Integer vote(@Param("userid") Integer userid,@Param("bookid") Integer bookid,@Param("nums") Integer nums);

    @Select("<script>select *," +
            "(select username from users where userid=v.userid) username," +
            "(select bookname from books where bookid=v.bookid) bookname " +
            "from votes v " +
            "<where>" +
            "<if test=\"bookid!=null\">" +
            " and bookid=#{bookid} " +
            "</if> " +
            "<if test=\"userid!=null\">" +
            " and userid=#{userid} " +
            "</if> " +
            "</where>" +
            "order by votetime desc" +
            "</script>")
    List<Map<String,Object>> queryVote(@Param("bookid") Integer bookid,@Param("userid") Integer userid);

    @Select("select COALESCE(sum(expmoney),0) from expenses where exptypeid=1 and userid=#{userid}")
    Integer queryMoney(Integer userid);

}

