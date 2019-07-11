package com.qianqiu.novel.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReplyDao {

    @Select("<script>" +
            "SELECT r.*,b.bookname,e.content repcontent,r.content\n" +
            "FROM reply r\n" +
            "LEFT JOIN evaluate e\n" +
            "ON r.evaid = e.evaid\n" +
            "LEFT JOIN books b\n" +
            "ON e.bookid = b.bookid\n" +
            "LEFT JOIN users u\n" +
            "ON r.userid = u.userid\n" +
            "<where>" +
            "<if test=\'userid != null\'>r.userid = #{userid}</if>" +
            "</where>" +
            "</script>")
    public List<Map<String,Object>> queryReply(@Param("userid") Integer userid);
}
