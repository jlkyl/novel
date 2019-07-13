package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Booktype;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface BooktypeDao {

    @Select("<script>" +
            "SELECT b.*,b2.typename typename1,e.empname\n" +
            "from booktype b\n" +
            "LEFT JOIN\n" +
            "booktype b2\n" +
            "on b.parentid = b2.typeid\n" +
            "left JOIN\n" +
            "emps e\n" +
            "on b.operateeid = e.empid\n" +
            "ORDER BY b.typeid\n" +
            "<if test=\"offset!=null and pageSize!=null\">\n" +
            "   limit #{offset},#{pageSize}\n" +
            "</if></script>")
    public List<Map<String,Object>> queryAll(@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);

    @Select("select count(*) from booktype")
    Integer getCount();

    @Select("select * from booktype where typeid = #{typeid}")
    public Booktype queryById(Integer typeid);

    @SelectKey(keyColumn = "typeid",keyProperty = "typeid",before = false,resultType = Integer.class,statement = "select max(typeid) from booktype")
    @Insert("INSERT INTO `novel`.`booktype` (`typeid`, `typename`, `icon`, `parentid`, `operateeid`, `operatedate`) VALUES (NULL, #{typename}, #{icon}, #{parentid}, #{operateeid},NOW())")
    public int add(Booktype bt);

    @Update("UPDATE `novel`.`booktype` SET `typename`=#{typename}, `icon`=#{icon}, `parentid`=#{parentid}, `operateeid`=#{operateeid}, `operatedate`=NOW() WHERE (`typeid`=#{typeid})")
    public int update(Booktype bt);

    @Delete("delete from booktype where typeid = #{typeid};delete from booktype where parentid = #{typeid}")
    public int del(Integer typeid);

    @Select("select * from booktype where typename = #{typename}")
    public Booktype queryByName(String typename);

    //查询父类
    @Select("SELECT *,(select count(*) from books where typeid in (select typeid from booktype where parentid = bt.typeid or typeid = bt.typeid)) nums from booktype bt where parentid is null or parentid=typeid")
    public List<Map<String,Object>> queryParentall();

    @Select("select * from booktype where parentid=#{parentid}")
    public List<Map<String,Object>> queryByparentid(@Param("parentid") Integer parentid);

    @Select(value = "SELECT b.bookid,b.cover,b.bookname,bk.typename,b.state,b.details,COALESCE((select sum(wordnum) from chapters \n" +
            "where state=0 and rollid in (select rollid from rolls where bookid = b.bookid)),0) wordcount,\n" +
            "(SELECT chapterid from chapters where state =0 AND rollid in (SELECT rollid from rolls WHERE bookid = b.bookid)order by chapternum desc LIMIT 1 ) chapterid,\n" +
            "(SELECT chaptername from chapters where state =0 AND rollid in (SELECT rollid from rolls WHERE bookid = b.bookid)order by chapternum desc LIMIT 1) chaptername,\n" +
            "(select updatetime from chapters where state=0 and rollid in (select rollid from rolls where bookid = b.bookid) ORDER BY chapternum desc LIMIT 1) updatetime \n" +
            "FROM books b \n" +
            "JOIN booktype bk\n" +
            "on b.typeid = bk.typeid\n" +
            "WHERE b.userid = #{userid}")
    List<Map<String,Object>> querybytu(Integer userid);
}
