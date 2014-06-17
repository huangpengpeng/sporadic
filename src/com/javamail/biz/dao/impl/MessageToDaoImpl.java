package com.javamail.biz.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.common.jdbc.JdbcTemplateBaseDao;
import com.common.jdbc.SqlBuilder;
import com.javamail.biz.dao.MessageToDao;
import com.javamail.biz.entity.MessageTo;

@Repository
public class MessageToDaoImpl extends JdbcTemplateBaseDao implements MessageToDao{

	@Override
	public List<MessageTo> query(Long fromId, Long toId,Date startDate,Date endDate) {
		return queryForList(fromId, toId,startDate,endDate);
	}
	
	protected List<MessageTo> queryForList(Long fromId, Long toId,Date startDate,Date endDate) {
		SqlBuilder sqlBuilder=new SqlBuilder("select * from MessageTo where 1=1");
		if(fromId!=null){
			sqlBuilder.andEqualTo("fromId", fromId);
		}
		if(toId!=null){
			sqlBuilder.andEqualTo("toId", toId);
		}
		if(startDate!=null){
			sqlBuilder.andGreaterThanOrEqualTo("sendTime", startDate);
		}
		if(endDate!=null){
			sqlBuilder.andLessThanOrEqualTo("sendTime", endDate);
		}
		return query(sqlBuilder);
	}


	@Override
	public void add(MessageTo messageTo) {
		super.add(messageTo);
	}


	@Override
	protected Class<?> getEntityClass() {
		return MessageTo.class;
	}
	
}
