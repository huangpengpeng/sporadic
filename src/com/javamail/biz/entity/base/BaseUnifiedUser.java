package com.javamail.biz.entity.base;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.common.jdbc.BaseEntity;

/**
 * 
		
		* 描述:同意用户
		*
		* @author huangpengpeng
		* @version 1.0
		* @since 2013年9月6日 上午9:35:58
 */
@MappedSuperclass
public class BaseUnifiedUser extends BaseEntity{

	private static final long serialVersionUID = -4519183386307646257L;
	
	public BaseUnifiedUser(){}
	
	public BaseUnifiedUser(Date registerTime,String ip,String password,String username){
		this.setRegisterTime(registerTime);
		this.setIp(ip);
		this.setPassword(password);
		this.setUsername(username);
	}

	/**
	 * 注册时间
	 */
	private Date registerTime;
	
	/**
	 * 注册IP
	 */
	private String ip;
	
	/**
	 * 用户名
	 */
	private String password;
	
	/**
	 * 密码
	 */
	private String username;

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
