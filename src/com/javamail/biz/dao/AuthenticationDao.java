package com.javamail.biz.dao;

import com.javamail.biz.entity.Authentication;


public interface AuthenticationDao {

	long add(Authentication auth);
	
	Authentication queryForObject(Long id);
}
