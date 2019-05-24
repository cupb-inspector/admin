package hxy.inspec.admin.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.core.config.Order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hxy.inspec.admin.dao.OrdersDao;
import hxy.inspec.admin.po.Inspector;
import hxy.inspec.admin.po.Orders;

public class OrderService {

	private final static Logger logger = LoggerFactory.getLogger(OrderService.class);

	// 插入订单
	public boolean insert(Orders order) {
		OrdersDao orderDao = new OrdersDao();
		try {
			int flag = orderDao.insert(order);
			if (flag == 1) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<Orders> selectAllByTel(String tel) throws IOException {
		OrdersDao ordersDao = new OrdersDao();
		List<Orders> list = ordersDao.selectAllByTel(tel);
		return list;
	}

	public List<Orders> selectAll() throws IOException {
		OrdersDao ordersDao = new OrdersDao();
		List<Orders> list = ordersDao.selectAll();
		return list;
	}
	
	
	
	public List<Orders> findUserByQualtelAndStatus(Orders orders) throws IOException {
		OrdersDao ordersDao = new OrdersDao();
		List<Orders> list = ordersDao.selectAll();
		return list;
	}

	public Orders selectOrderById(String ordersId) throws IOException {
		OrdersDao ordersDao = new OrdersDao();
		Orders list = ordersDao.selectAllById(ordersId);
		return list;
	}
	
	
	

	public  List<Orders> selectOrdersByStatus(String status) throws IOException {
		logger.info("查询状态为"+status+"的订单");
		OrdersDao ordersDao = new OrdersDao();
		 List<Orders> list = ordersDao.selectOrdersByStatus(status);
		return list;
	}
	
	public  List<Orders> selectOrdersByDateAndStatus(HashMap<String, Object> status) throws IOException {
		OrdersDao ordersDao = new OrdersDao();
		 List<Orders> list = ordersDao.selectOrdersByDateAndStatus(status);
		return list;
	}
	//查询今天和明天的订单
	public  List<Orders> selectTodayTomorrowOrders() throws IOException {
		Date date =new Date();
		DateFormat df1 = DateFormat.getDateInstance();//日期格式，精确到日,这个会有一点问题,如果是2019-5-17就不会是2019-05-17
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
		String today = formatter.format(date);
		logger.info("今日:"+today);
		HashMap<String, Object> status = new HashMap<>();
		status.put("excedate", today);
		status.put("status", "5");
		status.put("start", 0);
		status.put("size", 5);//查询最近5条

		OrdersDao ordersDao = new OrdersDao();
		 List<Orders> list = ordersDao.selectOrdersByDateAndStatus(status);
		return list;
	}
	

	public  List<Orders> selectOrdersByStatusJudge(HashMap<String, Object> status) throws IOException {
		OrdersDao ordersDao = new OrdersDao();
		 List<Orders> list = ordersDao.selectOrdersByStatusJudge(status);
		return list;
	}
	
	

	public boolean updateInspect(Orders order) {
		OrdersDao ordersDao = new OrdersDao();
		int flag = ordersDao.updateInspect(order);
		if (flag == 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateInspector(Orders order) {
		OrdersDao ordersDao = new OrdersDao();
		int flag = ordersDao.updateInspector(order);
		if (flag == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean assignOrders(Orders order) {
		OrdersDao ordersDao = new OrdersDao();
		int flag = ordersDao.updateInspector(order);
		if (flag == 1) {
			//新建线程发送邮件到质检员
			new Thread(new SendEmailRunnable(order)).start();
			return true;
		} else {
			return false;
		}
	}

	public void updateReport(Orders orders) {
		// TODO Auto-generated method stub
		
	}

}



class SendEmailRunnable implements Runnable{

	private final static Logger logger = LoggerFactory.getLogger(SendEmailRunnable.class);
	Orders orders;
	SendEmailRunnable(Orders orders){
		this.orders=orders;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		InspectorService inspectorService = new InspectorService();
		Inspector inspector = inspectorService.findInspectorById(orders.getQualId());
		if (inspector != null && !"null".equals(inspector)) {
			// 发送邮件给质检员
			MailService mailService = new MailService();
			mailService.sendMailToInspector(inspector);
		} else {
			logger.error("该质检员不存在：" + orders.getQualId());
		}
	}
	
}
