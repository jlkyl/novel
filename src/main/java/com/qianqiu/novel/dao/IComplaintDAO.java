package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Complaint;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface IComplaintDAO {
    @Insert("insert into complaint values(null,#{userid},#{chapid},#{content},0,now(),null,null)")
    Integer add(Complaint complaint);
    @Update("update complaint set state=#{state},operateeid=#{operateeid},operatedate=now() where comid=#{comid}")
    Integer update(Complaint complaint);
    @Select("<script>" +
            "select a.*,bookname,(select pen from users where userid=b.userid) pen,(select typename from booktype where typeid=b.typeid) typename from \n" +
            "(select *,getEmp(operateeid) empname,(select username from users where userid=c.userid) username,(select chaptername from chapters where chapterid=c.chapid) chaptername,(select url from chapters where chapterid=c.chapid) url,(select bookid from rolls where rollid = (select rollid from chapters where chapterid=c.chapid)) bookid from complaint c) a\n" +
            "LEFT JOIN books b on b.bookid=a.bookid " +
            "<where>" +
            "<if test=\"state!=null\">" +
            " and a.state=#{state}" +
            "</if>" +
            "<if test=\"state==null\">" +
            " and a.state!=0" +
            "</if>" +
            "<if test=\"userid!=null\">" +
            " and b.userid==#{userid}" +
            "</if>" +
            "</where>" +
            "order by comtime desc " +
            "limit #{offset},#{pageSize}" +
            "</script>")
    List<Map<String,Object>> query(@Param("state") Integer state,@Param("userid") Integer userid,@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);
    @Select("<script>" +
            "select count(*) from \n" +
            "(select *,(select bookid from rolls where rollid = (select rollid from chapters where chapterid=c.chapid)) bookid from complaint c) a\n" +
            "LEFT JOIN books b on b.bookid=a.bookid " +
            "<where>" +
            "<if test=\"state!=null\">" +
            " and a.state=#{state}" +
            "</if>" +
            "<if test=\"state==null\">" +
            " and a.state!=0" +
            "</if>" +
            "<if test=\"userid!=null\">" +
            " and b.userid==#{userid}" +
            "</if>" +
            "</where>" +
            "</script>")
    Integer getCount(@Param("state") Integer state,@Param("userid") Integer userid);
}
