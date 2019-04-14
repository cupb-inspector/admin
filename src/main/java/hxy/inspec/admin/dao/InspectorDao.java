package hxy.inspec.admin.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hxy.inspec.admin.datasources.DataConnection;
import hxy.inspec.admin.po.Inspector;

public class InspectorDao {

	private final static Logger logger = LoggerFactory.getLogger(InspectorDao.class);
	public List<Inspector> selectAll() throws IOException{
		
		SqlSession sqlSession = DataConnection.getSqlSession();
		List<Inspector> goodsList = sqlSession.selectList("Inspector.selectAll");
		logger.info("质检员查询结果条数"+goodsList.size());
		for (Inspector good : goodsList) {
			System.out.format("%s\n", good.getUserName());
		}
		sqlSession.commit();
		sqlSession.close();
		return goodsList;
		
		
	}
	

}
