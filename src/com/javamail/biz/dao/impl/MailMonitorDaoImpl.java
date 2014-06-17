package com.javamail.biz.dao.impl;

import org.springframework.stereotype.Repository;

import com.common.jdbc.JdbcTemplateBaseDao;
import com.common.jdbc.SqlBuilder;
import com.javamail.biz.dao.MailMonitorDao;
import com.javamail.biz.entity.MailMonitor;

@Repository
public class MailMonitorDaoImpl extends JdbcTemplateBaseDao implements  MailMonitorDao{

	@Override
	public void add(MailMonitor mailMonitor) {
		super.add(mailMonitor);
	}

	@Override
	public Class<?> getEntityClass() {
		return MailMonitor.class;
	}

	@Override
	public MailMonitor queryForObject(String code) {
		return queryForObject(new SqlBuilder(
				"select * from MailMonitor where 1=1").andEqualTo("code", code));
	}

	@Override
	public MailMonitor queryForObject(Long id) {
		return super.queryForObject(id);
	}

}
