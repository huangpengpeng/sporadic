package com.javamail.biz.entity.base;

import javax.persistence.MappedSuperclass;

import com.common.jdbc.BaseEntity;

/**
 * 邮件smtp host
 * @author hpp
 *
 */
@MappedSuperclass
public class BaseHost extends BaseEntity{

	private static final long serialVersionUID = -8194186680648782147L;
	
	public BaseHost(){}
	
	public BaseHost(String suffix,String host){
		this.suffix=suffix;
		this.host=host;
	}

	/**
	 * 后缀
	 */
	private String suffix;
	
	/**
	 * 根据邮件后缀匹配host
	 */
	private String host;

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
}
