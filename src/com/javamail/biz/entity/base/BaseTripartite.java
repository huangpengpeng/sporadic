package com.javamail.biz.entity.base;

import javax.persistence.MappedSuperclass;

import com.common.jdbc.BaseEntity;

/**
 * 
 * 
 * 描述:第三方账户类型
 * 
 * @author huangpengpeng
 * @version 1.0
 * @since 2013年9月9日 上午9:16:32
 */
@MappedSuperclass
public class BaseTripartite extends BaseEntity {

	private static final long serialVersionUID = -8790411610126427365L;

	public BaseTripartite(){}
	
	public BaseTripartite(String name, String appSecret,
			String appKey, String authorizeUrl,
			String tokenUrl, String openIdUrl,String userInfoUrl) {
		this.setName(name);
		this.setAppSecret(appSecret);
		this.setAppKey(appKey);
		this.setAuthorizeUrl(authorizeUrl);
		this.setTokenUrl(tokenUrl);
		this.setOpenIdUrl(openIdUrl);
		this.setUserInfoUrl(userInfoUrl);
	}

	/**
	 * 类型名称（腾讯，微博）
	 */
	private String name;

	/**
	 * 应用编号
	 */
	private String appSecret;

	/**
	 * 应用密钥
	 */
	private String appKey;

	/**
	 * 授权地址
	 */
	private String authorizeUrl;


	/**
	 * 根据授权获取Token地址
	 */
	private String tokenUrl;

	/**
	 * 根据TOKEN获取用户唯一标示OPENId地址
	 */
	private String openIdUrl;
	
	/**
	 * 获取用户信息地址
	 */
	private String userInfoUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAuthorizeUrl() {
		return authorizeUrl;
	}

	public void setAuthorizeUrl(String authorizeUrl) {
		this.authorizeUrl = authorizeUrl;
	}


	public String getTokenUrl() {
		return tokenUrl;
	}

	public void setTokenUrl(String tokenUrl) {
		this.tokenUrl = tokenUrl;
	}

	public String getOpenIdUrl() {
		return openIdUrl;
	}

	public void setOpenIdUrl(String openIdUrl) {
		this.openIdUrl = openIdUrl;
	}

	public String getUserInfoUrl() {
		return userInfoUrl;
	}

	public void setUserInfoUrl(String userInfoUrl) {
		this.userInfoUrl = userInfoUrl;
	}
}
