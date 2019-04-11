package hxy.inspec.admin.dao;
import java.io.IOException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hxy.inspec.admin.datasources.DataConnection;
import hxy.inspec.admin.po.AdminUser;

public class AdminUserDao {
	private final static Logger logger = LoggerFactory.getLogger(AdminUserDao.class);
	
	public AdminUser selectUserByPhone(String phone) throws IOException {

		SqlSession sqlSession = DataConnection.getSqlSession();
	
		AdminUser user = sqlSession.selectOne("AdminUser.findUserByNumber", phone);
		
		if(user!=null) {
			logger.info("用户"+user.getAdminName());
		}
		
		
		sqlSession.commit();//清空缓存
		sqlSession.close();
		return user;
	}
	public int insert(AdminUser user) throws IOException {
		SqlSession sqlSession = DataConnection.getSqlSession();
		int flag = sqlSession.insert("AdminUser.insert", user);
		sqlSession.commit();
		sqlSession.close();
		logger.info("插入后结果：" + flag);
		return flag;
	}
	public int delete(String tel) throws IOException {
		// TODO Auto-generated method stub
		SqlSession sqlSession = DataConnection.getSqlSession();
		int flag = sqlSession.insert("AdminUser.delete", tel);
		sqlSession.commit();
		sqlSession.close();
		logger.info("插入后结果：" + flag);
		return flag;
		
	}

}
