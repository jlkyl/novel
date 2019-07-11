package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Evaluate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface EvaluateDao {

    @Select("<script>" +
            "SELECT e.*,b.bookname,u.pen,u.head,\n" +
            "(select count(r.replyid) from reply r where evaid in (select evaid from evaluate where evaid = e.evaid)) recount \n" +
            "FROM evaluate e\n" +
            "LEFT JOIN books b\n" +
            "ON e.bookid = b.bookid\n" +
            "LEFT JOIN users u\n" +
            "ON e.userid = u.userid\n" +
            "<where>\n" +
            "<if test=\'userid != null\'>and e.userid = #{userid}</if>\n" +
            "<if test=\'bookid != null\'>and e.bookid = #{bookid}</if>\n" +
            "</where>" +
            "</script>")
    public List<Map<String,Object>> queryEvaluate(@Param("userid") Integer userid,@Param("bookid") Integer bookid);

    @Select("<script>" +
            "SELECT e.*,b.bookname,u.pen,u.head,\n" +
            "(select count(r.replyid) from reply r where evaid in (select evaid from evaluate where evaid = e.evaid)) recount \n" +
            "FROM evaluate e\n" +
            "LEFT JOIN books b\n" +
            "ON e.bookid = b.bookid\n" +
            "LEFT JOIN users u\n" +
            "ON e.userid = u.userid\n" +
            "<where>\n" +
            "<if test=\'evaid != null\'>and e.evaid = #{evaid}</if>\n" +
            "<if test=\'bookid != null\'>and e.bookid = #{bookid}</if>\n" +
            "</where>" +
            "</script>")
    public List<Map<String,Object>> queryById(@Param("bookid") Integer bookid,@Param("evaid") Integer evaid);

    @Insert("INSERT INTO `novel`.`evaluate` (`evaid`, `userid`, `bookid`, `content`, `level`, `evatime`) VALUES (NULL, #{userid}, #{bookid}, #{content}, #{level}, NOW())")
    public int add(Evaluate e);
}
