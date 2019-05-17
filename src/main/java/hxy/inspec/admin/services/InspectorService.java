package hxy.inspec.admin.services;

import java.io.IOException;
import java.util.List;

import hxy.inspec.admin.dao.InspectorDao;
import hxy.inspec.admin.po.Inspector;

public class InspectorService {
	public List<Inspector> selectAll() throws IOException {
		InspectorDao ordersDao = new InspectorDao();
		List<Inspector> list = ordersDao.selectAll();
		return list;
	}
	
//	查找质检员信息
	public Inspector findInspectorByTel(String tel) {
		
		InspectorDao ordersDao = new InspectorDao();
		
		Inspector inspector = ordersDao.findInspectorByTel(tel);
		
		return inspector;
		
	}
	
//	查找质检员信息
	public Inspector findInspectorById(String id) {
		
		InspectorDao ordersDao = new InspectorDao();
		
		Inspector inspector = ordersDao.findInspectorById(id);
		
		return inspector;
		
	}
	

}
