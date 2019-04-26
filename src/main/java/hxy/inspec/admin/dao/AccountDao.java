package hxy.inspec.admin.dao;


import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hxy.inspec.admin.datasources.DataConnection;
import hxy.inspec.admin.po.Account;



public class AccountDao {
	private final static Logger logger = LoggerFactory.getLogger(AccountDao.class);
	public int insert(Account user) throws IOException {
		SqlSession sqlSession = DataConnection.getSqlSession();
		int flag = sqlSession.insert("Account.insert", user);
		sqlSession.commit();
		sqlSession.close();
		logger.info("插入后结果：" + flag);
		return flag;
	}
	public List<Account> selectAllByTel(String tel) throws IOException {
		// TODO Auto-generated method stub

		SqlSession sqlSession = DataConnection.getSqlSession();
		List<Account> goodsList = sqlSession.selectList("Account.findOrdersByTel", tel);
		logger.info("查询结果条数"+goodsList.size());
	
		sqlSession.commit();
		sqlSession.close();
		return goodsList;
	
	}
	public List<Account> selectAllByStatus(String tel) throws IOException {
		// TODO Auto-generated method stub

		SqlSession sqlSession = DataConnection.getSqlSession();
		List<Account> goodsList = sqlSession.selectList("Account.findAllByStatus", tel);
		logger.info("查询结果条数"+goodsList.size());
	
		sqlSession.commit();
		sqlSession.close();
		return goodsList;
	
	}
	public List<Account> selectAll() throws IOException {

		SqlSession sqlSession = DataConnection.getSqlSession();
		List<Account> goodsList = sqlSession.selectList("Account.selectAll");
		logger.info("查询结果条数"+goodsList.size());
	
		sqlSession.commit();
		sqlSession.close();
		return goodsList;
	}
}
