package com.qianqiu.novel.dao;

import java.util.List;
import java.util.Map;

import com.qianqiu.novel.entity.Weekpush;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IWeekpushDAO {
    @Insert("insert into weekpush values(null,#{bookid},#{potential},#{money},SUBDATE(CURDATE(),-7))")
    Integer add(Weekpush weekpush);
    @Select("select count(*) from weekpush where pushtime between getMonday(SUBDATE(CURDATE(),-7)) and getSunday(SUBDATE(CURDATE(),-7))")
    Integer getCount();

    @Select("select count(*) from weekpush where pushtime between getMonday(SUBDATE(CURDATE(),-7)) and getSunday(SUBDATE(CURDATE(),-7))and bookid=#{bookid}")
    Integer getbookidCount(@Param("bookid") Integer bookid);

    @Select("<script>" +
            "select w.*,bookname,typeid,userid,(select typename from booktype where typeid = COALESCE((select parentid from booktype where typeid=b.typeid),b.typeid)) typename,(select pen from users where userid=b.userid) pen,getMonday(pushtime) start,getSunday(pushtime) end from weekpush w left join books b on w.bookid=b.bookid " +
            "<if test=\"param1!=null\">" +
            "where pushtime between getMonday(CURDATE()) and getSunday(CURDATE()) " +
            "</if>" +
            "<if test=\"param2!=null\">" +
            "where userid = #{param2}" +
            "</if>" +
            "</script>")
    List<Map<String,Object>> findAll(Integer all,Integer userid);
}
