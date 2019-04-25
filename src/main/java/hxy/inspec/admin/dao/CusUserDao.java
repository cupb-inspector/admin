package hxy.inspec.admin.dao;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hxy.inspec.admin.datasources.DataConnection;
import hxy.inspec.admin.po.CusUser;

public class CusUserDao {

	private final static Logger logger = LoggerFactory.getLogger(CusUserDao.class);
	public List<CusUser> selectAll() throws IOException{
		
		SqlSession sqlSession = DataConnection.getSqlSession();
		List<CusUser> goodsList = sqlSession.selectList("CusUser.selectAll");
		logger.info("质检员查询结果条数"+goodsList.size());

		sqlSession.commit();
		sqlSession.close();
		return goodsList;
		
		
	}
	public CusUser findCusUserByTel(String tel) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DataConnection.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		CusUser user = sqlSession.selectOne("CusUser.findUserByNumber", tel);
		sqlSession.commit();//清空缓存
		sqlSession.close();
		return user;
	}
	

}
