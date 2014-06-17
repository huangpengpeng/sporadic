package com.javamail.biz.manager;

import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;

/**
 * 邮件处理业务类
 * 
 * @author hpp
 * 
 */
public interface MessageMng {

	/**
	 * 
	 * 描述:
	 * 
	 * @param subject
	 *            发送主题
	 * @param content
	 *            发送内容
	 * @param to
	 *            接收者账户
	 * @param formEmail
	 *            发送者账户
	 * @param fromPwd
	 *            发送者密码
	 * @param sendIp
	 *            发送IP
	 * @param domain
	 *            发送域名
	 * @author huangpengpeng2013年11月11日 下午1:27:27
	 */
	void send(String formEmail, String formPwd, String to, String subject,
			String content, Long userId, String sendIp, String domain)
			throws MailAuthenticationException, MailSendException;
}
