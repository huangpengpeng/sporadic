package com.javamail.biz.entity;

import javax.persistence.Entity;

import com.javamail.biz.entity.base.BaseHost;

@Entity
public class Host extends BaseHost{
	
	public Host(){}

	public Host(String suffix, String host) {
		super(suffix, host);
	}

	private static final long serialVersionUID = 3046740707647867424L;
}
