package com.javamail.biz.manager.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.util.ParamMap;
import com.javamail.biz.dao.MailMonitorDao;
import com.javamail.biz.entity.MailMonitor;
import com.javamail.biz.manager.MailMonitorMng;

@Transactional
@Service
public class MailMonitorMngImpl implements MailMonitorMng {

	@Override
	public MailMonitor queryForObject(String code) {
		return dao.queryForObject(code);
	}

	@Override
	public void add(String code, String codeType, Long messageId) {
		MailMonitor mailMonitor = new MailMonitor(code, codeType, messageId);
		dao.add(mailMonitor);
	}

	@Override
	public void add(String code, String codeType, Long messageId, String link) {
		MailMonitor mailMonitor = new MailMonitor(code, codeType, messageId,
				link);
		dao.add(mailMonitor);
	}

	@Override
	public Map<String, Object> getMailMonitorInfo(String domain, String content) {
		// 将邮件内容的链接进行转换
		Map<String, Object> converterMap = convert(domain, content);
		// 获取用户是否访问邮件埋点的Map
		Map<String, String> buriedMap = insertBuried(domain,
				(String) converterMap.get("content"));
		converterMap.putAll(buriedMap);
		return converterMap;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected Map<String, Object> convert(String domain, String content) {
		List<Map<String, Object>> aHrefList = new ArrayList<Map<String, Object>>();
		Pattern p = Pattern
				.compile(
						"<a\\s.*?href\\s*=\\s*\'?\"?([^(\\s\")]+)\\s*\'?\"?[^>]*>(.*?)</a>",
						Pattern.CASE_INSENSITIVE | Pattern.DOTALL
								| Pattern.MULTILINE);
		Matcher matcher = p.matcher(content);
		while (matcher.find()) {
			String result = matcher.group();
			// 对结果进行二次正则，匹配出中文字符
			String reg1 = "href\\s*=\\s*\'?\"?([^(\\s\")]+)\\s*\'?\"";
			Pattern p1 = Pattern.compile(reg1);
			Matcher m1 = p1.matcher(result);
			while (m1.find()) {
				String arg0 = m1.group();
				String code = StringUtils.remove(UUID.randomUUID().toString(),
						'-');
				String targetLink = m1.group().replace("href", "")
						.replace("\"", "").replace("=", "");
				aHrefList.add(new ParamMap().add("code", code)
						.add("targetLink", targetLink).getPraamMap());
				result = result.replace(arg0, "href=\"" + "http://" + domain
						+ "/mail/" + code + ".jpeg\"");
			}
			content = content.replace(matcher.group(), result);
		}
		return new ParamMap().add("content", content)
				.add("aHrefList", aHrefList).getPraamMap();
	}

	/**
	 * 插入邮件访问埋点，通过此埋点，可以监控到用户是否访问过此邮件
	 * 
	 * @param original
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map<String, String> insertBuried(String domain, String original) {
		// 生成首次访问code
		String code = StringUtils.remove(UUID.randomUUID().toString(), '-');
		StringBuffer buffer = new StringBuffer(original);
		// 插入一张隐藏的图片作为访问回调
		// 将首次访问的埋点增加到邮件发送内容里面
		buffer.append("<img src='http://" + domain + "/mail/" + code
				+ ".jpeg' style='display:none' />");
		return new ParamMap().add("code", code)
				.add("content", buffer.toString()).getPraamMap();
	}

	@Override
	public MailMonitor queryForObject(Long id) {
		return dao.queryForObject(id);
	}


	@Autowired
	private MailMonitorDao dao;
}
