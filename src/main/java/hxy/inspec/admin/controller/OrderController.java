package hxy.inspec.admin.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hxy.inspec.admin.po.AdminUser;
import hxy.inspec.admin.po.CusUser;
import hxy.inspec.admin.po.Inspector;
import hxy.inspec.admin.po.Orders;
import hxy.inspec.admin.services.CusUserService;
import hxy.inspec.admin.services.InspectorService;
import hxy.inspec.admin.services.MailService;
import hxy.inspec.admin.services.OrderService;
import hxy.inspec.admin.util.Configuration;

@Controller
@RequestMapping("/")
public class OrderController {
	private final static Logger logger = LoggerFactory.getLogger(OrderController.class);

	@RequestMapping(value = "/cusInsertOrder", method = RequestMethod.POST)
	public void cusInsertOrder(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		// 获取用户是否登录
		AdminUser user = (AdminUser) request.getSession().getAttribute("user");
		int resultCode = -1;
		if (user != null) {
			// https://blog.csdn.net/u013230511/article/details/48314491
			String excdate = null;
			String facname = null;
			String facaddress = null;
			String facman = null;
			String factel = null;
			String profile = null;
			String file = null;
			String reports = null;
			int status = 0;
			String fee = null;
			String cost = null;
			String otherCost = null;
			String profit = null;
			String goods = null;
			boolean flag = false;
			try {
				excdate = request.getParameter("excdate").trim();// 执行日期
				facname = request.getParameter("facname").trim();// 工厂名称
				facaddress = request.getParameter("facaddress").trim();// 工厂地址
				facman = request.getParameter("facman").trim();// 联系人
				factel = request.getParameter("factel").trim();// 联系人电话
				profile = request.getParameter("profile").trim();// 备注
				goods = request.getParameter("goods").trim();// 备注
//			file = request.getParameter("file").trim();// 附件
				flag = true;
			} catch (NullPointerException e) {
				logger.warn("传入的是一个null");
			}
			if (flag) {
//			获取时间
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
				String date = dateFormat.format(now);
				status = Configuration.BILL_ASSIGNED;// 1.提交成功 2.正在验货员正在接单 3.验货员已经出发，4.报告撰写中，5，已完成
				Orders order = new Orders();
				order.setCusId(user.getAdminTel());
				order.setCost(cost);
				order.setDate(date);
				order.setExcedate(excdate);
				order.setFactoryaddress(facaddress);
				order.setFactoryman(facman);
				order.setFactoryname(facname);
				order.setFactorytel(factel);
				order.setFile(file);
				order.setReportfile(reports);
				order.setProfile(profile);
				order.setStatus(status);
				order.setGoods(goods);
				OrderService orderService = new OrderService();
				if (orderService.insert(order)) {
					resultCode = 200;
				} else {
					resultCode = 500;
				}
			} else {
				resultCode = 400;// bad request
			}
		} else {
			resultCode = 604;// 返回没有数据
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

	@RequestMapping(value = "new-orders-details", method = RequestMethod.GET)
	public String cusSelectOrder(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		try {
			// 返回页面防止出现中文乱码
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 获取用户是否登录
		AdminUser user = (AdminUser) request.getSession().getAttribute("user");

		String ordersId = request.getParameter("id");
		logger.info("id：" + ordersId);

		// 依据id查询数据库得知数据库的订单详细信息

		OrderService orderService = new OrderService();
		Orders orders = null;
		CusUser cusUser = null;
		try {
			orders = orderService.selectOrderById(ordersId);

			// 依据订单查询订单的客户信息
			CusUserService cusUserService = new CusUserService();
			cusUser = cusUserService.selectUserById(orders.getCusId());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (orders != null && cusUser != null) {
			model.addAttribute("status", orders.getStatusString());
			model.addAttribute("ordersId", ordersId);
			model.addAttribute("goods", orders.getGoods());
			model.addAttribute("custel", orders.getCusId());
			model.addAttribute("exceData", orders.getExcedate());
			String inspectTel = orders.getQualId();
			if ("null".equals(inspectTel)) {
				model.addAttribute("inspec", "请填写质检员号码");
			} else
				model.addAttribute("inspec", orders.getQualId());

			model.addAttribute("exceData", orders.getExcedate());
			model.addAttribute("factoyName", orders.getFactoryname());
			model.addAttribute("facAddress", orders.getFactoryaddress());
			model.addAttribute("facMan", orders.getFactoryman());
			model.addAttribute("facTel", orders.getFactorytel());
			model.addAttribute("cusName", cusUser.getCusname());
			model.addAttribute("city", cusUser.getCity());
			model.addAttribute("email", cusUser.getEmail());
			model.addAttribute("cusOrders", cusUser.getCusOrders());
			model.addAttribute("money", cusUser.getCusMoney());
			model.addAttribute("integral", cusUser.getCusgrade());
		}

		return "orders/new-orders-details";

	}

	@RequestMapping(value = "/details-orders2", method = RequestMethod.GET)
	public String cusSelectOrder2(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		try {
			// 返回页面防止出现中文乱码
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 获取用户是否登录
		AdminUser user = (AdminUser) request.getSession().getAttribute("user");
		String ordersId = request.getParameter("id");
		logger.info("id：" + ordersId);

		// 依据id查询数据库得知数据库的订单详细信息
		OrderService orderService = new OrderService();
		Orders orders = null;
		try {
			orders = orderService.selectOrderById(ordersId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (orders != null) {
			model.addAttribute("status", orders.getStatusString());
			model.addAttribute("ordersId", ordersId);
			model.addAttribute("goods", orders.getGoods());
			model.addAttribute("custel", orders.getCusId());
			model.addAttribute("exceData", orders.getExcedate());
			String inspectTel = orders.getQualId();
			if ("null".equals(inspectTel)) {
				model.addAttribute("inspec", "请填写质检员号码");
			} else
				model.addAttribute("inspec", orders.getQualId());
			model.addAttribute("exceData", orders.getExcedate());
			model.addAttribute("factoyName", orders.getFactoryname());
			model.addAttribute("facAddress", orders.getFactoryaddress());
			model.addAttribute("facMan", orders.getFactoryman());
			model.addAttribute("facTel", orders.getFactorytel());
			model.addAttribute("date", orders.getDate());
//			model.addAttribute("", orders.getExcedate());

			// 通过订单的验货员信息与用户信息找到两个人资料
			CusUserService cusUserService = new CusUserService();
			CusUser cusUser = cusUserService.selectUserById(orders.getCusId());

			if (cusUser != null) {
				model.addAttribute("culName", cusUser.getCusname());
				model.addAttribute("culAddress", cusUser.getCusaddress());
				model.addAttribute("culEmail", cusUser.getEmail());
				model.addAttribute("culMoney", cusUser.getCusMoney());
				model.addAttribute("culGrade", cusUser.getCustrade());
				model.addAttribute("culOrders", cusUser.getCusOrders());
			}

			InspectorService inspectorService = new InspectorService();
			Inspector inspector = inspectorService.findInspectorById(orders.getQualId());
			if (inspector != null) {
				model.addAttribute("inspName", inspector.getUserName());
				model.addAttribute("inspAddress", inspector.getAddress());
				model.addAttribute("inspTel", inspector.getUserTel());
				model.addAttribute("inspOrders", inspector.getOrders());
				model.addAttribute("inspMoney", inspector.getMoney());
				model.addAttribute("integral", inspector.getIntegral());

			}

		}

		return "orders/orders-details2";

	}
	
//	@RequestMapping(value = "/details-orders-ajax", method = RequestMethod.GET)
//	public String cusSelectOrderAjax(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
//		try {
//			// 返回页面防止出现中文乱码
//			request.setCharacterEncoding("UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		// 获取用户是否登录
//		AdminUser user = (AdminUser) request.getSession().getAttribute("user");
//		String ordersId = request.getParameter("id");
//		logger.info("id：" + ordersId);
//		return "orders/orders-details-ajax";
//	}
	

	@RequestMapping(value = "/orders-details-report-conform", method = RequestMethod.GET)
	public String ordersDetailsReportConform(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		try {
			// 返回页面防止出现中文乱码
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 获取用户是否登录
		AdminUser user = (AdminUser) request.getSession().getAttribute("user");

		String ordersId = request.getParameter("id");
		logger.info("id：" + ordersId);

		// 依据id查询数据库得知数据库的订单详细信息

		OrderService orderService = new OrderService();
		Orders orders = null;
		try {
			orders = orderService.selectOrderById(ordersId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (orders != null) {
			model.addAttribute("status", orders.getStatusString());
			model.addAttribute("ordersId", ordersId);
			model.addAttribute("goods", orders.getGoods());
			model.addAttribute("custel", orders.getCusId());
			model.addAttribute("exceData", orders.getExcedate());
			String inspectTel = orders.getQualId();
			if ("null".equals(inspectTel)) {
				model.addAttribute("inspec", "请填写质检员号码");
			} else
				model.addAttribute("inspec", orders.getQualId());

			model.addAttribute("exceData", orders.getExcedate());
			model.addAttribute("factoyName", orders.getFactoryname());
			model.addAttribute("facAddress", orders.getFactoryaddress());
			model.addAttribute("facMan", orders.getFactoryman());
			model.addAttribute("facTel", orders.getFactorytel());
			model.addAttribute("report", orders.getReportfile());
			model.addAttribute("reportuuid", orders.getReportfileuuid());

			// 通过订单的验货员信息与用户信息找到两个人资料
			CusUserService cusUserService = new CusUserService();
			CusUser cusUser = cusUserService.findCusUserByTel(orders.getCusId());

			if (cusUser != null) {
				model.addAttribute("culName", cusUser.getCusname());
				model.addAttribute("culAddress", cusUser.getCusaddress());
				model.addAttribute("culEmail", cusUser.getEmail());
				model.addAttribute("culMoney", cusUser.getCusMoney());
				model.addAttribute("culGrade", cusUser.getCustrade());
				model.addAttribute("culOrders", cusUser.getCusOrders());
			} else
				logger.error("用户信息异常！" + orders.getCusId());

			InspectorService inspectorService = new InspectorService();
			Inspector inspector = inspectorService.findInspectorByTel(orders.getQualId());
			if (inspector != null) {
				logger.info("inspector:" + inspector);
				model.addAttribute("inspName", inspector.getUserName());
				model.addAttribute("inspAddress", inspector.getAddress());
				model.addAttribute("inspTel", inspector.getUserTel());
				model.addAttribute("inspOrders", inspector.getOrders());
				model.addAttribute("inspMoney", inspector.getMoney());
				model.addAttribute("integral", inspector.getIntegral());

			} else
				logger.error("质检员信息异常！" + orders.getQualId());

		}

		return "orders/orders-details-report-conform";

	}

	@RequestMapping(value = "/allotOrder", method = RequestMethod.POST)
	public void allotOrder(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		// 获取用户是否登录
		AdminUser user = (AdminUser) request.getSession().getAttribute("user");
		int resultCode = -1;
		if (user != null) {
			// https://blog.csdn.net/u013230511/article/details/48314491
			String inpectTel = null;
			String fee = null;
			int status = 0;
			String orderId = null;
			boolean flag = true;
			try {
				inpectTel = request.getParameter("inpectTel").trim();// 执行日期
				fee = request.getParameter("fee").trim();// 工厂名称
				orderId = request.getParameter("id").trim();// 工厂名称
				flag = true;
			} catch (NullPointerException e) {
				logger.warn("传入的是一个null");
			}
			if (flag) {
//			获取时间
				Date now = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");// 可以方便地修改日期格式
				String date = dateFormat.format(now);
				status = Configuration.BILL_ASSIGNING;// 1.提交成功 2.正在验货员正在接单 3.验货员已经出发，4.报告撰写中，5，已完成
				Orders order = new Orders();
				order.setFee(fee);
				order.setQualId(inpectTel);
				order.setOrderid(orderId);
				order.setStatus(status);

				OrderService orderService = new OrderService();
				if (orderService.updateInspect(order)) {
					resultCode = 200;
				} else {
					resultCode = 500;
				}
			} else {
				resultCode = 400;// bad request
			}
		} else {
			resultCode = 604;// 返回没有数据
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

	@RequestMapping(value = "/assign", method = RequestMethod.POST)
	public void assignOrders(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		// 获取用户是否登录
		AdminUser user = (AdminUser) request.getSession().getAttribute("user");
		int resultCode = -1;
		if (user != null) {
			boolean flag = false;
			String id = null;
			String qualId = null;
			try {
				id = request.getParameter("id").trim();// 订单号
				qualId = request.getParameter("tel").trim();// 质检员的id
				logger.info("传入两个参数" + id + "\t" + qualId);
				flag = true;
			} catch (NullPointerException e) {
				logger.warn("传入的是一个null");
			}
			if (flag) {
				Orders order = new Orders();
				order.setQualId(qualId);
				order.setOrderid(id);
				order.setStatus(Configuration.BILL_ASSIGNING);// 已分配

//			为该用户更新订单，依据订单的id查找订单，修改质检员的电话号码
				OrderService orderService = new OrderService();
				if (orderService.assignOrders(order)) {
					resultCode = 200;
				
				} else {
					resultCode = 500;
				}
				;
			} else {

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

}
