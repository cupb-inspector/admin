package hxy.inspec.admin.services;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hxy.inspec.admin.dao.AdminUserDao;
import hxy.inspec.admin.po.AdminUser;

public class AdminUserService {
	private final static Logger logger = LoggerFactory.getLogger(AdminUserService.class);

	public AdminUser login(String tel) {

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

}
