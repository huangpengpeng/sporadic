package com.javamail.biz.dao.impl;

import org.springframework.stereotype.Repository;

import com.common.jdbc.JdbcTemplateBaseDao;
import com.common.jdbc.SqlBuilder;
import com.javamail.biz.dao.HostDao;
import com.javamail.biz.entity.Host;

@Repository
public class HostDaoImpl extends JdbcTemplateBaseDao implements HostDao {

	@Override
	public Host queryForObject(String suffix) {
		SqlBuilder sqlBuilder = new SqlBuilder(
				"select * from Host where 1=1");
		if (suffix != null) {
			sqlBuilder.andEqualTo("suffix", suffix);
		}
		return queryForObject(sqlBuilder);
	}

	@Override
	protected Class<?> getEntityClass() {
		return Host.class;
	}

	@Override
	public long add(Host host) {
		return super.add(host);
	}
}
