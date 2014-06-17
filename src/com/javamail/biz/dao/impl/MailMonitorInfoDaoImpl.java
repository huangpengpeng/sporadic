package com.javamail.biz.dao.impl;

import org.springframework.stereotype.Repository;

import com.common.jdbc.JdbcTemplateBaseDao;
import com.javamail.biz.dao.MailMonitorInfoDao;
import com.javamail.biz.entity.MailMonitorInfo;

@Repository
public class MailMonitorInfoDaoImpl extends JdbcTemplateBaseDao implements
MailMonitorInfoDao {

	@Override
	public void add(MailMonitorInfo info) {
		super.add(info);
	}

	@Override
	public Class<?> getEntityClass() {
		return MailMonitorInfo.class;
	}

}
