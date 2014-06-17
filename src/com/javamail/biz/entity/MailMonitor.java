package com.javamail.biz.entity;

import javax.persistence.Entity;

import com.javamail.biz.entity.base.BaseMailMonitor;

@Entity
public class MailMonitor extends BaseMailMonitor {

	private static final long serialVersionUID = -2260052651892701354L;

	public MailMonitor() {
	}

	public MailMonitor(String code, String type, Long messageId) {
		super(code, type, messageId, null);
	}

	public MailMonitor(String code, String codeType, Long messageId, String link) {
		super(code, codeType, messageId, link);
	}

	/**
	 * 
	 * 
	 * 描述:监控类型
	 * 
	 * @author huangpengpeng
	 * @version 1.0
	 * @since 2013年11月11日 上午11:29:02
	 */
	public enum MailMonitorType {
		邮件查看, 邮件内容点击
	}
}
