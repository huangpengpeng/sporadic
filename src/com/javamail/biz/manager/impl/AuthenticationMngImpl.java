package com.javamail.biz.manager.impl;

import static com.common.util.CryptoDesUtils.PASSWORD_CRYPT_KEY;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.util.CryptoDesUtils;
import com.common.web.CookieUtils;
import com.common.web.springmvc.SessionProvider;
import com.javamail.biz.dao.AuthenticationDao;
import com.javamail.biz.entity.Authentication;
import com.javamail.biz.entity.Authentication.License;
import com.javamail.biz.entity.UnifiedUser;
import com.javamail.biz.manager.AuthenticationMng;
import com.javamail.biz.manager.UnifiedUserMng;
import com.javamail.biz.manager.UnifiedUserMng.BadCredentialsException;
import com.javamail.biz.manager.UnifiedUserMng.UsernameNotFoundException;

@Transactional
@Service
public class AuthenticationMngImpl implements AuthenticationMng {

	@Override
	public Authentication retrieve(Long authId) {
		return dao.queryForObject(authId);
	}

	protected long add(String ip, Date loginTime, Long userId, String license) {
		return dao.add(new Authentication(ip, loginTime, userId, license));
	}

	@Override
	public Authentication login(String username, String password, String ip,
			HttpServletRequest request, HttpServletResponse response)
			throws UsernameNotFoundException, BadCredentialsException {
		UnifiedUser user = unifiedUserMng.login(username, password);
		Long authId = add(ip, new Date(), user.getId(), License.账户.toString());
		session.setAttribute(request, AUTH_KEY, authId);
		Authentication auth = retrieve(authId);
		addRemember(username, request, response);
		return auth;
	}

	@Override
	public Authentication thirdParty(String username, String ip,
			HttpServletRequest request, HttpServletResponse response)
			throws UsernameNotFoundException, BadCredentialsException {
		UnifiedUser user = unifiedUserMng.getByUsername(username);
		addRemember(username, request, response);
		Long authId = add(ip, new Date(), user.getId(), License.第三方.toString());
		session.setAttribute(request, AUTH_KEY, authId);
		Authentication auth = retrieve(authId);
		return auth;
	}

	@Override
	public Authentication remember(String rememberValue, String ip,
			HttpServletRequest request, HttpServletResponse response)
			throws UsernameNotFoundException, BadCredentialsException {
		try {
			Long userId = Long.valueOf(CryptoDesUtils.decrypt(rememberValue,
					PASSWORD_CRYPT_KEY));
			UnifiedUser user = unifiedUserMng.queryForObject(userId);
			Long authId = add(ip, new Date(), user.getId(),
					License.COOKIE.toString());
			session.setAttribute(request, AUTH_KEY, authId);
			return retrieve(authId);
		} catch (Exception e) {
			throw new BadCredentialsException("cookie 认证失败[" + rememberValue
					+ "]");
		}
	}

	
	@Override
	public void cancleRemember(HttpServletRequest request,
			HttpServletResponse response) {
		session.logout(request, response);
		if (CookieUtils.getCookie(request, COOKIE_KEY) != null) {
			CookieUtils.cancleCookie(request, response, COOKIE_KEY, null);
		}
	}

	/**
	 * 
	 * 描述:cookie 设置记忆登录
	 * 
	 * @param username
	 * @param request
	 * @param response
	 * @author huangpengpeng2013年9月10日 下午3:02:20
	 */
	protected void addRemember(String username, HttpServletRequest request,
			HttpServletResponse response) {
		String cookieValue = "";
		try {
			UnifiedUser user = unifiedUserMng.getByUsername(username);
			cookieValue = CryptoDesUtils.encrypt(String.valueOf(user.getId()),
					PASSWORD_CRYPT_KEY);
			CookieUtils.addCookie(request, response, COOKIE_KEY, cookieValue,
					Integer.MAX_VALUE, null);
		} catch (Exception e) {
		}
	}

	@Autowired
	private UnifiedUserMng unifiedUserMng;
	@Autowired
	private SessionProvider session;
	@Autowired
	private AuthenticationDao dao;
}
