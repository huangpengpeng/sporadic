package com.javamail.biz.dao.impl;

import org.springframework.stereotype.Repository;

import com.common.jdbc.JdbcTemplateBaseDao;
import com.javamail.biz.dao.MessageDao;
import com.javamail.biz.entity.Message;

@Repository
public class MessageDaoImpl extends JdbcTemplateBaseDao implements MessageDao {

	@Override
	public long add(Message message) {
		return super.add(message);
	}

	@Override
	public Class<?> getEntityClass() {
		return Message.class;
	}
}
