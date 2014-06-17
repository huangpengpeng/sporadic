package com.javamail.biz.entity;

import javax.persistence.Entity;

import com.javamail.biz.entity.base.BaseTripartiteInfo;

@Entity
public class TripartiteInfo extends BaseTripartiteInfo {

	public TripartiteInfo() {
	}

	public TripartiteInfo(String openId, Long userId, Long tripartiteId) {
		super(openId, userId, tripartiteId);
	}


	private static final long serialVersionUID = -2282690998181338220L;
}
