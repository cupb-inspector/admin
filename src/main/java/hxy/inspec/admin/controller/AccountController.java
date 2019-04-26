package hxy.inspec.admin.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hxy.inspec.admin.po.AdminUser;



@Controller
@RequestMapping("/")
public class AccountController {
	private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

	@RequestMapping(value = "/account-select", method = RequestMethod.POST)
	public void cusInsertOrder(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		// 获取用户是否登录
		AdminUser user = (AdminUser) request.getSession().getAttribute("user");
		int resultCode = 0;
		if (user != null) {
			
			
			
			
			
			
		}
		// 返回信息
		org.json.JSONObject user_data = new org.json.JSONObject();
		user_data.put("resultCode", resultCode);
		user_data.put("key2", "today4");
		user_data.put("key3", "today2");
		String jsonStr2 = user_data.toString();
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().append(jsonStr2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
