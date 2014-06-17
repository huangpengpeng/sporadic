package com.javamail.biz.dao;

import com.javamail.biz.entity.TripartiteInfo;


public interface TripartiteInfoDao {

	TripartiteInfo queryForObject(String openId);
	
	long add(TripartiteInfo tripartite);
}
