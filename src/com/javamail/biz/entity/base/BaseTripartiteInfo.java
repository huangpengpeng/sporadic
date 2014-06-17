package com.javamail.biz.entity.base;

import javax.persistence.MappedSuperclass;

import com.common.jdbc.BaseEntity;

@MappedSuperclass
public class BaseTripartiteInfo extends BaseEntity {

	private static final long serialVersionUID = -6022929391593849953L;

	public BaseTripartiteInfo() {
	}

	public BaseTripartiteInfo(String openId, Long userId, Long tripartiteId) {
		this.setUserId(userId);
		this.setTripartiteId(tripartiteId);
		this.setOpenId(openId);
	}

	/**
	 * 账户名称
	 */
	private String openId;

	/**
	 * 所属用户
	 */
	private Long userId;

	/**
	 * 账户类型
	 */
	private Long tripartiteId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTripartiteId() {
		return tripartiteId;
	}

	public void setTripartiteId(Long tripartiteId) {
		this.tripartiteId = tripartiteId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
}
