package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Votes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface VotesDao {

    @Select("SELECT v.*,v.nums,b.bookname,u.username,sum(v.voteid) num \n" +
            "FROM votes v\n" +
            "LEFT JOIN books b\n" +
            "on v.bookid = b.bookid\n" +
            "LEFT JOIN users u\n" +
            "on v.userid = u.userid\n" +
            "WHERE v.userid = #{userid}")
    public List<Map<String,Object>> queryVoteById(@Param("userid") Integer userid);
}
