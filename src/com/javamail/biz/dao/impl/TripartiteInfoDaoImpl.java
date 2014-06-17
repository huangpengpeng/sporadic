package com.javamail.biz.dao.impl;

import org.springframework.stereotype.Repository;

import com.common.jdbc.JdbcTemplateBaseDao;
import com.common.jdbc.SqlBuilder;
import com.javamail.biz.dao.TripartiteInfoDao;
import com.javamail.biz.entity.TripartiteInfo;

@Repository
public class TripartiteInfoDaoImpl extends JdbcTemplateBaseDao implements
		TripartiteInfoDao {

	@Override
	public TripartiteInfo queryForObject(String openId) {
		SqlBuilder sqlBuilder = new SqlBuilder(
				"select * from TripartiteInfo where 1=1");
		if (openId != null) {
			sqlBuilder.andEqualTo("openId", openId);
		}
		return super.queryForObject(sqlBuilder);
	}

	@Override
	public long add(TripartiteInfo tripartite) {
		return super.add(tripartite);
	}

	@Override
	protected Class<?> getEntityClass() {
		return TripartiteInfo.class;
	}
}
