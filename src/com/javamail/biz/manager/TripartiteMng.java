package com.javamail.biz.manager;

import com.javamail.biz.entity.Tripartite;

public interface TripartiteMng {

	Tripartite queryForObject(Long id);

	/**
	 * 
	 * 描述:获取用户授权地址
	 * 
	 * @param id
	 * @param back
	 * @return
	 * @author huangpengpeng2013年9月10日 上午9:02:53
	 */
	String getAuthorize(Long id, String back);

	/**
	 * 
			
			* 描述:用户授权Token
			*
			* @author huangpengpeng
			* @version 1.0
			* @since 2013年11月10日 下午2:57:55
	 */
	public static class Token{
		public Token(String accessToken,String openId){
			this.setAccessToken(accessToken);
			this.setOpenId(openId);
		}
		//用户Token
		private String accessToken;
		//用户唯一编号
		private String openId;
		public String getAccessToken() {
			return accessToken;
		}
		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}
		public String getOpenId() {
			return openId;
		}
		public void setOpenId(String openId) {
			this.openId = openId;
		}
	}
	
	/**
	 * 
	 * 描述:根据用户授权 获取token
	 * 
	 * @param id
	 * @return
	 * @author huangpengpeng2013年9月10日 上午9:06:00
	 * @throws AuthenticationException 
	 */
	Token getAccessToken(Long id, String code, String back) throws AuthenticationException;
	
	/**
	 * 
	 * 描述:
	 * 
	 * @param id
	 * @param token
	 *            用户授权TOKEN
	 * @param name
	 *            用户唯一是别名 （UID|OPENID）
	 * @return
	 * @author huangpengpeng2013年9月10日 上午9:07:55
	 * @throws AuthenticationException
	 */
	String getUsername(Long id, String token,String openId)
			throws AuthenticationException;
	
	/**
	 * 
			
			* 描述:授权认证失败
			*
			* @author huangpengpeng
			* @version 1.0
			* @since 2013年9月10日 上午9:19:23
	 */
	public static class AuthenticationException extends Exception{
		
		private static final long serialVersionUID = 3560127901291633000L;

		public AuthenticationException() {
		}

		public AuthenticationException(String msg) {
			super(msg);
		}
		public AuthenticationException(String msg,Throwable e) {
			super(msg,e);
		}
	}
}
