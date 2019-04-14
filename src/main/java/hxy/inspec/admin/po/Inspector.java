package hxy.inspec.admin.po;

import lombok.Data;

@Data
public class Inspector {
	private String userId;
	private String userName;
	private String userTel;
	private String userPasswd;
	private String userGrade;
	private String province;
	private String city;
	private String district;
	private String address;
	private String rmb;//人民币
	private String orders;//订单数
	private String integral;
	private String status;

}
