package com.javamail.biz.manager.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.http.HttpClient;
import com.common.http.HttpException;
import com.common.http.Response;
import com.common.util.IpUtils;
import com.javamail.biz.dao.MessageDao;
import com.javamail.biz.entity.Host;
import com.javamail.biz.entity.MailMonitor.MailMonitorType;
import com.javamail.biz.entity.Message;
import com.javamail.biz.entity.MessageTo.ToType;
import com.javamail.biz.manager.HostMng;
import com.javamail.biz.manager.MailMonitorMng;
import com.javamail.biz.manager.MessageMng;
import com.javamail.biz.manager.MessageToMng;

@Transactional
@Service
public class MessageMngImpl implements MessageMng {

	@Override
	public void send(final String formEmail, String formPwd, final String to,
			final String subject, String content, Long userId, String sendIp,
			String domain) throws MailAuthenticationException,
			MailSendException {
		log.info("发送者:{} 开始发送邮件:{} IP {}",
				new Object[] { formEmail, to, sendIp });
		// 将转化过后的邮件内容发送给接收者
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		final String personal = formEmail;
		final String text = addMessage(content, formEmail, subject, to, sendIp,
				domain, userId);
		;
		sender.setHost(getHost(formEmail).getHost());
		sender.setUsername(formEmail);
		sender.setPassword(formPwd);
		MimeMessagePreparator mine = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage)
					throws MessagingException, UnsupportedEncodingException {
				MimeMessageHelper msg = new MimeMessageHelper(mimeMessage,
						false, "UTF-8");
				msg.setSubject(subject);
				msg.setTo(to);
				msg.setFrom(formEmail, personal);
				msg.setText(text, true);
			}
		};
		sender.send(mine);
		log.info("发送者:{} 结束发送邮件:{} IP {}",
				new Object[] { formEmail, to, sendIp });
	}

	/**
	 * 增加消息信息
	 * @param content 消息内容
	 * @param from 发送者
	 * @param subject 邮件主题
	 * @param to 接受者
	 * @param sendIp 发送者IP
	 * @param domain 域名
	 * @param userId 发送用户
	 * @return
	 */
	protected String addMessage(String content, String from, String subject,
			String to, String sendIp, String domain, Long userId) {
		// 将邮件内容进行转换
		Map<String, Object> mailMonitorInfoMap = mailMonitorMng
				.getMailMonitorInfo(domain, content);
		// 增加消息体
		Message message = new Message(sendIp, IpUtils.getAddress(sendIp),
				content, (String) mailMonitorInfoMap.get("content"), subject,
				userId, from);
		message.setId(dao.add(message));
		// 增加消息去向
		messageToMng.add(from, to, message.getId(), ToType.直送.toString());
		// 将链接信息插入数据库
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> aHrefList = ((List<Map<String, Object>>) mailMonitorInfoMap
				.get("aHrefList"));
		for (Map<String, Object> map : aHrefList) {
			mailMonitorMng.add((String) map.get("code"),
					MailMonitorType.邮件内容点击.toString(), message.getId(),
					(String) map.get("targetLink"));
		}
		// 将用户是否访问的埋点插入数据库
		mailMonitorMng.add((String) mailMonitorInfoMap.get("code"),
				MailMonitorType.邮件查看.toString(), message.getId());
		return (String) mailMonitorInfoMap.get("content");
	}

	/**
	 * 根据邮件名称获取邮件host地址
	 * 
	 * @param email
	 * @return
	 */
	protected Host getHost(String email) {
		String suffix = email.substring(email.indexOf("@") + 1);
		Host host = hostMng.queryForObject(suffix);
		if (host != null) {
			return host;
		}
		try {
			Response response = new HttpClient().get("http://mail." + suffix);
			if (response.getResponseAsString().contains("关于腾讯")) {
				return new Host(suffix, "smtp.exmail.qq.com");
			}
		} catch (HttpException e) {
		}
		host = new Host(suffix, "smtp." + suffix);
		host.setId(hostMng.add(host));
		return host;
	}

	@Autowired
	private MessageDao dao;
	@Autowired
	private MessageToMng messageToMng;
	@Autowired
	private HostMng hostMng;
	@Autowired
	private MailMonitorMng mailMonitorMng;
	private Logger log = LoggerFactory.getLogger(MessageMngImpl.class);

}
