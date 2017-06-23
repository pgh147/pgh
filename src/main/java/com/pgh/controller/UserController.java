package com.pgh.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.pgh.quartz.AccessTokenTaker;
import com.pgh.util.HttpUtils;
import com.pgh.vo.AccessToke;

@Controller  
@RequestMapping("/user") 
public class UserController {
	@Resource
	public AccessTokenTaker accessTokeTaker;
	
	@RequestMapping(value="page",method = RequestMethod.GET)  
	@ResponseBody
	public String page(HttpServletRequest request, HttpServletResponse response) {
		return "/WEB-INF/views/subpage/user.html";
	}
	
	@RequestMapping(value="userInfo.json",method = RequestMethod.GET)  
	@ResponseBody
	public Map<String,Object> UserInfo(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String,Object>();
		AccessToke u2 = JSON.parseObject(accessTokeTaker.getNew(),AccessToke.class);   
		String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+u2.getAccess_token();
		Map<String,String> params = new HashMap<String,String>();
		params.put("contentType", "application/json");
		String result = HttpUtils.sendGet(url, "utf8");
		map.put("result", JSON.parseObject(result));
		return map;
	}
}
