package com.javamail.biz.entity;

import java.util.Date;

import javax.persistence.Entity;

import com.javamail.biz.entity.base.BaseMessageTo;

/**
 * 消息去向
 * 
 * @author hpp
 * 
 */
@Entity
public class MessageTo extends BaseMessageTo {

	public MessageTo() {
	}

	public MessageTo(String from, String to, Long messageId, String toType) {
		super(from, to, messageId, toType, null);
	}

	public void init() {
		if (getSendTime() == null) {
			setSendTime(new Date());
		}
	}

	private static final long serialVersionUID = -8276480672145747464L;

	/**
	 * 
	 * 
	 * 描述:消息接收类型
	 * 
	 * @author huangpengpeng
	 * @version 1.0
	 * @since 2013年11月11日 上午11:16:04
	 */
	public enum ToType {
		抄送, 密送, 直送, 图片代理
	}
}
