package com.javamail.biz.entity;

import javax.persistence.Entity;

import com.javamail.biz.entity.base.BaseTripartite;

@Entity
public class Tripartite extends BaseTripartite {

	private static final long serialVersionUID = 5944408767529998021L;

	public Tripartite() {
	}

	public Tripartite(String name, String appSecret, String appKey,
			String authorizeUrl, String tokenUrl, String openIdUrl,
			String userInfoUrl) {
		super(name, appSecret, appKey, authorizeUrl, tokenUrl, openIdUrl,
				userInfoUrl);
	}
}
