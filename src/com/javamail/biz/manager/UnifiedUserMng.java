package com.javamail.biz.manager;

import com.javamail.biz.entity.UnifiedUser;

public interface UnifiedUserMng {

	/**
	 * 
	 * 描述:用户登录
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws UsernameNotFoundException
	 * @throws BadCredentialsException
	 * @author huangpengpeng2013年11月10日 下午2:05:05
	 */
	UnifiedUser login(String username, String password)
			throws UsernameNotFoundException, BadCredentialsException;

	/**
	 * 
	 * 描述:根据编号查询用户信息
	 * 
	 * @param id
	 * @return
	 * @author huangpengpeng2013年11月10日 下午2:05:14
	 */
	UnifiedUser queryForObject(Long id);

	/**
	 * 
	 * 描述:根据用户名获取用户信息
	 * 
	 * @param username
	 * @return
	 * @author huangpengpeng2013年11月10日 下午2:34:14
	 */
	UnifiedUser getByUsername(String username);

	/**
	 * 
	 * 描述:增加用户,密码为空
	 * 
	 * @param username
	 * @param ip
	 * @return
	 * @author huangpengpeng2013年9月9日 下午2:10:57
	 */
	UnifiedUser add(String username, String ip);

	UnifiedUser add(String username, String password, String ip);

	/**
	 * 
	 * 
	 * 描述:用户名不存在
	 * 
	 * @author huangpengpeng
	 * @version 1.0
	 * @since 2013年9月6日 上午9:45:15
	 */
	public static class UsernameNotFoundException extends Exception {

		private static final long serialVersionUID = 2939092112620491003L;

		public UsernameNotFoundException() {
		}

		public UsernameNotFoundException(String msg) {
			super(msg);
		}
	}

	/**
	 * 
	 * 
	 * 描述:认证信息错误异常。如：密码错误。
	 * 
	 * @author huangpengpeng
	 * @version 1.0
	 * @since 2013年9月6日 上午9:45:52
	 */
	public static class BadCredentialsException extends Exception {

		private static final long serialVersionUID = 2939092112620491003L;

		public BadCredentialsException() {
		}

		public BadCredentialsException(String msg) {
			super(msg);
		}
	}
}
