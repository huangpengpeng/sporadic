package com.javamail.biz.manager;

import com.javamail.biz.entity.TripartiteInfo;

public interface TripartiteInfoMng {

	/**
	 * 
	 * 描述:
	 * 
	 * @param username
	 *            用户名
	 * @param openId
	 *            第三方唯一编号
	 * @param tripartiteId
	 *            第三方账户类型编号
	 * @param ip
	 *            注册地址
	 * @return
	 * @author huangpengpeng2013年11月10日 下午2:17:37
	 */
	TripartiteInfo add(String username,String openId, Long tripartiteId,String ip);

	/**
	 * 
			* 描述:根据第三方唯一编号查看
			* @param openId
			* @return
			* @author huangpengpeng2013年11月10日 下午2:07:28
	 */
	TripartiteInfo queryForObject(String openId);
}
