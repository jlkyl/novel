package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Users;
import org.apache.ibatis.annotations.*;

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

    @Insert("insert into Users (userid,username,phone,password,realname, sex, idcard, email, pen, head, sign, author) values(null,concat('Reader',#{phone}),#{phone},#{password},#{realname},'男',#{idcard},#{email},#{pen},#{head},#{sign},0)")
    public int addlogin(Users users );

    @Select("select * from users where pen=#{pen}")
    public Users findByPen(String pen);
    @Update("update users set pen=#{pen},email=#{email},realname=#{realname},idcard=#{idcard},author=1 where userid=#{userid}")
    public int updUser(Users users);

    @Select("select * from users where phone=#{phone}")
    public Integer Surephone(@Param("phone")String phone);

    @Select("select * from users where email=#{email}")
    public Integer Sureemail(@Param("email")String email);

    @Select("select * from users where pen=#{username}")
    public Integer Surepen(@Param("username")String username);

    @Select("select * from users where realname=#{realname}")
    public Integer Surerealname(@Param("realname")String username);


    @Select("select * from users where idcard=#{idcard}")
    public Integer Sureidcard(@Param("idcard")String username);

}
