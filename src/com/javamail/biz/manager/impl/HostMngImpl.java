package com.javamail.biz.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javamail.biz.dao.HostDao;
import com.javamail.biz.entity.Host;
import com.javamail.biz.manager.HostMng;

@Transactional
@Service
public class HostMngImpl implements HostMng{

	@Override
	public Host queryForObject(String suffix) {
		return dao.queryForObject(suffix);
	}
	
	
	@Override
	public long add(Host host) {
		return dao.add(host);
	}
	
	@Autowired
	private HostDao dao;
}
