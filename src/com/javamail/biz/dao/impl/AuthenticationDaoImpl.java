package com.javamail.biz.dao.impl;

import org.springframework.stereotype.Repository;

import com.common.jdbc.JdbcTemplateBaseDao;
import com.javamail.biz.dao.AuthenticationDao;
import com.javamail.biz.entity.Authentication;

@Repository
public class AuthenticationDaoImpl extends JdbcTemplateBaseDao implements AuthenticationDao{

	@Override
	public long add(Authentication auth) {
		return super.add(auth);
	}

	@Override
	protected Class<?> getEntityClass() {
		return Authentication.class;
	}

	@Override
	public Authentication queryForObject(Long id) {
		return super.queryForObject(id);
	}

}
