package hxy.inspec.admin.services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import hxy.inspec.admin.po.CusUser;
import hxy.inspec.admin.po.Inspector;
import hxy.inspec.admin.util.Configration;
import hxy.inspec.admin.util.SendMail;

public class MailService {
	// 发送邮件

	public boolean sendMailToInspector(Inspector inspector) {

		// 邮件主题
		String title = "验货通知";
		// 质检员的用户名，邮箱
		// 邮件正文
		String htmlContent = inspector.getUserName() + ",您好！您有新的订单请及时处理。环球验货质检\n地址：浙江宁波";

		// 收件人
		List<String> receivers = new ArrayList<String>();
//		receivers.add("aohanhongzhi@qq.com");
//		receivers.add("aohanhongzhi@126.com");
		receivers.add(inspector.getMail().trim());

		// 附件
		String fileName1 = "/home/yz/有点东西.png";
		File file1 = new File(fileName1);
		String fileName2 = "/home/yz/我的简历.pdf";
		File file2 = new File(fileName2);
		List<File> fileList = new ArrayList<File>();
//		fileList.add(file1);
//		fileList.add(file2);
		// 执行发送
		return new SendMail().sendEmail(title, htmlContent, receivers, fileList);
	}

	public boolean sendMailToCustomer(CusUser cusUser) {

		// 邮件主题
		String title = "验货通知";
		// 质检员的用户名，邮箱
		// 邮件正文
		String htmlContent = cusUser.getCusname() + ",您好！您的订单已验货完成，报告已经生成，请及时确认。环球验货质检\n地址：浙江宁波";

		// 收件人
		List<String> receivers = new ArrayList<String>();
//		receivers.add("aohanhongzhi@qq.com");
//		receivers.add("aohanhongzhi@126.com");
		receivers.add(cusUser.getMail().trim());

		// 附件
		
//		String fileName1 =Configration.FILE_ROOT_DIR+ "/home/yz/有点东西.png";
//		File file1 = new File(fileName1);
		String fileName2 = "/home/yz/我的简历.pdf";
		File file2 = new File(fileName2);
		List<File> fileList = new ArrayList<File>();
//		fileList.add(file1);
//		fileList.add(file2);
		// 执行发送
		return new SendMail().sendEmail(title, htmlContent, receivers, fileList);
	}

}
