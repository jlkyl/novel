package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Labels;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LabelsDao {
    @Select("select * from labels")
    List<Labels> queryAll();
    @SelectKey(keyProperty = "labelid",keyColumn = "labelid",before = false,resultType = Integer.class,statement = "select max(labelid) from labels")
    @Insert("INSERT INTO `novel`.`labels` (`labelid`, `labelname`, `operateeid`, `operatedate`) VALUES (#{labelid}, #{labelname}, null, now());\n")
    int add(Labels l);
    @Update("UPDATE `novel`.`labels` SET  `labelname`=#{labelname}, `operateeid`=null, `operatedate`=#{operatedate} WHERE (`labelid`=#{labelid});\n")
    int update(Labels l);
    @Delete("delete from labels where labelid = #{labelid}")
    int del(Integer labelid);
    @Select("select * from labels where labelid = #{labelid}")
    List<Labels> queryId(Integer labelid);

    @Select("<script>select * from labels" +
            "<if test=\"offset!=null and pageSize!=null\">\n" +
            "   limit #{offset},#{pageSize}\n" +
            "</if></script>")
    List<Labels> findAll(@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);

    @Select("select count(*) from labels")
    Integer getCount();
    @Select("select * from labels where labelname=#{labelname}")
    Labels queryName(String labelname);
}
