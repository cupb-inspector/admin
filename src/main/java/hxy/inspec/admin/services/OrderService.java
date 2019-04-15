package hxy.inspec.admin.services;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hxy.inspec.admin.dao.OrdersDao;
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

	public Orders selectAllById(String ordersId) throws IOException {
		OrdersDao ordersDao = new OrdersDao();
		Orders list = ordersDao.selectAllById(ordersId);
		return list;
	}
	
	
	

	public  List<Orders> selectOrdersByStatus(String status) throws IOException {
		OrdersDao ordersDao = new OrdersDao();
		 List<Orders> list = ordersDao.selectOrdersByStatus(status);
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

	public void updateReport(Orders orders) {
		// TODO Auto-generated method stub
		
	}

}
