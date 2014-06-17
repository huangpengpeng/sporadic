package com.javamail.biz.entity.base;

import javax.persistence.MappedSuperclass;

import com.common.jdbc.BaseEntity;

/**
 * 联系人
 * 
 * @author hpp
 * 
 */
@MappedSuperclass
public class BaseContact extends BaseEntity {

	private static final long serialVersionUID = 2558477177553792445L;

	public BaseContact(Long fromId, Long toId) {
		this.setFromId(fromId);
		this.setToId(toId);
	}

	/**
	 * 发件箱账户编号
	 */
	private Long fromId;

	/**
	 * 收件箱
	 */
	private Long toId;

	public Long getFromId() {
		return fromId;
	}

	public void setFromId(Long fromId) {
		this.fromId = fromId;
	}

	public Long getToId() {
		return toId;
	}

	public void setToId(Long toId) {
		this.toId = toId;
	}
	
}
