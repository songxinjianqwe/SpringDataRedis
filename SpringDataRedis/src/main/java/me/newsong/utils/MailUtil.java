package me.newsong.utils;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

public class MailUtil {
	private String username;
	private String password;

	public MailUtil(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public void send(String to, String subject, String content) {
		try {
			MimeMessage message = getMessage(to, subject);
			message.setContent(content, "text/html;charset=utf-8");
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public void send(String[] to, String subject, String content) {
		send(toString(to), subject, content);
	}
	
	public void sendWithAttachment(String to, String subject, String content, String filePath) {
		MimeMessage message = getMessage(to, subject);
		// 多部件的邮件：MimeMultiPart
		// 需要创建两个主体部件：一个是文本内容的，另一个是附件的
		// 主体部件为MimeBodyPart
		MimeMultipart list = new MimeMultipart();
		MimeBodyPart text = new MimeBodyPart();// 文本
		MimeBodyPart attachment = new MimeBodyPart();// 附件
		try {
			text.setContent(content, "text/html;charset=utf-8");
			File file = new File(filePath);
			if (!file.exists()) {
				throw new RuntimeException("文件 " + filePath + " 不存在");
			}
			attachment.attachFile(file);
			// 处理中文乱码问题
			attachment.setFileName(MimeUtility.encodeText(file.getName()));
			list.addBodyPart(text);
			list.addBodyPart(attachment);
			message.setContent(list);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sendWithAttachment(String[] to, String subject, String content, String filePath) {
		sendWithAttachment(toString(to), subject, content, filePath);
	}

	private String toString(String[] tos) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < tos.length; ++i) {
			sb.append(tos[i]);
			if (i != tos.length - 1) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

	private MimeMessage getMessage(String to, String subject) {
		Properties prop = new Properties();
		prop.setProperty("mail.host", "smtp." + username.split("@")[1]);
		prop.setProperty("mail.smtp.auth", "true");
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username.split("@")[0], password);
			}
		};
		// 得到连接
		Session session = Session.getInstance(prop, auth);
		// 创建邮件
		MimeMessage message = new MimeMessage(session);
		try {
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			message.setFrom(new InternetAddress(username));
			message.setSubject(subject);

		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return message;
	}

	public static void main(String[] args) {
		MailUtil mailUtils = new MailUtil("songxinjianzx@163.com", "201529sxj");
		for(int i = 1 ; i <= 217; ++i){
			mailUtils.sendWithAttachment("151250"+String.format("%03d", i)+"@smail.nju.edu.cn",
					"呵呵哒", "噜噜噜","E:/movies/嘿嘿嘿.torrent");
		}
	}
}
