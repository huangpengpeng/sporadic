package com.javamail.biz.manager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javamail.biz.entity.Authentication;
import com.javamail.biz.manager.UnifiedUserMng.BadCredentialsException;
import com.javamail.biz.manager.UnifiedUserMng.UsernameNotFoundException;

public interface AuthenticationMng {

	/**
	 * 认证信息session key
	 */
	public static final String AUTH_KEY = "auth_key";

	/**
	 * 用户信息KEY
	 */
	public static final String USER_KEY = "user_key";

	/**
	 * COOKIE　认证KEY
	 */
	public static final String COOKIE_KEY = "cookie_key";

	/**
	 * 通过认证ID，获得认证信息。本方法会检查认证是否过期。
	 * 
	 * @param authId
	 *            认证ID
	 * @return 返回Authentication对象。如果authId不存在或已经过期，则返回null。
	 */
	Authentication retrieve(Long authId);

	/**
	 * 
	 * 描述:授权登录
	 * 
	 * @param username
	 * @param password
	 * @param ip
	 * @param request
	 * @param response
	 * @return
	 * @author huangpengpeng2013年9月6日 上午9:17:11
	 * @throws BadCredentialsException
	 * @throws UsernameNotFoundException
	 */
	Authentication login(String username, String password, String ip,
			HttpServletRequest request, HttpServletResponse response)
			throws UsernameNotFoundException, BadCredentialsException;

	/**
	 * 
	 * 描述:无密码授权
	 * 
	 * @param username
	 * @param ip
	 * @param request
	 * @param response
	 * @return
	 * @throws UsernameNotFoundException
	 * @throws BadCredentialsException
	 * @author huangpengpeng2013年9月9日 下午12:57:39
	 */
	Authentication thirdParty(String username, String ip,
			HttpServletRequest request, HttpServletResponse response)
			throws UsernameNotFoundException, BadCredentialsException;

	/**
	 * 
	 * 描述:cookie 记忆登录
	 * 
	 * @param rememberValue
	 * @param request
	 * @param response
	 * @return
	 * @throws UsernameNotFoundException
	 * @throws BadCredentialsException
	 * @author huangpengpeng2013年9月10日 下午3:07:02
	 */
	Authentication remember(String rememberValue, String ip,
			HttpServletRequest request, HttpServletResponse response)
			throws UsernameNotFoundException, BadCredentialsException;

	/**
	 * 
	 * 描述:取消 Cookie自动记忆登录功能
	 * 
	 * @param request
	 * @param response
	 * @author huangpengpeng2013年9月10日 下午5:03:03
	 */
	void cancleRemember(HttpServletRequest request, HttpServletResponse response);

}
