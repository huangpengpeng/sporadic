package com.javamail.biz.dao;

import com.javamail.biz.entity.MailMonitor;

public interface MailMonitorDao {

	void add(MailMonitor connect);
	
	MailMonitor queryForObject(String code);
	MailMonitor queryForObject(Long id);
}
