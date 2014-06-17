package com.javamail.biz.dao.impl;

import org.springframework.stereotype.Repository;

import com.common.jdbc.JdbcTemplateBaseDao;
import com.javamail.biz.dao.TripartiteDao;
import com.javamail.biz.entity.Tripartite;
import com.javamail.biz.entity.TripartiteInfo;

@Repository
public class TripartiteDaoImpl extends JdbcTemplateBaseDao implements
		TripartiteDao {

	@Override
	public Tripartite queryForObject(Long id) {
		return super.queryForObject(id);
	}

	@Override
	protected Class<?> getEntityClass() {
		return Tripartite.class;
	}

	@Override
	public long add(TripartiteInfo info) {
		return super.add(info);
	}

}
