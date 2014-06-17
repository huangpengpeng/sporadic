package com.javamail.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.util.JsonUtils;
import com.common.web.RequestUtils;
import com.common.web.ResponseUtils;
import com.javamail.biz.manager.AuthenticationMng;
import com.javamail.biz.manager.UnifiedUserMng;
import com.javamail.biz.manager.UnifiedUserMng.BadCredentialsException;
import com.javamail.biz.manager.UnifiedUserMng.UsernameNotFoundException;
import com.javamail.web.WebErrors;

@Controller
public class RegisterAct {
	public static final String RETURN_URL = "returnUrl";
	public static final String LOGIN_INPUT = "/index.html";

	/**
	 * 注册用户
	 * 
	 * @param username
	 * @param password
	 * @param verifyPassword
	 */
	@RequestMapping("/register.jhtml")
	public void add(HttpServletRequest request, HttpServletResponse response,
			ModelMap model, String username, String password,
			String verifyPassword, String returnUrl) {
		WebErrors errors = validateAdd(request, username, password,
				verifyPassword);
		if (!errors.hasErrors()) {
			String ip = RequestUtils.getIpAddr(request);
			manager.add(username, password, ip);
			try {
				authMng.login(username, verifyPassword, ip, request, response);
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

	/**
	 * 验证用户名是否存在
	 * 
	 * @param request
	 * @param model
	 * @param username
	 */
	@RequestMapping("/check.jhtml")
	public void check(HttpServletRequest request, ModelMap model,
			String username) {
		WebErrors errors = WebErrors.create(request);
		errors.ifBlank(username, "用户名", 20);
		if (!errors.hasErrors()) {
			if (manager.getByUsername(username) != null) {
				errors.addErrorString("用户名已存在");
			}
		}
		if (errors.hasErrors()) {
			errors.toModel(model);
		}
	}

	protected WebErrors validateAdd(HttpServletRequest request,
			String username, String password, String verifyPassword) {
		WebErrors errors = WebErrors.create(request);
		errors.ifBlank(username, "用户名", 20);
		errors.ifBlank(password, "密码", 20);
		errors.ifBlank(verifyPassword, "确认密码", 20);
		if (errors.hasErrors()) {
			return errors;
		}
		if (!password.equals(verifyPassword)) {
			errors.addErrorString("确认密码错误");
		}
		return errors;
	}

	@Autowired
	private UnifiedUserMng manager;
	@Autowired
	private AuthenticationMng authMng;
}
