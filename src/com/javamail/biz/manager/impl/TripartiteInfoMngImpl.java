package com.javamail.biz.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javamail.biz.dao.TripartiteInfoDao;
import com.javamail.biz.entity.TripartiteInfo;
import com.javamail.biz.entity.UnifiedUser;
import com.javamail.biz.manager.TripartiteInfoMng;
import com.javamail.biz.manager.UnifiedUserMng;

@Transactional
@Service
public class TripartiteInfoMngImpl implements TripartiteInfoMng {

	@Override
	public TripartiteInfo add(String username, String openId,
			Long tripartiteId, String ip) {
		// 当用户名重复时将用户的唯一CODE增加到用户名后面，保证用户名的唯一性
		while (unifiedUserMng.getByUsername(username) != null) {
			username += "1";
		}
		// 增加用户
		UnifiedUser unifiedUser = unifiedUserMng.add(username, ip);
		// 绑定用户第三方账户信息
		TripartiteInfo info = new TripartiteInfo(openId, unifiedUser.getId(),
				tripartiteId);
		info.setId(dao.add(info));
		return info;
	}

	@Override
	public TripartiteInfo queryForObject(String openId) {
		return dao.queryForObject(openId);
	}

	@Autowired
	private UnifiedUserMng unifiedUserMng;
	@Autowired
	private TripartiteInfoDao dao;
}
