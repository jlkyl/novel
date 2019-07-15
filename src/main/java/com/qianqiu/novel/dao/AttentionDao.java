package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Attention;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface AttentionDao {

    @Select(value = "SELECT a.*,u.username,u.head from users u\n" +
            "join attention a\n" +
            "ON a.userid = u.userid\n" +
            " where a.userid = #{userid} and attentuser in (select userid from attention where attentuser=#{userid})")
    List<Map<String,Object>> query(Integer userid);

    @Select(value = "SELECT u.username,u.head,a.* from users u\n" +
            "join attention a\n" +
            "ON a.attentuser = u.userid\n" +
            "where a.userid = #{userid}")
    List<Map<String,Object>> queryas(Integer userid);

    @Select(value = "SELECT u.username,u.head,a.*,(SELECT count(*) from attention where userid=#{userid} and attentuser=a.userid) state from users u\n" +
            "join attention a\n" +
            "ON a.userid = u.userid\n" +
            "where a.attentuser = #{userid}")
    List<Map<String,Object>> queryasd(Integer userid);

    @Select("<script>" +
            "SELECT u.userid,COALESCE(u.vip,0) vip,u.username,COALESCE(u.head,0) head,u.author,u.sex,\n" +
            "(SELECT count(*) from attention where attentuser=#{userid}) states,\n" +
            "(SELECT COUNT(attentid) from attention  where userid=#{userid}) aten " +
            "<if test=\"loginid!=null\">" +
            ",(select count(*) from attention where userid=#{loginid} and attentuser=#{userid}) state " +
            "</if>" +
            "from users u\n" +
            "WHERE u.userid= #{userid}" +
            "</script>")
    List<Map<String,Object>> queryst(@Param("userid") Integer userid,@Param("loginid") Integer loginid);
    @Insert(value = "INSERT INTO `novel`.`attention` ( `userid`, `attentuser`)"  +
            " VALUES ( #{userid}, #{attentuser})")
    int add(Attention a);

    @Delete(value = " DELETE FROM attention  WHERE attentuser =#{attentuser} and userid = #{userid}")
    Integer del(Integer attentuser,Integer userid);


}
