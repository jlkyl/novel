package com.qianqiu.novel.dao;

import java.util.List;
import java.util.Map;

import com.qianqiu.novel.entity.Books;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IBooksDAO {

    @Select("SELECT b.*,bt.typename,u.username\n" +
            "FROM books b LEFT JOIN booktype bt \n" +
            "ON b.typeid = bt.typeid\n" +
            "LEFT JOIN users u\n" +
            "ON b.userid = u.userid\n" +
            "ORDER BY bookid")
    public List<Map<String,Object>> queryBook();

    @Select("<script>" +
            "SELECT b.*,bt.typename,u.pen\n" +
            "FROM books b LEFT JOIN booktype bt \n" +
            "ON b.typeid = bt.typeid\n" +
            "LEFT JOIN users u\n" +
            "ON b.userid = u.userid\n" +
            "<where>" +
            "<if test=\'kw!=null\'>or u.pen like concat('%',#{kw},'%')</if>" +
            "<if test=\'kw!=null\'>or b.bookname like concat('%',#{kw},'%')</if>" +
            "</where>" +
            "</script>")
    public List<Map<String,Object>> likeBook(@Param("kw") String kw);
}
