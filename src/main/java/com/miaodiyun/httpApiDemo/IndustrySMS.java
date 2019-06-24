package com.miaodiyun.httpApiDemo;

import java.net.URLEncoder;

import com.miaodiyun.httpApiDemo.common.Config;
import com.miaodiyun.httpApiDemo.common.HttpUtil;
import com.qianqiu.novel.utils.Util;

/**
 * 验证码通知短信接口
 * 
 * @ClassName: IndustrySMS
 * @Description: 验证码通知短信接口
 *
 */
public class IndustrySMS
{
	private static String operation = "/industrySMS/sendSMS";

	private static String accountSid = Config.ACCOUNT_SID;
	private static String templateid = "899234068";
	private static String param = Util.createRandomVcode();
	
	/**
	 * 验证码通知短信
	 */
	public static String execute(String to)
	{
		String tmpSmsContent = null;
	    try{
	      tmpSmsContent = URLEncoder.encode(templateid, "UTF-8");
	    }catch(Exception e){
	      
	    }
	    String url = Config.BASE_URL + operation;
	    String body = "accountSid=" + accountSid + "&to=" + to + "&templateid=" + tmpSmsContent
	        + "&param=" + param + HttpUtil.createCommonParam();

	    // 提交请求
	    String result = HttpUtil.post(url, body);
	    return param;
	}
}
