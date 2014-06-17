package com.javamail.biz.manager.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.security.encoder.PwdEncoder;
import com.javamail.biz.dao.UnifiedUserDao;
import com.javamail.biz.entity.UnifiedUser;
import com.javamail.biz.manager.UnifiedUserMng;

@Transactional
@Service
public class UnifiedUserMngImpl implements UnifiedUserMng {

	@Override
	public UnifiedUser login(String username, String password)
			throws UsernameNotFoundException, BadCredentialsException {
		UnifiedUser user = getByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("username not found: "
					+ username);
		}
		if (!pwdEncoder.isPasswordValid(user.getPassword(), password)) {
			throw new BadCredentialsException("password invalid");
		}
		return user;
	}

	@Override
	public UnifiedUser queryForObject(Long id) {
		return dao.queryForObject(id);
	}

	@Override
	public UnifiedUser add(String username, String ip) {
		UnifiedUser unifiedUser = new UnifiedUser(ip, null, username);
		unifiedUser.init();
		unifiedUser.setId(dao.add(unifiedUser));
		return unifiedUser;
	}

	@Override
	public UnifiedUser add(String username, String password, String ip) {
		UnifiedUser unifiedUser = new UnifiedUser(ip,
				pwdEncoder.encodePassword(password), username);
		unifiedUser.init();
		unifiedUser.setId(dao.add(unifiedUser));
		return unifiedUser;
	}

	@Override
	public UnifiedUser getByUsername(String username) {
		return dao.queryForObject(username);
	}

	@Autowired
	private PwdEncoder pwdEncoder;
	@Autowired
	private UnifiedUserDao dao;
}
