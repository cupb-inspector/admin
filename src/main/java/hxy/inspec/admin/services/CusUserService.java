package hxy.inspec.admin.services;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hxy.inspec.admin.controller.AccountController;
import hxy.inspec.admin.dao.CusUserDao;
import hxy.inspec.admin.dao.InspectorDao;
import hxy.inspec.admin.po.CusUser;
import hxy.inspec.admin.po.Inspector;

public class CusUserService {
	private final static Logger logger = LoggerFactory.getLogger(CusUserService.class);
	public List<CusUser> selectAll() throws IOException {
		CusUserDao ordersDao = new CusUserDao();
		List<CusUser> list = ordersDao.selectAll();
		return list;
	}

//	查找质检员信息
	public CusUser findCusUserByTel(String tel) {

		CusUserDao ordersDao = new CusUserDao();

		CusUser inspector = ordersDao.findCusUserByTel(tel);

		return inspector;

	}

	public CusUser selectUserById(String id) {
		// TODO Auto-generated method stub
		// 依据电话号码查询数据库，返回对象，比对是否正确
		logger.info("查询是否存在:" + id);
		CusUserDao userDao = new CusUserDao();
		return userDao.selectUserById(id);

	}

	public int update(CusUser cusUser) throws IOException {
		// TODO Auto-generated method stub
		CusUserDao cusUserDao = new CusUserDao();
		return cusUserDao.update(cusUser);
	}

}
