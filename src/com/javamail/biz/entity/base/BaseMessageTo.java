package com.javamail.biz.entity.base;

import java.util.Date;

import javax.persistence.MappedSuperclass;

import com.common.jdbc.BaseEntity;

/**
 * 消息走向
 * @author hpp
 *
 */
@MappedSuperclass
public class BaseMessageTo extends BaseEntity {

	private static final long serialVersionUID = -2530129640944188139L;
	
	public BaseMessageTo(){}
	
	public BaseMessageTo(String from, String toEmail, Long messageId,
			String toType, Date sendTime) {
		this.setForm(from);
		this.setToEmail(toEmail);
		this.setMessageId(messageId);
		this.setToType(toType);
	}

	/**
	 * 发件账户
	 */
	private String form;
	
	/**
	 * 收件账户
	 */
	private String  toEmail;
	
	/**
	 * 消息内容
	 */
	private Long messageId;
	
	/**
	 * 邮件去向类型（抄送,密送,直送）
	 */
	private String toType;
	
	/**
	 * 发送时间
	 */
	private Date sendTime;

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}


	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}


	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getToType() {
		return toType;
	}

	public void setToType(String toType) {
		this.toType = toType;
	}
}
