package com.javamail.biz.entity;

import java.util.Date;

import javax.persistence.Entity;

import com.javamail.biz.entity.base.BaseUnifiedUser;

@Entity
public class UnifiedUser extends BaseUnifiedUser{

	public UnifiedUser(){}
	
	public UnifiedUser(String ip, String password,
			String username) {
		super(null, ip, password, username);
	}

	public void init()
	{
		if(getRegisterTime()==null){
			setRegisterTime(new Date());
		}
	}
	private static final long serialVersionUID = -8578276386902040062L;
}
