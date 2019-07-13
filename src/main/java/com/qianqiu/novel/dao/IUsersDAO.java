package com.qianqiu.novel.dao;

import com.qianqiu.novel.entity.Users;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface IUsersDAO {

    @Select(value = "SELECT a.attentuser,b.bookname,b.cover,bk.typename,b.state,COALESCE(u.head,0) head,u.author,u.pen,u.sign,b.details,COUNT(bookid) as kd ,\n" +
            "(SELECT SUM(wordnum) from chapters) as mo,(SELECT TIMESTAMPDIFF(DAY,b.createtime,NOW())) as ks,\n" +
            "(SELECT chapterid from chapters where state =0 AND rollid in (SELECT rollid from roles WHERE bookid = b.bookid)order by \n" +
            "chapternum desc LIMIT 1 ) chapterid,(SELECT chaptername from chapters where state =0 AND rollid in\n" +
            "(SELECT rollid from roles WHERE bookid = b.bookid)order by chapternum desc LIMIT 1 ) chaptername from users u \n" +
            "join books b \n" +
            "on b.userid = u.userid \n" +
            "JOIN booktype bk\n" +
            "on b.typeid = bk.typeid\n" +
            "join attention a\n" +
            "ON a.userid = u.userid")
    List<Map<Users,Object>> querys();

    @Select("select * from users where username=#{username} and password=#{password}")
    public Users unamelogin(@Param("username") String username, @Param("password") String password);

    @Select("select * from users where email=#{email} and password=#{password}")
    public Users emaillogin(@Param("email") String username, @Param("password") String password);

    @Select("select * from users where phone=#{phone} and password=#{password}")
    public Users plogin(@Param("phone") String username, @Param("password") String password);

    @Select("select * from users where phone=#{phone}")
    public Users yzmlogin(@Param("phone") String phone);

    @Select("<script>" +
            "select count(*) from users" +
            "<where>" +
            " and siteid is null" +
            "<if test=\"username!=null and username!=''\">" +
            " and username like concat('%',#{username},'%') " +
            "</if>" +
            "<if test=\"phone!=null and phone!=''\">" +
            " and username like concat('%',#{phone},'%') " +
            "</if>" +
            "<if test=\"author!=null and author!=10\">" +
            " and author=#{author} " +
            "</if>" +
            "</where>" +
            "</script>")
    public Integer getCount(@Param("username")String username,@Param("phone")String phone,@Param("author")Integer author);

    @Select("<script>select *,(select COALESCE(sum(expmoney),0)/1000*0.5-COALESCE((select sum(expmoney) from expenses where userid=u.userid and exptypeid=4),0) from expenses where bookid in (select bookid from books where userid=u.userid)) expmoney from users u " +
            "<where>" +
            " and siteid is null" +
            "<if test=\"username!=null and username!=''\">" +
            " and username like concat('%',#{username},'%') " +
            "</if>" +
            "<if test=\"phone!=null and phone!=''\">" +
            " and username like concat('%',#{phone},'%') " +
            "</if>" +
            "<if test=\"author!=null and author!=10\">" +
            " and author=#{author} " +
            "</if>" +
            "</where>" +
            " order by vip desc" +
            "<if test=\"offset!=null and pageSize!=null\">\n" +
            "   limit #{offset},#{pageSize}\n" +
            "</if></script>")
    public List<Map<String,Object>> listAll(@Param("offset")Integer offset,@Param("pageSize")Integer pageSize,@Param("username")String username,@Param("phone")String phone,@Param("author")Integer author);

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

    @Select("select u.*,(select count(*) from books where userid=u.userid) nums,(select COALESCE(sum(expmoney),0)/1000*0.5-COALESCE((select sum(expmoney) from expenses where userid=u.userid and exptypeid=4),0) from expenses where bookid in (select bookid from books where userid=u.userid)) expmoney from users u where author=2 and siteid=#{siteid}")
    List<Map<String,Object>> findAuthor(Integer siteid);

    @Update("update users set pen=#{pen},email=#{email},realname=#{realname},idcard=#{idcard},phone=#{phone} where userid=#{userid}")
    Integer updAuthor(Users users);

    @Update("<script>" +
            "UPDATE `novel`.`users` " +
            "<set>" +
            "<if test=\"money!=null\">" +
            " `money`=#{money}, " +
            "</if>" +
            "<if test=\"ticked!=null\">" +
            " `ticked`=#{ticked}, " +
            "</if>" +
            "</set>" +
            " WHERE (`userid`=#{userid})" +
            "</script>")
    int update(@Param("money") Integer money,@Param("ticked") Integer ticked,@Param("userid") Integer userid);
    @Update("UPDATE `novel`.`users` SET `username`=#{username},`sex`=#{sex},`sign`=#{sign} WHERE (`userid`=#{userid})")
    public int updateUser(Users users);

    @Update("UPDATE `novel`.`users` SET `head`=#{head} WHERE (`userid`=#{userid})")
    public int updhead(Users users);

    @Select("select * from users where userid = #{userid}")
    Users findByid(Integer userid);

    @Select("select ticked,COALESCE((select sum(nums) from votes where userid=#{userid}),0) voteticket,COALESCE((select sum(nums) from votes where userid=#{userid} and votetime between getMonday(CURDATE()) and getSunday(CURDATE())),0) weekticket from users where userid=#{userid}")
    Map<String,Object> getTicket(Integer userid);
    @Update("update users set vip = #{param1} where userid = #{param2}")
    Integer updVip(Integer vip,Integer userid);

    @Select("select * from users where userid = #{userid}")
    public Users queryAuthorById (Integer userid);

    @Select("select (select COALESCE(sum(expmoney),0)/1000*0.5 from expenses where " +
            "bookid in (select bookid from books where userid=#{userid})) money," +
            "(select COALESCE(sum(expmoney),0) from expenses where userid=#{userid} and exptypeid=4) expmoney")
    Map<String,Object> queryIncome(Integer userid);
}
