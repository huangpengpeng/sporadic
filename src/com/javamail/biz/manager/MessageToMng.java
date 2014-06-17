package com.javamail.biz.manager;

import java.util.Date;
import java.util.List;

import com.javamail.biz.entity.MessageTo;

public interface MessageToMng {

	List<MessageTo> query(Long fromId,Long toId,Date startDate,Date endDate);
	
	/**
	 * 增加消息去向
	 * @param messageTo
	 */
	void add(String from,String to,Long messageId,
			 String toType);
}
