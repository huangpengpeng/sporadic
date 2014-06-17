package com.javamail.biz.entity.base;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.common.jdbc.BaseEntity;

/**
 * 端连接商城监控有点内容点击
 * 
 * @author hp
 * 
 */
@MappedSuperclass
public class BaseMailMonitor extends BaseEntity {

	private static final long serialVersionUID = -1068577862999990137L;

	public BaseMailMonitor() {
	}

	public BaseMailMonitor(String code, String type, Long messageId, String link) {
		this.setCode(code);
		this.setType(type);
		this.setMessageId(messageId);
		this.setLink(link);
	}

	/**
	 * 生成CODe
	 */
	private String code;

	/**
	 * code 类型(邮件查看:EMIAL_GET,邮件点击:EMAIL_CLICK)
	 */
	private String type;

	/**
	 * 扩展编号
	 */
	private Long messageId;

	/**
	 * 需要跳转的目标链接
	 */
	@Column(length = 1000)
	private String link;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
