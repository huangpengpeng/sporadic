package com.javamail.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.common.util.StringUtils.IncludeMode;
import com.common.web.RequestUtils;
import com.common.web.springmvc.SessionProvider;
import com.javamail.biz.entity.Tripartite;
import com.javamail.biz.entity.TripartiteInfo;
import com.javamail.biz.entity.UnifiedUser;
import com.javamail.biz.manager.AuthenticationMng;
import com.javamail.biz.manager.TripartiteInfoMng;
import com.javamail.biz.manager.TripartiteMng;
import com.javamail.biz.manager.TripartiteMng.AuthenticationException;
import com.javamail.biz.manager.TripartiteMng.Token;
import com.javamail.biz.manager.UnifiedUserMng;
import com.javamail.biz.manager.UnifiedUserMng.BadCredentialsException;
import com.javamail.biz.manager.UnifiedUserMng.UsernameNotFoundException;
import com.javamail.web.WebErrors;

@Controller
@RequestMapping(value = "/tripartite")
public class TripartiteAct {

	/**
	 * 
	 * 描述:三方用户授权入口
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @author huangpengpeng2013年9月6日 下午5:24:16
	 */
	@RequestMapping(value = "/input.jhtml")
	public String input(HttpServletRequest request, RedirectAttributes model,
			Long id, String returnUrl) {
		WebErrors errors = validateInput(id, returnUrl, request);
		if (!errors.hasErrors()) {
			Tripartite tripartite = manager.queryForObject(id);
			session.setAttribute(request, RETURN_URL, returnUrl);
			session.setAttribute(request, TYPE, tripartite);
			// 跳转到认证地址
			return manager.getAuthorize(id, getBack(request));
		}
		return errors.showErrorPage(model, LOGIN_INPUT);
	}

	/**
	 * 
	 * 描述:第三方回调登录
	 * 
	 * @param request
	 * @param responsee
	 * @param model
	 * @return
	 * @author huangpengpeng2013年9月9日 上午9:08:05
	 * @throws BadCredentialsException
	 * @throws UsernameNotFoundException
	 */
	@RequestMapping(value = "/login.jhtml")
	public String submit(HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes model, String code) throws UsernameNotFoundException, BadCredentialsException {
		Tripartite tripartite = (Tripartite) session
				.getAttribute(request, TYPE);
		WebErrors errors = validateSubmit(tripartite, code, request);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model, LOGIN_INPUT);
		}
		try {
			String returnUrl = (String) session.getAttribute(request,
					RETURN_URL);
			/****************** 获取第三方用户授权token *************************/
			Token token = manager.getAccessToken(tripartite.getId(), code,
					getBack(request));
			/******************** tokens[1] openId 查看用户是否登陆过 *************/
			TripartiteInfo tripartiteInfo = tripartiteInfoMng
					.queryForObject(token.getOpenId());
			/****************** 获取第三方用户信息 *******************************/
			String username = manager.getUsername(tripartite.getId(),
					token.getAccessToken(), token.getOpenId());
			// tripartiteInfo 存在 IfEnable 说明该用户已经启用
			if (tripartiteInfo != null) {
				UnifiedUser user = unifiedUserMng.queryForObject(tripartiteInfo
						.getUserId());
				/****************** 第三方授权系统无密码授权登录 ********************/
				authMng.thirdParty(user.getUsername(),
						RequestUtils.getIpAddr(request), request, response);
			}
			// 执行注册操作
			else {
				tripartiteInfo = tripartiteInfoMng.add(
						com.common.util.StringUtils.spec(username,
								new IncludeMode[] { IncludeMode.中文,
										IncludeMode.数字, IncludeMode.字母,
										IncludeMode.下划线 }), token.getOpenId(),
						tripartite.getId(), RequestUtils.getIpAddr(request));
				UnifiedUser user = unifiedUserMng.queryForObject(tripartiteInfo
						.getUserId());
				// 第三方授权系统无密码授权登录
				authMng.thirdParty(user.getUsername(),
						RequestUtils.getIpAddr(request), request, response);
			}
			// 提交临时数据
			return returnUrl == null ? LOGIN_SUCCESS : "redirect:" + returnUrl;
		} catch (AuthenticationException e) {
			errors.addErrorString("授权认证失败");
			log.error("用户授权认证失败:[" + e.getMessage() + "]", e);
		}
		return errors.showErrorPage(model, LOGIN_INPUT);
	}

	/**
	 * 
	 * 描述:获取登录回答地址
	 * 
	 * @param request
	 * @return
	 * @author huangpengpeng2013年11月11日 上午9:53:08
	 */
	protected String getBack(HttpServletRequest request) {
		String url = "tripartite/login.jhtml";
		if (request.getServerName() != null) {
			url = "http://" + request.getServerName() + "/" + url;
		}
		return url;
	}

	protected WebErrors validateSubmit(Tripartite tripartite, String code,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (tripartite == null) {
			errors.addErrorString("参数错误,登录类型不能为空");
		}
		if (StringUtils.isBlank(code)) {
			errors.addErrorString("授权码不能为空");
		}
		return errors;
	}

	protected WebErrors validateInput(Long id, String reutrnUrl,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (id == null) {
			errors.addErrorString("第三方登录类型不能为空");
		}
		if (!errors.hasErrors()) {
			return errors;
		}
		if (manager.queryForObject(id) == null) {
			errors.addErrorString("第三方账户编号:[" + id + "]不存在");
		}
		return errors;
	}

	private final static String TYPE = "type";
	private static final String RETURN_URL = "returnUrl";
	public static final String LOGIN_INPUT = "redirect:/index.html";
	public static final String LOGIN_SUCCESS = "redirect:/index.html";

	@Autowired
	private TripartiteMng manager;
	@Autowired
	private TripartiteInfoMng tripartiteInfoMng;
	@Autowired
	private UnifiedUserMng unifiedUserMng;
	@Autowired
	private SessionProvider session;
	@Autowired
	private AuthenticationMng authMng;
	private Logger log = LoggerFactory.getLogger(TripartiteAct.class);
}
