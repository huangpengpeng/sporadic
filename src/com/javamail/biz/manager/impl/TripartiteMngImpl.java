package com.javamail.biz.manager.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.http.HttpClient;
import com.common.http.HttpException;
import com.common.http.PostParameter;
import com.common.http.Response;
import com.common.util.JsonUtils;
import com.javamail.biz.dao.TripartiteDao;
import com.javamail.biz.entity.Tripartite;
import com.javamail.biz.manager.TripartiteMng;

@Transactional
@Service
public class TripartiteMngImpl implements TripartiteMng {

	@Override
	public Tripartite queryForObject(Long id) {
		return dao.queryForObject(id);
	}

	@Override
	public String getAuthorize(Long id, String back) {
		Tripartite tripartite = queryForObject(id);
		StringBuffer buffer = new StringBuffer(tripartite.getAuthorizeUrl());
		buffer.append("&client_id=").append(tripartite.getAppSecret());
		buffer.append("&redirect_uri=").append(back);
		return "redirect:" + buffer.toString();
	}

	@Override
	public Token getAccessToken(Long id, String code, String back)
			throws AuthenticationException {
		Tripartite tripartite = queryForObject(id);
		PostParameter[] parameters = new PostParameter[] {
				new PostParameter("grant_type", "authorization_code"),
				new PostParameter("client_id", tripartite.getAppSecret()),
				new PostParameter("client_secret", tripartite.getAppKey()),
				new PostParameter("code", code),
				new PostParameter("redirect_uri", back) };
		Response response;
		try {
			response = new HttpClient(1500, 50000, 50000, 1024 * 10240).post(
					tripartite.getTokenUrl(), parameters);
		} catch (HttpException e) {
			throw new AuthenticationException("获取授权TOKEN HTTPCLIENT 调用失败"
					+ e.getMessage(), e);
		}
		Map<String, Object> toMap = parseQueryString(response
				.getResponseAsString());
		if (validateError(toMap)) {
			throw new AuthenticationException("获取用户授权返回失败:"
					+ response.getResponseAsString());
		}
		String access_token = getParams(toMap, "access_token");
		String openId = toMap.get("uid") == null ? openId(
				tripartite.getOpenIdUrl(), access_token) : (String) toMap
				.get("uid");
		return new Token(access_token, openId);
	}

	@Override
	public String getUsername(Long id, String token, String openId)
			throws AuthenticationException {
		Tripartite tripartite = queryForObject(id);
		String url = tripartite.getUserInfoUrl();
		url = url.replace("[access_token]", token)
				.replace("[oauth_consumer_key]", tripartite.getAppSecret())
				.replace("[openid]", openId).replace("[uid]", openId);
		Response response = null;
		try {
			response = new HttpClient(1500, 50000, 50000, 1024 * 10240).get(url);
		} catch (HttpException e) {
			throw new AuthenticationException("根据授权获取用户信息 调用失败"
					+ e.getMessage(), e);
		}
		Map<String, Object> toMap = parseQueryString(response
				.getResponseAsString());
		toMap = toMap.get("ret") == null ? toMap.get("error") != null ? null
				: toMap : toMap.get("ret").equals(0) ? toMap : null;
		if (validateError(toMap)) {
			throw new AuthenticationException("获取用户授权返回失败:"
					+ response.getResponseAsString());
		}
		return getParams(toMap, "nickname", "screen_name");

	}
	
	
	/**
	 * 
	 * 描述:获取用户第三方唯一识别吗
	 * 
	 * @param url
	 * @param token
	 *            用户授权
	 * @return
	 * @throws HttpException
	 * @author huangpengpeng2013年9月10日 上午9:46:44
	 * @throws AuthenticationException
	 */
	protected String openId(String url, String token)
			throws AuthenticationException {
		StringBuffer buffer = new StringBuffer(url);
		if (token != null) {
			buffer.append("?access_token=").append(token);
		}
		Response response = null;
		try {
			response = new HttpClient(1500, 50000, 50000, 1024 * 10240).get(buffer.toString());
		} catch (HttpException e) {
			throw new AuthenticationException("根据授权获取用户OPENID失败"
					+ e.getMessage(), e);
		}
		String text = response.getResponseAsString();
		return (String) JsonUtils.toMap(
				text.replace("callback(", "").replace(")", "")).get("openid");
	}

	
	/**
	 * 
	 * 描述:验证返回是否错误
	 * 
	 * @param toMap
	 * @return
	 * @author huangpengpeng2013年9月10日 上午10:30:10
	 */
	protected boolean validateError(Map<String, Object> toMap) {
		return toMap.get("error") != null
				|| (toMap.get("ret") != null && !Integer.valueOf(0).equals(
						toMap.get("ret")));
	}

	
	/**
	 * 
	 * 描述:获取用户授权返回的参数
	 * 
	 * @param toMap
	 * @param strings
	 * @return
	 * @throws AuthenticationException
	 * @author huangpengpeng2013年9月10日 上午9:41:33
	 */
	protected String getParams(Map<String, Object> toMap, String... strings)
			throws AuthenticationException {
		for (String str : strings) {
			String val = (String) toMap.get(str);
			if (val != null) {
				return val;
			}
		}
		throw new AuthenticationException("获取用户授权参数为空:[" + strings + "]");
	}

	/**
	 * 
	 * 描述:将字符串解析成Map
	 * 
	 * @param s
	 * @return
	 * @author huangpengpeng2013年9月10日 上午9:54:55
	 */
	protected Map<String, Object> parseQueryString(String s) {
		if (s == null) {
			throw new IllegalArgumentException();
		}
		Map<String, Object> toMap = new HashMap<String, Object>();
		try {
			toMap = JsonUtils.toMap(s.replace("callback(", "").replace(" );",
					""));
		} catch (IllegalStateException e) {
			for (String str : s.split("&")) {
				String[] valArray = str.split("=");
				toMap.put(valArray[0], valArray[1]);
			}
		}
		return toMap;
	}

	
	@Autowired
	private TripartiteDao dao;
}
