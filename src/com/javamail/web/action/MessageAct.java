package com.javamail.web.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.javamail.biz.manager.AuthenticationMng.AUTH_KEY;

import com.common.web.RequestUtils;
import com.common.web.springmvc.SessionProvider;
import com.javamail.biz.entity.Authentication;
import com.javamail.biz.manager.AuthenticationMng;
import com.javamail.biz.manager.MessageMng;
import com.javamail.web.WebErrors;
import com.javamail.web.springmvc.RedirectView;

@Controller
@RequestMapping(value = "/message/")
public class MessageAct {

	@RequestMapping(value = "send.jhtml")
	public String send(HttpServletRequest request, ModelMap model,
			RedirectAttributes redirectAttributes, String form,
			String password, String to, String subject, String text) {
		Long authId = (Long) session.getAttribute(request, AUTH_KEY);
		Authentication auth = authMng.retrieve(authId);
		WebErrors errors = validateSend(request, form, password, to, subject,
				text);
		if (errors.hasErrors()) {
			return errors.showErrorPage(model, "/send");
		}
		try {
			manager.send(form, password, to, subject, text, auth.getUserId(),
					RequestUtils.getIpAddr(request), request.getServerName());
		} catch (MailAuthenticationException e) {
			errors.addErrorString("发件箱用户名或密码错误");
		} catch (MailSendException e) {
			errors.addErrorString("链接远程服务器失败");
		}
		if (errors.hasErrors()) {
			model.addAttribute("form", form);
			model.addAttribute("to", to);
			model.addAttribute("subject", subject);
			model.addAttribute("text", text);
			return errors.showErrorPage(model, "/send");
		}
		return RedirectView.create(redirectAttributes, "/send.html")
				.redirectPage();
	}
	
	
	protected WebErrors validateSend(HttpServletRequest request,
			String formEmail, String formPwd, String to, String subject,
			String content) {
		WebErrors errors = WebErrors.create(request);
		errors.ifNotEmail(formEmail, "发件箱", 255);
		errors.ifBlank(formPwd, "发件箱密码", 255);
		errors.ifNotEmail(to, "收件箱", 255);
		errors.ifBlank(subject, "主题", 30);
		errors.ifBlank(content, "正文", 5000);
		return errors;
	}
	
	@Autowired
	private MessageMng manager;
	@Autowired
	private AuthenticationMng authMng;
	@Autowired
	private SessionProvider session;
}
