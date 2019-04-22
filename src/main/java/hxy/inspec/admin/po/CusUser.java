package hxy.inspec.admin.po;

import lombok.Data;

@Data
public class CusUser {
	private String cusid;
	private String custel;
	private String cusname;
	private String cuspasswd;
//	private String province;
//	private String city;
	private String cusgrade;//等级，与信用相关
	private String cusvip;
	private String cusaddress;// 地址
	private String cusdate;// 注册日期
	private String custrade;//行业

}
