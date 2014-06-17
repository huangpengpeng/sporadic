package com.javamail.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.common.util.JsonUtils;
import com.common.web.RequestUtils;
import com.common.web.ResponseUtils;
import com.common.web.springmvc.SessionProvider;
import com.javamail.biz.manager.AuthenticationMng;
import com.javamail.biz.manager.UnifiedUserMng.BadCredentialsException;
import com.javamail.biz.manager.UnifiedUserMng.UsernameNotFoundException;
import com.javamail.web.WebErrors;
import static com.javamail.biz.manager.AuthenticationMng.AUTH_KEY;

@Controller
public class LoginAct {

	public static final String RETURN_URL = "returnUrl";
	public static final String MESSAGE = "message";
	public static final String LOGIN_INPUT = "/login";
	public static final String LOGIN_SUCCESS = "/index";

	/**
	 * 
	 * 描述:登录入口
	 * 
	 * @param returnUrl
	 *            登录成功，并处理后，返回到该地址。
	 * @param message
	 *            登录是提示的信息，比如：“您需要登录后才能继续刚才的操作”，该信息必须用UTF-8编码进行URLEncode。
	 * @param request
	 * @param model
	 * @author huangpengpeng2013年9月5日 下午6:27:15
	 */
	@RequestMapping(value = "/login.jhtml", method = RequestMethod.GET)
	public String input(HttpServletRequest request, ModelMap model) {
		String returnUrl = RequestUtils.getQueryParam(request, RETURN_URL);
		String message = RequestUtils.getQueryParam(request, MESSAGE);
		if (!StringUtils.isBlank(returnUrl)) {
			model.addAttribute(RETURN_URL, returnUrl);
		}
		if (!StringUtils.isBlank(message)) {
			model.addAttribute(MESSAGE, message);
		}
		return LOGIN_INPUT;
	}

	@RequestMapping(value = "/login.jhtml", method = RequestMethod.POST)
	public void submit(String username, String password, String returnUrl,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		WebErrors errors = validateSubmit(username, password, request);
		if (!errors.hasErrors()) {
			try {
				authMng.login(username, password,
						RequestUtils.getIpAddr(request), request, response);
			} catch (UsernameNotFoundException e) {
				errors.addErrorString(e.getMessage());
			} catch (BadCredentialsException e) {
				errors.addErrorString(e.getMessage());
			}
		}
		if (errors.hasErrors()) {
			errors.toModel(model);
		}
		if (!StringUtils.isBlank(returnUrl)) {
			model.addAttribute(RETURN_URL, returnUrl);
		}
		ResponseUtils.renderJson(response, JsonUtils.renderJson(model));
	}

	@RequestMapping(value = "/logout.jhtml")
	public String logout(HttpServletRequest request,
			HttpServletResponse response) {
		Long authId = (Long) session.getAttribute(request, AUTH_KEY);
		if (authId != null) {
			session.logout(request, response);
		}
		authMng.cancleRemember(request, response);
		String returnUrl = RequestUtils.getQueryParam(request, RETURN_URL);
		return "redirect:" + (returnUrl == null ? "/login.jhtml" : returnUrl);
	}

	private WebErrors validateSubmit(String username, String password,
			HttpServletRequest request) {
		WebErrors errors = WebErrors.create(request);
		if (errors.ifOutOfLength(username, "username", 3, 100)) {
			return errors;
		}
		if (errors.ifOutOfLength(password, "password", 3, 32)) {
			return errors;
		}
		return errors;
	}

	@Autowired
	private SessionProvider session;
	@Autowired
	private AuthenticationMng authMng;
}
