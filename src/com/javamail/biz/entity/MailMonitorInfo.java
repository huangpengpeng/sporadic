package com.javamail.biz.entity;

import java.util.Date;

import javax.persistence.Entity;

import com.javamail.biz.entity.base.BaseMailMonitorInfo;


@Entity
public class MailMonitorInfo extends BaseMailMonitorInfo{

	public MailMonitorInfo(){};
	
	public MailMonitorInfo(Long mailMonitorId, String ip, String address,
			String type, Long messageId) {
		super(mailMonitorId, ip, address, type, messageId);
	}

	private static final long serialVersionUID = 443449909393116489L;

	public void init(){
		if(this.getAccessTime()==null){
			this.setAccessTime(new Date());
		}
	}
}
