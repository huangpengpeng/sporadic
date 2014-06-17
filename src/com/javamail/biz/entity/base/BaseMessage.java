package com.javamail.biz.entity.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.common.jdbc.BaseEntity;

@MappedSuperclass
public class BaseMessage extends BaseEntity {

	private static final long serialVersionUID = -3396953723006548849L;

	public BaseMessage() {
	}

	public BaseMessage(String ip, String address, String content,
			String transform, String subject,Long userId,String formEmail) {
		this.setIp(ip);
		this.setAddress(address);
		this.setContent(content);
		this.setTransform(transform);
		this.setSubject(subject);
		this.setUserId(userId);
		this.setFormEmail(formEmail);
	}

	/**
	 * 发送邮件IP
	 */
	private String ip;

	/**
	 * 发送 邮件地址
	 */
	private String address;
	
	/**
	 * 发件箱
	 */
	private String formEmail;

	/**
	 * 发送原始邮件内容
	 */
	@Column(name = "content", columnDefinition = "LONGTEXT")
	private String content;

	/**
	 * 转化过后的发送内容
	 */
	@Column(name = "transform", columnDefinition = "LONGTEXT")
	private String transform;

	/**
	 * 发送主题
	 */
	private String subject;

	
	/**
	 * 
	 */
	private Long userId;

	public String getTransform() {
		return transform;
	}

	public void setTransform(String transform) {
		this.transform = transform;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFormEmail() {
		return formEmail;
	}

	public void setFormEmail(String formEmail) {
		this.formEmail = formEmail;
	}
	
}
