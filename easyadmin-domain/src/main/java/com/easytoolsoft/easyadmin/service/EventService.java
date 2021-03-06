package com.easytoolsoft.easyadmin.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.lf5.LogLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easytoolsoft.easyadmin.dao.EventDao;
import com.easytoolsoft.easyadmin.data.PageInfo;
import com.easytoolsoft.easyadmin.data.jdbc.BaseService;
import com.easytoolsoft.easyadmin.po.EventPo;

@Service
public class EventService extends BaseService<EventDao, EventPo> {
	@Autowired
	public EventService(EventDao dao) {
		super(dao);
	}

	public void append(String source, String message, int userId, String account, LogLevel level) {
		EventPo event = new EventPo();
		event.setSource(source);
		event.setMessage(message);
		event.setUserId(userId);
		event.setAccount(account);
		event.setLevel(level.toString());
		event.setCreateTime(event.getCreateTime());
		event.setUrl("");
		this.add(event);
	}

	public List<EventPo> getEventsByKeyword(PageInfo page, String fieldName, String keyword) {
		String condition = "";
		if (StringUtils.isNotBlank(keyword)) {
			condition = fieldName + " like '%" + keyword + "%' ";
		}
		return this.getDao().query(condition, page);
	}
}