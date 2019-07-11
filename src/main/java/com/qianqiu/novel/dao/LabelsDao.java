package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Labels;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface LabelsDao {
    @Select("select * from labels")
    List<Labels> queryAll();
    @SelectKey(keyProperty = "labelid",keyColumn = "labelid",before = false,resultType = Integer.class,statement = "select max(labelid) from labels")
    @Insert("INSERT INTO `novel`.`labels` (`labelid`, `labelname`, `operateeid`, `operatedate`) VALUES (#{labelid}, #{labelname}, #{operateeid}, now());\n")
    int add(Labels l);
    @Update("UPDATE `novel`.`labels` SET  `labelname`=#{labelname}, `operateeid`=#{operateeid}, `operatedate`=now() WHERE (`labelid`=#{labelid});\n")
    int update(Labels l);
    @Delete("delete from book_label where labelid=#{labelid};delete from labels where labelid = #{labelid};")
    int del(Integer labelid);
    @Select("select * from labels where labelid = #{labelid}")
    List<Labels> queryId(Integer labelid);

    @Select("<script>select * from labels a LEFT JOIN emps e ON a.operateeid = e.empid" +
            "<if test=\"offset!=null and pageSize!=null\">\n" +
            "   limit #{offset},#{pageSize}\n" +
            "</if></script>")
    List<Map<String,Object>> findAll(@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);

    @Select("select count(*) from labels")
    Integer getCount();
    @Select("select * from labels where labelname=#{labelname}")
    Labels queryName(String labelname);
}
