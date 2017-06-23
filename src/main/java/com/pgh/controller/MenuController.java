package com.pgh.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;
import com.pgh.quartz.AccessTokenTaker;
import com.pgh.util.MenuUtil;
import com.pgh.vo.AccessToke;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/menu")
public class MenuController {
	@Resource
	public AccessTokenTaker accessTokeTaker;
	/**
	 * 创建菜单
	 * */
	@RequestMapping("/createMenu")
	@ResponseBody
	public Map<String,Object> createMenu(){
		Map<String,Object> map = new HashMap<String,Object>();
		String acc = accessTokeTaker.getNew();
		AccessToke u2 = JSON.parseObject(acc,AccessToke.class); 
		Object result = MenuUtil.createMenu(MenuUtil.getMenu(),u2.getAccess_token());
//		if(status==0){
//			System.out.println("菜单创建成功！");
//		}else{
//			System.out.println("菜单创建失败！");
//		}
		map.put("result", result);
		map.put("acc_toke", acc);
		return map;
	}
	
}
