package com.javamail.biz.dao;

import com.javamail.biz.entity.Host;

public interface HostDao {

	/**
	 * 根据后缀查询host
	 * @param suffix
	 * @return
	 */
	Host queryForObject(String suffix);

	long add(Host host);
}
