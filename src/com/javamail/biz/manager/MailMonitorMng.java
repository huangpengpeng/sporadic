package com.javamail.biz.manager;

import java.util.Map;

import com.javamail.biz.entity.MailMonitor;

public interface MailMonitorMng {

	MailMonitor queryForObject(Long id);
	
	/**
	 * 根据code查询链接
	 * 
	 * @param code
	 * @return
	 */
	MailMonitor queryForObject(String code);

	/**
	 * 增加数据
	 * 
	 * @param monitor
	 */
	void add(String code, String codeType, Long messageId);
	
	
	/**
	 * 增加数据
	 * @param code
	 * @param codeType
	 * @param messageId
	 * @param link
	 */
	void add(String code, String codeType, Long messageId,String link);

	/**
	 * 获取邮件监控信息
	 * 描述:将邮件内容里面的<a href="">链接全部转化成通过此系统跳转的链接 <br>
	 * 以便记录用户是否链接邮件内容
	 * 
	 * @param domain 域名
	 * @param content 转换内容
	 * @return
	 * @author huangpengpeng2013年11月11日 下午1:05:38
	 */
	Map<String, Object> getMailMonitorInfo(String domain,String content);
}
