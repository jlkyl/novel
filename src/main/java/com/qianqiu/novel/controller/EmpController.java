package com.qianqiu.novel.controller;


import com.qianqiu.novel.entity.Emps;
import com.qianqiu.novel.service.EmpService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("ep")
public class EmpController {
    @Resource
    EmpService es;

    @RequestMapping("loginB")
    @ResponseBody
    public Boolean loginB(HttpSession session, String uname, String pwd){
        //传入几个参数
        // 将账号密码从前台页面传到后台，调用登录的service方法，返回一个实体对象
        // username为账号，pwd为密码
        Emps e=es.loginB(uname, pwd);
        // 判断（这里只是粗劣的判断而已）
        if(e!=null){
            // 登陆成功 //将users存入session中
            session.setAttribute("emps", e);
            return true;
        }else{
            System.out.println("登陆失败");
            return false;
        }
    }
    @RequestMapping("queryEmp")
    public String queryEmp(){
        es.queryEmp();
        return "";
    }

    @RequestMapping("updateP")
    @ResponseBody
    public Boolean updateP(Emps e){
        es.updateP(e);
        return true;
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/back/login";
    }

    @RequestMapping("add")
    @ResponseBody
    public int add(Emps l,HttpSession session){
        Integer operateeid = ((Emps)session.getAttribute("emps")).getEmpid();
        l.setOperateeid(operateeid);
        return es.add(l);
    }

    @RequestMapping("update")
    @ResponseBody
    public int update(Emps l,HttpSession session){
        Integer operateeid = ((Emps)session.getAttribute("emps")).getEmpid();
        l.setOperateeid(operateeid);
         return es.update(l);
    }

    @RequestMapping("US")
    @ResponseBody
    public int updateState(Emps l){
        return es.US(l);
    }

    @RequestMapping("queryAll")
    @ResponseBody
    public List<Map<String,Object>> queryAll(){
        return es.queryAll();
    }


}
