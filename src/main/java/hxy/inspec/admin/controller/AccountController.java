package hxy.inspec.admin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.javassist.expr.NewArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hxy.inspec.admin.po.Account;
import hxy.inspec.admin.po.AdminUser;
import hxy.inspec.admin.po.CusUser;
import hxy.inspec.admin.services.AccountService;
import hxy.inspec.admin.services.CusUserService;

@Controller
@RequestMapping("/")
public class AccountController {
	private final static Logger logger = LoggerFactory.getLogger(AccountController.class);

	@RequestMapping(value = "/payment-details", method = RequestMethod.GET)
	public String cusInsertOrder(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		// 获取用户是否登录
		AdminUser user = (AdminUser) request.getSession().getAttribute("user");
		if (user != null) {
			String id = request.getParameter("id");
			AccountService accountService = new AccountService();
			try {
				Account account = accountService.selectAccountById(id);

				model.addAttribute("photo", account.getFileUuid());
				model.addAttribute("ordersId", account.getId());
				model.addAttribute("status", account.getTypeString());
				model.addAttribute("value", account.getValue());
				model.addAttribute("date", account.getTime());

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "finance/payment-details";
	}
	
	
	@RequestMapping(value = "/account-verify", method = RequestMethod.POST)
	public void accountVerify(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		// 获取用户是否登录
		AdminUser user = (AdminUser) request.getSession().getAttribute("user");
		int resultCode = 0;
		if (user != null) {
			String id = request.getParameter("id");
			String flag = request.getParameter("flag");
			AccountService accountService = new AccountService();
			
			try {
				boolean f =false;
				Account account2 = new Account();
				if ("conform".equals(flag)) {
					account2 = accountService.selectAccountById(id);
					account2.setStatus("1");
					String userTel = account2.getUserTel();
					CusUserService ca = new CusUserService();
					CusUser cusUser =  ca.findCusUserByTel(userTel);
					String op = account2.getOperate().trim();
					float a =-1;
					if ("add".equals(op)) {
						 a = Float.parseFloat(cusUser.getCusMoney())+Float.parseFloat(account2.getValue());
						f=true;
						
					}else if ("minus".equals(op)) {
						float money =Float.parseFloat(cusUser.getCusMoney());
						float value = Float.parseFloat(account2.getValue());
						if (money>=value) {
							 a = money-value;
						
							f=true;
						}else
							resultCode = 699;//逻辑错误
						
				
					}
					
					if(a!=-1) {
						cusUser.setCusMoney(String.valueOf(a));
						 if (1==ca.update(cusUser)) {
							resultCode=200;
						}else
							resultCode=599;//数据库操作失败
					}
				}else if ("cancel".equals(flag)) {
					logger.info("管理员拒绝通过充值");
					account2.setId(id);
					account2.setStatus("2");
					f=true;
					
				}
				
			
			if (f==true&&1==accountService.updateStatus(account2)) {
				resultCode=200;
			} else
				resultCode=599;//数据库操作失败


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				resultCode=598;//数据库内部错误
			}
		}
		logger.info("返回注册信息");
		org.json.JSONObject user_data = new org.json.JSONObject();
		user_data.put("resultCode", resultCode);
		user_data.put("key2", "today4");
		user_data.put("key3", "today2");
		String jsonStr2 = user_data.toString();
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().append(jsonStr2);
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
	}
	
}
