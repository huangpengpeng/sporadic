package com.javamail.biz.dao;

import com.javamail.biz.entity.Tripartite;
import com.javamail.biz.entity.TripartiteInfo;

public interface TripartiteDao {

	Tripartite queryForObject(Long id);
	
	long add(TripartiteInfo info);
}
