package hxy.inspec.admin.services;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hxy.inspec.admin.dao.AdminUserDao;
import hxy.inspec.admin.dao.AdminUserDao;
import hxy.inspec.admin.po.AdminUser;
import hxy.inspec.admin.po.CusUser;

public class AdminUserService {
	private final static Logger logger = LoggerFactory.getLogger(AdminUserService.class);

	public AdminUser login(String tel) throws IOException {

		// 依据电话号码查询数据库，返回对象，比对是否正确
		logger.info("查询是否存在:" + tel);
		AdminUserDao userDao = new AdminUserDao();
		return userDao.selectUserByPhone(tel);

	}

	public boolean insert(AdminUser user) {
		AdminUserDao userDao = new AdminUserDao();
		try {
			int flag = userDao.insert(user);
			if (flag == 1) {
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public boolean delete(String tel) throws IOException {
		
		AdminUserDao adminUserDao = new AdminUserDao();
		int fa= adminUserDao.delete(tel);
		if (fa==1) {
			return true;
		}
		
		return false;
		
	}
	public List<AdminUser> selectAll() throws IOException {
		AdminUserDao ordersDao = new AdminUserDao();
		List<AdminUser> list = ordersDao.selectAll();
		return list;
	}

}
