package hxy.inspec.admin.dao;
import java.io.IOException;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hxy.inspec.admin.datasources.DataConnection;
import hxy.inspec.admin.po.AdminUser;

public class AdminUserDao {
	private final static Logger logger = LoggerFactory.getLogger(AdminUserDao.class);
	
	public AdminUser selectUserByPhone(String phone) {

		SqlSession sqlSession = null;
		try {
			sqlSession = DataConnection.getSqlSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AdminUser user = sqlSession.selectOne("AdminUser.findUserByNumber", phone);
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

}
