package com.javamail.web.springmvc;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 
 * 
 * 描述:Spring Mvc 跳转工具类
 * 
 * @author huangpengpeng
 * @version 1.0
 * @since 2013年9月12日 下午4:03:33
 */
public class RedirectView {

	private RedirectAttributes redirectAttributes;

	/**
	 * 成功是跳转页面
	 */
	private String redirectPage;

	/**
	 * 成功跳转消息
	 */
	public final static String DEFAULT_MESSAGE = "操作成功";
	public final static String REDIRECT = "redirect:";

	public RedirectView(RedirectAttributes redirectAttributes,
			String redirectPage) {
		this.redirectAttributes = redirectAttributes;
		this.redirectPage = redirectPage;
	}

	/**
	 * 默认成功信息属性名称
	 */
	public static final String SUCCESS_ATTR_NAME = "success";

	/**
	 * 
	 * 描述:将值保存在model 执行redirect是会一直保存到下个页面
	 * 
	 * @param model
	 * @return
	 * @author huangpengpeng2013年9月12日 下午4:04:41
	 */
	public static RedirectView create(RedirectAttributes model,
			String redirectPage) {
		return new RedirectView(model, redirectPage);
	}

	public String redirectPage() {
		add(SUCCESS_ATTR_NAME, DEFAULT_MESSAGE);
		return getRedirectPage();
	}
	
	public String redirectPage(String message) {
		add(SUCCESS_ATTR_NAME, message);
		return getRedirectPage();
	}

	public RedirectView add(String attributeName, String attributeValue) {
		redirectAttributes.addFlashAttribute(attributeName, attributeValue);
		return this;
	}

	protected String getRedirectPage() {
		if (redirectPage.contains("redirect:")) {
			return redirectPage;
		} else {
			return REDIRECT + redirectPage;
		}
	}
}
