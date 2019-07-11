package com.qianqiu.novel.controller;

import com.qianqiu.novel.entity.Weekpush;
import com.qianqiu.novel.service.WeekpushService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("weekpush")
public class WeekpushController {

	@Resource
	WeekpushService service;

	@RequestMapping("addWeekpush")
	public String addWeekpush(Integer bookid,Integer potential,Integer money) {
		//查询已经申请周推的个数
		Integer i = service.getCount();
		if (i < 18) {
			//查询是否已经申请过下一周的周推
			Integer j = service.getbookidCount(bookid);
			if (j == 0) {
				Weekpush weekpush = new Weekpush();
				weekpush.setBookid(bookid);
				weekpush.setPotential(potential);
				weekpush.setMoney(money);
				Integer k = service.add(weekpush);


			System.out.println("添加成功");
			}else {
				System.out.println("添加失败");
			}
		}
		return "redirect:/authorWorks.html";
	}


	@RequestMapping("getbookidCount")
	@ResponseBody
	public Integer getbookidCount(Integer bookid) {
		Integer i = service.getCount();
		if (i < 18) {
			//查询是否已经申请过下一周的周推
			Integer j = service.getbookidCount(bookid);
			if (j == 0) {
				//可以周推
				System.out.println("可以周推");
				return 0;
			}else {
				//已经周推过
				System.out.println("已经周推过");
			return 1;
			}
	}else{
			//周推已满
			System.out.println("周推已满");
		return 2;
	}
	}
}
