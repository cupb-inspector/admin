package hxy.inspec.admin.services;



import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hxy.inspec.admin.dao.AccountDao;
import hxy.inspec.admin.po.Account;


public class AccountService {
	

	private final static Logger logger = LoggerFactory.getLogger(AccountService.class);

	// 插入订单
	public boolean insert(Account order) throws IOException {
		AccountDao orderDao = new AccountDao();
	
			int flag = orderDao.insert(order);
			if (flag == 1) {
				return true;
			}
		
		return false;
	}
	public List<Account> selectAllByTel(String tel) throws IOException {
		AccountDao orderDao = new AccountDao();
		List<Account> list = orderDao.selectAllByTel(tel);
		return list;
	}
	public List<Account> selectAll() throws IOException {
		AccountDao orderDao = new AccountDao();
		List<Account> list = orderDao.selectAll();
		return list;
	}
	public List<Account> selectAllByStatus(String tel) throws IOException {
		AccountDao orderDao = new AccountDao();
		List<Account> list = orderDao.selectAllByStatus(tel);
		return list;
	}
	public Account selectAccountById(String id) throws IOException {
		// TODO Auto-generated method stub
		logger.info("查询账户流水号"+id);
		AccountDao orderDao = new AccountDao();
		Account list = orderDao.selectAccountById(id);
		return list;
	}
	public int updateStatus(Account account) throws IOException {
		// TODO Auto-generated method stub
		logger.info("更新订单状态");
		AccountDao orderDao = new AccountDao();
		int list = orderDao.updateStatus(account);
		return list;
	}

}
