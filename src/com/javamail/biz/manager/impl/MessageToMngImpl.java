package com.javamail.biz.manager.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javamail.biz.dao.MessageToDao;
import com.javamail.biz.entity.MessageTo;
import com.javamail.biz.manager.MessageToMng;

@Transactional
@Service
public class MessageToMngImpl implements MessageToMng {

	@Override
	public List<MessageTo> query(Long fromId, Long toId,Date startDate,Date endDate) {
		return dao.query(fromId, toId,startDate,endDate);
	}



	@Override
	public void add(String from, String to, Long messageId, String toType) {
		MessageTo messageTo = new MessageTo(from, to, messageId, toType);
		messageTo.init();
		dao.add(messageTo);
	}
	

	
	@Autowired
	private MessageToDao dao;
}
