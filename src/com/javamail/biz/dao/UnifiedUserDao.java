package com.javamail.biz.dao;

import com.javamail.biz.entity.UnifiedUser;

public interface UnifiedUserDao {

	/**
	 * 
			* 描述:根据用户名查询
			* @param username
			* @return
			* @author huangpengpeng2013年9月6日 上午9:56:49
	 */
	UnifiedUser queryForObject(String username);
	
	UnifiedUser queryForObject(Long id);
	
	long add(UnifiedUser user);
	
	void update(Long id,String username,String password);
}
