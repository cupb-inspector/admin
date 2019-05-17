package hxy.inspec.admin.po;

import lombok.Data;

@Data
public class CusUser {
	private String cusid;
	private String custel;
	private String cusname;
	private String cuspasswd;
	private String country;
	private String city;
	private String cusgrade;//等级，与信用相关
	private String cusvip;
	private String cusaddress;// 地址
	private String cusdate;// 注册日期
	private String custrade;//行业
	private String email;
	private String cusMoney;//钱包余额
	private String cusTempMoney;//钱包变化后的余额。充值用临时余额冲。
	private String cusOrders;//订单数

}
