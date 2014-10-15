package com.crazy.singleweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crazy.singleweb.dao.impl.SingleDaoImpl;
import com.crazy.singleweb.entity.User;
import com.crazy.singleweb.service.SingleService;

@Component
public class DefaultSingleServiceImpl implements SingleService {

	private SingleDaoImpl singleDao;

	public User findUserByName(String name) {
		return singleDao.findUserByName(name);
	}

	@Autowired
	public void setSingleDao(SingleDaoImpl singleDao) {
		this.singleDao = singleDao;
	}

}
