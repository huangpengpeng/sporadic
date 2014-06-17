package com.javamail.web.filter;

import static com.javamail.biz.manager.AuthenticationMng.AUTH_KEY;
import static com.javamail.biz.manager.AuthenticationMng.USER_KEY;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.common.web.springmvc.HttpSessionProvider;
import com.common.web.springmvc.SessionProvider;
import com.javamail.biz.entity.Authentication;
import com.javamail.biz.entity.UnifiedUser;
import com.javamail.biz.manager.AuthenticationMng;
import com.javamail.biz.manager.UnifiedUserMng;

/**
 * 
 * 
 * 描述: 普通用户权限拦截去
 * 
 * @author huangpengpeng
 * @version 1.0
 * @since 2013年9月4日 下午4:35:30
 */
@WebFilter(filterName = "ordinary", urlPatterns = { "*.html", "*.jhtml" })
public class MemberFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		Long authId = (Long) session.getAttribute(request, AUTH_KEY);
		// 没有登录情况下
		if (authId == null) {
			String relativePath = getRelativePath(request);
			if (exclude(getRelativePath(request))) {
				chain.doFilter(request, response);
			} else {
				StringBuffer stringBuffer = new StringBuffer(LOGIN_INPUT);
				if (relativePath != null) {
					stringBuffer.append("?returnUrl=").append(relativePath);
				}
				response.sendRedirect(stringBuffer.toString());
			}
		}
		// 已经登录的情况
		else {
			Authentication auth = authMng.retrieve(authId);
			// 下面是已经登录的情况下
			UnifiedUser unifiedUser = unifiedUserMng.queryForObject(auth
					.getUserId());
			request.setAttribute(USER_KEY, unifiedUser);
			chain.doFilter(request, response);
		}
	}

	private String[] excludeUrls = new String[] { "/index.html",
			"/tripartite/input.jhtml", "/tripartite/login.jhtml",
			"/login.html", "/login.jhtml", "/register.html", "/register.jhtml",
			"/check.jhtml" };

	/**
	 * 不需要登录就可以访问的权限
	 * 
	 * @param uri
	 * @return
	 */
	private boolean exclude(String uri) {
		if (excludeUrls != null) {
			for (String exc : excludeUrls) {
				if (exc.equals(uri)) {
					return true;
				}
			}
		}
		return false;
	}

	protected String getRelativePath(HttpServletRequest request) {
		if (request.getAttribute(RequestDispatcher.INCLUDE_REQUEST_URI) != null) {
			String result = (String) request
					.getAttribute(RequestDispatcher.INCLUDE_PATH_INFO);
			if (result == null) {
				result = (String) request
						.getAttribute(RequestDispatcher.INCLUDE_SERVLET_PATH);
			} else {
				result = (String) request
						.getAttribute(RequestDispatcher.INCLUDE_SERVLET_PATH)
						+ result;
			}
			if ((result == null) || (result.equals(""))) {
				result = "/";
			}
			return (result);
		}

		// No, extract the desired path directly from the request
		String result = request.getPathInfo();
		if (result == null) {
			result = request.getServletPath();
		} else {
			result = request.getServletPath() + result;
		}
		if ((result == null) || (result.equals(""))) {
			result = "/";
		}
		return (result);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(arg0.getServletContext());
		if (authMng == null)
			authMng = ctx.getBean(AuthenticationMng.class);
		if (unifiedUserMng == null)
			unifiedUserMng = ctx.getBean(UnifiedUserMng.class);
	}

	private final static String LOGIN_INPUT = "/index.html";
	private SessionProvider session = new HttpSessionProvider();
	private AuthenticationMng authMng;
	private UnifiedUserMng unifiedUserMng;
}
