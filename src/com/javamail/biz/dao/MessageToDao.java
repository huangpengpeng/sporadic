package com.javamail.biz.dao;

import java.util.Date;
import java.util.List;

import com.javamail.biz.entity.MessageTo;

public interface MessageToDao {

	List<MessageTo> query(Long fromId, Long toId,Date startDate,Date endDate);
	
	/**
	 * 增加消息去向
	 * @param messageTo
	 */
	void add(MessageTo messageTo);
}
