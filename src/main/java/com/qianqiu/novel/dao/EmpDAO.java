package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Emps;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpDAO {
    @Select("select * from emps where uname=#{uname} and pwd=#{pwd}")
    public Emps loginB(@Param("uname" )String uname, @Param("pwd")String pwd);

    @Select("select * from emps")
    List<Emps> queryEmp();

    @Insert("INSERT INTO `novel`.`emps` (`empid`, `uname`, `pwd`, `empname`, `phone`, `idcard`, `roleid`, `state`, `operateeid`, `operatedate`) VALUES " +
            "( null,#{uname}, #{pwd}, #{empname}, #{phone}, #{idcard}, #{roleid}, 0, #{operateeid}, now());\n")
    int add(Emps e);

    @Update("UPDATE `novel`.`emps` SET  `uname`=#{uname}, `empname`=#{empname}, `phone`=#{phone}, `idcard`=#{idcard}, `roleid`=#{roleid}, `operateeid`=#{operateeid}, `operatedate`=now() WHERE (`empid`=#{empid});\n")
    int update(Emps e);

    @Update("UPDATE `novel`.`emps` SET  `state`=#{state},  `operatedate`=now() WHERE (`empid`=#{empid});\n")
    int US(Emps e);

    @Select("SELECT e.empid,e.uname,e.pwd,e.empname,e.phone,e.idcard,r.rolename,e.state,a.empname name,e.operatedate from emps e LEFT JOIN roles r on e.roleid = r.roleid LEFT JOIN emps a on e.operateeid = a.empid ")
    List<Map<String,Object>> queryAll();

    @Update("UPDATE `novel`.`emps` SET `pwd`=#{pwd} WHERE `empid`=#{empid}")
    public int updateP(Emps e);


}