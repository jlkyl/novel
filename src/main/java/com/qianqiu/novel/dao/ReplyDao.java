package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Reply;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReplyDao {

    @Select("<script>" +
            "SELECT r.*,b.bookname,e.content repcontent,r.content,u.head,u.username\n" +
            "FROM reply r\n" +
            "LEFT JOIN evaluate e\n" +
            "ON r.evaid = e.evaid\n" +
            "LEFT JOIN books b\n" +
            "ON e.bookid = b.bookid\n" +
            "LEFT JOIN users u\n" +
            "ON r.userid = u.userid\n" +
            "<where>" +
            "<if test=\'evaid != null\'>e.evaid = #{evaid}</if>" +
            "</where>" +
            "</script>")
    public List<Map<String,Object>> queryReply(@Param("evaid") Integer evaid);

    @Insert("INSERT INTO `novel`.`reply` (`replyid`, `userid`, `evaid`, `content`, `replytime`) VALUES (NULL, #{userid}, #{evaid}, #{content}, NOW())")
    public int add(Reply r);
}
