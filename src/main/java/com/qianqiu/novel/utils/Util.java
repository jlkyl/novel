package com.qianqiu.novel.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;


public class Util {
	//查找数组中指定元素的个数
	public static int getNums(String str,String flag){
		Pattern rex = Pattern.compile(flag);//创建正则表达之对象匹配flag
		Matcher m = rex.matcher(str);// 匹配str字符串的匹配器
		int nums = 0;// 计数器，看匹配上了几个
		while(m.find()){ // find()方法从字符串中匹配flag 找到返回true
			nums += 1; //找到1个 计数器值加 1
		}
		return nums;
	}
	//去除数组中的重复元素
	public static Object[] ifRepeat(Object[] strs){
		Set set = new HashSet();
		for(int i = 0 ; i<strs.length ; i++){
			set.add(strs[i]);
		}
		return set.toArray();
	}
	//在数组中删除指定数量的元素
	public static String remove(String str,String flag,Integer num){
		String[] strs = str.split("-");
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < strs.length; i++) {
			list.add(strs[i]);
		}
		for(int i=0 ; i<num; i++){
			list.remove(flag);
		}
		return StringUtils.join(list.toArray(),"-")+"-";
	}
	/**
	 * 随机生成6位随机验证码
	 * @return
	 */
	public static String createRandomVcode(){
		//验证码
		String vcode = "";
		for (int i = 0; i < 6; i++) {
			vcode = vcode + (int)(Math.random() * 9);
		}
		return vcode;
	}
	/**
	 * 发送邮箱验证码
	 * @param eamil
	 * @return
	 */
	public static String sendEamilCode(String email) {
		HtmlEmail send = new HtmlEmail();
		// 获取随机验证码
		String resultCode = createRandomVcode();
		try {
			send.setHostName("smtp.qq.com");
			send.setSmtpPort(465); //端口号
			send.setSSLOnConnect(true); //开启SSL加密
			send.setCharset("utf-8");
			send.addTo(email); //接收者的QQEamil
			send.setFrom("1073220986@qq.com", "渣渣辉"); //第一个参数是发送者的QQEamil   第二个参数是发送者QQ昵称
			send.setAuthentication("1073220986@qq.com", "rxoqqguylqnpbcaa"); //第一个参数是发送者的QQEamil 第二个参数是刚刚获取的授权码
			send.setSubject("渣渣辉特给您送上验证码"); //Eamil的标题 
			send.setMsg("Hello!欢迎大大光临，特此送上验证:   " + resultCode + "   请大大签收"); //Eamil的内容
			send.send(); //发送
		} catch (EmailException e) {
			e.printStackTrace();
		}
		return resultCode;//同等验证码
	}
}
	
