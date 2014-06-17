package com.javamail.biz.entity.base;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.common.jdbc.BaseEntity;

@MappedSuperclass
public class BaseEmail extends BaseEntity {

	private static final long serialVersionUID = 6886873167961770763L;
	
	public BaseEmail(){}

	public BaseEmail(String name,Date registered){
		this.setName(name);
		this.setRegistered(registered);
	}
	
	/**
	 * email账户名称
	 */
	private String name;

	/**
	 * 注册时间
	 */
	private Date registered;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegistered() {
		return registered;
	}

	public void setRegistered(Date registered) {
		this.registered = registered;
	}

}
