package com.javamail.biz.entity;

import java.util.Date;

import javax.persistence.Entity;

import com.javamail.biz.entity.base.BaseAuthentication;

@Entity
public class Authentication extends BaseAuthentication {

	public Authentication() {
	}

	public Authentication(String ip, Date loginTime, Long userId, String license) {
		super(ip, loginTime, userId, license);
	}

	/**
	 * 
	 * 描述:判断是否是cookie记忆授权
	 * 
	 * @return
	 * @author huangpengpeng2013年11月8日 上午11:34:41
	 */
	public boolean isRemember() {
		return License.COOKIE.toString().equals(this.getLicense());
	}

	/**
	 * 
	 * 
	 * 描述: 授权类型
	 * 
	 * @author huangpengpeng
	 * @version 1.0
	 * @since 2013年9月6日 上午10:54:39
	 */
	public enum License {
		/**
		 * cookie 认证
		 */
		COOKIE,
		/**
		 * 用户直接认证
		 */
		账户,

		/**
		 * 第三方授权
		 */
		第三方
	};

	private static final long serialVersionUID = 1044917582911743663L;
}
