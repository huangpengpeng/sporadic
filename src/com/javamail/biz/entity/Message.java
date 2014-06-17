package com.javamail.biz.entity;

import javax.persistence.Entity;

import com.javamail.biz.entity.base.BaseMessage;

/**
 * 消息对象
 * @author hpp
 *
 */
@Entity
public class Message extends BaseMessage {
	
	public Message(){}

	public Message(String ip, String address, String content, String transform,
			String subject, Long userId, String formEmail) {
		super(ip, address, content, transform, subject, userId, formEmail);
	}

	private static final long serialVersionUID = 8576824936503471544L;
}
