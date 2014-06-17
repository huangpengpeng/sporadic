package com.javamail.biz.entity.base;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.common.jdbc.BaseEntity;

@MappedSuperclass
public class BaseAuthentication extends BaseEntity {

	private static final long serialVersionUID = 6325380164683467723L;
	
	public BaseAuthentication(){}

	public BaseAuthentication(String ip, Date loginTime, Long userId,String license) {
		this.setIp(ip);
		this.setLoginTime(loginTime);
		this.setUserId(userId);
		this.setLicense(license);
	}

	/**
	 * 登录IP
	 */
	private String ip;

	/**
	 * 登录时间
	 */
	private Date loginTime;

	/**
	 * 登录用户
	 */
	private Long userId;
	
	/**
	 * 授权方式
	 */
	private String license;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}
}
