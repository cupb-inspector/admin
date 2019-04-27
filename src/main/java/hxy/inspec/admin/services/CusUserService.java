package hxy.inspec.admin.services;

import java.io.IOException;
import java.util.List;

import hxy.inspec.admin.dao.CusUserDao;
import hxy.inspec.admin.dao.InspectorDao;
import hxy.inspec.admin.po.CusUser;
import hxy.inspec.admin.po.Inspector;

public class CusUserService {
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

	public int update(CusUser cusUser) throws IOException {
		// TODO Auto-generated method stub
		CusUserDao cusUserDao = new CusUserDao();
		return cusUserDao.update(cusUser);
	}
	

}
