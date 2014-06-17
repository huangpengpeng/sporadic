package com.javamail.biz.dao.impl;

import org.springframework.stereotype.Repository;

import com.common.jdbc.JdbcTemplateBaseDao;
import com.common.jdbc.SqlBuilder;
import com.javamail.biz.dao.UnifiedUserDao;
import com.javamail.biz.entity.UnifiedUser;

@Repository
public class UnifiedUserDaoImpl extends JdbcTemplateBaseDao implements
		UnifiedUserDao {

	@Override
	public UnifiedUser queryForObject(String username) {
		SqlBuilder sqlBuilder = new SqlBuilder(
				"select * from UnifiedUser where 1=1");
		if(username!=null){
			sqlBuilder.andEqualTo("username", username);
		}
		return queryForObject(sqlBuilder);
	}

	@Override
	protected Class<?> getEntityClass() {
		return UnifiedUser.class;
	}

	@Override
	public UnifiedUser queryForObject(Long id) {
		return super.queryForObject(id);
	}

	@Override
	public long add(UnifiedUser user) {
		return super.add(user);
	}

	@Override
	public void update(Long id,String username, String password) {
		update((long)id,username, password);
	}

	
	protected long update(long id,String username,String password) {
		SqlBuilder sqlBuilder = new SqlBuilder(
				"update UnifiedUser set gmtModify=current_timestamp()");
		if(username!=null){
			sqlBuilder.set("username", username);
		}
		if(password!=null){
			sqlBuilder.set("password", password);
		}
		return update(id, sqlBuilder);
	}
}
