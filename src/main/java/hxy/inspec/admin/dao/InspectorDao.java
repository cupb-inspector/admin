package hxy.inspec.admin.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hxy.inspec.admin.datasources.DataConnection;
import hxy.inspec.admin.po.Inspector;
import hxy.inspec.admin.po.Orders;

public class InspectorDao {

	private final static Logger logger = LoggerFactory.getLogger(InspectorDao.class);
	public List<Inspector> selectAll() throws IOException{
		
		SqlSession sqlSession = DataConnection.getSqlSession();
		List<Inspector> goodsList = sqlSession.selectList("Inspector.selectAll");
		logger.info("质检员查询结果条数"+goodsList.size());
		sqlSession.commit();
		sqlSession.close();
		return goodsList;
		
		
	}
	public Inspector findInspectorByTel(String tel) {
		// TODO Auto-generated method stub
		SqlSession sqlSession = null;
		try {
			sqlSession = DataConnection.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Inspector user = sqlSession.selectOne("Inspector.findUserByNumber", tel);
		sqlSession.commit();//清空缓存
		sqlSession.close();
		return user;
	}
	

}
