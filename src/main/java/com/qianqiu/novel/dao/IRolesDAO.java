package com.qianqiu.novel.dao;

import java.util.List;

import com.qianqiu.novel.entity.Roles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IRolesDAO {
    List<Roles> findAll();
    Integer add(Roles roles);
    Integer del(Integer roleid);
    Integer upd(@Param("role") Roles roles);
    Integer addMenus(@Param("role") Roles roles);
}
