package com.javamail.biz.entity.base;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.common.jdbc.BaseEntity;

/**
 * 邮件链接访问记录
 * 
 * @author hp
 * 
 */
@MappedSuperclass
public class BaseMailMonitorInfo extends BaseEntity {

	private static final long serialVersionUID = 1740287376383432203L;

	public BaseMailMonitorInfo() {
	}

	public BaseMailMonitorInfo(Long mailMonitorId, String ip, String address,
			String type, Long messageId) {
		this.setMailMonitorId(mailMonitorId);
		this.setIp(ip);
		this.setAddress(address);
		this.setType(type);
		this.setMessageId(messageId);
	}

	/**
	 * 链接编号
	 */
	private Long mailMonitorId;

	/**
	 * 访问者IP
	 */
	private String ip;

	/**
	 * 访问者地址
	 */
	private String address;

	/**
	 * 访问时间
	 */
	private Date accessTime;

	/**
	 * 监控码类型
	 */
	private String type;

	/**
	 * 邮件消息编号
	 */
	private Long messageId;

	public Long getMailMonitorId() {
		return mailMonitorId;
	}

	public void setMailMonitorId(Long mailMonitorId) {
		this.mailMonitorId = mailMonitorId;
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

	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}
}
