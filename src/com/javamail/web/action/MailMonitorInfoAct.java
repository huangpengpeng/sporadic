package com.javamail.web.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sun.misc.BASE64Decoder;

import com.common.web.RequestUtils;
import com.javamail.biz.entity.MailMonitor;
import com.javamail.biz.entity.MailMonitor.MailMonitorType;
import com.javamail.biz.manager.MailMonitorInfoMng;
import com.javamail.biz.manager.MailMonitorMng;

/**
 * 监控消息
 * 
 * @author hpp
 * 
 */
@Controller
public class MailMonitorInfoAct {

	/**
	 * 增加邮件监控记录信息
	 * 
	 * @param code
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping(value = "/mail/{code}.jpeg")
	public void add(@PathVariable("code") String code,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		// 验证code是否存在
		MailMonitor mailMonitor = mailMonitorMng.queryForObject(code);
		Assert.notNull(mailMonitor);
		String ip = RequestUtils.getIpAddr(request);
		// 如果是查看邮件类型
		if (StringUtils.equals(MailMonitorType.邮件查看.toString(),
				mailMonitor.getType())) {
			manager.save(mailMonitor.getId(), ip);
		}
		// 如果是邮件内容跳转类型
		else if (StringUtils.equals(MailMonitorType.邮件内容点击.toString(),
				mailMonitor.getType())) {
			manager.save(mailMonitor.getId(), ip);
			response.sendRedirect(mailMonitor.getLink());
		}
	}

	private final static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

	/**
	 * 增加图片邮件监控
	 * 
	 * @param code
	 * @param response
	 * @param request
	 * @throws IOException
	 */
	@RequestMapping(value = "/imageMail/{code}.jpeg")
	public void addImageMail(@PathVariable("code") String code,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		byte[] bytes = decoder
				.decodeBuffer("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");
		InputStream buffin = new ByteArrayInputStream(bytes);
		BufferedImage img = ImageIO.read(buffin);
		ImageIO.write(img, "PNG", getResponse(response).getOutputStream());
	}

	/**
	 * 获取response
	 * 
	 * @param response
	 * @return
	 */
	protected HttpServletResponse getResponse(HttpServletResponse response) {
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/png");
		return response;
	}

	@Autowired
	private MailMonitorInfoMng manager;
	@Autowired
	private MailMonitorMng mailMonitorMng;
}
