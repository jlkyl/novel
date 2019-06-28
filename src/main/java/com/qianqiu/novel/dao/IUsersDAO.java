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
    //INSERT INTO `novel`.`users` (`userid`, `username`, `password`, realname, sex, idcard, email,pen, head, sign, author) VALUES (NULL, NULL, '123yl123', NULL, NULL, NULL, NULL, '16692155040', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
    @Insert("insert into Users (userid,username,phone,password,realname, sex, idcard, email, pen, head, sign, author) values(null,concat('Reader',#{phone}),#{phone},#{password},#{realname},#{sex},#{idcard},#{email},#{pen},#{head},#{sign},#{author})")
    public int addlogin(Users users );

    @Update("UPDATE `novel`.`users` SET `username`=#{username},`sex`=#{sex},`sign`=#{sign} WHERE (`userid`=#{userid})")
    public int update(Users users);

    @Update("UPDATE `novel`.`users` SET `head`=#{head} WHERE (`userid`=#{userid})")
    public int updhead(Users users);
}
