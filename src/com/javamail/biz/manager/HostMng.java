package com.javamail.biz.manager;

import com.javamail.biz.entity.Host;

public interface HostMng {

	/**
	 * 根据后缀查询host
	 * @param suffix
	 * @return
	 */
	Host queryForObject(String suffix);
	
	long add(Host host);
}
