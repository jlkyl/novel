package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Users;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IUsersDAO {

    @Select("select * from users where username=#{username} and password=#{password}")
    public Users unamelogin(@Param("username") String username, @Param("password") String password);

    @Select("select * from users where email=#{email} and password=#{password}")
    public Users emaillogin(@Param("email") String username, @Param("password") String password);

    @Select("select * from users where phone=#{phone} and password=#{password}")
    public Users plogin(@Param("phone") String username, @Param("password") String password);

    @Select("select * from users where phone=#{phone}")
    public Users yzmlogin(@Param("phone") String phone);

    @Insert("insert into Users (phone,password) values(#{users.phone},#{users.password})")
    public int addlogin(@Param("users") Users users );

}
