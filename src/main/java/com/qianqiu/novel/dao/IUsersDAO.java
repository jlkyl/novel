package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Users;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

import java.util.List;
@Mapper
public interface IUsersDAO {

    //查询
    //SELECT u.pen,u.sign,COUNT(bookid) as kd,(SELECT SUM(wordnum) from chapters) as 字数,(SELECT TIMESTAMPDIFF(DAY,b.createtime,NOW())) as ssk from users u
    //join books b
    //on b.userid = u.userid
    //where u.author = null
    @Select(value = "SELECT u.pen,u.sign,COUNT(bookid) as kd ,(SELECT SUM(wordnum) from chapters) as mo,(SELECT TIMESTAMPDIFF(DAY,b.createtime,NOW())) as ks from users u\n" +
            "   join books b " +
            "   on b.userid = u.userid")
     List<Users> querys();
    @Select("select * from users where username=#{username} and password=#{password}")
    public Users unamelogin(@Param("username") String username, @Param("password") String password);

    @Select("select * from users where email=#{email} and password=#{password}")
    public Users emaillogin(@Param("email") String username, @Param("password") String password);

    @Select("select * from users where phone=#{phone} and password=#{password}")
    public Users plogin(@Param("phone") String username, @Param("password") String password);

    @Select("select * from users where phone=#{phone}")
    public Users yzmlogin(@Param("phone") String phone);

    /*@Select("select * from users limit #{param1},#{param2}")
    public List<Users> page(Integer offset, Integer pageSize);
*/
   @Select("select count(*) from users")
    public Integer getCount();

    @Select("<script>select * from users" +
            "<if test=\"offset!=null and pageSize!=null\">\n" +
            "   limit #{offset},#{pageSize}\n" +
            "</if></script>")
    public List<Users> listAll(@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);

    @SelectKey(keyColumn = "userid",keyProperty = "userid",before = false,resultType = Integer.class,statement = "select max(userid) from users")
    @Insert("insert into users (userid,username,phone,password,realname, sex, idcard, email, pen, head, sign, author, siteid) values(null,#{username},#{phone},#{password},#{realname},#{sex},#{idcard},#{email},#{pen},#{head},#{sign},#{author},#{siteid})")
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

    @Select("select u.*,(select count(*) from books where userid=u.userid) nums from users u where author=2 and siteid=#{siteid}")
    List<Map<String,Object>> findAuthor(Integer siteid);

    @Update("update users set pen=#{pen},email=#{email},realname=#{realname},idcard=#{idcard},phone=#{phone} where userid=#{userid}")
    Integer updAuthor(Users users);

    @Select(value = "select money from users")
    List<Users> query();

    @Update(value = "UPDATE `novel`.`users` SET `money`=#{money},`ticket`=#{ticket} WHERE (`userid`=#{userid})")
    int update(@Param("money") Integer money,@Param("ticket") Integer ticket,@Param("userid") Integer userid);
}
