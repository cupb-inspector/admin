package hxy.inspect.admin.user;

import java.io.IOException;

import org.junit.Test;

import hxy.inspec.admin.po.AdminUser;
import hxy.inspec.admin.services.AdminUserService;

public class UserTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		AdminUserService userService = new AdminUserService();
		AdminUser user = userService.login("2");
		
		if(user!=null) {
			System.out.println("用户密码"+user.getAdminPasswd());
		}else {
			System.out.println("没有结果");
		}
		
		
	}
	@Test
	public void Delete() {
		AdminUserService userService = new AdminUserService();
		try {
			if(userService.delete("1")) {
				System.out.println("成功");
			}else
				System.out.println("失败");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
