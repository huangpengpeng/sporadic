package com.javamail.biz.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.util.IpUtils;
import com.javamail.biz.dao.MailMonitorInfoDao;
import com.javamail.biz.entity.MailMonitor;
import com.javamail.biz.entity.MailMonitorInfo;
import com.javamail.biz.manager.MailMonitorInfoMng;
import com.javamail.biz.manager.MailMonitorMng;

@Transactional
@Service
public class MailMonitorInfoMngImpl implements MailMonitorInfoMng {

	@Override
	public void save(Long mailMonitorId, String ip) {
		MailMonitor mailMonitor=mailMonitorMng.queryForObject(mailMonitorId);
		MailMonitorInfo info = new MailMonitorInfo(mailMonitorId, ip,
				IpUtils.getAddress(ip), mailMonitor.getType(),mailMonitor.getMessageId());
		info.init();
		dao.add(info);
	}

	@Autowired
	private MailMonitorMng mailMonitorMng;
	@Autowired
	private MailMonitorInfoDao dao;
}
