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

}
